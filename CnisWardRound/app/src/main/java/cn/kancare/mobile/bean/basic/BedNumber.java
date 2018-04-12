package cn.kancare.mobile.bean.basic;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bednumber")
public class BedNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// BedNumber_DBKey
	@DatabaseField(columnName = "BedNumber_DBKey",id=true)
	private int BedNumber_DBKey;

	// Department_DBKey 
	@DatabaseField(columnName = "Department_DBKey")
	private int Department_DBKey;

	// BedCode
	@DatabaseField(columnName = "BedCode")
	private String BedCode;

	// Bed
	@DatabaseField(columnName = "Bed")
	private String Bed;

	// Building
	@DatabaseField(columnName = "Building")
	private String Building;

	// Description
	@DatabaseField(columnName = "Description")
	private String Description;

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

	public int getBedNumber_DBKey() {
		return BedNumber_DBKey;
	}

	public void setBedNumber_DBKey(int BedNumber_DBKey) {
		this.BedNumber_DBKey = BedNumber_DBKey;
	}

	public int getDepartment_DBKey() {
		return Department_DBKey;
	}

	public void setDepartment_DBKey(int Department_DBKey) {
		this.Department_DBKey = Department_DBKey;
	}

	public String getBedCode() {
		return BedCode;
	}

	public void setBedCode(String BedCode) {
		this.BedCode = BedCode;
	}

	public String getBed() {
		return Bed;
	}

	public void setBed(String Bed) {
		this.Bed = Bed;
	}

	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String Building) {
		this.Building = Building;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
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
		return "bednumber [BedNumber_DBKey=" + BedNumber_DBKey
				+ ",Department_DBKey=" + Department_DBKey + ",BedCode="
				+ BedCode + ",Bed=" + Bed + ",Building=" + Building
				+ ",Description=" + Description + ",IsActive=" + IsActive
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",]";
	}
}
