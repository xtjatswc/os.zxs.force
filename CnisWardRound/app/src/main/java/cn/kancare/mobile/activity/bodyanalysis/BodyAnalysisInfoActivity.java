package cn.kancare.mobile.activity.bodyanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bo.BodyAnalysisReportBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.activity.BaseActivity;

public class BodyAnalysisInfoActivity extends BaseActivity {

	BodyAnalysisReportBo bodyAnalysisReportBo;
	int BodyAnalysisReportNo;

	WebView webView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			BodyAnalysisReport bodyAnalysisReport = bodyAnalysisReportBo
					.getDao().queryForId(BodyAnalysisReportNo);

			webView.loadDataWithBaseURL(null,
					bodyAnalysisReport.getReportHtml(), "text/html", "utf-8",
					null);
		} catch (Exception e) {
			doException(e);
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_BODYANALYSIS;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.body_analysis_info;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		BodyAnalysisReportNo = intent.getIntExtra("BodyAnalysisReportNo", 0);

	}

	@Override
	protected void initializeBo() throws Exception {
		bodyAnalysisReportBo = new BodyAnalysisReportBo(this);
	}

	@Override
	protected void setView() throws Exception {
		webView = (WebView) findViewById(R.id.webView);
	}

}
