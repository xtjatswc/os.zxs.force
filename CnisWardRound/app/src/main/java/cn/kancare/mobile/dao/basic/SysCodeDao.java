package cn.kancare.mobile.dao.basic;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class SysCodeDao extends BaseDao<SysCode>{


	public SysCodeDao(Context context) throws SQLException {
		super(context);
	}

	/**
	 * 查询syscode
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<SysCode> query(String typeName) throws SQLException {

		QueryBuilder<SysCode, Integer> qBuilder = dao.queryBuilder();
		qBuilder.where().eq("SystemCodeTypeName", typeName);
		qBuilder.orderBy("OrderBy", true);
		return qBuilder.query();

	}
	
	/**
	 * 查询syscode
	 * 
	 * @return
	 * @throws SQLException
	 */
	public SysCode queryBySysCode(String SystemCodeTypeName, String sysCode) throws SQLException {

		QueryBuilder<SysCode, Integer> qBuilder = dao.queryBuilder();
		qBuilder.where().eq("SystemCodeTypeName", SystemCodeTypeName).and().eq("SysCode", sysCode);
	
		return qBuilder.queryForFirst();

	}

	@Override
	protected String getPrimaryKey() {
		return "SystemCode_DBKEY";
	}
}