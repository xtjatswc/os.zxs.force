package cn.kancare.mobile.bo.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.laboratoryindex.SearchPageConfig;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.laboratoryindex.SearchPageConfigDao;

import com.alibaba.fastjson.JSON;

public class SearchPageConfigBo extends BaseBo<SearchPageConfigDao> {

	public SearchPageConfigBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws SQLException {
		dao = new SearchPageConfigDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<SearchPageConfig> models = JSON.parseArray(json,
				SearchPageConfig.class);

		for (SearchPageConfig model : models) {
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
