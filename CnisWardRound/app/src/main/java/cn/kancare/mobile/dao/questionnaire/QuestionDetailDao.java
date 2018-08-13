package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.QuestionDetail;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class QuestionDetailDao extends BaseDao<QuestionDetail> {

	public QuestionDetailDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "QuestionnaireQuestion_DBKey";
	}

	public List<QuestionDetail> query(int QuestionProperty) throws SQLException {
		QueryBuilder<QuestionDetail, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("QuestionProperty", QuestionProperty);
		queryBuilder.orderBy("QuestionnaireQuestion_DBKey", true);
		return queryBuilder.query();
	}
}