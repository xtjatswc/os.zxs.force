package cn.kancare.mobile.bean.questionnaire;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "questiondetailtype")
public class QuestionDetailType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// auto_increment
	@DatabaseField(columnName = "tid", id = true)
	private int tid;

	// propertyName
	@DatabaseField(columnName = "propertyName")
	private String propertyName;

	// propertyValue
	@DatabaseField(columnName = "propertyValue")
	private int propertyValue;

	// sort
	@DatabaseField(columnName = "sort")
	private int sort;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public int getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(int propertyValue) {
		this.propertyValue = propertyValue;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "questiondetailtype [tid=" + tid + ",propertyName="
				+ propertyName + ",propertyValue=" + propertyValue + ",sort="
				+ sort + ",]";
	}
}
