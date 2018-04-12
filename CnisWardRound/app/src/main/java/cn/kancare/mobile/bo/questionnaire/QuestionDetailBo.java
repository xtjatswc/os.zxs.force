package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.questionnaire.QuestionDetail;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.questionnaire.QuestionDetailDao;

import com.alibaba.fastjson.JSON;

public class QuestionDetailBo extends BaseBo<QuestionDetailDao> {

	public QuestionDetailBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws SQLException {
		dao = new QuestionDetailDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<QuestionDetail> models = JSON.parseArray(json,
				QuestionDetail.class);

		for (QuestionDetail model : models) {
			try {
				dao.create(model);
			} catch (Exception e) {
				try {
					dao.update(model);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}
}
