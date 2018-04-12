package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.laboratoryindex.SearchPageConfigBo;
import cn.kancare.mobile.bo.laboratoryindex.TestItemDetailBo;
import cn.kancare.mobile.bo.laboratoryindex.TestResultBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class LaboratoryIndexSyncHandle extends BaseSyncHandle {

	LaboratoryIndexBo laboratoryIndexBo;
	TestItemDetailBo testitemDetailBo;
	TestResultBo testResultBo;
	SearchPageConfigBo searchPageConfigBo;

	public LaboratoryIndexSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_LABORATORY_INDEX;
	}

	@Override
	protected void initializeBo() throws Exception {
		laboratoryIndexBo = new LaboratoryIndexBo(Global.currentActivity);
		testitemDetailBo = new TestItemDetailBo(Global.currentActivity);
		testResultBo = new TestResultBo(Global.currentActivity);
		searchPageConfigBo = new SearchPageConfigBo(Global.currentActivity);
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";

		// 同步LaboratoryIndex

		// 上传
		str3 = "/" + Global.WEB_API + "/laboratoryIndex.ashx?opt=upload";
		doUpload(str3, laboratoryIndexBo, "laboratoryIndex", doSyncListener);
		str3 = "/" + Global.WEB_API + "/testresult.ashx?opt=upload";
		doUpload(str3, testResultBo, "testresult", doSyncListener);

		str1 = "/" + Global.WEB_API + "/LaboratoryIndex.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/LaboratoryIndex.ashx?opt=getlist";
		doDownload(str1, str2, 0, 10, laboratoryIndexBo, "LaboratoryIndex",
				doSyncListener);

		// 同步testitemdetail
		str1 = "/" + Global.WEB_API + "/testitemdetail.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/testitemdetail.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, testitemDetailBo,
				"testitemdetail", doSyncListener);

		// 同步searchpageconfig
		// str1 = "/" + Global.WEB_API +
		// "/searchpageconfig.ashx?opt=getlistcount";
		// str2 = "/" + Global.WEB_API + "/searchpageconfig.ashx?opt=getlist";
		// doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
		// searchPageConfigBo,
		// "searchpageconfig", doSyncListener);

	}

}
