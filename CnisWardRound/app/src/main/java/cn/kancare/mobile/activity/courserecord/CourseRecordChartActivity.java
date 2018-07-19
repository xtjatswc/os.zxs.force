package cn.kancare.mobile.activity.courserecord;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bo.CourseRecordBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.view.AChart;
import os.zxs.force.core.view.activity.BaseActivity;

public class CourseRecordChartActivity  extends BaseActivity {

    AChart aChart = new AChart();
    CourseRecordBo courseRecordBo;
    CourseRecordChartActivity context;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            List<CourseRecord> lstCourseRecord = courseRecordBo.getDao()
                    .query(Global.currentPatient.getPatientHospitalize_DBKey());

            int yMax = 100;

            List<String> xLabels = new ArrayList<String>();

            //
            List<Double> x1 = new ArrayList<Double>();
            List<Double> y1 = new ArrayList<Double>();

            CourseRecord tmp = null; //去重复日期时用到
            for (CourseRecord courseRecord : lstCourseRecord) {

                //去除重复日期
                if(tmp != null && courseRecord.getCourseRecordDate().equals(tmp.getCourseRecordDate())){
                    continue;
                }
                tmp = courseRecord;

                String xLabel = DateHelper.getInstance().getDataStringMMdd(
                        courseRecord.getCourseRecordDate());

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
                if (courseRecord.getWeight() != 0) {
                    x1.add((double) j);
                    y = courseRecord.getWeight();
                    y1.add(y);
                }

                if (y > yMax) {
                    yMax = Convert.cash2Int(y);
                }
            }

            String[] titles = new String[] { "体重变化" };
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
            aChart.setChartSettings(renderer, "体重变化"
                            + "(kg)", "日期", "值",
                    -1, 8, -1, yMax + 10, Color.WHITE, Color.WHITE);

            View chart = ChartFactory.getLineChartView(this, dataset, renderer);

            setContentView(chart);

        } catch (Exception e) {
            doException(e);
        }
    }

    protected String getLogTag() {
        return LogTag.CNIS_LOG;
    }

    protected int getLayoutId() {
        return R.layout.course_record_info;
    }

    protected void receiveIntent(Intent intent) throws Exception {

    }

    protected void initializeBo() throws Exception {
        courseRecordBo = new CourseRecordBo(context);
    }

    protected void setView() throws Exception {
        context = this;
    }
}
