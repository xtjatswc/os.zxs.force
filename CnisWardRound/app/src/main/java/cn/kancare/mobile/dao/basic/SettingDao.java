package cn.kancare.mobile.dao.basic;

import java.sql.SQLException;

import android.content.Context;
import cn.kancare.mobile.bean.basic.Setting;
import cn.kancare.mobile.core.base.BaseDao;

public class SettingDao extends BaseDao<Setting> {

	public SettingDao(Context context) throws SQLException {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "SettingName";
	}

	public Setting querySetting(String settingName) throws SQLException {
		return dao.queryBuilder().limit(1).where()
				.eq("SettingName", settingName).queryForFirst();
	}
}