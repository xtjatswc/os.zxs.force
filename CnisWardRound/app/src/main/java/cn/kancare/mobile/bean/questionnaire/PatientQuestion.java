package cn.kancare.mobile.bean.questionnaire;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patientquestion")
public class PatientQuestion extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// PatientQuestion_DBKey
	@DatabaseField(columnName = "PatientQuestion_DBKey", id = true)
	private String PatientQuestion_DBKey;

	// PatientQuestionnaire_DBKey
	@DatabaseField(columnName = "PatientQuestionnaire_DBKey")
	private String PatientQuestionnaire_DBKey;

	// QuestionnaireQuestion_DBKey
	@DatabaseField(columnName = "QuestionnaireQuestion_DBKey")
	private int QuestionnaireQuestion_DBKey;

	// QuestionnaireNo
	@DatabaseField(columnName = "QuestionnaireNo")
	private String QuestionnaireNo;

	// QuestionnaireTitle
	@DatabaseField(columnName = "QuestionnaireTitle")
	private String QuestionnaireTitle;

	// QuestionnaireTarget
	@DatabaseField(columnName = "QuestionnaireTarget")
	private String QuestionnaireTarget;

	// QuestionnaireDescription
	@DatabaseField(columnName = "QuestionnaireDescription")
	private String QuestionnaireDescription;

	// CreateDate
	@DatabaseField(columnName = "CreateDate")
	private String CreateDate;

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

	public String getPatientQuestion_DBKey() {
		return PatientQuestion_DBKey;
	}

	public void setPatientQuestion_DBKey(String PatientQuestion_DBKey) {
		this.PatientQuestion_DBKey = PatientQuestion_DBKey;
	}

	public String getPatientQuestionnaire_DBKey() {
		return PatientQuestionnaire_DBKey;
	}

	public void setPatientQuestionnaire_DBKey(String PatientQuestionnaire_DBKey) {
		this.PatientQuestionnaire_DBKey = PatientQuestionnaire_DBKey;
	}

	public int getQuestionnaireQuestion_DBKey() {
		return QuestionnaireQuestion_DBKey;
	}

	public void setQuestionnaireQuestion_DBKey(int QuestionnaireQuestion_DBKey) {
		this.QuestionnaireQuestion_DBKey = QuestionnaireQuestion_DBKey;
	}

	public String getQuestionnaireNo() {
		return QuestionnaireNo;
	}

	public void setQuestionnaireNo(String QuestionnaireNo) {
		this.QuestionnaireNo = QuestionnaireNo;
	}

	public String getQuestionnaireTitle() {
		return QuestionnaireTitle;
	}

	public void setQuestionnaireTitle(String QuestionnaireTitle) {
		this.QuestionnaireTitle = QuestionnaireTitle;
	}

	public String getQuestionnaireTarget() {
		return QuestionnaireTarget;
	}

	public void setQuestionnaireTarget(String QuestionnaireTarget) {
		this.QuestionnaireTarget = QuestionnaireTarget;
	}

	public String getQuestionnaireDescription() {
		return QuestionnaireDescription;
	}

	public void setQuestionnaireDescription(String QuestionnaireDescription) {
		this.QuestionnaireDescription = QuestionnaireDescription;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String CreateDate) {
		this.CreateDate = CreateDate;
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
		return "patientquestion [PatientQuestion_DBKey="
				+ PatientQuestion_DBKey + ",PatientQuestionnaire_DBKey="
				+ PatientQuestionnaire_DBKey + ",QuestionnaireQuestion_DBKey="
				+ QuestionnaireQuestion_DBKey + ",QuestionnaireNo="
				+ QuestionnaireNo + ",QuestionnaireTitle=" + QuestionnaireTitle
				+ ",QuestionnaireTarget=" + QuestionnaireTarget
				+ ",QuestionnaireDescription=" + QuestionnaireDescription
				+ ",CreateDate=" + CreateDate + ",CreateBy=" + CreateBy
				+ ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
