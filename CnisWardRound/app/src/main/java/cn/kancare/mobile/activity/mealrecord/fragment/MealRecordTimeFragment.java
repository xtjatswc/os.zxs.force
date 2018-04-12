package cn.kancare.mobile.activity.mealrecord.fragment;

import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordInfoListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTimeListener;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.view.fragment.BaseFragment;

public class MealRecordTimeFragment extends BaseFragment implements
		MealRecordTimeListener {

	MealRecordInfoListener mealRecordInfoListener;

	ImageView ImageViewMinus;
	EditText textRecordDate;
	ImageView ImageViewPlus;

	java.util.Date date;
	DatePickerDialog datePicker;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		ImageViewMinus.setOnClickListener(new onClickListener());
		ImageViewPlus.setOnClickListener(new onClickListener());
		textRecordDate.setOnTouchListener(new onTouchListener());

		return layout;
	}

	private void initDatePicker(java.util.Date d) {
		textRecordDate.setText(DateHelper.getInstance().getDataString_2(d));

		datePicker = new DatePickerDialog(this.getActivity(),
				new OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {

						// 先保存
						mealRecordInfoListener.saveMealRecord();

						date.setYear(year - 1900);
						date.setMonth(monthOfYear);
						date.setDate(dayOfMonth);
						textRecordDate.setText(year + "-" + (monthOfYear + 1)
								+ "-" + dayOfMonth);
						mealRecordInfoListener.loadMealRecord();
					}
				}, DateHelper.getInstance().getYear(d), d.getMonth(),
				d.getDate());

		mealRecordInfoListener.loadMealRecord();
	}

	class onClickListener implements OnClickListener {

		public void onClick(View v) {

			mealRecordInfoListener.saveMealRecord();

			switch (v.getId()) {
			case R.id.ImageViewMinus:

				date.setDate(date.getDate() - 1);
				initDatePicker(date);

				break;
			case R.id.ImageViewPlus:

				date.setDate(date.getDate() + 1);
				initDatePicker(date);

				break;
			default:
				break;
			}

		}
	}

	class onTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			EditText et_start_time = (EditText) v;
			int inType = et_start_time.getInputType();
			et_start_time.setInputType(InputType.TYPE_NULL);

			datePicker.show();

			et_start_time.setInputType(inType);
			et_start_time.setSelection(et_start_time.getText().length());

			return false;
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_MEALRECORD;
	}

	@Override
	protected void initializeBo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getLayoutId() {
		return R.layout.mealrecord_fragment_time;
	}

	@Override
	protected void setView(View layout) throws Exception {
		ImageViewMinus = (ImageView) layout.findViewById(R.id.ImageViewMinus);
		textRecordDate = (EditText) layout.findViewById(R.id.textRecordDate);
		ImageViewPlus = (ImageView) layout.findViewById(R.id.ImageViewPlus);

		mealRecordInfoListener = (MealRecordInfoListener) getActivity();
	}

	public Date getRecordTime() {
		return date;
	}

	public String getRecordTime2() {
		return DateHelper.getInstance().getDataString_2(date);

	}

	public void setRecordTime(String recordTime) {

		// 初始化日期
		if (recordTime == null || recordTime.equals("")) {
			date = new java.util.Date();
		} else {
			date = DateHelper.getInstance().getDate(recordTime);
		}

		// 保证去掉日期后面的时分秒
		date = new Date(date.getYear(), date.getMonth(), date.getDate());

		initDatePicker(date);
	}

}
