package cn.kancare.mobile.activity.frame;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bo.net.SyncTask;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.fragment.BaseFragment;

public class SyncFragment extends BaseFragment {
	public Button button;
	public ProgressBar progressBar;
	public TextView textView;
	public Switch switchPatient;
	public Switch switchCourserecord;
	public Switch switchMealRecord;
	public Switch switchNutrientAdvice;
	public Switch switchUser;
	public Switch switchDepartment;
	public Switch switchBednumber;
	public Switch switchSysCode;
	public Switch switchQuestionnaire;
	public Switch switchQuestionnaireResult;
	public Switch switchBodyAnalysisReport;
	public Switch switchLaboratoryIndex;
	public Switch switchChinaFoodComposition;
	public LinearLayout linearLayoutTips;
	public DatePickerView textBeginDate;
	public DatePickerView textEndDate;
	public CheckBox chkClear;
	public LinearLayout linearLayoutClear;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		Global.currentActivity = getActivity();

		// 日期处理
		Date d = new Date();
		d.setDate(d.getDate() - 7);
		textBeginDate.setDate(d);
		
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				linearLayoutTips.removeAllViews();

				SyncTask asyncTask = new SyncTask(SyncFragment.this);
				asyncTask.execute(1000);
			}
		});

		// 按钮旋转动画
		animate(button).setDuration(2000);
		animate(button).rotationYBy(720);

		//
		if (Global.loginUser.getUser_DBKey() == 0) {
			linearLayoutClear.setVisibility(View.VISIBLE);
		} else {
			linearLayoutClear.setVisibility(View.GONE);
		}	

		return layout;
	}	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		animate(button).rotationYBy(720);
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC;
	}

	@Override
	protected void initializeBo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getLayoutId() {
		return R.layout.frame_async;
	}

	@Override
	protected void setView(View layout) throws Exception {
		button = (Button) layout.findViewById(R.id.buttonBeginAsync);
		progressBar = (ProgressBar) layout.findViewById(R.id.progressBarAsync);
		textView = (TextView) layout.findViewById(R.id.textViewAsyncTip);

		switchPatient = (Switch) layout.findViewById(R.id.switchPatient);
		switchCourserecord = (Switch) layout
				.findViewById(R.id.switchCourserecord);
		switchMealRecord = (Switch) layout.findViewById(R.id.switchMealRecord);
		switchNutrientAdvice = (Switch) layout.findViewById(R.id.switchNutrientAdvice);
		switchUser = (Switch) layout.findViewById(R.id.switchUser);
		switchDepartment = (Switch) layout.findViewById(R.id.switchDepartment);
		switchBednumber = (Switch) layout.findViewById(R.id.switchBednumber);
		switchSysCode = (Switch) layout.findViewById(R.id.switchSysCode);
		switchQuestionnaire = (Switch) layout
				.findViewById(R.id.switchQuestionnaire);
		switchQuestionnaireResult = (Switch) layout
				.findViewById(R.id.switchQuestionnaireResult);
		linearLayoutTips = (LinearLayout) layout
				.findViewById(R.id.linearLayoutTips);
		switchBodyAnalysisReport = (Switch) layout
				.findViewById(R.id.switchBodyAnalysisReport);
		switchLaboratoryIndex = (Switch) layout
				.findViewById(R.id.switchLaboratoryIndex);
		switchChinaFoodComposition = (Switch) layout
				.findViewById(R.id.switchChinaFoodComposition);

		textBeginDate = (DatePickerView) layout.findViewById(R.id.textBeginDate);
		textEndDate = (DatePickerView) layout.findViewById(R.id.textEndDate);
		chkClear = (CheckBox) layout.findViewById(R.id.chkClear);
		linearLayoutClear = (LinearLayout) layout
				.findViewById(R.id.linearLayoutClear);
	}

	public void AddSyncTip(String msg, int colorRes) {
		TextView textView = new TextView(getActivity());
		textView.setTextColor(ColorUtil.getColor(colorRes));
		textView.setText(msg);
		linearLayoutTips.addView(textView);
	}

}
