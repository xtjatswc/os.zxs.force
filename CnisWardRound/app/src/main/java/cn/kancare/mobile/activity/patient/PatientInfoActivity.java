package cn.kancare.mobile.activity.patient;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.BackFragment;
import cn.kancare.mobile.bo.basic.DepartmentBo;
import cn.kancare.mobile.bo.basic.SysCodeBo;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import cn.kancare.mobile.common.constant.SettingCode;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.util.spinner.SpinnerOption;
import cn.kancare.mobile.core.util.spinner.SpinnerUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.activity.BaseActivity;

import com.google.common.collect.Range;

public class PatientInfoActivity extends BaseActivity {

	PatientHospitalizeBasicInfoBo patientBo;
	DepartmentBo departmentBo;
	SysCodeBo sysCodeBo;

	Button Button_Save;
	Button Button_Save2;
	public EditText EditText_PatientName;
	public EditText EditText_PatientNo;
	public EditText EditText_ZYH;
	public EditText EditText_Age;
	public EditText EditText_Height;
	public EditText EditText_Weight;
	public RadioButton RadioButton1;
	public RadioButton RadioButton0;
	public DatePickerView DatePickerView_HospitalDate;
	public Spinner Spinner_Department;
	public EditText EditText_BedCode;
	public int OperateType;
	public String department_DBKey;

	public TextView TextView_Star_PatientName;
	public TextView TextView_Star_Department;
	public TextView TextView_Star_BedCode;
	public TextView TextView_Star_PatientNo;
	public TextView TextView_Star_ZYH;
	public Spinner Spinner_Staging;
	public EditText EditText_ClinicalDiagnosis;
	public BackFragment backFragment;
	public RadioButton RadioButtonIn;
	public RadioButton RadioButtonOut;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<SpinnerOption> lstSpinnerOptions;
		try {
			lstSpinnerOptions = departmentBo.getDepartmentOptions();

			SpinnerUtil.setSpinnerValue(this, Spinner_Department, "",
					lstSpinnerOptions);

			SpinnerUtil.setSpinnerValue(this, sysCodeBo, Spinner_Staging,
					"Staging", "0");

		} catch (Exception e) {
			doException(e);
		}

		if (OperateType == RequestCode.EDIT_PATIENT_INFO) {
			backFragment.setTitle("编辑患者信息");
			EditText_PatientName.setEnabled(false);
			EditText_PatientName
					.setText(Global.currentPatient.getPatientName());
			if (Convert.cash2Double(Global.currentPatient.getAge()) != 0) {
				EditText_Age.setText(Convert.RoundString2(
						Global.currentPatient.getAge(), 2));
			}

			if (Global.currentPatient.getHeight() != 0) {
				EditText_Height.setText(Convert.RoundString2(
						Global.currentPatient.getHeight(), 2));
			}

			if (Global.currentPatient.getWeight() != 0) {
				EditText_Weight.setText(Convert.RoundString2(
						Global.currentPatient.getWeight(), 2));
			}

			if (Global.currentPatient.getGender().equals("M")) {
				RadioButton1.setChecked(true);
			} else {
				RadioButton0.setChecked(true);
			}
			DatePickerView_HospitalDate.setDate(Global.currentPatient
					.getInHospitalData());
			try {
				SpinnerUtil.setSpinnerValue(Spinner_Department,
						Global.currentPatient.getDepartment_DBKey() + "");
				SpinnerUtil.setSpinnerValue(Spinner_Staging,
						Global.currentPatient.getStaging() + "");
			} catch (Exception e) {
				doException(e);
			}
			EditText_BedCode.setText(Global.currentPatient.getBedCode());
			EditText_PatientNo.setText(Global.currentPatient.getPatientNo());
			EditText_ZYH.setText(Global.currentPatient
					.getHospitalizationNumber());
			EditText_ClinicalDiagnosis.setText(Global.currentPatient
					.getClinicalDiagnosis());
			if(Global.currentPatient.getTherapyStatus().equals("9")){
				RadioButtonOut.setChecked(true);
			}else{
				RadioButtonIn.setChecked(true);
			}

		} else {
			backFragment.setTitle("新建患者信息");
			if (Global.AppMode.equals(SettingCode.APP_MODE_INNER)) {
				EditText_PatientNo.setText(DateHelper.getInstance()
						.getDataString(null, "MMddSSS"));
				EditText_ZYH.setText(DateHelper.getInstance().getDataString(
						null, "yyMMddSSS"));
			}

			// 科室切换成主界面选择的科室
			SpinnerUtil.setSpinnerValue(Spinner_Department, department_DBKey);
		}

