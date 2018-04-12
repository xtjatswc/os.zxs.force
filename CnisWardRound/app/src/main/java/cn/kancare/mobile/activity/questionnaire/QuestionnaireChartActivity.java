package cn.kancare.mobile.activity.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.QuestionnaireConstant;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.view.AChart;
import os.zxs.force.core.view.activity.BaseActivity;

public class QuestionnaireChartActivity extends BaseActivity {

	AChart aChart = new AChart();
	PatientQuestionnaireBo patientQuestionnaireBo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<PatientQuestionnaire> lstpPatientQuestionnaires = null;
		try {
			lstpPatientQuestionnaires = patientQuestionnaireBo.getDao().query(
					Global.currentPatient.getPatientHospitalize_DBKey());
		} catch (Exception e) {
			doException(e);
		}

		List<String> xLabels = new ArrayList<String>();

		// NRS2002
		List<Double> x1 = new ArrayList<Double>();
		List<Double> y1 = new ArrayList<Double>();

		// PG-SGA
		List<Double> x2 = new ArrayList<Double>();
		List<Double> y2 = new ArrayList<Double>();

		// MUST
		List<Double> x3 = new ArrayList<Double>();
		List<Double> y3 = new ArrayList<Double>();

		// MST
		List<Double> x4 = new ArrayList<Double>();
		List<Double> y4 = new ArrayList<Double>();

		// MNA-SF
		List<Double> x5 = new ArrayList<Double>();
		List<Double> y5 = new ArrayList<Double>();

		// NRI
		List<Double> x6 = new ArrayList<Double>();
		List<Double> y6 = new ArrayList<Double>();

		// SGA
		List<Double> x7 = new ArrayList<Double>();
		List<Double> y7 = new ArrayList<Double>();

		for (int i = 0; i < lstpPatientQuestionnaires.size(); i++) {
			PatientQuestionnaire patientQuestionnaire = lstpPatientQuestionnaires
					.get(i);

			String xLabel = DateHelper.getInstance().getDataStringMMdd(
					patientQuestionnaire.getScreeningDate());

			int j = 0;
			// 去重
			if (xLabels.size() > 0
					&& xLabels.get(xLabels.size() - 1).equals(xLabel)) {
				j = xLabels.size() - 1;
			} else {
				j = xLabels.size();
				xLabels.add(xLabel);
			}

			switch (patientQuestionnaire.getQuestionProperty()) {
			case QuestionnaireConstant.NRS2002_TYPEVALUE:
				x1.add((double) j);
				y1.add((double) patientQuestionnaire.getNSR2002Score());
				break;
			case QuestionnaireConstant.PGSGA_TYPEVALUE:
				x2.add((double) j);
				y2.add((double) patientQuestionnaire.getPGSGAScore());
				break;
			case QuestionnaireConstant.MUST_TYPEVALUE:
				x3.add((double) j);
				y3.add((double) patientQuestionnaire.getPGSGAScore());
				break;
			case QuestionnaireConstant.MST_TYPEVALUE:
				x4.add((double) j);
				y4.add((double) patientQuestionnaire.getPGSGAScore());
				break;
			case QuestionnaireConstant.MNASF_TYPEVALUE:
				x5.add((double) j);
				y5.add((double) patientQuestionnaire.getPGSGAScore());
				break;
			case QuestionnaireConstant.NRI_TYPEVALUE:
				x6.add((double) j);
				y6.add((double) patientQuestionnaire.getWeight3MonthAgo());
				break;
			case QuestionnaireConstant.SGA_TYPEVALUE:
				x7.add((double) j);
				y7.add((double) patientQuestionnaire.getPGSGAScore());
				break;
			default:
				break;
			}
		}

		// String[] titles = new String[]{"First" ,"Second" };
		String[] titles = new String[] { "NRS2002", "PG-SGA", "MUST", "MST",
				"MNA-SF", "NRI", "SGA" };
		List<List<Double>> x = new ArrayList<List<Double>>();
		List<List<Double>> y = new ArrayList<List<Double>>();

		x.add(x1);
		x.add(x2);
		x.add(x3);
		x.add(x4);
		x.add(x5);
		x.add(x6);
		x.add(x7);

		y.add(y1);
		y.add(y2);
		y.add(y3);
		y.add(y4);
		y.add(y5);
		y.add(y6);
		y.add(y7);

		XYMultipleSeriesDataset dataset = aChart.buildDataset(titles, x, y);

		/* 两条线，一条蓝色一条绿色 */
		// int[] colors = new int[]{Color. BLUE,Color.GREEN};
		int[] colors = new int[] { Color.GREEN, Color.BLUE, Color.GRAY,
				Color.CYAN, Color.LTGRAY, Color.MAGENTA, Color.RED };
		/* 设置两条线条节点的绘制属性 */
		// PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE
		// ,PointStyle.DIAMOND};
		PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND,
				PointStyle.CIRCLE, PointStyle.SQUARE, PointStyle.TRIANGLE,
				PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.POINT };

		XYMultipleSeriesRenderer renderer = aChart.buildRenderer(colors, styles, true);

		for (int i = 0; i < xLabels.size(); i++) {
			renderer.addXTextLabel(i, xLabels.get(i));
		}

		aChart.setChartSettings(renderer, "问卷筛查折线图", "日期", "分值", -1, 8, -1, 20,
				Color.WHITE, Color.WHITE);

		View chart = ChartFactory.getLineChartView(this, dataset, renderer);

		setContentView(chart);

	}

	

	@Override
	protected String getLogTag() {
		return LogTag.CURD_QUESTIONNAIRE;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.questionnaire_chart;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		patientQuestionnaireBo = new PatientQuestionnaireBo(this);

	}

	@Override
	protected void setView() throws Exception {

	}

}
