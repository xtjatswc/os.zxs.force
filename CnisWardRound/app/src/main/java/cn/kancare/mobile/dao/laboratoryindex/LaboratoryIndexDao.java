package cn.kancare.mobile.dao.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class LaboratoryIndexDao extends BaseDao<LaboratoryIndex> {

	public LaboratoryIndexDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "LaboratoryIndex_DBKey";
	}

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<LaboratoryIndex> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<LaboratoryIndex, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.limit(limit).offset(offset).orderBy("TestTime", false);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<LaboratoryIndex> query(String PatientHospitalize_DBKey)
			throws Exception {

		QueryBuilder<LaboratoryIndex, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.orderBy("TestTime", true);
		return qBuilder.query();

	}// ...other operations

	public void UpdatePatientHospitalize_DBKey(int newPatientHospitalize_DBKey,
			int oldPatientHospitalize_DBKey) throws SQLException {

		dao.updateRaw(
				"update LaboratoryIndex set PatientHospitalize_DBKey = ? where PatientHospitalize_DBKey = ?",
				newPatientHospitalize_DBKey + "", oldPatientHospitalize_DBKey
						+ "");

	}
}
