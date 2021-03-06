package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.basic.UserBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class UserSyncHandle extends BaseSyncHandle {

	UserBo userBo;

	public UserSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_USER;
	}

	@Override
	protected void initializeBo() {
		try {
			userBo = new UserBo(Global.currentActivity);
		} catch (Exception e) {
			doException(e);
		}
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";

		// 同步user
		str1 = "/" + Global.WEB_API + "/user.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/user.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, userBo, "user",
				doSyncListener);
	}
}
