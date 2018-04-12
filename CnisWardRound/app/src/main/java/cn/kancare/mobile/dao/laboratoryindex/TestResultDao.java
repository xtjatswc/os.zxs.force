package cn.kancare.mobile.dao.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

public class TestResultDao extends BaseDao<TestResult> {

	public TestResultDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "TestResult_DBKey";
	}

	public void deleteByLaboratoryIndex_DBKey(String LaboratoryIndex_DBKey)
			throws SQLException {
		DeleteBuilder<TestResult, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where()
				.eq("LaboratoryIndex_DBKey", LaboratoryIndex_DBKey);
		deleteBuilder.delete();
	}

	public List<TestResult> query(String LaboratoryIndex_DBKey)
			throws SQLException {
		QueryBuilder<TestResult, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("LaboratoryIndex_DBKey", LaboratoryIndex_DBKey);
		queryBuilder.orderBy("TestItemCode", true);
		return queryBuilder.query();
	}

	public TestResult query(String LaboratoryIndex_DBKey, int TestItemDetail_DBKey)
			throws SQLException {
		QueryBuilder<TestResult, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("LaboratoryIndex_DBKey", LaboratoryIndex_DBKey)
				.and().eq("TestItemDetail_DBKey", TestItemDetail_DBKey);
		// queryBuilder.orderBy("LaboratoryIndex_DBKey", true);
		return queryBuilder.queryForFirst();
	}

	public void UpdateLaboratoryIndex_DBKey(int newLaboratoryIndex_DBKey,
			int oldLaboratoryIndex_DBKey) throws SQLException {

		dao.updateRaw(
				"update testresult set LaboratoryIndex_DBKey = ? where LaboratoryIndex_DBKey = ?",
				newLaboratoryIndex_DBKey + "", oldLaboratoryIndex_DBKey + "");

	}
}