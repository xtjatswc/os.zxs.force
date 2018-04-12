package cn.kancare.mobile.bo.basic;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.Setting;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.basic.SettingDao;

public class SettingBo extends BaseBo<SettingDao> {

	public SettingBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new SettingDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		// TODO Auto-generated method stub

	}

	public void saveSetting(String settingName, String settingValue)
			throws Exception {
		Setting setting = dao.querySetting(settingName);
		if (setting == null) {
			setting = new Setting();
			setting.setSettingName(settingName);
			setting.setSettingValue(settingValue);
			dao.create(setting);
		} else {
			setting.setSettingValue(settingValue);
			dao.update(setting);
		}
	}

	public String getSetting(String settingName) throws Exception {
		Setting setting = dao.querySetting(settingName);
		if (setting != null)
			return setting.getSettingValue();

		return "";
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}
}