		Button_Save.setOnClickListener(new onClickListener());
		Button_Save2.setOnClickListener(new onClickListener());

	}

	@Override
	protected void setValidation() {
		super.setValidation();

		if (Global.AppMode.equals(SettingCode.APP_MODE_SINGLE)) {
			TextView_Star_Department.setVisibility(View.VISIBLE);
			TextView_Star_BedCode.setVisibility(View.VISIBLE);

			mAwesomeValidation.addValidation(EditText_BedCode, "^.{1,12}$",
					"床位长度为1~12！");
		} else {
			TextView_Star_PatientName.setVisibility(View.VISIBLE);
			TextView_Star_PatientNo.setVisibility(View.VISIBLE);
			TextView_Star_ZYH.setVisibility(View.VISIBLE);

			mAwesomeValidation.addValidation(EditText_PatientNo, "^.{1,12}$",
					"病案号长度为1~12！");
			mAwesomeValidation.addValidation(EditText_ZYH, "^.{1,12}$",
					"住院号长度为1~12！");
			mAwesomeValidation.addValidation(EditText_PatientName, "^.{1,10}$",
					"患者姓名长度为1~10！");
		}

		mAwesomeValidation.addValidation(EditText_Age, Range.closed(1f, 120f),
				"年龄的范围为1~120！");
		mAwesomeValidation.addValidation(EditText_Height,
				Range.closed(1f, 300f), "身高的范围为1~300！");
		mAwesomeValidation.addValidation(EditText_Weight,
				Range.closed(1f, 300f), "体重的范围为1~300！");

	}

	class onClickListener implements OnClickListener {

		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.Button_Save:
			case R.id.Button_Save2:

				if (!mAwesomeValidation.validate()) {
					return;
				}

				try {
					if (OperateType == RequestCode.NEW_PATIENT_INFO) {
						patientBo
								.createPatientBasicInfo(PatientInfoActivity.this);						
					} else if (OperateType == RequestCode.EDIT_PATIENT_INFO) {
						patientBo
								.updatePatientBasicInfo(PatientInfoActivity.this);
					}
					patientBo.getDao().updateOrderBy();
				} catch (Exception e) {
					doException(e);
				}

				PopUtil.show(PatientInfoActivity.this, "保存成功！");

				Intent intent = new Intent();
				if (OperateType == RequestCode.NEW_PATIENT_INFO) {

					intent.putExtra("patientName", EditText_PatientName
							.getText().toString());
					PatientInfoActivity.this.setResult(
							RequestCode.NEW_PATIENT_INFO, intent);
				}
				PatientInfoActivity.this.finish();
				break;

			default:
				break;
			}
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_PATIENT_INFO;
	}

	@Override
	protected void initializeBo() throws Exception {
		patientBo = new PatientHospitalizeBasicInfoBo(this);
		departmentBo = new DepartmentBo(this);
		sysCodeBo = new SysCodeBo(this);
	}

	@Override
	protected void setView() throws Exception {
		Button_Save = (Button) findViewById(R.id.Button_Save);
		Button_Save2 = (Button) findViewById(R.id.Button_Save2);
		EditText_PatientName = (EditText) findViewById(R.id.EditText_PatientName);
		EditText_Age = (EditText) findViewById(R.id.EditText_Age);
		EditText_Height = (EditText) findViewById(R.id.EditText_Height);
		EditText_Weight = (EditText) findViewById(R.id.EditText_Weight);
		RadioButton1 = (RadioButton) findViewById(R.id.RadioButton1);
		RadioButton0 = (RadioButton) findViewById(R.id.RadioButton0);
		DatePickerView_HospitalDate = (DatePickerView) findViewById(R.id.DatePickerView_HospitalDate);
		Spinner_Department = (Spinner) findViewById(R.id.Spinner_Department);
		EditText_BedCode = (EditText) findViewById(R.id.EditText_BedCode);
		EditText_PatientNo = (EditText) findViewById(R.id.EditText_PatientNo);
		EditText_ZYH = (EditText) findViewById(R.id.EditText_ZYH);

		TextView_Star_PatientName = (TextView) findViewById(R.id.TextView_Star_PatientName);
		TextView_Star_Department = (TextView) findViewById(R.id.TextView_Star_Department);
		TextView_Star_BedCode = (TextView) findViewById(R.id.TextView_Star_BedCode);
		TextView_Star_PatientNo = (TextView) findViewById(R.id.TextView_Star_PatientNo);
		TextView_Star_ZYH = (TextView) findViewById(R.id.TextView_Star_ZYH);
		Spinner_Staging = (Spinner) findViewById(R.id.Spinner_Staging);
		EditText_ClinicalDiagnosis = (EditText) findViewById(R.id.EditText_ClinicalDiagnosis);
		backFragment = (BackFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_back);
		RadioButtonIn = (RadioButton) findViewById(R.id.RadioButtonIn);
		RadioButtonOut = (RadioButton) findViewById(R.id.RadioButtonOut);

	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		OperateType = intent.getIntExtra("OperateType",
				RequestCode.NEW_PATIENT_INFO);

		department_DBKey = intent.getStringExtra("department_DBKey");
	}

	@Override
	protected int getLayoutId() {
		return R.layout.patient_info;
	}
}
