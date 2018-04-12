package cn.kancare.mobile.bean.questionnaire;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "questiondetail")
public class QuestionDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// QuestionnaireQuestion_DBKey
	@DatabaseField(columnName = "QuestionnaireQuestion_DBKey", id = true)
	private int QuestionnaireQuestion_DBKey;

	// QuestionNo
	@DatabaseField(columnName = "QuestionNo")
	private String QuestionNo;

	// QuestionTitle
	@DatabaseField(columnName = "QuestionTitle")
	private String QuestionTitle;

	// QuestionContent
	@DatabaseField(columnName = "QuestionContent")
	private String QuestionContent;

	// QuestionType
	@DatabaseField(columnName = "QuestionType")
	private String QuestionType;

	// QuestionProperty
	@DatabaseField(columnName = "QuestionProperty")
	private int QuestionProperty;

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

	public int getQuestionnaireQuestion_DBKey() {
		return QuestionnaireQuestion_DBKey;
	}

	public void setQuestionnaireQuestion_DBKey(int QuestionnaireQuestion_DBKey) {
		this.QuestionnaireQuestion_DBKey = QuestionnaireQuestion_DBKey;
	}

	public String getQuestionNo() {
		return QuestionNo;
	}

	public void setQuestionNo(String QuestionNo) {
		this.QuestionNo = QuestionNo;
	}

	public String getQuestionTitle() {
		return QuestionTitle;
	}

	public void setQuestionTitle(String QuestionTitle) {
		this.QuestionTitle = QuestionTitle;
	}

	public String getQuestionContent() {
		return QuestionContent;
	}

	public void setQuestionContent(String QuestionContent) {
		this.QuestionContent = QuestionContent;
	}

	public String getQuestionType() {
		return QuestionType;
	}

	public void setQuestionType(String QuestionType) {
		this.QuestionType = QuestionType;
	}

	public int getQuestionProperty() {
		return QuestionProperty;
	}

	public void setQuestionProperty(int QuestionProperty) {
		this.QuestionProperty = QuestionProperty;
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
		return "questiondetail [QuestionnaireQuestion_DBKey="
				+ QuestionnaireQuestion_DBKey + ",QuestionNo=" + QuestionNo
				+ ",QuestionTitle=" + QuestionTitle + ",QuestionContent="
				+ QuestionContent + ",QuestionType=" + QuestionType
				+ ",QuestionProperty=" + QuestionProperty + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
