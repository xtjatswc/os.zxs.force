package cn.kancare.mobile.activity.questionnaire;

import java.sql.SQLException;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import cn.kancare.mobile.R;
import os.zxs.force.activity.BackFragment;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.bo.questionnaire.OptionDetailBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireResultBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailTypeBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.QuestionnaireConstant.QuestionnaireStatus;
import cn.kancare.mobile.common.constant.RequestCode;
import cn.kancare.mobile.common.questionnaire.Question;
import cn.kancare.mobile.common.questionnaire.Questionnaire;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.activity.BaseActivity;
import os.zxs.force.core.view.fragment.OnBackListener;

public class QuestionnaireActivity extends BaseActivity implements
		OnBackListener {
	public int operateType;
	public String PatientQuestionnaire_DBKey;
	public int QuestionProperty;
	public String PatientHospitalize_DBKey;
	public PatientHospitalizeBasicInfo patientInfo;
	public Questionnaire questionnaire;

	public OptionDetailBo optionDetailBo;
	public QuestionDetailBo questionDetailBo;
	public QuestionDetailTypeBo questionDetailTypeBo;

	public PatientQuestionnaireBo patientQuestionnaireBo;
	public PatientQuestionBo patientQuestionBo;
	public PatientQuestionnaireResultBo patientQuestionnaireResultBo;

	public PatientHospitalizeBasicInfoBo patientHospitalizeBasicInfoBo;

	// QuestionnaireActivity
	public ScrollView scrollView;
	public EditText editTextPatientName;
	public EditText editTextHospitalizationNumber;
	public EditText editTextAge;
	public DatePickerView datePickerViewScreeningDate;
	public EditText editTextCurrentWeight;
	public EditText editTextCurrentHeight;
	public EditText editTextBMI;
	public EditText editTextWeight1MonthAgo;
	public EditText editTextWeight2MonthAgo;
	public EditText editTextWeight3MonthAgo;
	public EditText editTextWeight6MonthAgo;
	public EditText editTextWeight1;
	public EditText editTextWeight2;
	public EditText editTextWeight3;
	public EditText editTextWeight6;
	public Button btnSubmit;
	public Button btnSubmit2;
	public LinearLayout linearLayout; // 问卷容器

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			patientInfo = patientHospitalizeBasicInfoBo.getDao().queryForId(
					PatientHospitalize_DBKey);

			patientQuestionnaireBo.loadPatientQuestionnaire(this);

			if (editTextCurrentHeight.getText().equals("")) {
				editTextCurrentHeight.requestFocus();
			}

			setListener();

			CalcBMI();

		} catch (Exception e) {
			doException(e);
		}

	}

	public void setListener() {
		editTextCurrentHeight
				.addTextChangedListener(new OnWeightChangeListener());
		editTextCurrentWeight
				.addTextChangedListener(new OnWeightChangeListener());
		editTextWeight1MonthAgo
				.addTextChangedListener(new OnWeightChangeListener());
		editTextWeight2MonthAgo
				.addTextChangedListener(new OnWeightChangeListener());
		editTextWeight3MonthAgo
				.addTextChangedListener(new OnWeightChangeListener());
		editTextWeight6MonthAgo
				.addTextChangedListener(new OnWeightChangeListener());

		btnSubmit.setOnClickListener(new ClickListener());
		btnSubmit2.setOnClickListener(new ClickListener());
	}

	@Override
	public void setView() {
		scrollView = (ScrollView) findViewById(R.id.scrollViewQuestionnaire);
		editTextPatientName = (EditText) findViewById(R.id.editTextPatientName);
		editTextHospitalizationNumber = (EditText) findViewById(R.id.editTextHospitalizationNumber);
		editTextAge = (EditText) findViewById(R.id.editTextAge);
		datePickerViewScreeningDate = (DatePickerView) findViewById(R.id.datePickerViewScreeningDate);
		editTextCurrentWeight = (EditText) findViewById(R.id.editTextCurrentWeight);
		editTextCurrentHeight = (EditText) findViewById(R.id.editTextCurrentHeight);
		editTextBMI = (EditText) findViewById(R.id.editTextBMI);
		editTextWeight1MonthAgo = (EditText) findViewById(R.id.editTextWeight1MonthAgo);
		editTextWeight2MonthAgo = (EditText) findViewById(R.id.editTextWeight2MonthAgo);
		editTextWeight3MonthAgo = (EditText) findViewById(R.id.editTextWeight3MonthAgo);
		editTextWeight6MonthAgo = (EditText) findViewById(R.id.editTextWeight6MonthAgo);
		editTextWeight1 = (EditText) findViewById(R.id.editTextWeight1);
		editTextWeight2 = (EditText) findViewById(R.id.editTextWeight2);
		editTextWeight3 = (EditText) findViewById(R.id.editTextWeight3);
		editTextWeight6 = (EditText) findViewById(R.id.editTextWeight6);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit2 = (Button) findViewById(R.id.btnSubmit2);
		linearLayout = (LinearLayout) findViewById(R.id.layoutQuestionnaire);

	}

	class ClickListener implements OnClickListener {

		public void onClick(View v) {
			try {
				switch (v.getId()) {
				case R.id.btnSubmit:
				case R.id.btnSubmit2:

					SaveQuestionnaire();

					// 判断是否完成
					Question question = questionnaire.isFinish();
					if (question == null) {
						// 提交
						patientQuestionnaireBo
								.submitPatientQuestionnaire(QuestionnaireActivity.this);

						// 提交完成后查看结果
						Intent i = new Intent(QuestionnaireActivity.this,
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
					} else {
						question.scrollTo();
						PopUtil.AlertDialogOneButton(
								QuestionnaireActivity.this, "提示",
								"【" + question.questionDetail.getQuestionNo()
										+ "】 未答完，请答完所有题后再提交！", "知道了",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});
					}

					break;

				default:
					break;
				}
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	private void SaveQuestionnaire() {
		try {
			// 保存身高
			patientInfo.setHeight(Convert.cash2Double(editTextCurrentHeight
					.getText().toString()));
			patientHospitalizeBasicInfoBo.getDao().update(patientInfo);

			if (operateType == RequestCode.NEW_QUESTIONNAIRE) {
				// 先保存
				PatientQuestionnaire_DBKey = patientQuestionnaireBo
						.createPatientQuestionnaire(QuestionnaireActivity.this);
				// 转换为编辑模式
				operateType = RequestCode.EDIT_QUESTIONNAIRE;
			} else if (operateType == RequestCode.EDIT_QUESTIONNAIRE) {
				patientQuestionnaireBo
						.updatePatientQuestionnaire(QuestionnaireActivity.this);
			}
		} catch (Exception e) {
			doException(e);
		}

	}

	public class OnWeightChangeListener implements TextWatcher {

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		public void afterTextChanged(Editable s) {
			CalcBMI();
		}

	}

	private void CalcBMI() {
		try {
			double weight = Convert.cash2Double(editTextCurrentWeight.getText()
					.toString());
			double height = Convert.cash2Double(editTextCurrentHeight.getText()
					.toString());

			if (height == 0) {
				editTextBMI.setText("-");
			} else if (weight == 0) {
				editTextBMI.setText("-");
			} else {
				double bmi = weight / (height * height) * 10000;
				editTextBMI.setText(Convert.Round(bmi, 1) + "");
			}

			double weight1 = Convert.cash2Double(editTextWeight1MonthAgo
					.getText().toString());
			double weight2 = Convert.cash2Double(editTextWeight2MonthAgo
					.getText().toString());
			double weight3 = Convert.cash2Double(editTextWeight3MonthAgo
					.getText().toString());
			double weight6 = Convert.cash2Double(editTextWeight6MonthAgo
					.getText().toString());

			double w1 = Convert.Round((weight - weight1) / weight1 * 100, 2);
			double w2 = Convert.Round((weight - weight2) / weight2 * 100, 2);
			double w3 = Convert.Round((weight - weight3) / weight3 * 100, 2);
			double w6 = Convert.Round((weight - weight6) / weight6 * 100, 2);

			String tmp = "";
			tmp = w1 > 0 ? "+" : "";
			if (Double.isInfinite(w1) || Double.isNaN(w1))
				editTextWeight1.setText("");
			else
				editTextWeight1.setText(tmp + w1 + "%");

			tmp = w2 > 0 ? "+" : "";
			if (Double.isInfinite(w2) || Double.isNaN(w2))
				editTextWeight2.setText("");
			else
				editTextWeight2.setText(tmp + w2 + "%");

			tmp = w3 > 0 ? "+" : "";
			if (Double.isInfinite(w3) || Double.isNaN(w3))
				editTextWeight3.setText("");
			else
				editTextWeight3.setText(tmp + w3 + "%");

			tmp = w6 > 0 ? "+" : "";
			if (Double.isInfinite(w6) || Double.isNaN(w6))
				editTextWeight6.setText("");
			else
				editTextWeight6.setText(tmp + w6 + "%");

			patientQuestionnaireBo.autoCalcW(this, w1, w2, w3, w6, weight,
					weight1, weight2, weight3, weight6);

		} catch (Exception e) {
			doException(e);
		}

	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {

		operateType = intent.getIntExtra("OperateType", 0);
		PatientHospitalize_DBKey = intent
				.getStringExtra("PatientHospitalize_DBKey");

		if (operateType == RequestCode.NEW_QUESTIONNAIRE) {
			QuestionProperty = intent.getIntExtra("QuestionProperty", 0);
		} else if (operateType == RequestCode.EDIT_QUESTIONNAIRE) {
			PatientQuestionnaire_DBKey = intent.getStringExtra(
					"PatientQuestionnaire_DBKey");
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_QUESTIONNAIRE;
	}

	@Override
	protected void initializeBo() throws Exception {
		optionDetailBo = new OptionDetailBo(this);
		questionDetailBo = new QuestionDetailBo(this);
		questionDetailTypeBo = new QuestionDetailTypeBo(this);

		patientQuestionnaireBo = new PatientQuestionnaireBo(this);
		patientQuestionBo = new PatientQuestionBo(this);
		patientQuestionnaireResultBo = new PatientQuestionnaireResultBo(this);

		patientHospitalizeBasicInfoBo = new PatientHospitalizeBasicInfoBo(this);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.questionnaire;
	}

	public void BeforPressBack(final BackFragment backFragment) {
		try {
			// 如果已提交，则直接返回
			if (operateType == RequestCode.EDIT_QUESTIONNAIRE) {

				PatientQuestionnaire patientQuestionnaire = patientQuestionnaireBo
						.getDao().queryForId(PatientQuestionnaire_DBKey);
				if (patientQuestionnaire.getQuestionnaireStatus().equals(
						QuestionnaireStatus.SUBMITTED)) {
					backFragment.doBack();
					return;
				}
			}

			PopUtil.AlertDialog(this, "提示", "返回前是否保存当前问卷？", "保存",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							SaveQuestionnaire();
							// backFragment.doBack();
							QuestionnaireActivity.this.setResult(RESULT_OK);
							QuestionnaireActivity.this.finish();
						}
					}, "不保存", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// backFragment.doBack();
							QuestionnaireActivity.this.setResult(RESULT_OK);
							QuestionnaireActivity.this.finish();
						}
					});
		} catch (SQLException e) {
			doException(e);
		}

	}

}
