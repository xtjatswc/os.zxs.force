package os.zxs.force.core.base;

import android.app.Activity;

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

}
