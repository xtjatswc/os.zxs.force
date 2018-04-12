package cn.kancare.mobile.bo.advice;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.advice.fragment.AdviceInputFragment;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.advice.NutrientAdviceDetailDao;

import com.alibaba.fastjson.JSON;

public class NutrientAdviceDetailBo extends BaseBo<NutrientAdviceDetailDao> {

	public NutrientAdviceDetailBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new NutrientAdviceDetailDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<NutrientAdviceDetail> models = JSON.parseArray(json,
				NutrientAdviceDetail.class);

		for (NutrientAdviceDetail model : models) {
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
	public void doUploadResult(String result) throws Exception {
		if (!result.equals("")) {
			List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				NutrientAdviceDetail nutrientAdviceDetail = dao
						.queryForId(dbKeyEntity.getOldDBKey());
				nutrientAdviceDetail.setOperateFlag(OperateFlag.NONE);
				dao.update(nutrientAdviceDetail);
			}
		}

	}

	public void SaveNutrientAdviceDetail(AdviceInputFragment context,
			String NutrientAdvice_DBKey) throws Exception {
		// 判断该医嘱单的当前制剂是否存在
		NutrientAdviceDetail nutrientAdviceDetail = getDao().queryAdviceDetail(
				NutrientAdvice_DBKey,
				context.chinaFoodComposition.getRecipeAndProduct_DBKey());

		if (nutrientAdviceDetail == null) {
			nutrientAdviceDetail = new NutrientAdviceDetail();
			nutrientAdviceDetail.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
			nutrientAdviceDetail.setNutrientAdviceDetail_DBKEY(StringUtil
					.getUniqueDBKey());
			nutrientAdviceDetail.setNutrientAdvice_DBKey(NutrientAdvice_DBKey);
			nutrientAdviceDetail
					.setRecipeAndProduct_DBKey(context.chinaFoodComposition
							.getRecipeAndProduct_DBKey());

			//
			setInputAdviceDetail(context, nutrientAdviceDetail);

			// 创建日期、创建人
			nutrientAdviceDetail.setCreateTime(DateHelper.getInstance()
					.getDataString_1(null));
			nutrientAdviceDetail.setCreateBy(Global.loginUser.getUser_DBKey()
					+ "");
			nutrientAdviceDetail.setCreateIP(Global.LocalIpAddress);
			nutrientAdviceDetail.setCreateProgram(Global.CreateProgram);
			getDao().create(nutrientAdviceDetail);
		} else {
			//
			setInputAdviceDetail(context, nutrientAdviceDetail);
			getDao().update(nutrientAdviceDetail);
		}
	}

	private void setInputAdviceDetail(AdviceInputFragment context,
			NutrientAdviceDetail nutrientAdviceDetail) {
		nutrientAdviceDetail.setMedicine_DBKey(0);
		// 获取服用方式
		nutrientAdviceDetail.setAdviceDoTimeSegmental(context.getENTime()
				.getSysCode());

		// 肠内医嘱
		nutrientAdviceDetail.setNutrientAdviceType("2");
		nutrientAdviceDetail.setAdviceAmount(Convert
				.cash2Double(context.EditTextNum.getText().toString()));
		nutrientAdviceDetail.setSpecification(context.EditTextSpec.getText()
				.toString());
		nutrientAdviceDetail.setUnit(context.chinaFoodComposition
				.getMeasureUnitName());
		// 制剂方式
		nutrientAdviceDetail.setPreparationMode(context.getPreparationMode()
				.getSysCode());

		nutrientAdviceDetail
				.setNutrientAdviceDetailRemark(context.EditTextRemark.getText()
						.toString());
		nutrientAdviceDetail.setAdviceValidStatus("1");
		nutrientAdviceDetail
				.setNutrientProductCompleteNo(context.nutrientAdviceSummary
						.getNutrientAdviceSummary_DBKey());
		nutrientAdviceDetail.setCurrentPrice(context.chinaFoodComposition
				.getRecipeAndProductPrice());

		// 时间段
		String dotimeString = "";
		List<String> lstDotime = context.getDoTime();
		for (String string : lstDotime) {
			dotimeString += string + ",";
		}
		dotimeString = dotimeString.substring(0, dotimeString.length() - 1);
		nutrientAdviceDetail.setTakeOrder(dotimeString);
		nutrientAdviceDetail.setLiquidAmount(0.0);
	}

}
