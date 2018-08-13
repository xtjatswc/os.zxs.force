package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.frame.PatientListFragment.ViewHolder;
import cn.kancare.mobile.activity.questionnaire.QuestionnaireActivity;
import cn.kancare.mobile.activity.questionnaire.QuestionnaireNriActivity;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;
import cn.kancare.mobile.bean.questionnaire.PatientQuestion;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaireResult;
import cn.kancare.mobile.bean.questionnaire.QuestionDetail;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.QuestionnaireConstant;
import cn.kancare.mobile.common.constant.QuestionnaireConstant.QuestionnaireStatus;
import cn.kancare.mobile.common.constant.QuestionnaireConstant.QuestionnaireType;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.common.questionnaire.IQuestionOption;
import cn.kancare.mobile.common.questionnaire.Question;
import cn.kancare.mobile.common.questionnaire.QuestionCheckBox;
import cn.kancare.mobile.common.questionnaire.QuestionRadioButton;
import cn.kancare.mobile.common.questionnaire.Questionnaire;
import cn.kancare.mobile.core.base.BaseBo;
import os.zxs.force.core.baseanimation.BadgeView;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import os.zxs.force.core.util.ViewUtil;
import cn.kancare.mobile.dao.questionnaire.PatientQuestionnaireDao;

import com.alibaba.fastjson.JSON;

public class PatientQuestionnaireBo extends BaseBo<PatientQuestionnaireDao> {

