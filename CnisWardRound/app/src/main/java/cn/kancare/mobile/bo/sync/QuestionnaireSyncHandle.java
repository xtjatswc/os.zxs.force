package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.questionnaire.OptionDetailBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailTypeBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class QuestionnaireSyncHandle extends BaseSyncHandle {
	OptionDetailBo optionDetailBo;
	QuestionDetailBo questionDetailBo;
	QuestionDetailTypeBo questionDetailTypeBo;

	public QuestionnaireSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		
		// 同步QuestionDetailType
		str1 = "/" + Global.WEB_API + "/questiondetailtype.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/questiondetailtype.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, questionDetailTypeBo,
				"questiondetailtype", doSyncListener);

		// 同步QuestionDetail
		str1 = "/" + Global.WEB_API + "/QuestionDetail.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/QuestionDetail.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, questionDetailBo,
				"QuestionDetail", doSyncListener);

		// 同步OptionDetail
		str1 = "/" + Global.WEB_API + "/OptionDetail.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/OptionDetail.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, optionDetailBo,
				"OptionDetail", doSyncListener);
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_QUESTIONNAIRE;
	}

	@Override
	protected void initializeBo() {
		try {
			optionDetailBo = new OptionDetailBo(Global.currentActivity);
			questionDetailBo = new QuestionDetailBo(Global.currentActivity);
			questionDetailTypeBo = new QuestionDetailTypeBo(
					Global.currentActivity);
		} catch (Exception e) {
			doException(e);
		}
	}

}
