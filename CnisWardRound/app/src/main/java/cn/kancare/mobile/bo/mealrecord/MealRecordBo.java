package cn.kancare.mobile.bo.mealrecord;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.mealrecord.MealRecordActivity;
import cn.kancare.mobile.activity.mealrecord.MealRecordInfoActivity;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.mealrecord.MealRecord;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.FlagCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.mealrecord.MealRecordDao;

import com.alibaba.fastjson.JSON;

public class MealRecordBo extends BaseBo<MealRecordDao> {

	public MealRecordBo(Activity context) throws Exception {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setDao() throws Exception {
		dao = new MealRecordDao(context);
	}

	public void loadMealRecord(MealRecordInfoActivity activity)
			throws Exception {
		MealRecord mealRecord = getDao().query(
				activity.timeFragment.getRecordTime(),
				Global.currentPatient.getPatientHospitalize_DBKey());

		List<RelationOfDietaryFood> lstRelationOfDietaryFoods = null;
		if (mealRecord != null) {
			lstRelationOfDietaryFoods = activity.relationOfDietaryFoodBo
					.getDao().query(mealRecord.getMealRecord_DBKey(),
							activity.tmealFragment.getTmeal());
		}

		activity.foodFragment.setFoodRecords(lstRelationOfDietaryFoods);

	}

	/**
	 * @param activity
	 * @return true 保存或新增成功 false 未录入任何信息
	 * @throws Exception
	 */
	public Boolean saveMealRecord(MealRecordInfoActivity activity)
			throws Exception {

		// 先查询是否有这一天的数据
		MealRecord mealRecord = getDao().query(
				activity.timeFragment.getRecordTime(),
				Global.currentPatient.getPatientHospitalize_DBKey());
		if (mealRecord == null) {
			// 如果没有则新建
			String MealRecord_DBKey = StringUtil.getUniqueDBKey();
			mealRecord = new MealRecord();
			mealRecord.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
			mealRecord.setMealRecord_DBKey(MealRecord_DBKey);
			mealRecord.setMealRecordNo(MealRecord_DBKey + "");
			mealRecord.setCreateTime(DateHelper.getInstance().getDataString_1(
					null));
			mealRecord.setCreateBy(Global.loginUser.getUser_DBKey() + "");
			mealRecord.setCreateIP(Global.LocalIpAddress);
			mealRecord.setCreateProgram(Global.CreateProgram);
			mealRecord.setMealDate(activity.timeFragment.getRecordTime());
			mealRecord.setPageFlg(FlagCode.MEALRECORD_PAGETYPE);
			mealRecord.setPatientHospitalize_DBKey(Global.currentPatient
					.getPatientHospitalize_DBKey());

			if (activity.foodFragment.hasFoodRecords()) {
				getDao().create(mealRecord);
			} else {
				return false;
			}
		} else {
			// 有则修改
			mealRecord.setUpdateTime(DateHelper.getInstance().getDataString_1(
					null));
			mealRecord.setUpdateBy(Global.loginUser.getUser_DBKey() + "");
			mealRecord.setUpdateIP(Global.LocalIpAddress);
			mealRecord.setUpdateProgram(Global.CreateProgram);

			if (mealRecord.getOperateFlag() != OperateFlag.NEED_ADD_TO_SERVER) {
				mealRecord.setOperateFlag(OperateFlag.NEED_EDIT_TO_SERVER);
			}

			getDao().update(mealRecord);
		}

		// 餐次
		// 先删除食物
		activity.relationOfDietaryFoodBo.getDao().deleteRecords(
				mealRecord.getMealRecord_DBKey(),
				activity.tmealFragment.getTmeal());

		if (!activity.foodFragment.hasFoodRecords())
			return true;

		List<ChinaFoodComposition> lstChinaFoodCompositions = activity.foodFragment
				.getFoodRecords();
		for (ChinaFoodComposition chinaFoodComposition : lstChinaFoodCompositions) {
			if (chinaFoodComposition.getCurrentMealAmount() == 0)
				continue;

			// 新增食物
			activity.relationOfDietaryFoodBo.saveRelationOfDietaryFood(
					mealRecord.getMealRecord_DBKey(),
					activity.tmealFragment.getTmeal(), chinaFoodComposition);

		}

		// 更新状态为需要新增至服务器
		activity.relationOfDietaryFoodBo.getDao().updateOperateFlag(
				mealRecord.getMealRecord_DBKey(),
				OperateFlag.NEED_ADD_TO_SERVER);

		return true;
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<MealRecord> models = JSON.parseArray(json, MealRecord.class);

		for (MealRecord model : models) {
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
			RelationOfDietaryFoodBo relationOfDietaryFoodBo = new RelationOfDietaryFoodBo(
					context);

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				MealRecord mealRecord = dao.queryForId(dbKeyEntity.getOldDBKey());
				mealRecord.setOperateFlag(OperateFlag.NONE);
				dao.update(mealRecord);
				
				/*
				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
					dao.deleteById(dbKeyEntity.getOldDBKey());

					// 更新外键关联
					relationOfDietaryFoodBo.getDao().UpdateMealRecord_DBKey(
							dbKeyEntity.getNewDBKey(),
							dbKeyEntity.getOldDBKey());

				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
					// MealRecord mealRecord = dao.queryForId(dbKeyEntity
					// .getOldDBKey());
					// mealRecord.setOperateFlag(OperateFlag.NONE);
					//
					// dao.update(mealRecord);

					dao.deleteById(dbKeyEntity.getOldDBKey());

				}
				*/
			}
		}

	}

	public List<ChinaFoodComposition> getFoodAmountList(
			MealRecordActivity activity,
			List<ChinaFoodComposition> lstChinaFoodCompositions, MealRecord data)
			throws SQLException {

		List<RelationOfDietaryFood> lstRelationOfDietaryFoods = activity.relationOfDietaryFoodBo
				.getDao().query(data.getMealRecord_DBKey());
		for (ChinaFoodComposition chinaFoodComposition : lstChinaFoodCompositions) {
			chinaFoodComposition.setCurrentMealAmount(0.0);

			for (RelationOfDietaryFood relationOfDietaryFood : lstRelationOfDietaryFoods) {
				if (chinaFoodComposition.getChinaFoodComposition_DBKey() == relationOfDietaryFood
						.getChinaFoodComposition_DBKey()) {
					// 累加数量
					chinaFoodComposition
							.setCurrentMealAmount(chinaFoodComposition
									.getCurrentMealAmount()
									+ relationOfDietaryFood.getMealAmount());
				}
			}

		}
		return lstChinaFoodCompositions;
	}
}
