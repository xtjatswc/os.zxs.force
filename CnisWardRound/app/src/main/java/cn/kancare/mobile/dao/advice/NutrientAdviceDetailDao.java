package cn.kancare.mobile.dao.advice;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class NutrientAdviceDetailDao extends BaseDao<NutrientAdviceDetail> {

	public NutrientAdviceDetailDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "NutrientAdviceDetail_DBKEY";
	}

	public NutrientAdviceDetail queryAdviceDetail(String NutrientAdvice_DBKey,
			int RecipeAndProduct_DBKey) throws SQLException {
		QueryBuilder<NutrientAdviceDetail, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("NutrientAdvice_DBKey", NutrientAdvice_DBKey)
				.and().eq("RecipeAndProduct_DBKey", RecipeAndProduct_DBKey);
		return queryBuilder.queryForFirst();
	}

	public List<NutrientAdviceDetail> queryAdviceDetail(
			String NutrientAdvice_DBKey) throws SQLException {
		QueryBuilder<NutrientAdviceDetail, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("NutrientAdvice_DBKey", NutrientAdvice_DBKey);
		queryBuilder.orderBy("RecipeAndProduct_DBKey", true).orderBy(
				"TakeOrder", true);
		return queryBuilder.query();
	}

}
