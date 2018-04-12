package os.zxs.force.core.view;

import java.util.List;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.R.color;
import android.graphics.Paint.Align;

public class AChart {

	/**
	 * 创建线条
	 * 
	 * @param titles
	 *            线条的名称
	 * @param x
	 *            线条数据信息（X轴）
	 * @param y
	 *            线条数据信息(Y轴)
	 * @return
	 */
	public XYMultipleSeriesDataset buildDataset(String[] titles,
			List<List<Double>> x, List<List<Double>> y) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;// /有几条线
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i]);// /根据每条线的名称创建线

			/* 获取线上面的数据信息 */
			List<Double> xV = x.get(i);
			List<Double> yV = y.get(i);

			/* 获取线上面的点数 */
			int seriesLength = xV.size();
			/* 把点挨个加入到创建的线上 */
			for (int j = 0; j < seriesLength; j++) {
				series.add(xV.get(j), yV.get(j));
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

	/**
	 * 创建线条渲染器
	 * 
	 * @param colors
	 *            颜色
	 * @param styles
	 *            风格
	 * @param fill
	 *            是否填充
	 * @return
	 */
	public XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer sRender = new XYSeriesRenderer();
			sRender.setColor(colors[i]);
			sRender.setLineWidth(3);// 设置线宽

			// 点上显示文件的相关设置
			sRender.setDisplayChartValues(true);// 是否在点上显示值
			sRender.setDisplayChartValuesDistance(0);
			sRender.setChartValuesSpacing(10f);
			sRender.setChartValuesTextSize(20f);

			if (styles.length > i)
				sRender.setPointStyle(styles[i]);
			sRender.setFillPoints(fill);
			renderer.addSeriesRenderer(sRender);
		}
		return renderer;
	}

	/**
	 * 设置线的属性值
	 * 
	 * @param renderer
	 *            线条渲染器
	 * @param title
	 *            表名称
	 * @param xTitle
	 *            X轴名称
	 * @param yTitle
	 *            Y轴名称
	 * @param xMin
	 *            X轴的最小值
	 * @param xMax
	 *            X轴的最大值
	 * @param yMin
	 *            Y轴的最小值
	 * @param yMax
	 *            Y轴的最大值
	 * @param axesColor
	 *            线条颜色
	 * @param labelsColor
	 *            名称标签颜色
	 */
	public void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setPointSize(5f);// 设置点的大小
		renderer.setXLabels(0);// 设置只显示如1月，2月等替换后的东西，不显示1,2,3等

		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);

		renderer.setAxisTitleTextSize(20); // 坐标轴标题字体大小： 16
		renderer.setChartTitleTextSize(40); // 图表标题字体大小： 20
		renderer.setLabelsTextSize(20); // 轴标签字体大小： 15
		renderer.setLegendTextSize(25); // 图例字体大小： 15
		renderer.setLegendHeight(115);

		renderer.setMargins(new int[] { 150, 50, 40, 0 }); // 图形 4 边距 上/左/下/右.
		renderer.setBackgroundColor(color.white);//
		renderer.setShowGrid(true); // 设置网格显示
		renderer.setYLabelsAlign(Align.RIGHT);// 设置Y轴在标签右边

		renderer.setZoomEnabled(false); // 是否允许缩放
		renderer.setZoomButtonsVisible(false);

	}
}
