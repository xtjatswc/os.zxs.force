package cn.kancare.mobile.bo.basic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.core.base.BaseBo;
import os.zxs.force.core.util.spinner.SpinnerOption;
import cn.kancare.mobile.dao.basic.SysCodeDao;

import com.alibaba.fastjson.JSON;

public class SysCodeBo extends BaseBo<SysCodeDao> {

	public SysCodeBo(Activity context) throws Exception {
		super(context);
	}

	// public List<HashMap<String, Object>> getBednumberDataMap() {
	// List<HashMap<String, Object>> data = new ArrayList<HashMap<String,
	// Object>>();
	//
	// List<syscode> list = dao.query();
	// for (syscode model : list) {
	// HashMap<String, Object> item = new HashMap<String, Object>();
	// item.put("id", model.getBedNumber_DBKey());
	// item.put("code", model.getBedCode());
	// item.put("name", model.getBedCode());
	// item.put("firstletter", "");
	// data.add(item);
	// }
	//
	// return data;
	// }

	public void doDownloadJson(String json) throws SQLException {

		if (json == "")
			return;

		List<SysCode> models = JSON.parseArray(json, SysCode.class);

		for (SysCode model : models) {
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

	public ArrayList<SpinnerOption> getSpinnerOptions(String typeName)
			throws SQLException {
		ArrayList<SpinnerOption> towns = new ArrayList<SpinnerOption>();
		List<SysCode> list = dao.query(typeName);
		for (SysCode code : list) {
			SpinnerOption option = new SpinnerOption(code.getSysCode(),
					code.getSysCodeName());
			towns.add(option);
		}
		return towns;
	}

	@Override
	protected void setDao() throws Exception {
		dao = new SysCodeDao(context);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}
}
