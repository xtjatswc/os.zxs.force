package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class PatientSyncHandle extends BaseSyncHandle {
	PatientHospitalizeBasicInfoBo hospitalizebasicinfoBo;

	public PatientSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";
		
		// 上传
		str3 = "/" + Global.WEB_API + "/patienthospitalizebasicinfo.ashx?opt=upload";
		doUpload(str3, hospitalizebasicinfoBo, "patienthospitalizebasicinfo", doSyncListener);

		// 同步patienthospitalizebasicinfo
		str1 = "/" + Global.WEB_API + "/patienthospitalizebasicinfo.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/patienthospitalizebasicinfo.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, hospitalizebasicinfoBo,
				"patienthospitalizebasicinfo", doSyncListener);

		// 更新数据排序
		hospitalizebasicinfoBo.getDao().updateOrderBy();
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_PATIENT_BASICINFO;
	}

	@Override
	protected void initializeBo() {
		try {
			hospitalizebasicinfoBo = new PatientHospitalizeBasicInfoBo(
					Global.currentActivity);
		} catch (Exception e) {
			doException(e);
		}
	}

}
