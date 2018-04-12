package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.mealrecord.MealRecordBo;
import cn.kancare.mobile.bo.mealrecord.RelationOfDietaryFoodBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class MealRecordSyncHandle extends BaseSyncHandle {
	MealRecordBo mealRecordBo;
	RelationOfDietaryFoodBo relationOfDietaryFoodBo;

	public MealRecordSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		String str3 = "";

		// 上传 MealRecord
		str3 = "/" + Global.WEB_API + "/MealRecord.ashx?opt=upload";
		doUpload(str3, mealRecordBo, "MealRecord", doSyncListener);

		// 上传 RelationOfDietaryFood
		str3 = "/" + Global.WEB_API + "/RelationOfDietaryFood.ashx?opt=upload";
		doUpload(str3, relationOfDietaryFoodBo, "RelationOfDietaryFood", doSyncListener);

		// 同步MealRecord
		str1 = "/" + Global.WEB_API + "/MealRecord.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/MealRecord.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, mealRecordBo,
				"MealRecord", doSyncListener);

		// 同步RelationOfDietaryFood
		str1 = "/" + Global.WEB_API
				+ "/RelationOfDietaryFood.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/RelationOfDietaryFood.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE,
				relationOfDietaryFoodBo, "RelationOfDietaryFood",
				doSyncListener);

	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_MEALRECORD;
	}

	@Override
	protected void initializeBo() {
		try {
			mealRecordBo = new MealRecordBo(Global.currentActivity);
			relationOfDietaryFoodBo = new RelationOfDietaryFoodBo(
					Global.currentActivity);

		} catch (Exception e) {
			doException(e);
		}
	}

}
