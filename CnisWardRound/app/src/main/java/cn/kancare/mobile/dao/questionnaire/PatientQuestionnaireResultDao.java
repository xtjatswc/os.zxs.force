package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaireResult;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

public class PatientQuestionnaireResultDao extends
		BaseDao<PatientQuestionnaireResult> {

	public PatientQuestionnaireResultDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "PatientQuestionnaireResult_DBKey";
	}

	public List<PatientQuestionnaireResult> query(String PatientQuestion_DBKey)
			throws SQLException {
		QueryBuilder<PatientQuestionnaireResult, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("PatientQuestion_DBKey", PatientQuestion_DBKey);
		return queryBuilder.query();
	}

	public void deleteByPatientQuestiion_DBKey(String PatientQuestiion_DBKey)
			throws SQLException {
		DeleteBuilder<PatientQuestionnaireResult, Integer> deleteBuilder = dao
				.deleteBuilder();
		deleteBuilder.where().eq("PatientQuestion_DBKey",
				PatientQuestiion_DBKey);
		deleteBuilder.delete();
	}

	public void UpdatePatientQuestion_DBKey(int newPatientQuestion_DBKey,
			int oldPatientQuestion_DBKey) throws SQLException {
		UpdateBuilder<PatientQuestionnaireResult, Integer> updateBuilder = dao
				.updateBuilder();
		updateBuilder
				.updateColumnValue("PatientQuestion_DBKey",
						newPatientQuestion_DBKey).where()
				.eq("PatientQuestion_DBKey", oldPatientQuestion_DBKey);

		dao.updateRaw(
				"update patientquestionnaireresult set PatientQuestion_DBKey = ? where PatientQuestion_DBKey = ?",
				newPatientQuestion_DBKey + "", oldPatientQuestion_DBKey + "");

	}
}