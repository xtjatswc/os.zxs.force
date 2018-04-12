package cn.kancare.mobile.bo.questionnaire;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.questionnaire.OptionDetailDao;

import com.alibaba.fastjson.JSON;

public class OptionDetailBo extends BaseBo<OptionDetailDao> {

	public OptionDetailBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws SQLException {
		dao = new OptionDetailDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<OptionDetail> models = JSON.parseArray(json, OptionDetail.class);

		for (OptionDetail model : models) {
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
