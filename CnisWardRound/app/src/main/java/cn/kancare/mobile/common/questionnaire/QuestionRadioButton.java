package cn.kancare.mobile.common.questionnaire;

import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;
import cn.kancare.mobile.common.Global;

public class QuestionRadioButton extends RadioButton implements IQuestionOption {
	Activity context;
	OptionDetail optionDetail;
	Question question; // 这个项所属的题

	public QuestionRadioButton(Activity context, OptionDetail optionDetail) {
		super(context);
		this.context = context;
		this.optionDetail = optionDetail;
		this.setOnClickListener(new onClickListener());
	}

	class onClickListener implements View.OnClickListener {

		public void onClick(View v) {
			// 跳到下一题
			// Question nextQuestion = question.getNextQuestion();
			// if (nextQuestion != null) {
			//
			// nextQuestion.questionnaire.scrollView.scrollTo(
			// (int) nextQuestion.titleView.getX(),
			// (int) nextQuestion.titleView.getY()
			// + nextQuestion.questionnaire.getPaddingTop());
			// }

			// 跳到当前题
			question.scrollTo();

		}

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
