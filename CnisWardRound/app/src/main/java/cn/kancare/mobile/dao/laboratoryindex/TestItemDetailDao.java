package cn.kancare.mobile.dao.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class TestItemDetailDao extends BaseDao<TestItemDetail> {

	public TestItemDetailDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "TestItemDetail_DBKey";
	}

	@Override
	public List<TestItemDetail> queryForAll() throws SQLException {
		QueryBuilder<TestItemDetail, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.orderBy("TestItemCode", true);
		return queryBuilder.query();
	}

}