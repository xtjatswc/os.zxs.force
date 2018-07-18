package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class PatientQuestionnaireDao extends BaseDao<PatientQuestionnaire> {

	public PatientQuestionnaireDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "PatientQuestionnaire_DBKey";
	}

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<PatientQuestionnaire> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<PatientQuestionnaire, Integer> qBuilder = dao
				.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.limit(limit).offset(offset).orderBy("ScreeningDate", false)
				.orderBy("QuestionProperty", true);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<PatientQuestionnaire> query(int limit, int offset,
			String PatientHospitalize_DBKey, int QuestionProperty)
			throws Exception {

		QueryBuilder<PatientQuestionnaire, Integer> qBuilder = dao
				.queryBuilder();
		Where<PatientQuestionnaire, Integer> where = qBuilder.where();
		
		if (!PatientHospitalize_DBKey.equals("")) {
			where.eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}

		if (QuestionProperty != 0) {
			where.and().eq("QuestionProperty", QuestionProperty);
		}

		qBuilder.limit(limit).offset(offset).orderBy("ScreeningDate", false)
				.orderBy("QuestionProperty", true);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<PatientQuestionnaire> query(String PatientHospitalize_DBKey)
			throws Exception {

		QueryBuilder<PatientQuestionnaire, Integer> qBuilder = dao
				.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.orderBy("ScreeningDate", true);
		return qBuilder.query();

	}// ...other operations

	/**
	 * 获取某个患者最新的且当前体重不为空的一条记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public PatientQuestionnaire getLatest(String PatientHospitalize_DBKey)
			throws SQLException {
		QueryBuilder<PatientQuestionnaire, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("PatientHospitalize_DBKey",
				PatientHospitalize_DBKey).and().ne("WeightNow", 0);
		return queryBuilder.orderBy("ScreeningDate", false).limit(1)
				.queryForFirst();
	}

	public void UpdatePatientHospitalize_DBKey(int newPatientHospitalize_DBKey,
			int oldPatientHospitalize_DBKey) throws SQLException {

		dao.updateRaw(
				"update PatientQuestionnaire set PatientHospitalize_DBKey = ? where PatientHospitalize_DBKey = ?",
				newPatientHospitalize_DBKey + "", oldPatientHospitalize_DBKey
						+ "");

	}
}