package cn.kancare.mobile.common.questionnaire;

import android.content.Context;
import android.widget.CheckBox;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;

public class QuestionCheckBox extends CheckBox implements IQuestionOption{
	OptionDetail optionDetail;
	Question question; // 这个项所属的题

	public QuestionCheckBox(Context context, OptionDetail optionDetail) {
		super(context);
		this.optionDetail = optionDetail;
	}

	public Boolean getIsChecked() {
		return this.isChecked();
	}

	public void setIsChecked(Boolean isCheckd) {
		this.setChecked(isCheckd);
	}

	public int getScoreValue() {
		return optionDetail.getOptionPoint();
	}

	public int getQuestionOption_DBKey() {
		return optionDetail.getQuestionOption_DBKey();
	}

	public int getQuestionnaireQuestion_DBKey() {
		return optionDetail.getQuestionnaireQuestion_DBKey();
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
