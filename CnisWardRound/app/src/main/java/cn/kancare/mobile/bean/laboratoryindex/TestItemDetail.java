package cn.kancare.mobile.bean.laboratoryindex;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "testitemdetail")
public class TestItemDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// TestItemDetail_DBKey
	@DatabaseField(columnName = "TestItemDetail_DBKey", id = true)
	private int TestItemDetail_DBKey;

	// TestMedium_DBKey
	@DatabaseField(columnName = "TestMedium_DBKey")
	private int TestMedium_DBKey;

	// TestItemCode
	@DatabaseField(columnName = "TestItemCode")
	private String TestItemCode;

	// TestItemName
	@DatabaseField(columnName = "TestItemName")
	private String TestItemName;

	// TestItemUnit
	@DatabaseField(columnName = "TestItemUnit")
	private String TestItemUnit;

	// TestItemMinValue
	@DatabaseField(columnName = "TestItemMinValue")
	private String TestItemMinValue;

	// TestItemMaxValue
	@DatabaseField(columnName = "TestItemMaxValue")
	private String TestItemMaxValue;

	// IsEnabled
	@DatabaseField(columnName = "IsEnabled")
	private String IsEnabled;

	public int getTestItemDetail_DBKey() {
		return TestItemDetail_DBKey;
	}

	public void setTestItemDetail_DBKey(int TestItemDetail_DBKey) {
		this.TestItemDetail_DBKey = TestItemDetail_DBKey;
	}

	public int getTestMedium_DBKey() {
		return TestMedium_DBKey;
	}

	public void setTestMedium_DBKey(int TestMedium_DBKey) {
		this.TestMedium_DBKey = TestMedium_DBKey;
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

	public String getTestItemUnit() {
		return TestItemUnit;
	}

	public void setTestItemUnit(String TestItemUnit) {
		this.TestItemUnit = TestItemUnit;
	}

	public String getTestItemMinValue() {
		return TestItemMinValue;
	}

	public void setTestItemMinValue(String TestItemMinValue) {
		this.TestItemMinValue = TestItemMinValue;
	}

	public String getTestItemMaxValue() {
		return TestItemMaxValue;
	}

	public void setTestItemMaxValue(String TestItemMaxValue) {
		this.TestItemMaxValue = TestItemMaxValue;
	}

	public String getIsEnabled() {
		return IsEnabled;
	}

	public void setIsEnabled(String IsEnabled) {
		this.IsEnabled = IsEnabled;
	}

	@Override
	public String toString() {
		return "testitemdetail [TestItemDetail_DBKey=" + TestItemDetail_DBKey
				+ ",TestMedium_DBKey=" + TestMedium_DBKey + ",TestItemCode="
				+ TestItemCode + ",TestItemName=" + TestItemName
				+ ",TestItemUnit=" + TestItemUnit + ",TestItemMinValue="
				+ TestItemMinValue + ",TestItemMaxValue=" + TestItemMaxValue
				+ ",IsEnabled=" + IsEnabled + ",]";
	}
}
