package cn.kancare.mobile.bean.mealrecord;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "relationofdietaryfood")
public class RelationOfDietaryFood extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// RelationOfDietaryFood_DBKey
	@DatabaseField(columnName = "RelationOfDietaryFood_DBKey", id = true)
	private String RelationOfDietaryFood_DBKey;

	// MealRecord_DBKey
	@DatabaseField(columnName = "MealRecord_DBKey")
	private String MealRecord_DBKey;

	// ChinaFoodComposition_DBKey
	@DatabaseField(columnName = "ChinaFoodComposition_DBKey")
	private int ChinaFoodComposition_DBKey;

	// RecipeAndProduct_DBKey
	@DatabaseField(columnName = "RecipeAndProduct_DBKey")
	private int RecipeAndProduct_DBKey;

	// SysCode
	@DatabaseField(columnName = "SysCode")
	private String SysCode;

	// RodType
	@DatabaseField(columnName = "RodType")
	private String RodType;

	// MealAmount
	@DatabaseField(columnName = "MealAmount")
	private Double MealAmount;

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

	public String getRelationOfDietaryFood_DBKey() {
		return RelationOfDietaryFood_DBKey;
	}

	public void setRelationOfDietaryFood_DBKey(String RelationOfDietaryFood_DBKey) {
		this.RelationOfDietaryFood_DBKey = RelationOfDietaryFood_DBKey;
	}

	public String getMealRecord_DBKey() {
		return MealRecord_DBKey;
	}

	public void setMealRecord_DBKey(String MealRecord_DBKey) {
		this.MealRecord_DBKey = MealRecord_DBKey;
	}

	public int getChinaFoodComposition_DBKey() {
		return ChinaFoodComposition_DBKey;
	}

	public void setChinaFoodComposition_DBKey(int ChinaFoodComposition_DBKey) {
		this.ChinaFoodComposition_DBKey = ChinaFoodComposition_DBKey;
	}

	public int getRecipeAndProduct_DBKey() {
		return RecipeAndProduct_DBKey;
	}

	public void setRecipeAndProduct_DBKey(int RecipeAndProduct_DBKey) {
		this.RecipeAndProduct_DBKey = RecipeAndProduct_DBKey;
	}

	public String getSysCode() {
		return SysCode;
	}

	public void setSysCode(String SysCode) {
		this.SysCode = SysCode;
	}

	public String getRodType() {
		return RodType;
	}

	public void setRodType(String RodType) {
		this.RodType = RodType;
	}

	public Double getMealAmount() {
		return MealAmount;
	}

	public void setMealAmount(Double MealAmount) {
		this.MealAmount = MealAmount;
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
		return "relationofdietaryfood [RelationOfDietaryFood_DBKey="
				+ RelationOfDietaryFood_DBKey + ",MealRecord_DBKey="
				+ MealRecord_DBKey + ",ChinaFoodComposition_DBKey="
				+ ChinaFoodComposition_DBKey + ",RecipeAndProduct_DBKey="
				+ RecipeAndProduct_DBKey + ",SysCode=" + SysCode + ",RodType="
				+ RodType + ",MealAmount=" + MealAmount + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
