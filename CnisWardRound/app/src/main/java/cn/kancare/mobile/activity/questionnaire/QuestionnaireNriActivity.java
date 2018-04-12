package cn.kancare.mobile.activity.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.activity.BaseActivity;

public class QuestionnaireNriActivity extends BaseActivity {

	PatientHospitalizeBasicInfo patientInfo;

	PatientHospitalizeBasicInfoBo patientHospitalizeBasicInfoBo;
	public PatientQuestionnaireBo patientQuestionnaireBo;
	public PatientQuestionBo patientQuestionBo;

	public String PatientQuestionnaire_DBKey;
	public int operateType;

	public Button btnSubmit;
	public Button btnSubmit2;
	public EditText editTextXQ;
	public EditText editTextCurrentWeight;
	public EditText editTextWeight2;
	public EditText editTextNRI;
	public DatePickerView datePickerViewScreeningDate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {

			patientInfo = patientHospitalizeBasicInfoBo.getDao().queryForId(
					Global.currentPatient.getPatientHospitalize_DBKey());

			if (operateType == RequestCode.NEW_QUESTIONNAIRE) {
				datePickerViewScreeningDate.setText(DateHelper.getInstance()
						.getDataString_3(null));
				editTextCurrentWeight.setText(Convert.RoundString2(
						Global.currentPatient.getWeight(), 2));
			}
			if (operateType == RequestCode.EDIT_QUESTIONNAIRE) {
				PatientQuestionnaire patientQuestionnaire = patientQuestionnaireBo
						.getDao().queryForId(PatientQuestionnaire_DBKey);
				editTextCurrentWeight.setText(patientQuestionnaire
						.getWeightNow() + "");
				datePickerViewScreeningDate.setText(DateHelper.getInstance()
						.getDataString_3(
								patientQuestionnaire.getScreeningDate()));
				editTextWeight2.setText(patientQuestionnaire
						.getWeight2MonthAgo() + "");
				editTextNRI.setText(patientQuestionnaire.getWeight3MonthAgo()
						+ "");
				editTextXQ.setText(patientQuestionnaire.getWeight1MonthAgo()
						+ "");

				// 如果已提交，则禁用相关控件
				// if (patientQuestionnaire.getQuestionnaireStatus().equals(
				// QuestionnaireStatus.SUBMITTED)) {
				// btnSubmit.setVisibility(View.GONE);
				// btnSubmit2.setVisibility(View.GONE);
				// editTextCurrentWeight.setEnabled(false);
				// editTextScreeningDate.setEnabled(false);
				// editTextWeight2.setEnabled(false);
				// editTextNRI.setEnabled(false);
				// editTextXQ.setEnabled(false);
				//
				// editTextCurrentWeight.setFocusable(false);
				// editTextScreeningDate.setFocusable(false);
				// editTextWeight2.setFocusable(false);
				// editTextNRI.setFocusable(false);
				// editTextXQ.setFocusable(false);
				// }
			}

			btnSubmit.setOnClickListener(new ClickListener());
			btnSubmit2.setOnClickListener(new ClickListener());

			editTextXQ.addTextChangedListener(new OnTextChangeListener());
			editTextCurrentWeight
					.addTextChangedListener(new OnTextChangeListener());
			editTextWeight2.addTextChangedListener(new OnTextChangeListener());

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
			calcNRI();
		}

	}

	public void calcNRI() {

		double xq = Convert.cash2Double(editTextXQ.getText().toString());
		double currentWeight = Convert.cash2Double(editTextCurrentWeight
				.getText().toString());
		double weight2 = Convert.cash2Double(editTextWeight2.getText()
				.toString());

		double nri = 0;
		if (weight2 == 0) {
			nri = 1.519 * xq;
		} else {
			nri = 1.519 * xq + 4.17 * (currentWeight / weight2);
		}
		editTextNRI.setText(Convert.RoundString2(nri, 2));
	}

	@Override
	protected void setValidation() {
		super.setValidation();
		mAwesomeValidation.addValidation(editTextXQ, ".+", "该项为必填项！");
		mAwesomeValidation
				.addValidation(editTextCurrentWeight, ".+", "该项为必填项！");
		mAwesomeValidation.addValidation(editTextWeight2, ".+", "该项为必填项！");
	}

	class ClickListener implements OnClickListener {

		public void onClick(View v) {
			try {

				switch (v.getId()) {
				case R.id.btnSubmit:
				case R.id.btnSubmit2:

					if (!mAwesomeValidation.validate()) {
						return;
					}

					String PatientQuestionnaire_DBKey = patientQuestionnaireBo
							.createPatientQuestionnaire(QuestionnaireNriActivity.this);

					// 提交完成后查看结果
					Intent i = new Intent(QuestionnaireNriActivity.this,
							QuestionnaireResultActivity.class);
					Bundle bundle = new Bundle();
					try {
						bundle.putInt("OperateType",
								RequestCode.VIEW_QUESTIONNAIRE_RESULT);
						bundle.putString("PatientQuestionnaire_DBKey",
								PatientQuestionnaire_DBKey);
					} catch (Exception e) {
						doException(e);
					}
					i.putExtras(bundle);

					startActivityForResult(i,
							RequestCode.VIEW_QUESTIONNAIRE_RESULT);
					finish();
					break;
				}
			} catch (Exception e) {
				doException(e);
			}
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_QUESTIONNAIRE;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.questionnaire_nri;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		operateType = intent.getIntExtra("OperateType", 0); // PatientQuestionnaire_DBKey
		if (operateType == RequestCode.EDIT_QUESTIONNAIRE) {
			PatientQuestionnaire_DBKey = intent.getStringExtra(
					"PatientQuestionnaire_DBKey");
		}
	}

	@Override
	protected void initializeBo() throws Exception {
		patientHospitalizeBasicInfoBo = new PatientHospitalizeBasicInfoBo(this);
		patientQuestionnaireBo = new PatientQuestionnaireBo(this);
		patientQuestionBo = new PatientQuestionBo(this);
	}

	@Override
	protected void setView() throws Exception {
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit2 = (Button) findViewById(R.id.btnSubmit2);
		editTextXQ = (EditText) findViewById(R.id.editTextXQ);
		editTextCurrentWeight = (EditText) findViewById(R.id.editTextCurrentWeight);
		editTextWeight2 = (EditText) findViewById(R.id.editTextWeight2);
		editTextNRI = (EditText) findViewById(R.id.editTextNRI);
		datePickerViewScreeningDate = (DatePickerView) findViewById(R.id.datePickerViewScreeningDate);

	}

}
