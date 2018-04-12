package cn.kancare.mobile.activity.courserecord;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bo.CourseRecordBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.activity.BaseActivity;

import com.google.common.collect.Range;

public class CourseRecordInfoActivity extends BaseActivity {
	int operateType;
	CourseRecordBo courseBo;
	CourseRecord course;
	String PatientHospitalize_DBKey; // 新建时用
	String CourseRecord_DBKey; // 编辑时用

	// view
	public Spinner Nausea; // 恶心
	public Spinner Diarrhea; // 腹泻
	public Spinner Constipation; // 便秘
	public Spinner Vomit; // 呕吐
	public Spinner AbdominalDistension; // 腹胀
	public Spinner AbdominalPain; // 腹痛

	public EditText editNauseaRemark; // 恶心备注
	public EditText editDiarrheaRemark; // 腹泻备注
	public EditText editConstipationRemark; // 便秘备注
	public EditText editVomitRemark; // 呕吐备注
	public EditText editAbdominalDistensionRemark; // 腹胀备注
	public EditText editAbdominalPainRemark; // 腹痛备注

	public TextView Temperature; // 体温
	public SeekBar seekBarTemperature; // 体温
	public EditText Breathe; // 呼吸
	public Spinner Skin; // 皮肤
	public EditText HeartRate; // 心率
	public EditText DBP; // 血压
	public EditText SBP; // 血压
	public Spinner Edema; // 水肿

	public EditText TricepsSkinfold; // 三头肌皮脂厚度
	public EditText editHeight; // 身高
	public EditText editWeight; // 体重
	public EditText BMI; // BMI
	public EditText GripLeft; // 握力 R
	public EditText GripRight; // 握力 L
	public EditText BMR; // 基础代谢(BMR)
	public EditText WalkSpeed; // 6分钟步行速度(m/s)

	public EditText Drainage; // 引流量
	public EditText GastrointestinalDecompression; // 胃肠减压量
	public EditText UrineVolume; // 尿量

	public EditText Remark; // 备注

	public CheckBox chkNoShow; // 未见患者

	public DatePickerView DatePickerViewRecordDate; // 查房日期

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {

			if (operateType == RequestCode.NEW_COURSE_RECORD) {
				course = new CourseRecord();
				// 给初始值
				course.setTemperature(37.2);
				course.setBreathe(20);
				course.setHeartRate(75);
				course.setDBP(128);
				course.setSBP(82);
				course.setBMI(20.76);
				course.setCourseRecordDate(DateHelper.getInstance()
						.getDataString_2(null));
				course.setPatientHospitalize_DBKey(PatientHospitalize_DBKey);
			} else if (operateType == RequestCode.EDIT_COURSE_RECORD) {
				course = courseBo.getDao().queryForId(CourseRecord_DBKey);
			}

			courseBo.loadCourseRecordInfo(this, course);

			seekBarTemperature
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						public void onStopTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub

						}

