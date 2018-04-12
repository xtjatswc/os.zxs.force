package cn.kancare.mobile.bean.basic;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "department")
public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// Department_DBKey
	@DatabaseField(columnName = "Department_DBKey", id = true)
	private int Department_DBKey;

	// DepartmentCode
	@DatabaseField(columnName = "DepartmentCode")
	private String DepartmentCode;

	// DepartmentName
	@DatabaseField(columnName = "DepartmentName")
	private String DepartmentName;

	// DepartmentNameFirstLetter
	@DatabaseField(columnName = "DepartmentNameFirstLetter")
	private String DepartmentNameFirstLetter;

	// IsActive
	@DatabaseField(columnName = "IsActive")
	private String IsActive;

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

	public int getDepartment_DBKey() {
		return Department_DBKey;
	}

	public void setDepartment_DBKey(int Department_DBKey) {
		this.Department_DBKey = Department_DBKey;
	}

	public String getDepartmentCode() {
		return DepartmentCode;
	}

	public void setDepartmentCode(String DepartmentCode) {
		this.DepartmentCode = DepartmentCode;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String DepartmentName) {
		this.DepartmentName = DepartmentName;
	}

	public String getDepartmentNameFirstLetter() {
		return DepartmentNameFirstLetter;
	}

	public void setDepartmentNameFirstLetter(String DepartmentNameFirstLetter) {
		this.DepartmentNameFirstLetter = DepartmentNameFirstLetter;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
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

	@Override
	public String toString() {
		return "department [Department_DBKey=" + Department_DBKey
				+ ",DepartmentCode=" + DepartmentCode + ",DepartmentName="
				+ DepartmentName + ",DepartmentNameFirstLetter="
				+ DepartmentNameFirstLetter + ",IsActive=" + IsActive
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",]";
	}
}
