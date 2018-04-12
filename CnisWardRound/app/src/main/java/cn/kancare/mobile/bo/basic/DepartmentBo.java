package cn.kancare.mobile.bo.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.core.base.BaseBo;
import os.zxs.force.core.util.spinner.SpinnerOption;
import cn.kancare.mobile.dao.basic.DepartmentDao;

import com.alibaba.fastjson.JSON;

public class DepartmentBo extends BaseBo<DepartmentDao> {

	public DepartmentBo(Activity context) throws Exception {
		super(context);
	}

	public List<SpinnerOption> getDepartmentOptions() throws Exception {
		List<Department> list = dao.queryForAll();
		List<SpinnerOption> lstSpinnerOptions = new ArrayList<SpinnerOption>();
		lstSpinnerOptions.add(new SpinnerOption("", "全部科室"));

		for (Department model : list) {
			SpinnerOption spinnerOption = new SpinnerOption(
					model.getDepartment_DBKey() + "", model.getDepartmentName());

			lstSpinnerOptions.add(spinnerOption);
		}

		return lstSpinnerOptions;
	}

	public List<HashMap<String, Object>> getDepartmentDataMap()
			throws Exception {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		List<Department> list = dao.queryForAll();
		for (Department model : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", model.getDepartment_DBKey());
			item.put("code", model.getDepartmentCode());
			item.put("name", model.getDepartmentName());
			item.put("firstletter", model.getDepartmentNameFirstLetter());
			data.add(item);
		}

		return data;
	}

	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<Department> models = JSON.parseArray(json, Department.class);

		for (Department model : models) {
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
	protected void setDao() throws Exception {
		dao = new DepartmentDao(context);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}
}
