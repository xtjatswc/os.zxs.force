package cn.kancare.mobile.dao.mealrecord;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.mealrecord.MealRecord;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class MealRecordDao extends BaseDao<MealRecord> {

	public MealRecordDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "MealRecord_DBKey";
	}

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<MealRecord> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<MealRecord, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}

		if (limit == 0 && offset == 0) {
			qBuilder.orderBy("MealDate", false);
		} else {
			qBuilder.limit(limit).offset(offset).orderBy("MealDate", false);
		}
		return qBuilder.query();

	}// ...other operations

	public MealRecord query(Date date, String PatientHospitalize_DBKey)
			throws SQLException {

		return dao.queryBuilder().limit(1).where().eq("MealDate", date).and()
				.eq("PatientHospitalize_DBKey", PatientHospitalize_DBKey)
				.queryForFirst();

	}
	
	public void UpdatePatientHospitalize_DBKey(
			int newPatientHospitalize_DBKey, int oldPatientHospitalize_DBKey)
			throws SQLException {

		dao.updateRaw(
				"update MealRecord set PatientHospitalize_DBKey = ? where PatientHospitalize_DBKey = ?",
				newPatientHospitalize_DBKey + "",
				oldPatientHospitalize_DBKey + "");

	}
}