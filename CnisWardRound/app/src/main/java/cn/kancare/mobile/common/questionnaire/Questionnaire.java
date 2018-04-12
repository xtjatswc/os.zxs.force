package cn.kancare.mobile.common.questionnaire;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import cn.kancare.mobile.common.constant.QuestionnaireConstant;
import os.zxs.force.core.util.Convert;

public class Questionnaire {

	TreeMap<Integer, Question> mapQuestions;
	TreeMap<Integer, IQuestionOption> mapQuestionOptions;
	ScrollView scrollView;// 滚动条
	LinearLayout linearLayout;// 问卷容器
	Question previousQuestion;
	int QuestionProperty;

	public Questionnaire(ScrollView scrollView, LinearLayout linearLayout,
			int QuestionProperty) {
		this.scrollView = scrollView;
		this.linearLayout = linearLayout;
		mapQuestions = new TreeMap<Integer, Question>();
		mapQuestionOptions = new TreeMap<Integer, IQuestionOption>();
		this.QuestionProperty = QuestionProperty;
	}

	public void AddQuestion(Question question) {
		// 添加题
		mapQuestions.put(question.getQuestionnaireQuestion_DBKey(), question);
		question.setQuestionnaire(this);

		// 指定下一道题
		if (previousQuestion != null) {
			previousQuestion.setNextQuestion(question);
		}
		previousQuestion = question;

		// 添加项
		for (IQuestionOption iQuestionOption : question.getQuestionOptions()) {
			if (!mapQuestionOptions.containsKey(iQuestionOption
					.getQuestionOption_DBKey())) {
				mapQuestionOptions.put(
						iQuestionOption.getQuestionOption_DBKey(),
						iQuestionOption);
			}
		}
	}

	public IQuestionOption getQuestionOption(int id) {
		return mapQuestionOptions.get(id);
	}

	// 得到问卷得分
	public int getScore() {
		int score = 0;

		if (QuestionProperty == QuestionnaireConstant.NRS2002_TYPEVALUE) {
			score = getNRS2002Score();
		} else if (QuestionProperty == QuestionnaireConstant.PGSGA_TYPEVALUE) {
			score = getPGSGAScore();
		} else if (QuestionProperty == QuestionnaireConstant.SGA_TYPEVALUE) {
			score = getSGAScore();
		} else {
			score = getOtherScore();
		}

		return score;
	}

	private int getSGAScore() {
		int score = 0;

		int a = 0;
		int b = 0;
		int c = 0;

		for (IQuestionOption iQuestionOption : mapQuestionOptions.values()) {
			if (iQuestionOption.getIsChecked()) {
				if (iQuestionOption.getScoreValue() == 1) {
					a++;
				} else if (iQuestionOption.getScoreValue() == 2) {
					b++;
				} else if (iQuestionOption.getScoreValue() == 3) {
					c++;
				}
			}
		}

		if (a > b && a > c) {
			score = 1;
		} else if (b > a && b > c) {
			score = 2;
		} else if (c > a && c > b) {
			score = 3;
		}

		return score;
	}

	private int getOtherScore() {
		int score = 0;

		for (IQuestionOption iQuestionOption : mapQuestionOptions.values()) {
			if (iQuestionOption.getIsChecked()) {
				score += iQuestionOption.getScoreValue();
			}
		}
		return score;
	}

	private int getNRS2002Score() {

		int score = 0;
		for (Question question : mapQuestions.values()) {
			score += question.getScore();
		}

		return score;
	}

	private int getPGSGAScore() {

		// 3、4题取最高分
		// 11-13 脂肪 不参与分值计算
		// 14-20 肌肉 参与分值计算，且取分值最高的题
		// 21-23 液体 不参与分值计算

		int score = 0;
		int ques3 = 0;
		int ques4 = 0;

		int quesMuscle = 0;

		for (Question question : mapQuestions.values()) {
			int questionSN = Convert.cash2Int(question.questionDetail
					.getQuestionnaireQuestion_DBKey()) - 3;

			if (questionSN == 3) {
				ques3 = question.getScore();
				continue;
			} else if (questionSN == 4) {
				ques4 = question.getScore();
				continue;
			} else if (questionSN >= 11 && questionSN <= 13)
				continue;
			else if (questionSN >= 14 && questionSN <= 20) {
				if (quesMuscle < question.getScore()) {
					quesMuscle = question.getScore();
				}
				continue;
			} else if (questionSN >= 21 && questionSN <= 23)
				continue;

			score += question.getScore();
		}

		if (ques4 > ques3) {
			score += ques4;
		} else {
			score += ques3;
		}

		score += quesMuscle;
		return score;
	}

	public List<Question> getQuestions() {
		return new ArrayList<Question>(mapQuestions.values());
	}

	public Question getQuestion(int id) {
		return mapQuestions.get(id);
	}

	// 问卷是否已经答完,已答完返回null，否则返回未答完的题
	public Question isFinish() {
		for (Question question : mapQuestions.values()) {
			if (!question.isFinish())
				return question;
		}
		return null;
	}

	// 问卷容器离滚动条顶端的距离
	public int getPaddingTop() {
		return (int) linearLayout.getY();
	}
}
