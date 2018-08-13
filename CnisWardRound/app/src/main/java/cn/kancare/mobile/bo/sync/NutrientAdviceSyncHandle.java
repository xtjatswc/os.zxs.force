package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.advice.ChargingAdviceDetailBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceDetailBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class NutrientAdviceSyncHandle extends BaseSyncHandle {
	NutrientAdviceBo nutrientAdviceBo;
	NutrientAdviceDetailBo nutrientAdviceDetailBo;
	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;
	ChargingAdviceDetailBo chargingAdviceDetailBo;

	public NutrientAdviceSyncHandle(CnislogBo log) {
		super(log);

	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_NUTRIENT_ADVICE;
	}

	@Override
	protected void initializeBo() throws Exception {
		nutrientAdviceBo = new NutrientAdviceBo(Global.currentActivity);
		nutrientAdviceDetailBo = new NutrientAdviceDetailBo(
				Global.currentActivity);
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(
				Global.currentActivity);
		chargingAdviceDetailBo = new ChargingAdviceDetailBo(Global.currentActivity);

	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";

		// 同步

		// 上传
		str3 = "/" + Global.WEB_API + "/nutrientadvice.ashx?opt=upload";
		doUpload(str3, nutrientAdviceBo, "nutrientadvice", doSyncListener);
		str3 = "/" + Global.WEB_API + "/nutrientadvicedetail.ashx?opt=upload";
		doUpload(str3, nutrientAdviceDetailBo, "nutrientadvicedetail",
				doSyncListener);
		str3 = "/" + Global.WEB_API + "/nutrientadvicesummary.ashx?opt=upload";
		doUpload(str3, nutrientAdviceSummaryBo, "nutrientadvicesummary",
				doSyncListener);

		// 下载 nutrientadvice
		str1 = "/" + Global.WEB_API + "/nutrientadvice.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/nutrientadvice.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, nutrientAdviceBo,
				"nutrientadvice", doSyncListener);

		// 下载 nutrientadvicedetail
		str1 = "/" + Global.WEB_API
				+ "/nutrientadvicedetail.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/nutrientadvicedetail.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
				nutrientAdviceDetailBo, "nutrientadvicedetail", doSyncListener);

		// 同步nutrientadvicesummary
		str1 = "/" + Global.WEB_API
				+ "/nutrientadvicesummary.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/nutrientadvicesummary.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
				nutrientAdviceSummaryBo, "nutrientadvicesummary",
				doSyncListener);

		// 上传
		str1 = "/" + Global.WEB_API + "/chargingAdviceDetail.ashx?opt=upload";
		doUpload(str1, chargingAdviceDetailBo, "chargingAdviceDetail", doSyncListener);

		// 下载ChargingAdviceDetail
		str1 = "/" + Global.WEB_API + "/ChargingAdviceDetail.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/ChargingAdviceDetail.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chargingAdviceDetailBo,
				"ChargingAdviceDetail", doSyncListener);

	}

}
