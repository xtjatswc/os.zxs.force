package cn.kancare.mobile.bean.questionnaire;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patientquestionnaire")
public class PatientQuestionnaire extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// PatientQuestionnaire_DBKey
	@DatabaseField(columnName = "PatientQuestionnaire_DBKey", id = true)
	private String PatientQuestionnaire_DBKey;

	// ScreeningDate
	@DatabaseField(columnName = "ScreeningDate")
	private String ScreeningDate;

	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// WeightNow
	@DatabaseField(columnName = "WeightNow")
	private Double WeightNow;

	// Weight1MonthAgo
	@DatabaseField(columnName = "Weight1MonthAgo")
	private Double Weight1MonthAgo;

	// Weight2MonthAgo
	@DatabaseField(columnName = "Weight2MonthAgo")
	private Double Weight2MonthAgo;

	// Weight3MonthAgo
	@DatabaseField(columnName = "Weight3MonthAgo")
	private Double Weight3MonthAgo;

	// Weight6MonthAgo
	@DatabaseField(columnName = "Weight6MonthAgo")
	private Double Weight6MonthAgo;

	// NSR2002Score
	@DatabaseField(columnName = "NSR2002Score")
	private int NSR2002Score;

	// PGSGAScore
	@DatabaseField(columnName = "PGSGAScore")
	private int PGSGAScore;

	// PGSGAQualitativeResult
	@DatabaseField(columnName = "PGSGAQualitativeResult")
	private String PGSGAQualitativeResult;

	// QuestionnaireStatus
	@DatabaseField(columnName = "QuestionnaireStatus")
	private String QuestionnaireStatus;

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

	// Remark
	@DatabaseField(columnName = "Remark")
	private String Remark;

	// QuestionnaireNo
	@DatabaseField(columnName = "QuestionnaireNo")
	private String QuestionnaireNo;

	// QuestionProperty
	@DatabaseField(columnName = "QuestionProperty")
	private int QuestionProperty;
	
	// 是否接受营养治疗	0 不接受，1 接受
	@DatabaseField(columnName = "AgreeHelp")
	private int AgreeHelp;

	public String getPatientQuestionnaire_DBKey() {
		return PatientQuestionnaire_DBKey;
	}

	public void setPatientQuestionnaire_DBKey(String PatientQuestionnaire_DBKey) {
		this.PatientQuestionnaire_DBKey = PatientQuestionnaire_DBKey;
	}

	public String getScreeningDate() {
		return ScreeningDate;
	}

	public void setScreeningDate(String ScreeningDate) {
		this.ScreeningDate = ScreeningDate;
	}

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String PatientHospitalize_DBKey) {
		this.PatientHospitalize_DBKey = PatientHospitalize_DBKey;
	}

	public Double getWeightNow() {
		return WeightNow;
	}

	public void setWeightNow(Double WeightNow) {
		this.WeightNow = WeightNow;
	}

	public Double getWeight1MonthAgo() {
		if (Weight1MonthAgo == null) {
			return 0.0;
		}
		return Weight1MonthAgo;
	}

	public void setWeight1MonthAgo(Double Weight1MonthAgo) {
		this.Weight1MonthAgo = Weight1MonthAgo;
	}

	public Double getWeight2MonthAgo() {
		if (Weight2MonthAgo == null) {
			return 0.0;
		}
		return Weight2MonthAgo;
	}

	public void setWeight2MonthAgo(Double Weight2MonthAgo) {
		this.Weight2MonthAgo = Weight2MonthAgo;
	}

	public Double getWeight3MonthAgo() {
		if (Weight3MonthAgo == null) {
			return 0.0;
		}
		return Weight3MonthAgo;
	}

	public void setWeight3MonthAgo(Double Weight3MonthAgo) {
		this.Weight3MonthAgo = Weight3MonthAgo;
	}

	public Double getWeight6MonthAgo() {
		if (Weight6MonthAgo == null) {
			return 0.0;
		}
		return Weight6MonthAgo;
	}

	public void setWeight6MonthAgo(Double Weight6MonthAgo) {
		this.Weight6MonthAgo = Weight6MonthAgo;
	}

	public int getNSR2002Score() {
		return NSR2002Score;
	}

	public void setNSR2002Score(int NSR2002Score) {
		this.NSR2002Score = NSR2002Score;
	}

	public int getPGSGAScore() {
		return PGSGAScore;
	}

	public void setPGSGAScore(int PGSGAScore) {
		this.PGSGAScore = PGSGAScore;
	}

	public String getPGSGAQualitativeResult() {
		return PGSGAQualitativeResult;
	}

	public void setPGSGAQualitativeResult(String PGSGAQualitativeResult) {
		this.PGSGAQualitativeResult = PGSGAQualitativeResult;
	}

	public String getQuestionnaireStatus() {
		return QuestionnaireStatus;
	}

	public void setQuestionnaireStatus(String QuestionnaireStatus) {
		this.QuestionnaireStatus = QuestionnaireStatus;
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

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Remark) {
		this.Remark = Remark;
	}

	@Override
	public String toString() {
		return "patientquestionnaire [PatientQuestionnaire_DBKey="
				+ PatientQuestionnaire_DBKey + ",ScreeningDate="
				+ ScreeningDate + ",PatientHospitalize_DBKey="
				+ PatientHospitalize_DBKey + ",WeightNow=" + WeightNow
				+ ",Weight1MonthAgo=" + Weight1MonthAgo + ",Weight2MonthAgo="
				+ Weight2MonthAgo + ",Weight3MonthAgo=" + Weight3MonthAgo
				+ ",Weight6MonthAgo=" + Weight6MonthAgo + ",NSR2002Score="
				+ NSR2002Score + ",PGSGAScore=" + PGSGAScore
				+ ",PGSGAQualitativeResult=" + PGSGAQualitativeResult
				+ ",QuestionnaireStatus=" + QuestionnaireStatus + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",Remark=" + Remark
				+ ",]";
	}

	public String getQuestionnaireNo() {
		return QuestionnaireNo;
	}

	public void setQuestionnaireNo(String QuestionnaireNo) {
		this.QuestionnaireNo = QuestionnaireNo;
	}

	public int getQuestionProperty() {
		return QuestionProperty;
	}

	public void setQuestionProperty(int QuestionProperty) {
		this.QuestionProperty = QuestionProperty;
	}

	public int getAgreeHelp() {
		return AgreeHelp;
	}

	public void setAgreeHelp(int agreeHelp) {
		AgreeHelp = agreeHelp;
	}
}
