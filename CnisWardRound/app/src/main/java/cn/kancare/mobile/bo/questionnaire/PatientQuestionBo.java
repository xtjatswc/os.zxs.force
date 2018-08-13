package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.questionnaire.PatientQuestion;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.questionnaire.PatientQuestionDao;

import com.alibaba.fastjson.JSON;

public class PatientQuestionBo extends BaseBo<PatientQuestionDao> {

	public PatientQuestionBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new PatientQuestionDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<PatientQuestion> models = JSON.parseArray(json,
				PatientQuestion.class);

		for (PatientQuestion model : models) {
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

	public String createPatientQuestion(String PatientQuestionnaire_DBKey,
			int QuestionnaireQuestion_DBKey, String QuestionnaireNo)
			throws Exception {

		PatientQuestion patientQuestion = new PatientQuestion();
		patientQuestion.setCreateTime(DateHelper.getInstance().getDataString_1(
				null));
		patientQuestion.setCreateBy(Global.loginUser.getUser_DBKey() + "");
		patientQuestion.setCreateIP(Global.LocalIpAddress);
		patientQuestion.setCreateProgram(Global.CreateProgram);

		patientQuestion.setPatientQuestion_DBKey(StringUtil.getUniqueDBKey());
		patientQuestion
				.setPatientQuestionnaire_DBKey(PatientQuestionnaire_DBKey);
		patientQuestion
				.setQuestionnaireQuestion_DBKey(QuestionnaireQuestion_DBKey);
		patientQuestion.setQuestionnaireNo(QuestionnaireNo);
		patientQuestion.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);

		dao.create(patientQuestion);

		return patientQuestion.getPatientQuestion_DBKey();
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		if (!json.equals("")) {
			PatientQuestionnaireResultBo patientQuestionnaireResultBo = new PatientQuestionnaireResultBo(
					context);

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				PatientQuestion patientQuestion = dao.queryForId(dbKeyEntity.getOldDBKey());
				patientQuestion.setOperateFlag(OperateFlag.NONE);
				dao.update(patientQuestion);
				
				/*
				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
					dao.deleteById(dbKeyEntity.getOldDBKey());

					// 更新外键关联
					patientQuestionnaireResultBo.getDao()
							.UpdatePatientQuestion_DBKey(
									dbKeyEntity.getNewDBKey(),
									dbKeyEntity.getOldDBKey());
				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
					// 不用做操作

				}
				*/
			}
		}
	}
}
