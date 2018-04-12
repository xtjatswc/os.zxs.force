package cn.kancare.mobile.dao.advice;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class NutrientAdviceDao extends BaseDao<NutrientAdvice> {

	public NutrientAdviceDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "NutrientAdvice_DBKey";
	}

	public List<NutrientAdvice> queryByNutrientAdviceSummary_DBKey(
			String NutrientAdviceSummary_DBKey) throws SQLException {
		QueryBuilder<NutrientAdvice, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("NutrientAdviceSummary_DBKey",
				NutrientAdviceSummary_DBKey);
		return queryBuilder.query();
	}

}
