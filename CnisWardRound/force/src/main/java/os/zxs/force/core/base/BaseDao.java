package os.zxs.force.core.base;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import os.zxs.force.common.db.DatabaseHelper;
import os.zxs.force.common.constant.SyncConstant;

/**
 * @author Administrator Dao基类
 * @param <Bean>
 *        
 */
public abstract class BaseDao<Bean> {
	protected Context context;
	protected DatabaseHelper helper;
	protected Dao<Bean, Integer> dao;
	//得到主键列名
	protected abstract String getPrimaryKey();

	protected BaseDao(Context context) throws SQLException {
		this.context = context;
		helper = DatabaseHelper.getHelper(context);

		// 得到泛型T的class
		Class<Bean> beanClass = (Class<Bean>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		dao = helper.getDao(beanClass);
	}
	
	public Dao<Bean, Integer> getOrmliteDao() {
		return dao;
	}

	/**
	 * 创建一条记录
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void create(Bean model) throws SQLException {
		dao.create(model);

	}

	/**
	 * 修改一条记录
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void update(Bean model) throws SQLException {

		dao.update(model);

	}
	
	/**
	 * 删除一条记录
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void deleteById(int id) throws SQLException {
		dao.deleteById(id);
		// DeleteBuilder<T, Integer> dBuilder = dao.deleteBuilder();
		// dBuilder.where().eq(getPrimaryKey(), id);
		// dBuilder.delete();

	}
	
	/**
	 * 删除一条记录
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void deleteById(String id) throws SQLException {
		//dao.deleteById(id);
		 DeleteBuilder<Bean, Integer> dBuilder = dao.deleteBuilder();
		 dBuilder.where().eq(getPrimaryKey(), id);
		 dBuilder.delete();

	}

	/**
	 * @param id
	 * @return 查询一条记录
	 * @throws SQLException
	 */
	public Bean queryForId(int id) throws SQLException {
		return dao.queryForId(id);
		// return dao.queryBuilder().where().eq(getPrimaryKey(), id)
		// .queryForFirst();

	}
	
	/**
	 * @param id
	 * @return 查询一条记录
	 * @throws SQLException
	 */
	public Bean queryForId(String id) throws SQLException {
		//return dao.queryForId(id);
		 return dao.queryBuilder().where().eq(getPrimaryKey(), id)
		 .queryForFirst();

	}
	
	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<Bean> queryForAll() throws SQLException {
		return dao.queryForAll();
	}
	

	/**获取最新一条记录
	 * @return
	 * @throws Exception
	 */
	public Bean getLatest() throws SQLException {
		Bean model = dao.queryBuilder().orderBy(getPrimaryKey(), false).limit(1).queryForFirst();
		return model;
	}
	
	/**删除所有记录
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException {
		dao.deleteBuilder().delete();
	}

	/**
	 * 查询需要上传的记录行数
	 *
	 * @return
	 * @throws SQLException
	 */
	public long queryNeedUploadRecordsCount() throws SQLException {
		QueryBuilder<Bean, Integer> qBuilder = dao.queryBuilder();
		qBuilder.where().ne("OperateFlag", SyncConstant.OperateFlag.NONE);
		qBuilder.orderBy(getPrimaryKey(), true);
		return qBuilder.countOf();
	}

	/**
	 * 查询需要上传的记录
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<Bean> queryNeedUploadRecords(int limit, int offset) throws SQLException {
		QueryBuilder<Bean, Integer> qBuilder = dao.queryBuilder();
		qBuilder.where().ne("OperateFlag", SyncConstant.OperateFlag.NONE);
		qBuilder.limit(limit).offset(offset);
		qBuilder.orderBy(getPrimaryKey(), true);
		return qBuilder.query();
	}
}
