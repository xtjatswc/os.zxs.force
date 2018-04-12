package cn.kancare.mobile.core.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.base.BaseDao;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.ExceptionUtil;
import os.zxs.force.core.util.HttpUtil;
import os.zxs.force.core.log.CnislogBo;

public abstract class BaseSyncHandle {
	protected CnislogBo log;

	// 按入院日期同步
	public String sdate;
	public String edate;

	// 同步前清空相关表数据
	public Boolean isClearData;

	/**
	 * 获取Activity的TAG
	 */
	protected abstract String getLogTag();

	/**
	 * 初始化Bo对象
	 */
	protected abstract void initializeBo() throws Exception;

	public abstract void doSync(DoSyncListener doSyncListener) throws Exception;

	public BaseSyncHandle(CnislogBo log) {
		try {
			this.log = log;

			initializeBo();
		} catch (Exception e) {
			doException(e);
		}
	}

	protected void doException(Exception e) {
		ExceptionUtil.doException(log, getLogTag(), e);
	}

	protected String getListJson(String requestUrlPart, String tableName,
			int limitStart, int limitSize) throws Exception {
		String url = "http://" + Global.WEB_REQUEST_IP + requestUrlPart
				+ "&limitStart=" + limitStart + "&limitSize=" + limitSize
				+ "&sdate=" + sdate + "&edate=" + edate;

		return HttpUtil.doHttpGet(url);
	}

	protected String getListCount(String requestUrlPart, String tableName)
			throws Exception {
		String url = "http://" + Global.WEB_REQUEST_IP + requestUrlPart
				+ "&sdate=" + sdate + "&edate=" + edate;

		return HttpUtil.doHttpGet(url);
	}

	protected String doUploadPost(String requestUrlPart, String TableName,
			String json) throws Exception {
		String url = "http://" + Global.WEB_REQUEST_IP + requestUrlPart;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uploadJsonData", json));
		return HttpUtil.doHttpPost(url, params);
	}

	// 上传
	protected void doUpload(String requestUrlPart, BaseBo baseBo,
			String TableName, DoSyncListener doSyncListener) throws Exception {
		// 分次上传
		int limitCount = baseBo.queryNeedUploadRecordsCount();
		int limitSize = 10;// 每次上传的记录条数

		// String json = baseBo.doUploadJson();
		// String result = doUploadPost(requestUrlPart, TableName, json);
		// baseBo.doUploadResult(result);
		
		for (int i = 0; i < limitCount; i += limitSize) {
			
			log.info(getLogTag(), "获取" + TableName + "需要上传的数据成功,limitStart=" + i
					+ ",limitSize=" + limitSize);

			try {
				//因为每次上传成功后，待上传记录条数都会减少，所以offset参数传0就行
				String json = baseBo.doUploadJson(limitSize, 0);//baseBo.doUploadJson(limitSize, i);
				String result = doUploadPost(requestUrlPart, TableName, json);
				baseBo.doUploadResult(result);
			} catch (Exception e) {
				doException(e);
			}

			log.info(getLogTag(), "完成转换并保存");

			int progress = (i + limitSize) * 100 / limitCount;
			doSyncListener.over(progress);
		}
	}

	// 下载
	protected void doDownload(String requestCountUrlPart,
			String requestListUrlPart, int limitCount, int limitSize,
			BaseBo baseBo, String TableName, DoSyncListener doSyncListener)
			throws Exception {

		// 如果是管理员模式，则删除表数据后，再下载
		if (Global.loginUser.getUser_DBKey() == 0) {
			if (isClearData) {
				BaseDao baseDao = (BaseDao) baseBo.getDao();
				baseDao.deleteAll();
			}
		}

		if (limitCount == 0) {
			limitCount = Convert.cash2Int(getListCount(requestCountUrlPart,
					TableName));
		}

		for (int i = 0; i < limitCount; i += limitSize) {
			String json = getListJson(requestListUrlPart, TableName, i,
					limitSize);

			log.info(getLogTag(), "获取" + TableName + "数据成功,limitStart=" + i
					+ ",limitSize=" + limitSize);

			try {
				baseBo.doDownloadJson(json);
			} catch (Exception e) {
				doException(e);
			}

			log.info(getLogTag(), "完成转换并保存");

			int progress = (i + limitSize) * 100 / limitCount;
			doSyncListener.over(progress);
		}
	}

}
