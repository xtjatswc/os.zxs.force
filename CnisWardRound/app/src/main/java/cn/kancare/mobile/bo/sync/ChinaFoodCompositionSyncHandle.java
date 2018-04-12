package cn.kancare.mobile.bo.sync;

import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;

public class ChinaFoodCompositionSyncHandle extends BaseSyncHandle {

	ChinaFoodCompositionBo chinaFoodCompositionBo;

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
	}

	@Override
	public void doSync(DoSyncListener doSyncListener) throws Exception {
		String str1 = "";
		String str2 = "";
		
		// 同步chinafoodcomposition
		str1 = "/" + Global.WEB_API + "/chinafoodcomposition.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/chinafoodcomposition.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chinaFoodCompositionBo,
				"chinafoodcomposition", doSyncListener);

		// 多次同步操作一张表时，只清空一次该表
		if (isClearData) {
			isClearData = false;
		}

		// 同步RecipeAndProduct
		str1 = "/" + Global.WEB_API + "/RecipeAndProduct.ashx?opt=getlistcount";
		str2 = "/" + Global.WEB_API + "/RecipeAndProduct.ashx?opt=getlist";
		doDownload(str1, str2, 0, Global.REQUEST_LIMIT_SIZE, chinaFoodCompositionBo,
				"RecipeAndProduct", doSyncListener);

	}
}
