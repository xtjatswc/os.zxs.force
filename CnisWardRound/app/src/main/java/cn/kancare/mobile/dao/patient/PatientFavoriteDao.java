package cn.kancare.mobile.dao.patient;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.patient.PatientFavorite;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.DeleteBuilder;

public class PatientFavoriteDao extends BaseDao<PatientFavorite> {

	public PatientFavoriteDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "PKey";
	}

	public PatientFavorite queryForId(String PKey) throws SQLException {
		return dao.queryBuilder().where().eq("PKey", PKey).queryForFirst();
	}

	public void deleteById(String PKey) throws SQLException {
		DeleteBuilder<PatientFavorite, Integer> deleteBuilder = dao
				.deleteBuilder();
		deleteBuilder.where().eq("PKey", PKey);
		deleteBuilder.delete();
	}

	public List<PatientFavorite> queryByUserDBkey() throws SQLException {

		return dao.queryBuilder().where()
				.eq("User_DBKey", Global.loginUser.getUser_DBKey()).query();
	}
}
