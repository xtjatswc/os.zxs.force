package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.questionnaire.QuestionDetailType;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.questionnaire.QuestionDetailTypeDao;

import com.alibaba.fastjson.JSON;

public class QuestionDetailTypeBo extends BaseBo<QuestionDetailTypeDao> {

	public QuestionDetailTypeBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new QuestionDetailTypeDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<QuestionDetailType> models = JSON.parseArray(json,
				QuestionDetailType.class);

		for (QuestionDetailType model : models) {
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
