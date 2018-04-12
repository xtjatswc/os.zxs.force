package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.CourseRecordBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class CourseRecordSyncHandle extends BaseSyncHandle {

	CourseRecordBo courseBo;

	public CourseRecordSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_COURSERECORD;
	}

	@Override
	protected void initializeBo() {
		try {
			courseBo = new CourseRecordBo(Global.currentActivity);
		} catch (Exception e) {
			doException(e);
		}
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";

		// 上传
		str3 = "/" + Global.WEB_API + "/courserecord.ashx?opt=upload";
		doUpload(str3, courseBo, "courserecord", doSyncListener);

		// 同步courserecord
		str1 = "/" + Global.WEB_API + "/courserecord.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/courserecord.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, courseBo,
				"courserecord", doSyncListener);
	}
}
