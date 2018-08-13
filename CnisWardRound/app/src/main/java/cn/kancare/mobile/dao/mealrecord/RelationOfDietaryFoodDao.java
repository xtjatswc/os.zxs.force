package cn.kancare.mobile.dao.mealrecord;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

public class RelationOfDietaryFoodDao extends BaseDao<RelationOfDietaryFood> {

	public RelationOfDietaryFoodDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "RelationOfDietaryFood_DBKey";
	}

	public List<RelationOfDietaryFood> query(String MealRecord_DBKey)
			throws SQLException {
		QueryBuilder<RelationOfDietaryFood, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("MealRecord_DBKey", MealRecord_DBKey);
		return queryBuilder.query();
	}

	public List<RelationOfDietaryFood> query(String MealRecord_DBKey, String tmeal)
			throws SQLException {
		QueryBuilder<RelationOfDietaryFood, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("MealRecord_DBKey", MealRecord_DBKey).and()
				.eq("SysCode", tmeal);

		return queryBuilder.query();
	}

	public void deleteByMealRecord_DBKey(String MealRecord_DBKey)
			throws SQLException {
		DeleteBuilder<RelationOfDietaryFood, Integer> deleteBuilder = dao
				.deleteBuilder();
		deleteBuilder.where().eq("MealRecord_DBKey", MealRecord_DBKey);
		deleteBuilder.delete();
	}

	public void UpdateMealRecord_DBKey(int newMealRecord_DBKey,
			int oldMealRecord_DBKey) throws SQLException {

		dao.updateRaw(
				"update RelationOfDietaryFood set MealRecord_DBKey = ? where MealRecord_DBKey = ?",
				newMealRecord_DBKey + "", oldMealRecord_DBKey + "");

	}

	public void updateOperateFlag(String mealRecord_DBKey, int operateFlag)
			throws SQLException {
		dao.updateRaw(
				"update RelationOfDietaryFood set OperateFlag = ? where MealRecord_DBKey = ?",
				operateFlag + "", mealRecord_DBKey + "");
	}

	public void deleteRecords(String MealRecord_DBKey, String tmeal)
			throws SQLException {
		DeleteBuilder<RelationOfDietaryFood, Integer> deleteBuilder = dao
				.deleteBuilder();
		deleteBuilder.where().eq("MealRecord_DBKey", MealRecord_DBKey).and()
				.eq("SysCode", tmeal);

		deleteBuilder.delete();
	}

}