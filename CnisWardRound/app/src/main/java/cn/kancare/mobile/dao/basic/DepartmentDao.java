package cn.kancare.mobile.dao.basic;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class DepartmentDao extends BaseDao<Department> {

	public DepartmentDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "Department_DBKey";
	}

	@Override
	public List<Department> queryForAll() throws SQLException {
		QueryBuilder<Department, Integer> queryBuilder = dao.queryBuilder();
		Where<Department, Integer> where = queryBuilder.where();
		where.eq("IsActive", "1");
		queryBuilder.orderBy("DepartmentName", true);
		return queryBuilder.query();
	}
}