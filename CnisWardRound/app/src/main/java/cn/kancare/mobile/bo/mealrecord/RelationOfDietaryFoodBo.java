package cn.kancare.mobile.bo.mealrecord;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.mealrecord.RelationOfDietaryFoodDao;

import com.alibaba.fastjson.JSON;

public class RelationOfDietaryFoodBo extends BaseBo<RelationOfDietaryFoodDao> {

	public RelationOfDietaryFoodBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws SQLException {
		dao = new RelationOfDietaryFoodDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<RelationOfDietaryFood> models = JSON.parseArray(json,
				RelationOfDietaryFood.class);

		for (RelationOfDietaryFood model : models) {
			try {
				dao.create(model);
			} catch (Exception e) {
				try {
					dao.update(model);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}

	@Override
	public void doUploadResult(String json) throws Exception {
		if (!json.equals("")) {

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
					RelationOfDietaryFood relationOfDietaryFood = dao.queryForId(dbKeyEntity.getOldDBKey());
					relationOfDietaryFood.setOperateFlag(OperateFlag.NONE);
					dao.update(relationOfDietaryFood);
					//dao.deleteById(dbKeyEntity.getOldDBKey());
				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
					// 不用做操作

				}
			}
		}
	}

	public void saveRelationOfDietaryFood(String MealRecord_DBKey, String tmeal,
			ChinaFoodComposition chinaFoodComposition) throws Exception {
		RelationOfDietaryFood relationOfDietaryFood = new RelationOfDietaryFood();
		String RelationOfDietaryFood_DBKey = StringUtil.getUniqueDBKey();

		relationOfDietaryFood
				.setRelationOfDietaryFood_DBKey(RelationOfDietaryFood_DBKey);
		relationOfDietaryFood
				.setChinaFoodComposition_DBKey(chinaFoodComposition
						.getChinaFoodComposition_DBKey());
		relationOfDietaryFood.setMealAmount(chinaFoodComposition
				.getCurrentMealAmount());
		relationOfDietaryFood.setMealRecord_DBKey(MealRecord_DBKey);
		relationOfDietaryFood.setSysCode(tmeal);
		relationOfDietaryFood.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		getDao().create(relationOfDietaryFood);
	}
}
