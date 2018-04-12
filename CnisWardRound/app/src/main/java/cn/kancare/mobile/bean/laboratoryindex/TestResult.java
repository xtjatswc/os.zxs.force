package cn.kancare.mobile.bean.laboratoryindex;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "testresult")
public class TestResult extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// TestResult_DBKey
	@DatabaseField(columnName = "TestResult_DBKey", id = true)
	private String TestResult_DBKey;

	// LaboratoryIndex_DBKey
	@DatabaseField(columnName = "LaboratoryIndex_DBKey")
	private String LaboratoryIndex_DBKey;

	// TestItemDetail_DBKey
	@DatabaseField(columnName = "TestItemDetail_DBKey")
	private int TestItemDetail_DBKey;

	// TestResultNo
	@DatabaseField(columnName = "TestResultNo")
	private int TestResultNo;

	// TestItemCode
	@DatabaseField(columnName = "TestItemCode")
	private String TestItemCode;

	// TestItemName
	@DatabaseField(columnName = "TestItemName")
	private String TestItemName;

	// TestItemValue
	@DatabaseField(columnName = "TestItemValue")
	private String TestItemValue;

	// TestItemUnit
	@DatabaseField(columnName = "TestItemUnit")
	private String TestItemUnit;

	// TestItemMaxValue
	@DatabaseField(columnName = "TestItemMaxValue")
	private String TestItemMaxValue;

	// TestItemMinValue
	@DatabaseField(columnName = "TestItemMinValue")
	private String TestItemMinValue;

	// IsPositive
	@DatabaseField(columnName = "IsPositive")
	private String IsPositive;

	// IsOverMax
	@DatabaseField(columnName = "IsOverMax")
	private String IsOverMax;

	// IsOverMin
	@DatabaseField(columnName = "IsOverMin")
	private String IsOverMin;

	// TestMedium_DBKey
	@DatabaseField(columnName = "TestMedium_DBKey")
	private int TestMedium_DBKey;

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

	public String getTestResult_DBKey() {
		return TestResult_DBKey;
	}

	public void setTestResult_DBKey(String TestResult_DBKey) {
		this.TestResult_DBKey = TestResult_DBKey;
	}

	public String getLaboratoryIndex_DBKey() {
		return LaboratoryIndex_DBKey;
	}

	public void setLaboratoryIndex_DBKey(String LaboratoryIndex_DBKey) {
		this.LaboratoryIndex_DBKey = LaboratoryIndex_DBKey;
	}

	public int getTestItemDetail_DBKey() {
		return TestItemDetail_DBKey;
	}

	public void setTestItemDetail_DBKey(int TestItemDetail_DBKey) {
		this.TestItemDetail_DBKey = TestItemDetail_DBKey;
	}

	public int getTestResultNo() {
		return TestResultNo;
	}

	public void setTestResultNo(int TestResultNo) {
		this.TestResultNo = TestResultNo;
	}

	public String getTestItemCode() {
		return TestItemCode;
	}

	public void setTestItemCode(String TestItemCode) {
		this.TestItemCode = TestItemCode;
	}

	public String getTestItemName() {
		return TestItemName;
	}

	public void setTestItemName(String TestItemName) {
		this.TestItemName = TestItemName;
	}

	public String getTestItemValue() {
		return TestItemValue;
	}

	public void setTestItemValue(String TestItemValue) {
		this.TestItemValue = TestItemValue;
	}

	public String getTestItemUnit() {
		return TestItemUnit;
	}

	public void setTestItemUnit(String TestItemUnit) {
		this.TestItemUnit = TestItemUnit;
	}

	public String getTestItemMaxValue() {
		return TestItemMaxValue;
	}

	public void setTestItemMaxValue(String TestItemMaxValue) {
		this.TestItemMaxValue = TestItemMaxValue;
	}

	public String getTestItemMinValue() {
		return TestItemMinValue;
	}

	public void setTestItemMinValue(String TestItemMinValue) {
		this.TestItemMinValue = TestItemMinValue;
	}

	public String getIsPositive() {
		return IsPositive;
	}

	public void setIsPositive(String IsPositive) {
		this.IsPositive = IsPositive;
	}

	public String getIsOverMax() {
		return IsOverMax;
	}

	public void setIsOverMax(String IsOverMax) {
		this.IsOverMax = IsOverMax;
	}

	public String getIsOverMin() {
		return IsOverMin;
	}

	public void setIsOverMin(String IsOverMin) {
		this.IsOverMin = IsOverMin;
	}

	public int getTestMedium_DBKey() {
		return TestMedium_DBKey;
	}

	public void setTestMedium_DBKey(int TestMedium_DBKey) {
		this.TestMedium_DBKey = TestMedium_DBKey;
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
		return "testresult [TestResult_DBKey=" + TestResult_DBKey
				+ ",LaboratoryIndex_DBKey=" + LaboratoryIndex_DBKey
				+ ",TestItemDetail_DBKey=" + TestItemDetail_DBKey
				+ ",TestResultNo=" + TestResultNo + ",TestItemCode="
				+ TestItemCode + ",TestItemName=" + TestItemName
				+ ",TestItemValue=" + TestItemValue + ",TestItemUnit="
				+ TestItemUnit + ",TestItemMaxValue=" + TestItemMaxValue
				+ ",TestItemMinValue=" + TestItemMinValue + ",IsPositive="
				+ IsPositive + ",IsOverMax=" + IsOverMax + ",IsOverMin="
				+ IsOverMin + ",TestMedium_DBKey=" + TestMedium_DBKey
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",]";
	}
}