	public PatientQuestionnaireBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new PatientQuestionnaireDao(context);
	}

	public void autoCalcW(QuestionnaireActivity activity, double w1, double w2,
			double w3, double w6, double weight, double weight1,
			double weight2, double weight3, double weight6) throws Exception {
		// 计算跟体重相关的题
		if (activity.QuestionProperty == 2) {
			// PG-SGA

			// PG-SGA-1
			if (w1 <= -10 || w6 <= -20) {
				activity.questionnaire.getQuestionOption(11).setIsChecked(true);
			} else if ((w1 <= -5 && w1 >= -9.9) || (w6 <= -10 && w6 >= -19.9)) {
				activity.questionnaire.getQuestionOption(12).setIsChecked(true);
			} else if ((w1 <= -3 && w1 >= -4.9) || (w6 <= -6 && w6 >= -9.9)) {
				activity.questionnaire.getQuestionOption(13).setIsChecked(true);
			} else if ((w1 <= -2 && w1 >= -2.9) || (w6 <= -2 && w6 >= -5.9)) {
				activity.questionnaire.getQuestionOption(14).setIsChecked(true);
			} else if ((w1 <= 0 && w1 >= -1.9) || (w6 <= 0 && w6 >= -1.9)) {
				activity.questionnaire.getQuestionOption(15).setIsChecked(true);
			}

			// PG-SGA-2
			if (w1 < 0) {
				activity.questionnaire.getQuestionOption(16).setIsChecked(true);
			} else if (w1 == 0) {
				activity.questionnaire.getQuestionOption(17).setIsChecked(true);
			} else if (w1 > 0) {
				activity.questionnaire.getQuestionOption(18).setIsChecked(true);
			}
		} else if (activity.QuestionProperty == 3) {
			// MUST
			// MUST-2
			if (w3 > -5) {
				activity.questionnaire.getQuestionOption(126)
						.setIsChecked(true);
			} else if (w3 <= -5 && w3 >= -10) {
				activity.questionnaire.getQuestionOption(127)
						.setIsChecked(true);
			} else if (w3 < -10) {
				activity.questionnaire.getQuestionOption(128)
						.setIsChecked(true);
			}

		} else if (activity.QuestionProperty == 4) {
			// MST

			if (w1 < 0) {
				// 不确定
				activity.questionnaire.getQuestionOption(132)
						.setIsChecked(true);

				double s = weight1 - weight;
				if (s >= 1 && s <= 5) {
					activity.questionnaire.getQuestionOption(133).setIsChecked(
							true);
				} else if (s >= 6 && s <= 10) {
					activity.questionnaire.getQuestionOption(134).setIsChecked(
							true);
				} else if (s >= 11 && s <= 15) {
					activity.questionnaire.getQuestionOption(135).setIsChecked(
							true);
				} else if (s >= 15) {
					activity.questionnaire.getQuestionOption(136).setIsChecked(
							true);
				}

			} else {
				// 无
				activity.questionnaire.getQuestionOption(131)
						.setIsChecked(true);
			}
		} else if (activity.QuestionProperty == 5) {
			// MNA-SF

			// B、过去三个月，体重下降情况
			double s = weight3 - weight;
			if (s > 3) {
				activity.questionnaire.getQuestionOption(142)
						.setIsChecked(true);
			} else if (s >= 1 && s <= 3) {
				activity.questionnaire.getQuestionOption(144)
						.setIsChecked(true);
			} else if (s == 0) {
				activity.questionnaire.getQuestionOption(145)
						.setIsChecked(true);
			}
		} else if (activity.QuestionProperty == 7) {
			// SGA
			if (w6 >= -10 && w6 <= 10) {
				activity.questionnaire.getQuestionOption(160)
						.setIsChecked(true);
			} else if (w6 < -10) {
				activity.questionnaire.getQuestionOption(162)
						.setIsChecked(true);
			}
		}
	}

	public void loadPatientQuestionnaire(QuestionnaireActivity activity)
			throws Exception {
		activity.editTextPatientName.setText(Global.currentPatient
				.getPatientName());
		activity.editTextHospitalizationNumber.setText(Global.currentPatient
				.getHospitalizationNumber());
		activity.editTextAge.setText(Global.currentPatient.getAge());

		if (activity.operateType == RequestCode.NEW_QUESTIONNAIRE) {
			PatientQuestionnaire patientQuestionnaire = activity.patientQuestionnaireBo
					.getDao().getLatest(activity.PatientHospitalize_DBKey);
			activity.datePickerViewScreeningDate.setText(DateHelper
					.getInstance().getDataString_2(null));
			setWeight(activity, patientQuestionnaire);

			// 加载问卷题库
			loadQuestionnaire(activity, activity.QuestionProperty,
					QuestionnaireStatus.UNFINISHED);
		} else if (activity.operateType == RequestCode.EDIT_QUESTIONNAIRE) {
			PatientQuestionnaire patientQuestionnaire = activity.patientQuestionnaireBo
					.getDao().queryForId(activity.PatientQuestionnaire_DBKey);

			activity.datePickerViewScreeningDate.setText(DateHelper
					.getInstance().getDataString_3(
							patientQuestionnaire.getScreeningDate()));
			setWeight(activity, patientQuestionnaire);

			// 如果已提交，则禁用相关控件
			if (patientQuestionnaire.getQuestionnaireStatus().equals(
					QuestionnaireStatus.SUBMITTED)) {
				// activity.btnSubmit.setVisibility(View.GONE);
				// activity.btnSubmit2.setVisibility(View.GONE);
			}

			// 加载问卷题库
			loadQuestionnaire(activity,
					patientQuestionnaire.getQuestionProperty(),
					patientQuestionnaire.getQuestionnaireStatus());

			loadEditInfo(activity);
		}
	}

	private void loadEditInfo(QuestionnaireActivity activity) throws Exception {
		List<PatientQuestion> lstPatientQuestion = activity.patientQuestionBo
				.getDao().queryPatientQuestions(activity.PatientQuestionnaire_DBKey);
		for (PatientQuestion patientQuestion : lstPatientQuestion) {
			List<PatientQuestionnaireResult> lstPatientQuestionnaireResult = activity.patientQuestionnaireResultBo
					.getDao().query(patientQuestion.getPatientQuestion_DBKey());
			for (PatientQuestionnaireResult patientQuestionnaireResult : lstPatientQuestionnaireResult) {
				// 设置选项的选中状态
				IQuestionOption iQuestionOption = activity.questionnaire
						.getQuestionOption(patientQuestionnaireResult
								.getQuestionOption_DBKey());
				if (iQuestionOption != null) {
					iQuestionOption.setIsChecked(true);
				}
			}

		}
	}

	private void loadQuestionnaire(final QuestionnaireActivity activity,
			int QuestionProperty, String questionnaireStatus) throws Exception {

		activity.questionnaire = new Questionnaire(activity.scrollView,
				activity.linearLayout, QuestionProperty);
		List<QuestionDetail> lstQuestionDetail = activity.questionDetailBo
				.getDao().query(QuestionProperty);
		for (QuestionDetail questionDetail : lstQuestionDetail) {

			// 添加空白
			View space = ViewUtil.getBlankView(activity, 20);
			activity.linearLayout.addView(space);

			// 添加分隔线
			ImageView iv = ViewUtil.getDividerView(activity);
			activity.linearLayout.addView(iv);

			// 添加空白
			View space2 = ViewUtil.getBlankView(activity, 20);
			activity.linearLayout.addView(space2);

			// 问卷题目
			TextView textView = new TextView(activity);
			textView.setTextSize(20);
			textView.getPaint().setFakeBoldText(true); // 设置粗体字
			textView.setText(questionDetail.getQuestionTitle());
			activity.linearLayout.addView(textView);

			Question question = new Question(textView, questionDetail);

			List<OptionDetail> lstOptionDetails = activity.optionDetailBo
					.getDao().query(
							questionDetail.getQuestionnaireQuestion_DBKey());

			// 年龄
			int age = Convert.cash2Int(Convert
					.cash2Double(Global.currentPatient.getAge()));

			// 题目选项
			if (questionDetail.getQuestionType().equals(
					QuestionnaireType.SINGLE_CHOICE)) {

				RadioGroup radioGroup = new RadioGroup(activity);
				activity.linearLayout.addView(radioGroup);

				// 单选
				for (OptionDetail optionDetail : lstOptionDetails) {

					QuestionRadioButton radioButton = new QuestionRadioButton(
							activity, optionDetail);
					LayoutParams layoutParams = new LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.WRAP_CONTENT);
					radioButton.setLayoutParams(layoutParams);
					radioButton.setText(optionDetail.getOptionContent());
					radioGroup.addView(radioButton);
					// nrs2002第3题，3 年龄年龄≥70岁,根据患者年龄自动选择
					if (activity.operateType == RequestCode.NEW_QUESTIONNAIRE) {
						if (optionDetail.getQuestionOption_DBKey() == 8) {
							// 否 （0分）
							if (age < 70) {
								radioButton.setChecked(true);
							}
						} else if (optionDetail.getQuestionOption_DBKey() == 9) {
							// 是 （1分）
							if (age >= 70) {
								radioButton.setChecked(true);
							}
						}
					}
					question.AddQuestionOption(radioButton);
					// if (questionnaireStatus
					// .equals(QuestionnaireStatus.SUBMITTED))
					// radioButton.setEnabled(false);
				}

			} else if (questionDetail.getQuestionType().equals(
					QuestionnaireType.MULTIPLE_CHOICE)) {

				// 多选
				for (OptionDetail optionDetail : lstOptionDetails) {
					QuestionCheckBox checkBox = new QuestionCheckBox(activity,
							optionDetail);
					checkBox.setText(optionDetail.getOptionContent());
					activity.linearLayout.addView(checkBox);
					question.AddQuestionOption(checkBox);

					// if (questionnaireStatus
					// .equals(QuestionnaireStatus.SUBMITTED))
					// checkBox.setEnabled(false);

				}
			}
			activity.questionnaire.AddQuestion(question);
		}
	}

	private void setWeight(QuestionnaireActivity activity,
			PatientQuestionnaire patientQuestionnaire) {
		if (patientQuestionnaire == null) {

			if (Global.currentPatient.getHeight() != 0) {
				activity.editTextCurrentHeight.setText(Convert
						.cash2Int(Global.currentPatient.getHeight()) + "");
			}
			//从患者信息获取体重信息
			if (Global.currentPatient.getWeight() != 0) {
				activity.editTextCurrentWeight.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
				activity.editTextWeight1MonthAgo.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
				activity.editTextWeight2MonthAgo.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
				activity.editTextWeight3MonthAgo.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
				activity.editTextWeight6MonthAgo.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
			}
		} else {
			//从上一次的问卷中获取体重信息,如果没有，则从患者基本信息中获取体重信息, 1、2、3、6月份如果获取不到体重信息，则从当前体重赋值

			if (patientQuestionnaire.getWeightNow() == 0) {
				activity.editTextCurrentWeight.setText(Convert
						.cash2Int(Global.currentPatient.getWeight()) + "");
			}else{
				activity.editTextCurrentWeight.setText(Convert
						.cash2Int(patientQuestionnaire.getWeightNow()) + "");
			}

			if (Global.currentPatient.getHeight() != 0)
				activity.editTextCurrentHeight.setText(Convert
						.cash2Int(Global.currentPatient.getHeight()) + "");

			if (patientQuestionnaire.getWeight1MonthAgo() == 0){
				activity.editTextWeight1MonthAgo.setText(Convert
						.cash2Int(activity.editTextCurrentWeight.getText().toString()) + "");
			}else{
				activity.editTextWeight1MonthAgo.setText(Convert
						.cash2Int(patientQuestionnaire.getWeight1MonthAgo() + "")
						+ "");
			}


			if (patientQuestionnaire.getWeight2MonthAgo() == 0){
				activity.editTextWeight2MonthAgo.setText(Convert
						.cash2Int(activity.editTextCurrentWeight.getText().toString()) + "");
			}else{
				activity.editTextWeight2MonthAgo.setText(Convert
						.cash2Int(patientQuestionnaire.getWeight2MonthAgo())
						+ "");
			}

			if (patientQuestionnaire.getWeight3MonthAgo() == 0){
				activity.editTextWeight3MonthAgo.setText(Convert
						.cash2Int(activity.editTextCurrentWeight.getText().toString()) + "");
			}else{
				activity.editTextWeight3MonthAgo.setText(Convert
						.cash2Int(patientQuestionnaire.getWeight3MonthAgo())
						+ "");
			}

			if (patientQuestionnaire.getWeight6MonthAgo() == 0){
				activity.editTextWeight6MonthAgo.setText(Convert
						.cash2Int(activity.editTextCurrentWeight.getText().toString()) + "");
			}else{
				activity.editTextWeight6MonthAgo.setText(Convert
						.cash2Int(patientQuestionnaire.getWeight6MonthAgo())
						+ "");
			}
		}
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<PatientQuestionnaire> models = JSON.parseArray(json,
				PatientQuestionnaire.class);

		for (PatientQuestionnaire model : models) {
			try {
				dao.create(model);
			} catch (Exception e) {
				try {
					dao.update(model);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}

	public void deleteQuestionnaire(PatientQuestionBo patientQuestionBo,
			PatientQuestionnaireResultBo patientQuestionnaireResultBo,
			String PatientQuestionnaire_DBKey) throws Exception {

		List<PatientQuestion> lstPatientQuestion = patientQuestionBo.getDao()
				.queryPatientQuestions(PatientQuestionnaire_DBKey);
		for (PatientQuestion patientQuestion : lstPatientQuestion) {
			// delete patientquestionnaireresult
			patientQuestionnaireResultBo.getDao()
					.deleteByPatientQuestiion_DBKey(
							patientQuestion.getPatientQuestion_DBKey());
		}

		// delete patientquestion
		patientQuestionBo.getDao().deleteByPatientQuestionnaire_DBKey(
				PatientQuestionnaire_DBKey);

		// delte patientquestionnaire
		dao.deleteById(PatientQuestionnaire_DBKey);

	}

	public String updatePatientQuestionnaire(QuestionnaireActivity activity)
			throws Exception {

		PatientQuestionnaire patientQuestionnaire = dao
				.queryForId(activity.PatientQuestionnaire_DBKey);
		String questionnaireNo = patientQuestionnaire.getQuestionnaireNo();
		activity.QuestionProperty = patientQuestionnaire.getQuestionProperty();
		int operateFlag = patientQuestionnaire.getOperateFlag();
		// 保存patientquestionnaire
		patientQuestionnaire = getPatientQuestionnaire(activity,
				activity.PatientQuestionnaire_DBKey, questionnaireNo);
		patientQuestionnaire.setUpdateTime(DateHelper.getInstance()
				.getDataString_1(null));
		patientQuestionnaire.setUpdateBy(Global.loginUser.getUser_DBKey() + "");
		patientQuestionnaire.setUpdateIP(Global.LocalIpAddress);
		patientQuestionnaire.setUpdateProgram(Global.CreateProgram);
		if (operateFlag == OperateFlag.NONE) {
			patientQuestionnaire
					.setOperateFlag(OperateFlag.NEED_EDIT_TO_SERVER);
		} else if (operateFlag == OperateFlag.NEED_ADD_TO_SERVER) {
			patientQuestionnaire.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		}
		dao.update(patientQuestionnaire);

		List<PatientQuestion> lstPatientQuestions = activity.patientQuestionBo
				.getDao().queryPatientQuestions(activity.PatientQuestionnaire_DBKey);

		for (PatientQuestion patientQuestion : lstPatientQuestions) {
			// patientquestion表的数据不用动

			// 删除 patientquestionnaireresult
			activity.patientQuestionnaireResultBo.getDao()
					.deleteByPatientQuestiion_DBKey(
							patientQuestion.getPatientQuestion_DBKey());

			// 创建 patientquestionnaireresult
			Question question = activity.questionnaire
					.getQuestion(patientQuestion
							.getQuestionnaireQuestion_DBKey());
			for (IQuestionOption iQuestionOption : question
					.getQuestionOptions()) {
				if (iQuestionOption.getIsChecked()) {
					// 保存patientquestionnaireresult
					activity.patientQuestionnaireResultBo
							.createPatientQuestionnaireResult(
									patientQuestion.getPatientQuestion_DBKey(),
									iQuestionOption.getQuestionOption_DBKey());
				}
			}
		}

		return activity.PatientQuestionnaire_DBKey;
	}

	public String createPatientQuestionnaire(QuestionnaireNriActivity activity)
			throws Exception {

		PatientQuestionnaire patientQuestionnaire = new PatientQuestionnaire();
		patientQuestionnaire.setWeightNow(Convert
				.cash2Double(activity.editTextCurrentWeight.getText()
						.toString()));
		patientQuestionnaire.setWeight1MonthAgo(Convert
				.cash2Double(activity.editTextXQ.getText().toString()));
		patientQuestionnaire.setWeight2MonthAgo(Convert
				.cash2Double(activity.editTextWeight2.getText().toString()));
		patientQuestionnaire.setWeight3MonthAgo(Convert
				.cash2Double(activity.editTextNRI.getText().toString()));
		// patientQuestionnaire.setWeight6MonthAgo(Convert
		// .cash2Double(activity.editTextWeight6MonthAgo.getText()
		// .toString()));

		patientQuestionnaire.setPatientHospitalize_DBKey(Global.currentPatient
				.getPatientHospitalize_DBKey());

		patientQuestionnaire
				.setPatientQuestionnaire_DBKey(StringUtil.getUniqueDBKey());
		// patientQuestionnaire.setPGSGAQualitativeResult(PGSGAQualitativeResult)
		String questionnaireNo = DateHelper.getInstance().getDataString(null,
				"yyyyMMddHHmmss");
		patientQuestionnaire.setQuestionnaireNo(questionnaireNo);
		patientQuestionnaire
				.setQuestionnaireStatus(QuestionnaireStatus.SUBMITTED);
		patientQuestionnaire
				.setQuestionProperty(QuestionnaireConstant.NRI_TYPEVALUE);
		patientQuestionnaire.setRemark("");
		patientQuestionnaire.setScreeningDate(activity.datePickerViewScreeningDate
				.getText().toString());

		patientQuestionnaire.setCreateTime(DateHelper.getInstance()
				.getDataString_1(null));
		patientQuestionnaire.setCreateBy(Global.loginUser.getUser_DBKey() + "");
		patientQuestionnaire.setCreateIP(Global.LocalIpAddress);
		patientQuestionnaire.setCreateProgram(Global.CreateProgram);
		patientQuestionnaire.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		dao.create(patientQuestionnaire);

		String PatientQuestion_DBKey = activity.patientQuestionBo
				.createPatientQuestion(patientQuestionnaire.getPatientQuestionnaire_DBKey(), 40,
						questionnaireNo);

		return patientQuestionnaire.getPatientQuestionnaire_DBKey();
	}

	public String createPatientQuestionnaire(QuestionnaireActivity activity)
			throws Exception {

		// 获取dbkey
		String PatientQuestionnaire_DBKey = StringUtil.getUniqueDBKey();

		// 删除可能的冗余数据
		deleteQuestionnaire(activity.patientQuestionBo,
				activity.patientQuestionnaireResultBo,
				PatientQuestionnaire_DBKey);

		String questionnaireNo = DateHelper.getInstance().getDataString(null,
				"yyyyMMddHHmmss");
		// 保存patientquestionnaire
		PatientQuestionnaire patientQuestionnaire = getPatientQuestionnaire(
				activity, PatientQuestionnaire_DBKey, questionnaireNo);
		patientQuestionnaire.setCreateTime(DateHelper.getInstance()
				.getDataString_1(null));
		patientQuestionnaire.setCreateBy(Global.loginUser.getUser_DBKey() + "");
		patientQuestionnaire.setCreateIP(Global.LocalIpAddress);
		patientQuestionnaire.setCreateProgram(Global.CreateProgram);
		patientQuestionnaire.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		dao.create(patientQuestionnaire);

		for (Question question : activity.questionnaire.getQuestions()) {
			// 保存patientquestion
			String PatientQuestion_DBKey = activity.patientQuestionBo
					.createPatientQuestion(PatientQuestionnaire_DBKey,
							question.getQuestionnaireQuestion_DBKey(),
							questionnaireNo);

			for (IQuestionOption iQuestionOption : question
					.getQuestionOptions()) {
				if (iQuestionOption.getIsChecked()) {
					// 保存patientquestionnaireresult
					activity.patientQuestionnaireResultBo
							.createPatientQuestionnaireResult(
									PatientQuestion_DBKey,
									iQuestionOption.getQuestionOption_DBKey());
				}
			}
		}

		return PatientQuestionnaire_DBKey;
	}

	private PatientQuestionnaire getPatientQuestionnaire(
			QuestionnaireActivity activity, String dbkey, String questionnaireNo)
			throws SQLException {
		PatientQuestionnaire patientQuestionnaire = new PatientQuestionnaire();
		patientQuestionnaire.setWeightNow(Convert
				.cash2Double(activity.editTextCurrentWeight.getText()
						.toString()));
		patientQuestionnaire.setWeight1MonthAgo(Convert
				.cash2Double(activity.editTextWeight1MonthAgo.getText()
						.toString()));
		patientQuestionnaire.setWeight2MonthAgo(Convert
				.cash2Double(activity.editTextWeight2MonthAgo.getText()
						.toString()));
		patientQuestionnaire.setWeight3MonthAgo(Convert
				.cash2Double(activity.editTextWeight3MonthAgo.getText()
						.toString()));
		patientQuestionnaire.setWeight6MonthAgo(Convert
				.cash2Double(activity.editTextWeight6MonthAgo.getText()
						.toString()));

		//如果患者基本信息中没填体重，则把问卷中的体重更新到患者基本信息中
		if(Global.currentPatient.getWeight() == 0){
			Global.currentPatient.setWeight(patientQuestionnaire.getWeightNow());
			activity.patientHospitalizeBasicInfoBo.getDao().update(Global.currentPatient);
		}

		patientQuestionnaire
				.setPatientHospitalize_DBKey(activity.PatientHospitalize_DBKey);

		patientQuestionnaire.setPatientQuestionnaire_DBKey(dbkey);
		// patientQuestionnaire.setPGSGAQualitativeResult(PGSGAQualitativeResult)

		patientQuestionnaire.setQuestionnaireNo(questionnaireNo);
		patientQuestionnaire
				.setQuestionnaireStatus(QuestionnaireStatus.UNFINISHED);
		patientQuestionnaire.setQuestionProperty(activity.QuestionProperty);
		patientQuestionnaire.setRemark("");
		patientQuestionnaire
				.setScreeningDate(activity.datePickerViewScreeningDate
						.getText().toString());

		return patientQuestionnaire;

	}

	public void submitPatientQuestionnaire(QuestionnaireActivity activity)
			throws Exception {

		PatientQuestionnaire patientQuestionnaire = dao
				.queryForId(activity.PatientQuestionnaire_DBKey);
		// 分值
		int score = activity.questionnaire.getScore();
		if (activity.QuestionProperty == 1) {
			patientQuestionnaire.setNSR2002Score(score);
			patientQuestionnaire.setPGSGAScore(0);
		} else {
			patientQuestionnaire.setNSR2002Score(0);
			patientQuestionnaire.setPGSGAScore(score);
		}
		patientQuestionnaire
				.setQuestionnaireStatus(QuestionnaireStatus.SUBMITTED);
		dao.update(patientQuestionnaire);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		if (!json.equals("")) {
			PatientQuestionBo patientQuestionBo = new PatientQuestionBo(context);

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				
				PatientQuestionnaire patientQuestionnaire = dao
						.queryForId(dbKeyEntity.getOldDBKey());
				patientQuestionnaire.setOperateFlag(OperateFlag.NONE);

				dao.update(patientQuestionnaire);
				/*
				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
					dao.deleteById(dbKeyEntity.getOldDBKey());

					// 更新外键关联
					patientQuestionBo.getDao()
							.UpdatePatientQuestionnaire_DBKey(
									dbKeyEntity.getNewDBKey(),
									dbKeyEntity.getOldDBKey());
				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
					
				}
				*/
			}
		}
	}

	public void setQuestionnaireInfo(PatientHospitalizeBasicInfo patientinfo,
			ViewHolder holder) throws Exception {

		// 问卷图标
		final ImageView imageViewQuestionnaire = holder.imageViewQuestionnaire;
		BadgeView badge5 = holder.badge;
		int count = 0; // 今天做过多少个问卷

		List<PatientQuestionnaire> lstPatientQuestionnaires = dao.query(20, 0,
				patientinfo.getPatientHospitalize_DBKey());
		if (lstPatientQuestionnaires.size() > 0) {

			imageViewQuestionnaire
					.setBackgroundResource(R.drawable.questionnaire);
			// 如果有风险，则显示橙色图标
			String nowDateString = DateHelper.getInstance().getDataString_2(
					null);
			String screeningDate = "";

			for (PatientQuestionnaire patientQuestionnaire : lstPatientQuestionnaires) {

				screeningDate = DateHelper.getInstance().getDataString_3(
						patientQuestionnaire.getScreeningDate());
				if (screeningDate.equals(nowDateString)) {
					count++;
				}

				if (patientQuestionnaire.getQuestionnaireStatus().equals(
						QuestionnaireStatus.SUBMITTED)) {

					Boolean flag = hasThreats(patientQuestionnaire);

					if (flag) {
						imageViewQuestionnaire
								.setBackgroundResource(R.drawable.questionnaire3);
					}
				}
			}

		} else {
			imageViewQuestionnaire
					.setBackgroundResource(R.drawable.questionnaire2);
		}

		if (count > 0) {
			// 如果今天做过问卷，则气泡提示
			badge5.setText(count + "");
			badge5.show();
		} else {
			badge5.hide();
		}

	}

	public Boolean hasThreats(PatientQuestionnaire patientQuestionnaire) {
		Boolean flag = false;

		if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.NRS2002_TYPEVALUE) {
			if (patientQuestionnaire.getNSR2002Score() >= 3) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.PGSGA_TYPEVALUE) {
			if (patientQuestionnaire.getPGSGAScore() > 1) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.MUST_TYPEVALUE) {
			if (patientQuestionnaire.getPGSGAScore() >= 1) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.MST_TYPEVALUE) {
			if (patientQuestionnaire.getPGSGAScore() >= 2) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.MNASF_TYPEVALUE) {
			if (patientQuestionnaire.getPGSGAScore() <= 11) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.NRI_TYPEVALUE) {
			if (patientQuestionnaire.getWeight3MonthAgo() < 83.5) {
				flag = true;
			}
		} else if (patientQuestionnaire.getQuestionProperty() == QuestionnaireConstant.SGA_TYPEVALUE) {
			if (patientQuestionnaire.getPGSGAScore() == 3) {
				flag = true;
			}
		}
		return flag;
	}

}
