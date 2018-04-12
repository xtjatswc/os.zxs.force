package cn.kancare.mobile.bo.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.BedNumber;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.basic.BedNumberDao;

import com.alibaba.fastjson.JSON;

public class BedNumberBo extends BaseBo<BedNumberDao> {

	public BedNumberBo(Activity context) throws Exception {
		super(context);
	}

	public List<HashMap<String, Object>> getBednumberDataMap() throws Exception {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		List<BedNumber> list = dao.queryForAll();

		for (BedNumber model : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", model.getBedNumber_DBKey());
			item.put("code", model.getBedCode());
			item.put("name", model.getBedCode());
			item.put("firstletter", "");
			data.add(item);
		}

		return data;
	}

	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<BedNumber> models = JSON.parseArray(json, BedNumber.class);

		for (BedNumber model : models) {
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
		dao = new BedNumberDao(context);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}
}
