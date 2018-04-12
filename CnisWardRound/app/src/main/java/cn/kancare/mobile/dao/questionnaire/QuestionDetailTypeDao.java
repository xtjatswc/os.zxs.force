package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.QuestionDetailType;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class QuestionDetailTypeDao extends BaseDao<QuestionDetailType>{

	public QuestionDetailTypeDao(Context context) throws SQLException {
		super(context);
	}	

	@Override
	protected String getPrimaryKey() {		
		return "tid";
	}
	
	public QuestionDetailType queryQuestionDetailType(int questionProperty) throws SQLException {
		QueryBuilder<QuestionDetailType, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("propertyValue", questionProperty);
		return queryBuilder.limit(1).queryForFirst();
	}
}