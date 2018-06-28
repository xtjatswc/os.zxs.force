package cn.kancare.mobile.activity.laboratoryindex;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.laboratoryindex.TestItemDetailBo;
import cn.kancare.mobile.bo.laboratoryindex.TestResultBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.AssetUtil;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.Loading;
import os.zxs.force.core.view.activity.BaseActivity;

public class LaboratoryIndexInfoActivity extends BaseActivity {

	private Context context = this;
	public LaboratoryIndexBo laboratoryIndexBo;
	public TestItemDetailBo testitemDetailBo;
	public TestResultBo testResultBo;

	public String LaboratoryIndex_DBKey;
	public int OperateType;

	Boolean isFinish = false;

	WebView webView;
	Button btnSave;
	Button btnSave2;
	public EditText editTestType;
	DatePickerView DatePickerViewRecordDate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			if (OperateType == RequestCode.NEW_LABORATORYINDEX) {
				String html = AssetUtil
						.getContent("template/laboratoryindex.txt");

				List<TestItemDetail> lstTestitemDetails = testitemDetailBo
						.getDao().queryForAll();

				DatePickerViewRecordDate.setDate(new Date());
				VelocityContext context = new VelocityContext();
				context.put("details", lstTestitemDetails);
				context.put("currentdate", DatePickerViewRecordDate.getText());// DateHelper.getInstance().getDataString_2(null)
				StringWriter w = new StringWriter();
				Velocity.evaluate(context, w, "", html);

				webView.getSettings().setJavaScriptEnabled(true);
				webView.addJavascriptInterface(new Handler(), "handler");
				webView.loadDataWithBaseURL(null, w + "", "text/html", "utf-8",
						null);

			} else if (OperateType == RequestCode.VIEW_LABORATORYINDEX) {
				btnSave.setVisibility(View.GONE);
				btnSave2.setVisibility(View.GONE);
				editTestType.setEnabled(false);
				DatePickerViewRecordDate.setEnabled(false);

				LaboratoryIndex laboratoryIndex = laboratoryIndexBo.getDao()
						.queryForId(LaboratoryIndex_DBKey);

				editTestType.setText(laboratoryIndex.getTestType());
				DatePickerViewRecordDate.setText(DateHelper.getInstance()
						.getDataString_3(laboratoryIndex.getTestTime()));
				webView.getSettings().setJavaScriptEnabled(true);
				webView.addJavascriptInterface(new Handler(), "handler");
				webView.loadDataWithBaseURL(null,
						laboratoryIndex.getReportHtml(), "text/html", "utf-8",
						null);
			} else if (OperateType == RequestCode.EDIT_LABORATORYINDEX) {
				String html = AssetUtil
						.getContent("template/laboratoryindex_edit.txt");

				LaboratoryIndex laboratoryIndex = laboratoryIndexBo.getDao()
						.queryForId(LaboratoryIndex_DBKey);
				List<TestResult> lstTestResult = testResultBo.getDao().query(
						LaboratoryIndex_DBKey);

				editTestType.setText(laboratoryIndex.getTestType());
				DatePickerViewRecordDate.setText(DateHelper.getInstance()
						.getDataString_3(laboratoryIndex.getTestTime()));
				VelocityContext context = new VelocityContext();
				context.put("details", lstTestResult);
				context.put("currentdate", DatePickerViewRecordDate.getText());
				StringWriter w = new StringWriter();
				Velocity.evaluate(context, w, "", html);

				webView.getSettings().setJavaScriptEnabled(true);
				webView.addJavascriptInterface(new Handler(), "handler");
				webView.loadDataWithBaseURL(null, w + "", "text/html", "utf-8",
						null);
			}

		} catch (Exception e) {
			doException(e);
		}

		btnSave.setOnClickListener(new ClickListener());
		btnSave2.setOnClickListener(new ClickListener());
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_LABORATORYINDEX;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.laboratoryindex_info;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		LaboratoryIndex_DBKey = intent.getStringExtra("LaboratoryIndex_DBKey");
		OperateType = intent.getIntExtra("OperateType", 0);
	}

	@Override
	protected void initializeBo() throws Exception {
		laboratoryIndexBo = new LaboratoryIndexBo(this);
		testitemDetailBo = new TestItemDetailBo(this);
		testResultBo = new TestResultBo(this);
	}

	@Override
	protected void setView() throws Exception {
		webView = (WebView) findViewById(R.id.webView);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave2 = (Button) findViewById(R.id.btnSave2);
		editTestType = (EditText) findViewById(R.id.editTestType);
		DatePickerViewRecordDate = (DatePickerView) findViewById(R.id.DatePickerViewRecordDate);
	}

	class ClickListener implements OnClickListener {

		public void onClick(View v) {
			try {
				switch (v.getId()) {
				case R.id.btnSave:
				case R.id.btnSave2:

					Loading.turn(context);
					if (!isFinish) {
						// 这样判断是为防止多次提交
						isFinish = true;
						// webView.loadUrl("javascript: window.handler.saveTestResult(document.body.innerHTML);");
						webView.loadUrl("javascript: _post();");
					}
					break;
				}
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	class Handler {

		public void beforSave(String testTime) {
			try {

				laboratoryIndexBo.saveLaboratoryIndex(
						LaboratoryIndexInfoActivity.this,
						DatePickerViewRecordDate.getText().toString());

				testResultBo.getDao().deleteByLaboratoryIndex_DBKey(
						LaboratoryIndex_DBKey);

			} catch (Exception e) {
				doException(e);
			}

		}

		public void saveTestResult(String dbkey, String testItemValue) {

			int testItemDetail_DBKey = Convert.cash2Int(dbkey);
			try {
				testResultBo.saveTestResult(LaboratoryIndexInfoActivity.this,
						testItemValue, testItemDetail_DBKey);

			} catch (Exception e) {
				doException(e);
			}
		}

		public void saveSuccess() {
			PopUtil.show(LaboratoryIndexInfoActivity.this, "保存成功！");

			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}

		public void itemNameClick(String TestItemDetail_DBKey) {

			Intent i = new Intent(LaboratoryIndexInfoActivity.this,
					LaboratoryIndexChartActivity.class);
			Bundle bundle = new Bundle();
			try {
				bundle.putInt("TestItemDetail_DBKey",
						Convert.cash2Int(TestItemDetail_DBKey));
			} catch (Exception e) {
				doException(e);
			}
			i.putExtras(bundle);

			startActivity(i);
		}
	}

}
