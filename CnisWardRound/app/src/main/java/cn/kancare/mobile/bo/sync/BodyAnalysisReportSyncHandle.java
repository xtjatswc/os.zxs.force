package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.BodyAnalysisReportBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class BodyAnalysisReportSyncHandle extends BaseSyncHandle {

	BodyAnalysisReportBo bodyAnalysisReportBo;

	public BodyAnalysisReportSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_BODYANALYSISREPORT;
	}

	@Override
	protected void initializeBo() throws Exception {
		bodyAnalysisReportBo = new BodyAnalysisReportBo(Global.currentActivity);
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";

		// 同步BodyAnalysisReport
		str1 = "/" + Global.WEB_API
				+ "/BodyAnalysisReport.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/BodyAnalysisReport.ashx?opt=getlist";
		doDownload(str1, str2, 0, 10, bodyAnalysisReportBo,
				"BodyAnalysisReport", doSyncListener);
	}

}
