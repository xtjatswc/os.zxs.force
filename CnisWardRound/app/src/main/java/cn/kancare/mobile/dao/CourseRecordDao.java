package cn.kancare.mobile.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class CourseRecordDao extends BaseDao<CourseRecord> {

	public CourseRecordDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "CourseRecord_DBKey";
	}

	public List<CourseRecord> query(String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<CourseRecord, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.orderBy("CourseRecordDate", true);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<CourseRecord> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<CourseRecord, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.limit(limit).offset(offset)
				.orderBy("CourseRecordDate", false);
		return qBuilder.query();

	}// ...other operations
	
	public void UpdatePatientHospitalize_DBKey(
			int newPatientHospitalize_DBKey, int oldPatientHospitalize_DBKey)
			throws SQLException {

		dao.updateRaw(
				"update CourseRecord set PatientHospitalize_DBKey = ? where PatientHospitalize_DBKey = ?",
				newPatientHospitalize_DBKey + "",
				oldPatientHospitalize_DBKey + "");

	}
}