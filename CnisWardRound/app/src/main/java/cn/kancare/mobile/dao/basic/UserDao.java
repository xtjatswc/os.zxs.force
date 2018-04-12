package cn.kancare.mobile.dao.basic;

import java.sql.SQLException;

import android.content.Context;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.core.base.BaseDao;

public class UserDao extends BaseDao<User>{


	public UserDao(Context context) throws SQLException {
		super(context);

	}



	/**
	 * 查询一个用户
	 * 
	 * @param User
	 * @throws SQLException
	 */
	public User queryByLoginID(String userLoginID) throws SQLException {

		return dao.queryBuilder().where().eq("UserLoginID", userLoginID)
				.queryForFirst();

	}// ...other operations

	@Override
	protected String getPrimaryKey() {
		return "User_DBKey";
	}

}