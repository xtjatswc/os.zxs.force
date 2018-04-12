package cn.kancare.mobile.bean.questionnaire;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patientquestionnaireresult")
public class PatientQuestionnaireResult extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// auto_increment
	@DatabaseField(columnName = "PatientQuestionnaireResult_DBKey", id = true)
	private String PatientQuestionnaireResult_DBKey;

	// PatientQuestion_DBKey
	@DatabaseField(columnName = "PatientQuestion_DBKey")
	private String PatientQuestion_DBKey;

	// QuestionOption_DBKey
	@DatabaseField(columnName = "QuestionOption_DBKey")
	private int QuestionOption_DBKey;

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

	public String getPatientQuestionnaireResult_DBKey() {
		return PatientQuestionnaireResult_DBKey;
	}

	public void setPatientQuestionnaireResult_DBKey(
			String PatientQuestionnaireResult_DBKey) {
		this.PatientQuestionnaireResult_DBKey = PatientQuestionnaireResult_DBKey;
	}

	public String getPatientQuestion_DBKey() {
		return PatientQuestion_DBKey;
	}

	public void setPatientQuestion_DBKey(String PatientQuestion_DBKey) {
		this.PatientQuestion_DBKey = PatientQuestion_DBKey;
	}

	public int getQuestionOption_DBKey() {
		return QuestionOption_DBKey;
	}

	public void setQuestionOption_DBKey(int QuestionOption_DBKey) {
		this.QuestionOption_DBKey = QuestionOption_DBKey;
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
		return "patientquestionnaireresult [PatientQuestionnaireResult_DBKey="
				+ PatientQuestionnaireResult_DBKey + ",PatientQuestion_DBKey="
				+ PatientQuestion_DBKey + ",QuestionOption_DBKey="
				+ QuestionOption_DBKey + ",CreateBy=" + CreateBy
				+ ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
