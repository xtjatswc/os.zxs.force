package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaireResult;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.questionnaire.PatientQuestionnaireResultDao;

import com.alibaba.fastjson.JSON;

public class PatientQuestionnaireResultBo extends
		BaseBo<PatientQuestionnaireResultDao> {

	public PatientQuestionnaireResultBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new PatientQuestionnaireResultDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<PatientQuestionnaireResult> models = JSON.parseArray(json,
				PatientQuestionnaireResult.class);

		for (PatientQuestionnaireResult model : models) {
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

	public String createPatientQuestionnaireResult(String PatientQuestion_DBKey,
			int QuestionOption_DBKey) throws Exception {
		// 获取dbkey
		String PatientQuestionnaireResult_DBKey = StringUtil.getUniqueDBKey();

		PatientQuestionnaireResult patientQuestionnaireResult = new PatientQuestionnaireResult();
		patientQuestionnaireResult.setCreateTime(DateHelper.getInstance()
				.getDataString_1(null));
		patientQuestionnaireResult.setCreateBy(Global.loginUser.getUser_DBKey()
				+ "");
		patientQuestionnaireResult.setCreateIP(Global.LocalIpAddress);
		patientQuestionnaireResult.setCreateProgram(Global.CreateProgram);

		patientQuestionnaireResult
				.setPatientQuestionnaireResult_DBKey(PatientQuestionnaireResult_DBKey);

		patientQuestionnaireResult
				.setPatientQuestion_DBKey(PatientQuestion_DBKey);
		patientQuestionnaireResult
				.setQuestionOption_DBKey(QuestionOption_DBKey);
		patientQuestionnaireResult
				.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		dao.create(patientQuestionnaireResult);
		return PatientQuestionnaireResult_DBKey;
	}

	@Override
	public void doUploadResult(String result) throws Exception {
		if (!result.equals("")) {
			List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
					dao.deleteById(dbKeyEntity.getOldDBKey());
				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
					//不用做操作
				}
			}
		}
	}
}
