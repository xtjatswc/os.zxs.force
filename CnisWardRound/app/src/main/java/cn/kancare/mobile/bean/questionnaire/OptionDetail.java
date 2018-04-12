package cn.kancare.mobile.bean.questionnaire;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "optiondetail")
public class OptionDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// QuestionOption_DBKey
	@DatabaseField(columnName = "QuestionOption_DBKey", id = true)
	private int QuestionOption_DBKey;

	// QuestionnaireQuestion_DBKey
	@DatabaseField(columnName = "QuestionnaireQuestion_DBKey")
	private int QuestionnaireQuestion_DBKey;

	// OptionCode
	@DatabaseField(columnName = "OptionCode")
	private String OptionCode;

	// OptionOrderIndex
	@DatabaseField(columnName = "OptionOrderIndex")
	private int OptionOrderIndex;

	// OptionContent
	@DatabaseField(columnName = "OptionContent")
	private String OptionContent;

	// OptionPoint
	@DatabaseField(columnName = "OptionPoint")
	private int OptionPoint;

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

	public int getQuestionOption_DBKey() {
		return QuestionOption_DBKey;
	}

	public void setQuestionOption_DBKey(int QuestionOption_DBKey) {
		this.QuestionOption_DBKey = QuestionOption_DBKey;
	}

	public int getQuestionnaireQuestion_DBKey() {
		return QuestionnaireQuestion_DBKey;
	}

	public void setQuestionnaireQuestion_DBKey(int QuestionnaireQuestion_DBKey) {
		this.QuestionnaireQuestion_DBKey = QuestionnaireQuestion_DBKey;
	}

	public String getOptionCode() {
		return OptionCode;
	}

	public void setOptionCode(String OptionCode) {
		this.OptionCode = OptionCode;
	}

	public int getOptionOrderIndex() {
		return OptionOrderIndex;
	}

	public void setOptionOrderIndex(int OptionOrderIndex) {
		this.OptionOrderIndex = OptionOrderIndex;
	}

	public String getOptionContent() {
		return OptionContent;
	}

	public void setOptionContent(String OptionContent) {
		this.OptionContent = OptionContent;
	}

	public int getOptionPoint() {
		return OptionPoint;
	}

	public void setOptionPoint(int OptionPoint) {
		this.OptionPoint = OptionPoint;
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
		return "optiondetail [QuestionOption_DBKey=" + QuestionOption_DBKey
				+ ",QuestionnaireQuestion_DBKey=" + QuestionnaireQuestion_DBKey
				+ ",OptionCode=" + OptionCode + ",OptionOrderIndex="
				+ OptionOrderIndex + ",OptionContent=" + OptionContent
				+ ",OptionPoint=" + OptionPoint + ",CreateBy=" + CreateBy
				+ ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
