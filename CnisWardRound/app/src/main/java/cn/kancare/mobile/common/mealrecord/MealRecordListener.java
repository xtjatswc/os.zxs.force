package cn.kancare.mobile.common.mealrecord;

import java.util.Date;
import java.util.List;

import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;

public class MealRecordListener {

	public interface MealRecordTimeListener {

		public void setRecordTime(String recordTime);

		public Date getRecordTime();

		public String getRecordTime2();

	}

	public interface MealRecordTmealListener {
		public String getTmeal();
	}

	public interface MealRecordInfoListener {
		public void loadMealRecord();

		public void saveMealRecord();
		
		// 计算营养成分
		public void calcNutrition();
	}

	public interface MealRecordFoodListener {

		// 赋值
		public void setFoodRecords(
				List<RelationOfDietaryFood> lstRelationOfDietaryFoods);

		// 是否有点选
		public Boolean hasFoodRecords();

		// 获取点选
		public List<ChinaFoodComposition> getFoodRecords();
	}

	public interface MealRecordTotalListener {

		// 计算营养成分
		public void calcNutrition(List<ChinaFoodComposition> lstChinaFoodCompositions);

	}
}
