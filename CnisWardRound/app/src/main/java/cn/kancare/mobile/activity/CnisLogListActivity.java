package cn.kancare.mobile.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.kancare.mobile.R;
import cn.kancare.mobile.common.constant.LogTag;
import cn.yan.slidelayout.MainActivity;
import os.zxs.force.core.constant.LogLevel;
import os.zxs.force.core.log.CnisLog;
import os.zxs.force.core.log.CnislogBo;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.Loading;
import os.zxs.force.core.view.activity.BaseListActivity;
import tablayoutsamples.ui.SimpleHomeActivity;

public class CnisLogListActivity extends BaseListActivity<CnisLog> {

	Context context = this;
	CnislogBo cnislogBo;

	Button btnClearLog;
	Button ButtonTabLayout;
	Button ButtonSlideLayout;

	ColorStateList red;
	ColorStateList green;
	ColorStateList yellow;
	ColorStateList black;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btnClearLog.setOnClickListener(new onClickHandler());
		ButtonTabLayout.setOnClickListener(new onClickHandler());
		ButtonSlideLayout.setOnClickListener(new onClickHandler());

	}

	class onClickHandler implements View.OnClickListener {

		public void onClick(View v) {
			try {
				switch (v.getId()) {
				case R.id.btnClearLog:

					cnislogBo.getDao().deleteAll();

					refreshList();
					break;
				case R.id.ButtonTabLayout:

					Intent i = new Intent(context,
							SimpleHomeActivity.class);
					startActivity(i);

					break;
				case R.id.ButtonSlideLayout:

					Intent i2 = new Intent(context,
							MainActivity.class);
					startActivity(i2);

					break;
				default:
					break;
				}
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	@Override
	protected List<CnisLog> getInitializeData() throws Exception {
		return cnislogBo.getDao().query(20, 0);
	}

	@Override
	protected List<CnisLog> getMoreData(int listCount) throws Exception {
		return cnislogBo.getDao().query(20, listCount);
	}

	@Override
	protected int getListId() {
		return R.id.lvCnisLog;
	}

	@Override
	protected int getListItemLayoutId() {
		return R.layout.cnislog_list_item;
	}

	@Override
	protected void setListItemView(final int position, View view, CnisLog data,
			final ViewGroup parent) {
		// 编号
		TextView textViewLogID = (TextView) view
				.findViewById(R.id.textViewLogID);
		textViewLogID.setText("" + data.getID());
		// 等级
		TextView textViewLevel = (TextView) view
				.findViewById(R.id.textViewLevel);
		textViewLevel.setText("	" + data.getLevel());
		// tag
		TextView textViewTag = (TextView) view.findViewById(R.id.textViewTag);
		textViewTag.setText("	" + data.getTag());

		// 记录时间
		TextView textViewCreateTime = (TextView) view
				.findViewById(R.id.textViewCreateTime);
		textViewCreateTime.setText("	" + data.getCreateTime());

		// 日志信息
		TextView textViewMsg = (TextView) view.findViewById(R.id.textViewMsg);
		textViewMsg.setText("	" + data.getMsg());

		if (data.getLevel().equals(LogLevel.ERROR)) {
			textViewMsg.setTextColor(red);
		} else if (data.getLevel().equals(LogLevel.DEBUG)) {
			textViewMsg.setTextColor(green);
		} else if (data.getLevel().equals(LogLevel.WARN)) {
			textViewMsg.setTextColor(yellow);
		} else {
			textViewMsg.setTextColor(black);
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CNIS_LOG;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.cnislog_list;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		cnislogBo = new CnislogBo(this);
	}

	@Override
	protected void setView() throws Exception {
		btnClearLog = (Button) findViewById(R.id.btnClearLog);
		View decorView = this.getWindow().getDecorView();
		ButtonTabLayout = ViewFindUtils.find(decorView, R.id.ButtonTabLayout);
		ButtonSlideLayout = ViewFindUtils.find(decorView, R.id.ButtonSlideLayout);

		red = ColorUtil.getColor(R.color.red);
		green = ColorUtil.getColor(R.color.green);
		yellow = ColorUtil.getColor(R.color.lightsalmon);
		black = ColorUtil.getColor(R.color.black);
	}

	@Override
	protected void setViewHolder(View view) {
		// TODO Auto-generated method stub

	}

}
