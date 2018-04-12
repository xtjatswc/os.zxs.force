package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.questionnaire.PatientQuestionBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireResultBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class QuestionnaireResultSyncHandle extends BaseSyncHandle {
	PatientQuestionBo patientQuestionBo;
	PatientQuestionnaireBo patientQuestionnaireBo;
	PatientQuestionnaireResultBo patientQuestionnaireResultBo;

	public QuestionnaireResultSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";

		// 上传 PatientQuestionnaire
		str3 = "/" + Global.WEB_API + "/PatientQuestionnaire.ashx?opt=upload";
		doUpload(str3, patientQuestionnaireBo, "PatientQuestionnaire",doSyncListener);

		// 上传 PatientQuestion
		str3 = "/" + Global.WEB_API + "/PatientQuestion.ashx?opt=upload";
		doUpload(str3, patientQuestionBo, "PatientQuestion",doSyncListener);

		// 上传 PatientQuestionnaireResult
		str3 = "/" + Global.WEB_API
				+ "/PatientQuestionnaireResult.ashx?opt=upload";
		doUpload(str3, patientQuestionnaireResultBo,
				"PatientQuestionnaireResult",doSyncListener);

		// 同步PatientQuestionnaire
		str1 = "/" + Global.WEB_API
				+ "/PatientQuestionnaire.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/PatientQuestionnaire.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
				patientQuestionnaireBo, "PatientQuestionnaire", doSyncListener);

		// 同步PatientQuestion
		str1 = "/" + Global.WEB_API + "/PatientQuestion.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/PatientQuestion.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, patientQuestionBo,
				"PatientQuestion", doSyncListener);

		// 同步PatientQuestionnaireResult
		str1 = "/" + Global.WEB_API
				+ "/PatientQuestionnaireResult.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API
				+ "/PatientQuestionnaireResult.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
				patientQuestionnaireResultBo, "PatientQuestionnaireResult",
				doSyncListener);
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_QUESTIONNAIRE;
	}

	@Override
	protected void initializeBo() {
		try {
			patientQuestionBo = new PatientQuestionBo(Global.currentActivity);
			patientQuestionnaireBo = new PatientQuestionnaireBo(
					Global.currentActivity);
			patientQuestionnaireResultBo = new PatientQuestionnaireResultBo(
					Global.currentActivity);
		} catch (Exception e) {
			doException(e);
		}
	}

}
