package cn.kancare.mobile.bean.basic;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "syscode")
public class SysCode implements Serializable {
	/**
	 *  
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// SystemCode_DBKEY
	@DatabaseField(columnName = "SystemCode_DBKEY",id=true)
	private int SystemCode_DBKEY;

	// SystemCodeTypeName
	@DatabaseField(columnName = "SystemCodeTypeName")
	private String SystemCodeTypeName;

	// SystemCodeTypeCode
	@DatabaseField(columnName = "SystemCodeTypeCode")
	private String SystemCodeTypeCode;

	// SysCode
	@DatabaseField(columnName = "SysCode")
	private String SysCode;

	// SysCodeName
	@DatabaseField(columnName = "SysCodeName")
	private String SysCodeName;

	// SysCodeShortName
	@DatabaseField(columnName = "SysCodeShortName")
	private String SysCodeShortName;

	// OrderBy
	@DatabaseField(columnName = "OrderBy")
	private String OrderBy;

	// IsActive
	@DatabaseField(columnName = "IsActive")
	private String IsActive;

	// EditType
	@DatabaseField(columnName = "EditType")
	private String EditType;

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

	public int getSystemCode_DBKEY() {
		return SystemCode_DBKEY;
	}

	public void setSystemCode_DBKEY(int SystemCode_DBKEY) {
		this.SystemCode_DBKEY = SystemCode_DBKEY;
	}

	public String getSystemCodeTypeName() {
		return SystemCodeTypeName;
	}

	public void setSystemCodeTypeName(String SystemCodeTypeName) {
		this.SystemCodeTypeName = SystemCodeTypeName;
	}

	public String getSystemCodeTypeCode() {
		return SystemCodeTypeCode;
	}

	public void setSystemCodeTypeCode(String SystemCodeTypeCode) {
		this.SystemCodeTypeCode = SystemCodeTypeCode;
	}

	public String getSysCode() {
		return SysCode;
	}

	public void setSysCode(String SysCode) {
		this.SysCode = SysCode;
	}

	public String getSysCodeName() {
		return SysCodeName;
	}

	public void setSysCodeName(String SysCodeName) {
		this.SysCodeName = SysCodeName;
	}

	public String getSysCodeShortName() {
		return SysCodeShortName;
	}

	public void setSysCodeShortName(String SysCodeShortName) {
		this.SysCodeShortName = SysCodeShortName;
	}

	public String getOrderBy() {
		return OrderBy;
	}

	public void setOrderBy(String OrderBy) {
		this.OrderBy = OrderBy;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
	}

	public String getEditType() {
		return EditType;
	}

	public void setEditType(String EditType) {
		this.EditType = EditType;
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
		return "syscode [SystemCode_DBKEY=" + SystemCode_DBKEY
				+ ",SystemCodeTypeName=" + SystemCodeTypeName
				+ ",SystemCodeTypeCode=" + SystemCodeTypeCode + ",SysCode="
				+ SysCode + ",SysCodeName=" + SysCodeName
				+ ",SysCodeShortName=" + SysCodeShortName + ",OrderBy="
				+ OrderBy + ",IsActive=" + IsActive + ",EditType=" + EditType
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",]";
	}
}
