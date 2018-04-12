package cn.kancare.mobile.activity.laboratoryindex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.laboratoryindex.TestItemDetailBo;
import cn.kancare.mobile.bo.laboratoryindex.TestResultBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.view.AChart;
import os.zxs.force.core.view.activity.BaseActivity;

public class LaboratoryIndexChartActivity extends BaseActivity {

	AChart aChart = new AChart();
	int TestItemDetail_DBKey;

	LaboratoryIndexBo laboratoryIndexBo;
	TestResultBo testResultBo;
	TestItemDetailBo testItemDetailBo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			TestItemDetail testItemDetail = testItemDetailBo.getDao()
					.queryForId(TestItemDetail_DBKey);

			int yMax = Convert.cash2Int(testItemDetail.getTestItemMaxValue());

			List<LaboratoryIndex> lstlLaboratoryIndexs = laboratoryIndexBo
					.getDao()
					.query(Global.currentPatient.getPatientHospitalize_DBKey());

			List<String> xLabels = new ArrayList<String>();

			//
			List<Double> x1 = new ArrayList<Double>();
			List<Double> y1 = new ArrayList<Double>();

			for (LaboratoryIndex laboratoryIndex : lstlLaboratoryIndexs) {
				TestResult testResult = null;
				if (laboratoryIndex.getReportHtml() == null) {
					testResult = testResultBo.getDao().query(
							laboratoryIndex.getLaboratoryIndex_DBKey(),
							TestItemDetail_DBKey);
				}else {
					String str = getTestItemvalue(laboratoryIndex.getReportHtml(),
							"itemNameClick\\(\"" + TestItemDetail_DBKey + "\"\\).+?'TestItemValue'>(.*?)</td>");
					if(!str.equals("")){
						testResult = new TestResult();
						testResult.setTestItemValue(str);
					}
				}
				

				String xLabel = DateHelper.getInstance().getDataStringMMdd(
						laboratoryIndex.getTestTime());

				int j = 0;
				// 去重
				if (xLabels.size() > 0
						&& xLabels.get(xLabels.size() - 1).equals(xLabel)) {
					j = xLabels.size() - 1;
				} else {
					j = xLabels.size();
					xLabels.add(xLabel);
				}

				double y = 0;
				if (testResult != null && !testResult.getTestItemValue().equals("")) {
					x1.add((double) j);
					y = Convert.cash2Double(testResult.getTestItemValue());
					y1.add((double) y);
				}

				if (y > yMax) {
					yMax = Convert.cash2Int(y);
				}
			}

			String[] titles = new String[] { testItemDetail.getTestItemName() };
			List<List<Double>> x = new ArrayList<List<Double>>();
			List<List<Double>> y = new ArrayList<List<Double>>();

			x.add(x1);
			y.add(y1);

			XYMultipleSeriesDataset dataset = aChart.buildDataset(titles, x, y);

			int[] colors = new int[] { Color.GREEN };
			PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND };

			XYMultipleSeriesRenderer renderer = aChart.buildRenderer(colors,
					styles, true);
			for (int i = 0; i < xLabels.size(); i++) {
				renderer.addXTextLabel(i, xLabels.get(i));
			}

			yMax = (yMax == 0 ? 20 : yMax);
			aChart.setChartSettings(renderer, testItemDetail.getTestItemName()
					+ "(" + testItemDetail.getTestItemUnit() + ")", "日期", "值",
					-1, 8, -1, yMax + 10, Color.WHITE, Color.WHITE);

			View chart = ChartFactory.getLineChartView(this, dataset, renderer);

			setContentView(chart);

		} catch (Exception e) {
			doException(e);
		}
	}
	
	/**
	 * 获取查询的字符串 将匹配的字符串取出
	 */
	private String getTestItemvalue(String str, String regx) {
		// 1.将正在表达式封装成对象Patten 类来实现
		Pattern pattern = Pattern.compile(regx, Pattern.DOTALL);
		// 2.将字符串和正则表达式相关联
		Matcher matcher = pattern.matcher(str);
		// 3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

		// System.out.println(matcher.matches());
		// 查找符合规则的子串
		while (matcher.find()) {
			// 获取 字符串
			// System.out.println(matcher.group());
			// 获取的字符串的首位置和末位置
			// System.out.println(matcher.start()+"--"+matcher.end());
			return matcher.group(1);
			
		}
		return "";
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_LABORATORYINDEX;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.laboratoryindex_info;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		TestItemDetail_DBKey = intent.getIntExtra("TestItemDetail_DBKey", 0);

	}

	@Override
	protected void initializeBo() throws Exception {
		laboratoryIndexBo = new LaboratoryIndexBo(this);
		testResultBo = new TestResultBo(this);
		testItemDetailBo = new TestItemDetailBo(this);
	}

	@Override
	protected void setView() throws Exception {
		// TODO Auto-generated method stub

	}

}
