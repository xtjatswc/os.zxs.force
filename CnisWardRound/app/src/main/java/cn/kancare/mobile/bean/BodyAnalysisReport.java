package cn.kancare.mobile.bean;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bodyanalysisreport")
public class BodyAnalysisReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3091676914319558601L;
	
	// BodyAnalysisReportNo
	@DatabaseField(columnName = "BodyAnalysisReportNo", id=true)
	private int BodyAnalysisReportNo;
	
	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// TestTime
	@DatabaseField(columnName = "TestTime")
	private String TestTime;

	// ReportHtml
	@DatabaseField(columnName = "ReportHtml")
	private String ReportHtml;

	public int getBodyAnalysisReportNo() {
		return BodyAnalysisReportNo;
	}

	public void setBodyAnalysisReportNo(int bodyAnalysisReportNo) {
		BodyAnalysisReportNo = bodyAnalysisReportNo;
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


}
