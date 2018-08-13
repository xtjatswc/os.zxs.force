package cn.kancare.mobile.dao.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.laboratoryindex.SearchPageConfig;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class SearchPageConfigDao extends BaseDao<SearchPageConfig> {

	public SearchPageConfigDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "search_DBKEY";
	}

	@Override
	public List<SearchPageConfig> queryForAll() throws SQLException {
		QueryBuilder<SearchPageConfig, Integer> queryBuilder = dao.queryBuilder();
		return queryBuilder.query();
	}

}