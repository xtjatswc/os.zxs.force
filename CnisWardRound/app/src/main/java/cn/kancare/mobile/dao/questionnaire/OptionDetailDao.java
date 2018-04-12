package cn.kancare.mobile.dao.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class OptionDetailDao extends BaseDao<OptionDetail> {

	public OptionDetailDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "QuestionOption_DBKey";
	}

	public List<OptionDetail> query(int QuestionnaireQuestion_DBKey)
			throws SQLException {
		QueryBuilder<OptionDetail, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("QuestionnaireQuestion_DBKey",
				QuestionnaireQuestion_DBKey);
		queryBuilder.orderBy("OptionOrderIndex", true);
		return queryBuilder.query();
	}
}