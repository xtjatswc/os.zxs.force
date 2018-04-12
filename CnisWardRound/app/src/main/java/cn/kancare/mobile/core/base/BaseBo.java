package cn.kancare.mobile.core.base;

import android.app.Activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

import os.zxs.force.core.util.Convert;

public abstract class BaseBo<Dao> {
	protected Dao dao;
	protected Activity context;

	public BaseBo(Activity context) throws Exception {
		this.context = context;
		setDao();
	}

	protected abstract void setDao() throws Exception;

	// 处理数据下载json
	public abstract void doDownloadJson(String json) throws Exception;

	// 获取上传数据json
	// public abstract String doUploadJson() throws Exception ;
	// 处理上传数据结果
	public abstract void doUploadResult(String json) throws Exception;

	public Dao getDao() {
		return dao;
	}

	//获取需要上传的记录行数
	public int queryNeedUploadRecordsCount() throws Exception {
		return Convert.cash2Int(((BaseDao) dao).queryNeedUploadRecordsCount());
	}

	public String doUploadJson(int limit, int offset) throws Exception {

		List<BaseBean> lst = ((BaseDao) dao).queryNeedUploadRecords(limit, offset);

		String json = JSON.toJSONString(lst);

		return json;
	}
}
