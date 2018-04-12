package cn.kancare.mobile.bean.mealrecord;

import java.util.Date;

import cn.kancare.mobile.core.base.BaseBean;
import os.zxs.force.core.util.DateHelper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mealrecord")
public class MealRecord extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// MealRecord_DBKey
	@DatabaseField(columnName = "MealRecord_DBKey", id = true)
	private String MealRecord_DBKey;

	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// MealRecordNo
	@DatabaseField(columnName = "MealRecordNo")
	private String MealRecordNo;

	// MealDate
	@DatabaseField(columnName = "MealDate")
	private Date MealDate;

	// CreateBy
	@DatabaseField(columnName = "CreateBy")
	private String CreateBy;

	// CreateTime
	@DatabaseField(columnName = "CreateTime")
	private String CreateTime;

	// CreateProgram
	@DatabaseField(columnName = "CreateProgram")
	private String CreateProgram;

	// CreateIP
	@DatabaseField(columnName = "CreateIP")
	private String CreateIP;

	// UpdateBy
	@DatabaseField(columnName = "UpdateBy")
	private String UpdateBy;

	// UpdateTime
	@DatabaseField(columnName = "UpdateTime")
	private String UpdateTime;

	// UpdateProgram
	@DatabaseField(columnName = "UpdateProgram")
	private String UpdateProgram;

	// UpdateIP
	@DatabaseField(columnName = "UpdateIP")
	private String UpdateIP;

	// pageFlg
	@DatabaseField(columnName = "pageFlg")
	private int pageFlg;

	public String getMealRecord_DBKey() {
		return MealRecord_DBKey;
	}

	public void setMealRecord_DBKey(String MealRecord_DBKey) {
		this.MealRecord_DBKey = MealRecord_DBKey;
	}

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String PatientHospitalize_DBKey) {
		this.PatientHospitalize_DBKey = PatientHospitalize_DBKey;
	}

	public String getMealRecordNo() {
		return MealRecordNo;
	}

	public void setMealRecordNo(String MealRecordNo) {
		this.MealRecordNo = MealRecordNo;
	}

	public Date getMealDate() {
		return MealDate;
	}

	public void setMealDate(Date MealDate) {
		this.MealDate = MealDate;
	}

	public String getMealDateString() {
		return DateHelper.getInstance().getDataString_2(MealDate);
	}

	public void setMealDateString(String MealDate) {

	}

	public String getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(String CreateBy) {
		this.CreateBy = CreateBy;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getCreateProgram() {
		return CreateProgram;
	}

	public void setCreateProgram(String CreateProgram) {
		this.CreateProgram = CreateProgram;
	}

	public String getCreateIP() {
		return CreateIP;
	}

	public void setCreateIP(String CreateIP) {
		this.CreateIP = CreateIP;
	}

	public String getUpdateBy() {
		return UpdateBy;
	}

	public void setUpdateBy(String UpdateBy) {
		this.UpdateBy = UpdateBy;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	public String getUpdateProgram() {
		return UpdateProgram;
	}

	public void setUpdateProgram(String UpdateProgram) {
		this.UpdateProgram = UpdateProgram;
	}

	public String getUpdateIP() {
		return UpdateIP;
	}

	public void setUpdateIP(String UpdateIP) {
		this.UpdateIP = UpdateIP;
	}

	public int getPageFlg() {
		return pageFlg;
	}

	public void setPageFlg(int pageFlg) {
		this.pageFlg = pageFlg;
	}

	@Override
	public String toString() {
		return "mealrecord [MealRecord_DBKey=" + MealRecord_DBKey
				+ ",PatientHospitalize_DBKey=" + PatientHospitalize_DBKey
				+ ",MealRecordNo=" + MealRecordNo + ",MealDate=" + MealDate
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",pageFlg=" + pageFlg + ",]";
	}
}
