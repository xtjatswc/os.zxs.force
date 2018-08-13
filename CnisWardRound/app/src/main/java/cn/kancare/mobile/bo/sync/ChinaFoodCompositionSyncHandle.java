package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.advice.ChargingAdviceDetailBo;
import cn.kancare.mobile.bo.advice.ChargingItemsBo;
import cn.kancare.mobile.bo.advice.ChargingItemsRelationBo;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class ChinaFoodCompositionSyncHandle extends BaseSyncHandle {

	ChinaFoodCompositionBo chinaFoodCompositionBo;
	ChargingItemsBo chargingItemsBo;
	ChargingItemsRelationBo chargingItemsRelationBo;

	public ChinaFoodCompositionSyncHandle(CnislogBo log) {
		super(log);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getLogTag() {
		return LogTag.ASYNC_CHINA_FOOD_COMPOSITION;
	}

	@Override
	protected void initializeBo() throws Exception {
		chinaFoodCompositionBo = new ChinaFoodCompositionBo(
				Global.currentActivity);
		chargingItemsBo = new ChargingItemsBo(Global.currentActivity);
		chargingItemsRelationBo = new ChargingItemsRelationBo(Global.currentActivity);
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";


		// 下载ChargingItems
		str1 = "/" + Global.WEB_API + "/ChargingItems.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/ChargingItems.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chargingItemsBo,
				"ChargingItems", doSyncListener);

		// 下载ChargingItemsRelation
		str1 = "/" + Global.WEB_API + "/ChargingItemsRelation.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/ChargingItemsRelation.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chargingItemsRelationBo,
				"ChargingItemsRelation", doSyncListener);

		//<-------------------------------
		// 下载chinafoodcomposition
		str1 = "/" + Global.WEB_API + "/chinafoodcomposition.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/chinafoodcomposition.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chinaFoodCompositionBo,
				"chinafoodcomposition", doSyncListener);

		// 多次同步操作一张表时，只清空一次该表
		if (isClearData) {
			isClearData = false;
		}

		// 下载RecipeAndProduct
		str1 = "/" + Global.WEB_API + "/RecipeAndProduct.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/RecipeAndProduct.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chinaFoodCompositionBo,
				"RecipeAndProduct", doSyncListener);
		//-------------------------------->

	}
}
