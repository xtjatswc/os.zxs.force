package cn.kancare.mobile.activity.laboratoryindex;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.laboratoryindex.TestResultBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseListActivity;

public class LaboratoryIndexListActivity extends
		BaseListActivity<LaboratoryIndex> {

	ImageButton btnNew;

	LaboratoryIndexBo laboratoryIndexBo;
	TestResultBo testResultBo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btnNew.setOnClickListener(new onClickHandler());

	}

	private class onClickHandler implements android.view.View.OnClickListener {
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btnAdd:
				Intent i = new Intent(LaboratoryIndexListActivity.this,
						LaboratoryIndexInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType",
							RequestCode.NEW_LABORATORYINDEX);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.NEW_LABORATORYINDEX);
				break;

			default:
				break;
			}

		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshList();
	}

	@Override
	protected int getPageSize() {
		return 20;
	}

	@Override
	protected List<LaboratoryIndex> getMoreData(int pageSize, int offset) throws Exception {
		return laboratoryIndexBo.getDao().query(pageSize, offset,
				Global.currentPatient.getPatientHospitalize_DBKey());
	}

	@Override
	protected int getListId() {
		return R.id.lvLaboratoryindex;
	}

	@Override
	protected int getListItemLayoutId() {
		return R.layout.laboratoryindex_list_item;
	}

	@Override
	protected void setListItemView(final int position, final View view,
			LaboratoryIndex data, final ViewGroup parent) {
		// 报告名称
		TextView TextViewTestType = (TextView) view
				.findViewById(R.id.TextViewTestType);
		if (data.getTestType() != null && !data.getTestType().equals("")) {
			TextViewTestType.setVisibility(View.VISIBLE);
			TextViewTestType.setText(data.getTestType());
		} else {
			TextViewTestType.setVisibility(View.GONE);
			TextViewTestType.setText("");
		}

		// 编号
		TextView tvLaboratoryIndex_DBKey = (TextView) view
				.findViewById(R.id.tvLaboratoryIndex_DBKey);
		tvLaboratoryIndex_DBKey
				.setText("编号：" + data.getLaboratoryIndex_DBKey());

		// 检验日期
		TextView tvTestTime = (TextView) view.findViewById(R.id.tvTestTime);
		tvTestTime.setText("	检验日期："
				+ DateHelper.getInstance().getDataString_3(data.getTestTime()));

		// 删除
		final ImageView imageDelete = (ImageView) view
				.findViewById(R.id.imageDelete);
		imageDelete.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				LaboratoryIndexListActivity.this.onListItemSubClick(view,
						parent, position, imageDelete.getId());
			}
		});

		TextView tvNew = (TextView) view.findViewById(R.id.tvNew);
		if (data.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
			tvNew.setVisibility(View.VISIBLE);
		} else {
			tvNew.setVisibility(View.GONE);
		}

		TextView tvNote = (TextView) view.findViewById(R.id.tvNote);
		tvNote.setVisibility(View.GONE);
		tvNote.setText("");
		String noteString = "";
		if (data.getReportHtml() == null) {
			try {
				List<TestResult> lsTestResults = testResultBo.getDao().query(
						data.getLaboratoryIndex_DBKey());
				StringBuilder sb = new StringBuilder();
				for (TestResult testResult : lsTestResults) {
					if (!testResult.getTestItemValue().equals("")) {
						sb.append(testResult.getTestItemName());
						sb.append(" , ");
					}
				}
				noteString = sb.toString();
			} catch (SQLException e) {
				doException(e);
			}

		} else {
			noteString = getString(data.getReportHtml(),
					"'TestItemName'>(.*?)</td>");

		}

		if (!noteString.equals("")) {
			tvNote.setVisibility(View.VISIBLE);
			tvNote.setText(noteString);
		}
	}

	/**
	 * 获取查询的字符串 将匹配的字符串取出
	 */
	private String getString(String str, String regx) {
		// 1.将正在表达式封装成对象Patten 类来实现
		Pattern pattern = Pattern.compile(regx, Pattern.DOTALL);
		// 2.将字符串和正则表达式相关联
		Matcher matcher = pattern.matcher(str);
		// 3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

		StringBuilder sb = new StringBuilder();
		// System.out.println(matcher.matches());
		// 查找符合规则的子串
		while (matcher.find()) {
			// 获取 字符串
			// System.out.println(matcher.group());
			// 获取的字符串的首位置和末位置
			// System.out.println(matcher.start()+"--"+matcher.end());
			sb.append(matcher.group(1));
			sb.append(" , ");
		}
		return sb.toString();
	}

	@Override
	public void onListItemSubClick(View item, View widget, int position,
			int which) {
		try {
			final LaboratoryIndex laboratoryIndex = adapter.getItem(position);

			switch (which) {
			case R.id.imageDelete:

				// 删除
				PopUtil.AlertDialog(LaboratoryIndexListActivity.this, "编号『"
						+ laboratoryIndex.getLaboratoryIndex_DBKey() + "』",
						"确定删除该记录？", "确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								try {
									testResultBo
											.getDao()
											.deleteByLaboratoryIndex_DBKey(
													laboratoryIndex
															.getLaboratoryIndex_DBKey());
									laboratoryIndexBo
											.getDao()
											.deleteById(
													laboratoryIndex
															.getLaboratoryIndex_DBKey());
									removeAndRefresh();
								} catch (Exception e) {
									doException(e);
								}
								dialog.dismiss();

							}
						});
				break;
			}
		} catch (Exception e) {
			doException(e);
		}
	}

	protected void onListItemClick(LaboratoryIndex data) {
		super.onListItemClick(data);

		// 查看
		Intent i = new Intent(LaboratoryIndexListActivity.this,
				LaboratoryIndexInfoActivity.class);
		Bundle bundle = new Bundle();
		try {
			if (data.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
				bundle.putInt("OperateType", RequestCode.EDIT_LABORATORYINDEX);
			} else {
				bundle.putInt("OperateType", RequestCode.VIEW_LABORATORYINDEX);
			}
			bundle.putString("LaboratoryIndex_DBKey", adapter.getCurrentItem()
					.getLaboratoryIndex_DBKey());
		} catch (Exception e) {
			doException(e);
		}
		i.putExtras(bundle);

		startActivityForResult(i, RequestCode.VIEW_LABORATORYINDEX);
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_LABORATORYINDEX;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.laboratoryindex_list;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		laboratoryIndexBo = new LaboratoryIndexBo(this);
		testResultBo = new TestResultBo(this);
	}

	@Override
	protected void setView() throws Exception {
		btnNew = (ImageButton) findViewById(R.id.btnAdd);
	}

	@Override
	protected void setViewHolder(View view) {
		// TODO Auto-generated method stub

	}

}
