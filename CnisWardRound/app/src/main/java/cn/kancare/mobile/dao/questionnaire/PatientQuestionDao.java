package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.PatientQuestion;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

public class PatientQuestionDao extends BaseDao<PatientQuestion> {

	public PatientQuestionDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "PatientQuestion_DBKey";
	}

	public List<PatientQuestion> queryPatientQuestions(String PatientQuestionnaire_DBKey)
			throws SQLException {
		QueryBuilder<PatientQuestion, Integer> queryBuilder = dao
				.queryBuilder();
		queryBuilder.where().eq("PatientQuestionnaire_DBKey",
				PatientQuestionnaire_DBKey);
		return queryBuilder.query();
	}

	public void deleteByPatientQuestionnaire_DBKey(
			String PatientQuestionnaire_DBKey) throws SQLException {
		DeleteBuilder<PatientQuestion, Integer> deleteBuilder = dao
				.deleteBuilder();
		deleteBuilder.where().eq("PatientQuestionnaire_DBKey",
				PatientQuestionnaire_DBKey);
		deleteBuilder.delete();
	}

	public void UpdatePatientQuestionnaire_DBKey(
			int newPatientQuestionnaire_DBKey, int oldPatientQuestionnaire_DBKey)
			throws SQLException {
//		dao.updateBuilder()
//				.updateColumnValue("PatientQuestionnaire_DBKey",
//						newPatientQuestionnaire_DBKey)
//				.where()
//				.eq("PatientQuestionnaire_DBKey", oldPatientQuestionnaire_DBKey);

		dao.updateRaw(
				"update patientquestion set PatientQuestionnaire_DBKey = ? where PatientQuestionnaire_DBKey = ?",
				newPatientQuestionnaire_DBKey + "",
				oldPatientQuestionnaire_DBKey + "");

	}
}