package cn.kancare.mobile.bo.basic;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.common.constant.FlagCode;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.basic.ChinaFoodCompositionDao;

import com.alibaba.fastjson.JSON;

public class ChinaFoodCompositionBo extends BaseBo<ChinaFoodCompositionDao> {

	public ChinaFoodCompositionBo(Activity context) throws Exception {
		super(context);
	}

	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<ChinaFoodComposition> models = JSON.parseArray(json,
				ChinaFoodComposition.class);

		for (ChinaFoodComposition model : models) {
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
	protected void setDao() throws Exception {
		dao = new ChinaFoodCompositionDao(context);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}

	//肠内医嘱能量计算
	public ChinaFoodComposition computeNutritionTotal(
			List<ChinaFoodComposition> lstChinaFoodCompositions) {
		ChinaFoodComposition total = new ChinaFoodComposition();

		for (ChinaFoodComposition item : lstChinaFoodCompositions) {
			if (item.getCurrentMealAmount() != 0) {

				if (item.getFoodTableInsideType() == FlagCode.FOOD_TABLE_INSIDE_TYPE) {
					// 肠内制剂 CurrentMealAmount为净含量
					total.setEnergy(total.getEnergy() + item.getEnergy()
							* item.getCurrentMealAmount() / 100);
					total.setProtein(total.getProtein() + item.getProtein()
							* item.getCurrentMealAmount() / 100);
					total.setFat(total.getFat() + item.getFat()
							* item.getCurrentMealAmount() / 100);
					// 碳水化合物不用减去膳食纤维 - item.getTotalDietaryFiber()
					total.setCarbohydrate(total.getCarbohydrate()
							+ item.getCarbohydrate()
							* item.getCurrentMealAmount() / 100);
					total.setCa(total.getCa() + item.getCa()
							* item.getCurrentMealAmount() / 100);
					total.setP(total.getP() + item.getP()
							* item.getCurrentMealAmount() / 100);
					total.setK(total.getK() + item.getK()
							* item.getCurrentMealAmount() / 100);
					total.setNa(total.getNa() + item.getNa()
							* item.getCurrentMealAmount() / 100);

					total.setMg(total.getMg() + item.getMg()
							* item.getCurrentMealAmount() / 100);
					total.setFe(total.getFe() + item.getFe()
							* item.getCurrentMealAmount() / 100);
					total.setVitaminC(total.getVitaminC() + item.getVitaminC()
							* item.getCurrentMealAmount() / 100);
					total.setTotalDietaryFiber(total.getTotalDietaryFiber()
							+ item.getTotalDietaryFiber()
							* item.getCurrentMealAmount() / 100);
				}

			}
		}
		return total;
	}

	//中国食物成份能量计算
	public ChinaFoodComposition computeTotal(
			List<ChinaFoodComposition> lstChinaFoodCompositions) {
		ChinaFoodComposition total = new ChinaFoodComposition();

		for (ChinaFoodComposition item : lstChinaFoodCompositions) {
			if (item.getCurrentMealAmount() != 0) {

				if (item.getFoodTableInsideType() == FlagCode.FOOD_TABLE_INSIDE_TYPE
						&& item.getWrapperType() == FlagCode.WRAPPER_TYPE) {
					// 原包装肠内制剂
					total.setEnergy(total.getEnergy() + item.getEnergy()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setProtein(total.getProtein() + item.getProtein()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setFat(total.getFat() + item.getFat()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					// 碳水化合物不用减去膳食纤维 - item.getTotalDietaryFiber()
					total.setCarbohydrate(total.getCarbohydrate()
							+ item.getCarbohydrate()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setCa(total.getCa() + item.getCa()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setP(total.getP() + item.getP()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setK(total.getK() + item.getK()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setNa(total.getNa() + item.getNa()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);

					total.setMg(total.getMg() + item.getMg()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setFe(total.getFe() + item.getFe()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setVitaminC(total.getVitaminC() + item.getVitaminC()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
					total.setTotalDietaryFiber(total.getTotalDietaryFiber()
							+ item.getTotalDietaryFiber()
							* item.getCurrentMealAmount()
							* item.getNutrientProductSpecification() / 100);
				} else if (item.getFoodTableInsideType() == FlagCode.FOOD_TABLE_INSIDE_TYPE
						&& item.getWrapperType() != FlagCode.WRAPPER_TYPE) {
					// 拆包装肠内制剂

					total.setEnergy(total.getEnergy() + item.getEnergy()
							* item.getCurrentMealAmount() / 100);
					total.setProtein(total.getProtein() + item.getProtein()
							* item.getCurrentMealAmount() / 100);
					total.setFat(total.getFat() + item.getFat()
							* item.getCurrentMealAmount() / 100);
					// 碳水化合物不用减去膳食纤维
					total.setCarbohydrate(total.getCarbohydrate()
							+ item.getCarbohydrate()
							* item.getCurrentMealAmount() / 100);

					total.setCa(total.getCa() + item.getCa()
							* item.getCurrentMealAmount() / 100);
					total.setP(total.getP() + item.getP()
							* item.getCurrentMealAmount() / 100);
					total.setK(total.getK() + item.getK()
							* item.getCurrentMealAmount() / 100);
					total.setNa(total.getNa() + item.getNa()
							* item.getCurrentMealAmount() / 100);

					total.setMg(total.getMg() + item.getMg()
							* item.getCurrentMealAmount() / 100);
					total.setFe(total.getFe() + item.getFe()
							* item.getCurrentMealAmount() / 100);
					total.setVitaminC(total.getVitaminC() + item.getVitaminC()
							* item.getCurrentMealAmount() / 100);
					total.setTotalDietaryFiber(total.getTotalDietaryFiber()
							+ item.getTotalDietaryFiber()
							* item.getCurrentMealAmount() / 100);
				} else {
					total.setEnergy(total.getEnergy() + item.getEnergy()
							* item.getCurrentMealAmount());
					total.setProtein(total.getProtein() + item.getProtein()
							* item.getCurrentMealAmount());
					total.setFat(total.getFat() + item.getFat()
							* item.getCurrentMealAmount());
					// 碳水化合物不用减去膳食纤维
					total.setCarbohydrate(total.getCarbohydrate()
							+ item.getCarbohydrate()
							* item.getCurrentMealAmount());
					total.setCa(total.getCa() + item.getCa()
							* item.getCurrentMealAmount());
					total.setP(total.getP() + item.getP()
							* item.getCurrentMealAmount());
					total.setK(total.getK() + item.getK()
							* item.getCurrentMealAmount());
					total.setNa(total.getNa() + item.getNa()
							* item.getCurrentMealAmount());

					total.setMg(total.getMg() + item.getMg()
							* item.getCurrentMealAmount());
					total.setFe(total.getFe() + item.getFe()
							* item.getCurrentMealAmount());
					total.setVitaminC(total.getVitaminC() + item.getVitaminC()
							* item.getCurrentMealAmount());
					total.setTotalDietaryFiber(total.getTotalDietaryFiber()
							+ item.getTotalDietaryFiber()
							* item.getCurrentMealAmount());
				}

			}
		}
		return total;
	}
}
