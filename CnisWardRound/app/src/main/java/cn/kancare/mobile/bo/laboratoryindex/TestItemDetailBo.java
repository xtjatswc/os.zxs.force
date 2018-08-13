package cn.kancare.mobile.bo.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.laboratoryindex.TestItemDetailDao;

import com.alibaba.fastjson.JSON;

public class TestItemDetailBo extends BaseBo<TestItemDetailDao> {

	public TestItemDetailBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new TestItemDetailDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<TestItemDetail> models = JSON.parseArray(json,
				TestItemDetail.class);

		for (TestItemDetail model : models) {
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
