package cn.kancare.mobile.bean.laboratoryindex;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "laboratoryindex")
public class LaboratoryIndex extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3091676914319558601L;
	
	// LaboratoryIndex_DBKey
	@DatabaseField(columnName = "LaboratoryIndex_DBKey", id=true)
	private String LaboratoryIndex_DBKey;
	
	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// TestTime
	@DatabaseField(columnName = "TestTime")
	private String TestTime;

	// ReportHtml
	@DatabaseField(columnName = "ReportHtml")
	private String ReportHtml;
	
	// TestType
	@DatabaseField(columnName = "TestType")
	private String TestType;

	public String getLaboratoryIndex_DBKey() {
		return LaboratoryIndex_DBKey;
	}

	public void setLaboratoryIndex_DBKey(String laboratoryIndex_DBKey) {
		LaboratoryIndex_DBKey = laboratoryIndex_DBKey;
	}
	
	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String patientHospitalize_DBKey) {
		PatientHospitalize_DBKey = patientHospitalize_DBKey;
	}

	public String getTestTime() {
		return TestTime;
	}

	public void setTestTime(String testTime) {
		TestTime = testTime;
	}

	public String getReportHtml() {
		return ReportHtml;
	}

	public void setReportHtml(String reportHtml) {
		ReportHtml = reportHtml;
	}

	public String getTestType() {
		return TestType;
	}

	public void setTestType(String testType) {
		TestType = testType;
	}




}
