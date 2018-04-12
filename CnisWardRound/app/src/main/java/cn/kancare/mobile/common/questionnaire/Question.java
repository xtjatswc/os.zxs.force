package cn.kancare.mobile.common.questionnaire;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import cn.kancare.mobile.bean.questionnaire.QuestionDetail;
import os.zxs.force.core.util.Convert;

public class Question {
	public QuestionDetail questionDetail;
	List<IQuestionOption> lstQuestionOption;
	Questionnaire questionnaire; // 当前题所属的问卷
	Question nextQuestion;// 当前题的下一道题
	View titleView; //

	public Question(View titleView, QuestionDetail questionDetail) {
		this.questionDetail = questionDetail;
		this.titleView = titleView;
		lstQuestionOption = new ArrayList<IQuestionOption>();
	}

	public void AddQuestionOption(IQuestionOption iQuestionOption) {
		iQuestionOption.setQuestion(this);
		lstQuestionOption.add(iQuestionOption);
	}

	public List<IQuestionOption> getQuestionOptions() {
		return lstQuestionOption;
	}

	public int getQuestionnaireQuestion_DBKey() {
		return questionDetail.getQuestionnaireQuestion_DBKey();
	}

	public Boolean isFinish() {
		for (IQuestionOption iQuestionOption : lstQuestionOption) {
			if (iQuestionOption.getIsChecked())
				return true;
		}
		return false;
	}

	// 当前题的下一道题
	public void setNextQuestion(Question question) {
		this.nextQuestion = question;
	}

	public Question getNextQuestion() {
		return this.nextQuestion;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	// 得到本题得分
	public int getScore() {
		int score = 0;

		int questionSN = Convert.cash2Int(questionDetail.getQuestionnaireQuestion_DBKey()) - 3;

		if (questionSN == -2 || questionSN == 4 || questionSN == 5) {
			// nrs2002第1题多选题只取最高分
			// pgsga第5题、多选题 只取最高分
			for (IQuestionOption iQuestionOption : lstQuestionOption) {
				if (iQuestionOption.getIsChecked()) {
					if (iQuestionOption.getScoreValue() > score) {
						score = iQuestionOption.getScoreValue();
					}
				}
			}
		} else {
			for (IQuestionOption iQuestionOption : lstQuestionOption) {
				if (iQuestionOption.getIsChecked()) {
					score += iQuestionOption.getScoreValue();
				}
			}

		}
		return score;

	}

	// 跳到当前题
	public void scrollTo() {
		this.questionnaire.scrollView.scrollTo(
				(int) this.titleView.getX(),
				(int) this.titleView.getY()
						+ this.questionnaire.getPaddingTop());
	}
}
