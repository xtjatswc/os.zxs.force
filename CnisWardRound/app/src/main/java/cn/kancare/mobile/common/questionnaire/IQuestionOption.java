package cn.kancare.mobile.common.questionnaire;



public interface IQuestionOption {
	
	public Boolean getIsChecked();
	public void setIsChecked(Boolean isCheckd);
	public int getScoreValue();
	public int getQuestionOption_DBKey();
	public int getQuestionnaireQuestion_DBKey();
	public void setQuestion(Question question);
}
