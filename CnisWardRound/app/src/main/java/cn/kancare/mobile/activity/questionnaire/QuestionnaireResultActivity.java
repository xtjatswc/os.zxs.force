package cn.kancare.mobile.activity.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import os.zxs.force.activity.BackFragment;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.QuestionnaireConstant;
import cn.kancare.mobile.common.constant.SettingCode;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseActivity;
import os.zxs.force.core.view.fragment.OnBackListener;

public class QuestionnaireResultActivity extends BaseActivity implements
		OnBackListener {

	PatientQuestionnaireBo patientQuestionnaireBo;
	PatientQuestionnaire patientQuestionnaire;
	String PatientQuestionnaire_DBKey;

	TextView tvScore;
	TextView tvResult;
	TextView tvResult2;
	RadioButton RadioButtonAgree;
	RadioButton RadioButtonNotAgree;
	RadioGroup RadioGroupIsAgree;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			patientQuestionnaire = patientQuestionnaireBo.getDao().queryForId(
					PatientQuestionnaire_DBKey);
			int property = patientQuestionnaire.getQuestionProperty();

			String scoreString = null;
			String result = null;
			String result2 = null;
			double score = 0;
			if (property == QuestionnaireConstant.NRS2002_TYPEVALUE) {
				// NRS2002
				score = patientQuestionnaire.getNSR2002Score();
				if (score >= 3) {
					result = "患者有营养不良的风险，需营养支持治疗";
				} else {
					result = "若患者将接受重大手术，则每周重新评估其营养状况";
				}

			} else if (property == QuestionnaireConstant.PGSGA_TYPEVALUE) {
				// PG-SGA
				score = patientQuestionnaire.getPGSGAScore();
				if (score >= 9) {
					result = "急需进行症状改善和/或营养干预。";
					result2 = "重度营养不良";

				} else if (score >= 4 && score <= 8) {
					result = "需要营养师进行干预，并根据症状与医生和护士联合进行营养干预。";

				} else if (score >= 2 && score <= 3) {
					result = "由营养师、护士或其他医护人员对患者或家属进行宣教，并依据患者症状和实验室检查结果,进行药物干预。";

				} else if (score >= 0 && score <= 1) {
					result = "目前不需干预措施，治疗期间保持常规随诊及再评价。";
					result2 = "营养良好";
				}

				if (score >= 2 && score <= 8) {
					result2 = "可疑或中度营养不良";
				}
			} else if (property == QuestionnaireConstant.MUST_TYPEVALUE) {
				score = patientQuestionnaire.getPGSGAScore();

				if (score == 0) {
					result = "低营养风险，需定期进行重复筛查";
				} else if (score == 1) {
					result = "中度营养风险";
				} else if (score == 2) {
					result = "高度营养风险";
				} else if (score > 2) {
					result = "营养风险较高，需专业营养医生制定营养治疗方案";
				}
			} else if (property == QuestionnaireConstant.MST_TYPEVALUE) {
				score = patientQuestionnaire.getPGSGAScore();
				if (score >= 0 && score <= 1) {
					result = "无营养不良风险";
				} else if (score >= 2) {
					result = "有营养不良风险，需要给予营养支持";
				}
			} else if (property == QuestionnaireConstant.MNASF_TYPEVALUE) {
				score = patientQuestionnaire.getPGSGAScore();
				if (score >= 0 && score <= 7) {
					result = "营养不良";
				} else if (score >= 8 && score <= 11) {
					result = "有营养不良的风险";
				} else if (score >= 12) {
					// && score <= 14
					result = "营养正常";
				}
			} else if (property == QuestionnaireConstant.NRI_TYPEVALUE) {
				score = patientQuestionnaire.getWeight3MonthAgo();
				if (score <= 83.5) {
					result = "严重营养不良";
				} else if (score > 83.5 && score <= 97.5) {
					result = "轻度营养不良";
				} else if (score > 97.5 && score <= 100) {
					result = "临界营养不良";
				} else if (score > 100) {
					result = "营养正常";
				}
			} else if (property == QuestionnaireConstant.SGA_TYPEVALUE) {
				score = patientQuestionnaire.getPGSGAScore();
				if (score == 1) {
					result = "营养良好（大部分是A，或明显改善）";
				} else if (score == 2) {
					result = "轻－中度营养不良";
				} else if (score == 3) {
					result = "重度营养不良（大部分是C，明显的躯体症状）";
				}
			}

			scoreString = "评估结果:" + score + "分";

			tvScore.setText(scoreString);
			tvResult.setText(result);
			tvResult2.setText(result2);

			EditText remarkEditText = (EditText) findViewById(R.id.EditTextRemark);
			remarkEditText.setText(patientQuestionnaire.getRemark());
			if (patientQuestionnaire.getAgreeHelp() == 0) {
				RadioButtonNotAgree.setChecked(true);
			} else {
				RadioButtonAgree.setChecked(true);
			}

			if (Global.AppMode.equals(SettingCode.APP_MODE_INNER)) {
				RadioGroupIsAgree.setVisibility(View.GONE);
			}

			Button btnSave = (Button) findViewById(R.id.btnSave);
			btnSave.setOnClickListener(new onClickListener());

		} catch (Exception e) {
			doException(e);
		}
	}

	class onClickListener implements View.OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnSave:
				EditText remarkEditText = (EditText) findViewById(R.id.EditTextRemark);
				String remark = remarkEditText.getText().toString();
				patientQuestionnaire.setRemark(remark);
				patientQuestionnaire
						.setAgreeHelp(RadioButtonAgree.isChecked() ? 1 : 0);
				try {
					patientQuestionnaireBo.getDao()
							.update(patientQuestionnaire);
					PopUtil.show(QuestionnaireResultActivity.this, "保存成功！");
					setResult(RESULT_OK);
					QuestionnaireResultActivity.this.finish();
				} catch (Exception e) {
					doException(e);
				}
				break;

			default:
				break;
			}
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_QUESTIONNAIRE;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.questionnaire_result;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// operateType = intent.getIntExtra("OperateType", 0);
		PatientQuestionnaire_DBKey = intent.getStringExtra(
				"PatientQuestionnaire_DBKey");
	}

	@Override
	protected void initializeBo() throws Exception {
		patientQuestionnaireBo = new PatientQuestionnaireBo(this);
	}

	@Override
	protected void setView() throws Exception {
		tvScore = (TextView) findViewById(R.id.TextViewScore);
		tvResult = (TextView) findViewById(R.id.TextViewResult);
		tvResult2 = (TextView) findViewById(R.id.TextViewResult2);
		RadioButtonAgree = (RadioButton) findViewById(R.id.RadioButtonAgree);
		RadioButtonNotAgree = (RadioButton) findViewById(R.id.RadioButtonNotAgree);
		RadioGroupIsAgree = (RadioGroup) findViewById(R.id.RadioGroupIsAgree);

	}

	public void BeforPressBack(BackFragment backFragment) {
		setResult(RESULT_OK);
		QuestionnaireResultActivity.this.finish();
	}
}
