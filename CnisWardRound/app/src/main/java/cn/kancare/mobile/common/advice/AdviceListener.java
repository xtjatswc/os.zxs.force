package cn.kancare.mobile.common.advice;

import cn.kancare.mobile.bean.basic.ChinaFoodComposition;

public class AdviceListener {

	public interface AdviceInfoListener {
		public void ProductChange(ChinaFoodComposition chinaFoodComposition);

		public void refreshProductStatus(String NutrientAdviceSummary_DBKey)
				throws Exception;

		public String[] getAdviceDate();
	}

	public interface AdviceInputListener {
		public void ProductChange(ChinaFoodComposition chinaFoodComposition);

		public void loadNutrientAdviceSummary(String NutrientAdviceSummary_DBKey);
	}

	public interface AdviceTopListener {
		public String[] getAdviceDate();

		public void setAdviceDate(String NutrientAdviceSummary_DBKey)
				throws Exception;
	}

	public interface NutrientProductListener {
		public void refreshProductStatus(String NutrientAdviceSummary_DBKey)
				throws Exception;
	}
}
