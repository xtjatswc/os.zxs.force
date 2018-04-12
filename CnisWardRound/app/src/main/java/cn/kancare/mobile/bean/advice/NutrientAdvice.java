package cn.kancare.mobile.bean.advice;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nutrientadvice")
public class NutrientAdvice extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// NutrientAdvice_DBKey
	@DatabaseField(columnName = "NutrientAdvice_DBKey", id = true)
	private String NutrientAdvice_DBKey;

	// NutrientAdviceSummary_DBKey
	@DatabaseField(columnName = "NutrientAdviceSummary_DBKey")
	private String NutrientAdviceSummary_DBKey;

	// AdviceDate
	@DatabaseField(columnName = "AdviceDate")
	private String AdviceDate;

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

	public String getNutrientAdvice_DBKey() {
		return NutrientAdvice_DBKey;
	}

	public void setNutrientAdvice_DBKey(String NutrientAdvice_DBKey) {
		this.NutrientAdvice_DBKey = NutrientAdvice_DBKey;
	}

	public String getNutrientAdviceSummary_DBKey() {
		return NutrientAdviceSummary_DBKey;
	}

	public void setNutrientAdviceSummary_DBKey(String NutrientAdviceSummary_DBKey) {
		this.NutrientAdviceSummary_DBKey = NutrientAdviceSummary_DBKey;
	}

	public String getAdviceDate() {
		return AdviceDate;
	}

	public void setAdviceDate(String AdviceDate) {
		this.AdviceDate = AdviceDate;
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
		return "nutrientadvice [NutrientAdvice_DBKey=" + NutrientAdvice_DBKey
				+ ",NutrientAdviceSummary_DBKey=" + NutrientAdviceSummary_DBKey
				+ ",AdviceDate=" + AdviceDate + ",CreateBy=" + CreateBy
				+ ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
