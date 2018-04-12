package cn.kancare.mobile.bo;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.BodyAnalysisReportDao;

import com.alibaba.fastjson.JSON;

public class BodyAnalysisReportBo extends BaseBo<BodyAnalysisReportDao> {

	public BodyAnalysisReportBo(Activity context) throws Exception {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setDao() throws Exception {
		dao = new BodyAnalysisReportDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<BodyAnalysisReport> models = JSON.parseArray(json,
				BodyAnalysisReport.class);

		for (BodyAnalysisReport model : models) {
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
