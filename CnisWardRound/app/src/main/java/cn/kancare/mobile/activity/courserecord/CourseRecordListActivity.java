package cn.kancare.mobile.activity.courserecord;

import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bo.CourseRecordBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseListActivity;

public class CourseRecordListActivity extends BaseListActivity<CourseRecord> {
	CourseRecordBo courseBo;

	String PatientHospitalize_DBKey;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ImageButton btnNewCourseRecord = (ImageButton) findViewById(R.id.btnAdd);
		btnNewCourseRecord.setOnClickListener(new onClickHandler());

	}

	@Override
	protected void receiveIntent(Intent intent) {
		PatientHospitalize_DBKey = intent.getStringExtra("PatientHospitalize_DBKey");
	}

	@Override
	protected void initializeBo() throws Exception {
		courseBo = new CourseRecordBo(this);

	}

	@Override
	protected int getPageSize() {
		return 20;
	}

	@Override
	protected List<CourseRecord> getMoreData(int pageSize, int offset) throws Exception {
		return courseBo.getDao().query(pageSize, offset, PatientHospitalize_DBKey);
	}

	/**
	 * 新建查房记录
	 * 
	 * @param view
	 */
	private class onClickHandler implements android.view.View.OnClickListener {
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btnAdd:
				Intent i = new Intent(CourseRecordListActivity.this,
						CourseRecordInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType", RequestCode.NEW_COURSE_RECORD);
					bundle.putString("PatientHospitalize_DBKey",
							PatientHospitalize_DBKey);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.NEW_COURSE_RECORD);
				break;

			default:
				break;
			}

		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_COURSERECORD;
	}

	@Override
	protected int getListId() {
		return R.id.lvCourseRecord;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.course_record_list;
	}

	public int getListItemLayoutId() {
		return R.layout.course_record_list_item;
	}

	@Override
	protected void onListItemClick(final CourseRecord data) {
		super.onListItemClick(data);

		PopUtil.AlertSimpleDialog(this, R.array.SimpleListMenuArray,
				new OnMenuClickListener());
	}

	private class OnMenuClickListener implements
			DialogInterface.OnClickListener {

		public void onClick(DialogInterface arg0, int arg1) {

			if (arg1 == 0) {
				// 编辑
				Intent i = new Intent(CourseRecordListActivity.this,
						CourseRecordInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType", RequestCode.EDIT_COURSE_RECORD);
					bundle.putString("CourseRecord_DBKey", adapter
							.getCurrentItem().getCourseRecord_DBKey());
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.EDIT_COURSE_RECORD);
			} else if (arg1 == 1) {
				// 删除
				PopUtil.AlertDialog(CourseRecordListActivity.this, "提示",
						"确定删除该记录？", "确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								try {
									courseBo.getDao().deleteById(
											adapter.getCurrentItem()
													.getCourseRecord_DBKey());

									removeAndRefresh();
								} catch (Exception e) {
									doException(e);
								}
								dialog.dismiss();

							}
						});
			}
			arg0.dismiss();
		}
	}

	public void setListItemView(final int position, View view,
			CourseRecord data, final ViewGroup parent) {
		// 编号
		TextView tvCourseRecordNo = (TextView) view
				.findViewById(R.id.courseRecordNo);
		tvCourseRecordNo.setText("	编号：" + data.getCourseRecordNo());
		// 生成日期
		TextView tvCourseRecordDate = (TextView) view
				.findViewById(R.id.courseRecordDate);
		tvCourseRecordDate.setText("		查房日期：" + DateHelper.getInstance().getDataString_3(data.getCourseRecordDate()));
		// 记录人
		TextView tvCourseRecordCreateBy = (TextView) view
				.findViewById(R.id.courseRecordCreateBy);
		tvCourseRecordCreateBy.setText("	记录人：" + data.getCreateBy());

	}

	protected void onListItemSubClick(View item, View widget, int position, int which) throws Exception {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// requestCode标示请求的标示 resultCode表示有数据

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RequestCode.NEW_COURSE_RECORD:
				refreshList();
				break;
			case RequestCode.EDIT_COURSE_RECORD:
				refreshList();
				break;
			default:
				break;
			}
		}

	}

	@Override
	protected void setView() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setViewHolder(View view) {
		// TODO Auto-generated method stub
		
	}

}
