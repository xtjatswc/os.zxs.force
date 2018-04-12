package cn.kancare.mobile.dao.basic;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.SettingCode;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

/**
 * @author Administrator
 * 
 */
public class ChinaFoodCompositionDao extends BaseDao<ChinaFoodComposition> {

	public ChinaFoodCompositionDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "ChinaFoodComposition_DBKey";
	}

	/**
	 * @param FoodTableInsideType
	 *            0、食材 2、肠内制剂
	 * @return
	 * @throws SQLException
	 */
	public List<ChinaFoodComposition> query(String FoodTableInsideType)
			throws SQLException {

		if (Global.Individuation.equals(SettingCode.INDIVIDUATION_LXH)) {
			return dao.queryBuilder().orderBy("FoodCode", true).query();
		}

		return dao.queryBuilder().orderBy("FoodTableInsideType", true)
				.orderBy("FoodCode", true).query();

		// return dao.queryBuilder().where()
		// .eq("FoodTableInsideType", FoodTableInsideType).query();
	}

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<ChinaFoodComposition> query(int limit, int offset,
			String FoodTableInsideType) throws Exception {

		QueryBuilder<ChinaFoodComposition, Integer> qBuilder = dao
				.queryBuilder();
		qBuilder.where().eq("FoodTableInsideType", FoodTableInsideType);

		qBuilder.limit(limit).offset(offset).orderBy("FoodName", true);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public ChinaFoodComposition queryByRecipeAndProduct_DBKey(int RecipeAndProduct_DBKey)
			throws Exception {

		QueryBuilder<ChinaFoodComposition, Integer> qBuilder = dao
				.queryBuilder();
		qBuilder.where().eq("RecipeAndProduct_DBKey", RecipeAndProduct_DBKey);

		return qBuilder.queryForFirst();

	}// ...other operations
}