						public void onStartTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub

						}

						public void onProgressChanged(SeekBar seekBar,
								int progress, boolean fromUser) {
							Temperature.setText(((double) progress + 350) / 10
									+ "");

						}
					});

			editHeight.addTextChangedListener(new OnTextChangeListener());
			editWeight.addTextChangedListener(new OnTextChangeListener());

		} catch (Exception e) {
			doException(e);
		}
	}

	public class OnTextChangeListener implements TextWatcher {

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		public void afterTextChanged(Editable s) {
			try {
				double weight = Convert.cash2Double(editWeight.getText()
						.toString());
				double height = Convert.cash2Double(editHeight.getText()
						.toString());

				if (height == 0) {
					BMI.setText("");
				} else if (weight == 0) {
					BMI.setText("");
				} else {
					double bmi = weight / (height * height) * 10000;
					BMI.setText(Convert.Round(bmi, 1) + "");
				}
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	@Override
	protected void setValidation() {
		super.setValidation();
		mAwesomeValidation.addValidation(editHeight, Range.closed(30f, 300f),
				"身高的范围为30~300！");
		mAwesomeValidation.addValidation(editWeight, Range.closed(10f, 200f),
				"体重的范围为10~200！");
		mAwesomeValidation.addValidation(Breathe, Range.closed(1f, 60f),
				"呼吸的范围为1~60！");
		mAwesomeValidation.addValidation(HeartRate, Range.closed(1f, 300f),
				"心率的范围为1~300！");
		mAwesomeValidation.addValidation(DBP, Range.closed(30f, 250f),
				"该项的范围为30~250！");
		mAwesomeValidation.addValidation(SBP, Range.closed(30f, 250f),
				"该项的范围为30~250！");

	}

	@Override
	public void setView() {
		Nausea = (Spinner) findViewById(R.id.SpinnerNausea);
		Diarrhea = (Spinner) findViewById(R.id.SpinnerDiarrhea);
		Constipation = (Spinner) findViewById(R.id.SpinnerConstipation);
		Vomit = (Spinner) findViewById(R.id.SpinnerVomit);
		AbdominalDistension = (Spinner) findViewById(R.id.SpinnerAbdominalDistension);
		AbdominalPain = (Spinner) findViewById(R.id.SpinnerAbdominalPain);

		editNauseaRemark = (EditText) findViewById(R.id.editNauseaRemark);
		editDiarrheaRemark = (EditText) findViewById(R.id.editDiarrheaRemark);
		editConstipationRemark = (EditText) findViewById(R.id.editConstipationRemark);
		editVomitRemark = (EditText) findViewById(R.id.editVomitRemark);
		editAbdominalDistensionRemark = (EditText) findViewById(R.id.editAbdominalDistensionRemark);
		editAbdominalPainRemark = (EditText) findViewById(R.id.editAbdominalPainRemark);

		Temperature = (TextView) findViewById(R.id.textViewTemperature);
		seekBarTemperature = (SeekBar) findViewById(R.id.seekBarTemperature);
		Breathe = (EditText) findViewById(R.id.editTextBreathe);
		Skin = (Spinner) findViewById(R.id.SpinnerSkin);
		HeartRate = (EditText) findViewById(R.id.editHeartRate);
		DBP = (EditText) findViewById(R.id.editDBP);
		SBP = (EditText) findViewById(R.id.editSBP);
		Edema = (Spinner) findViewById(R.id.SpinnerEdema);

		TricepsSkinfold = (EditText) findViewById(R.id.editTricepsSkinfold);
		editHeight = (EditText) findViewById(R.id.editHeight);
		editWeight = (EditText) findViewById(R.id.editWeight);
		BMI = (EditText) findViewById(R.id.editBMI);
		GripLeft = (EditText) findViewById(R.id.editGripLeft);
		GripRight = (EditText) findViewById(R.id.editGripRight);
		BMR = (EditText) findViewById(R.id.editBMR);
		WalkSpeed = (EditText) findViewById(R.id.editWalkSpeed);
		Drainage = (EditText) findViewById(R.id.editDrainage);
		GastrointestinalDecompression = (EditText) findViewById(R.id.editGastrointestinalDecompression);
		UrineVolume = (EditText) findViewById(R.id.editUrineVolume);

		Remark = (EditText) findViewById(R.id.editRemark);
		chkNoShow = (CheckBox) findViewById(R.id.chkNoShow);
		DatePickerViewRecordDate = (DatePickerView) findViewById(R.id.DatePickerViewRecordDate);

	}

	@Override
	protected void initializeBo() throws Exception {
		courseBo = new CourseRecordBo(this);

	}

	public void btnSaveOnclickHandler(View view) {
		try {
			if (!mAwesomeValidation.validate()) {
				return;
			}

			if (operateType == RequestCode.NEW_COURSE_RECORD) {
				courseBo.AddCourseRecord(this, course);
			} else if (operateType == RequestCode.EDIT_COURSE_RECORD) {
				courseBo.EditCourseRecord(this, course);
			}

			PopUtil.show(this, "保存成功！");

			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();

		} catch (Exception e) {
			doException(e);
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_COURSERECORD;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		operateType = intent.getIntExtra("OperateType", 0);
		if (operateType == RequestCode.NEW_COURSE_RECORD) {
			PatientHospitalize_DBKey = intent
					.getStringExtra("PatientHospitalize_DBKey");

		} else if (operateType == RequestCode.EDIT_COURSE_RECORD) {
			CourseRecord_DBKey = intent.getStringExtra("CourseRecord_DBKey");

		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.course_record_info;
	}

}
