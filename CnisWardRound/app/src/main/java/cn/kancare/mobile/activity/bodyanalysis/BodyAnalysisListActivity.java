package cn.kancare.mobile.activity.bodyanalysis;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bo.BodyAnalysisReportBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.activity.BaseListActivity;

public class BodyAnalysisListActivity extends
		BaseListActivity<BodyAnalysisReport> {

	BodyAnalysisReportBo bodyAnalysisReportBo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public int getPageSize() {
		return 20;
	}

	public List<BodyAnalysisReport> getMoreData(int pageSize, int offset)
			throws Exception {
		return bodyAnalysisReportBo.getDao().query(pageSize, offset,
				Global.currentPatient.getPatientHospitalize_DBKey());
	}

	public int getListId() {
		return R.id.lvBodyAnalysis;
	}

	public int getListItemLayoutId() {
		return R.layout.body_analysis_list_item;
	}

	public void setListItemView(final int position, View view,
			BodyAnalysisReport data, final ViewGroup parent) {
		// 编号
		TextView tvBodyAnalysisReportNo = (TextView) view
				.findViewById(R.id.tvBodyAnalysisReportNo);
		tvBodyAnalysisReportNo.setText("	编号：" + data.getBodyAnalysisReportNo());

		// 生成日期
		TextView tvTestTime = (TextView) view.findViewById(R.id.tvTestTime);
		tvTestTime.setText("	测试日期：" + data.getTestTime());

	}

	public void onListItemSubClick(View item, View widget, int position, int which) throws Exception {

	}

	public void onListItemClick(BodyAnalysisReport data) {
		super.onListItemClick(data);

		// 查看
		Intent i = new Intent(this, BodyAnalysisInfoActivity.class);
		Bundle bundle = new Bundle();
		try {
			bundle.putInt("BodyAnalysisReportNo", adapter.getCurrentItem()
					.getBodyAnalysisReportNo());
		} catch (Exception e) {
			doException(e);
		}
		i.putExtras(bundle);

		startActivity(i);
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_BODYANALYSIS;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.body_analysis_list;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		bodyAnalysisReportBo = new BodyAnalysisReportBo(this);
	}

	@Override
	protected void setView() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setViewHolder(View view) {
		// TODO Auto-generated method stub

	}

}
