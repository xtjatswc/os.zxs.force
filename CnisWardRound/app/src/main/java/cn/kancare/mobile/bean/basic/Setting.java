package cn.kancare.mobile.bean.basic;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "setting")
public class Setting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 572962840753629744L;

	// id
	@DatabaseField(columnName = "id", generatedId=true)
	private int id;

	// SettingName
	@DatabaseField(columnName = "SettingName")
	private String SettingName;

	// SettingValue
	@DatabaseField(columnName = "SettingValue")
	private String SettingValue;

	public String getSettingName() {
		return SettingName;
	}

	public void setSettingName(String settingName) {
		SettingName = settingName;
	}

	public String getSettingValue() {
		return SettingValue;
	}

	public void setSettingValue(String settingValue) {
		SettingValue = settingValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
