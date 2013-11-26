package com.evmtv.epg.statistics;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-3-15 上午10:45:51
 */
public class CreateChartUtil {

	// 柱状图 数据集
	public static CategoryDataset getBarData(List<StatisticsResponse> statisticsResponses) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if(null!=statisticsResponses){
			for (StatisticsResponse statisticsResponse : statisticsResponses) {
				dataset.addValue(statisticsResponse.getContractNum(),"",statisticsResponse.getContractVal());
			}
		}
		return dataset;
	}

	// 饼状图 数据集
	public static PieDataset getPieData(List<StatisticsResponse> statisticsResponses) {
		DefaultPieDataset dataset = new DefaultPieDataset(); 
		if(null!=statisticsResponses){
			for (StatisticsResponse statisticsResponse : statisticsResponses) {
				dataset.setValue(statisticsResponse.getContractVal(), statisticsResponse.getContractNum());
			}
		}
		return dataset;
    }  

	/**
	 * 柱状图
	 * @param chartTitle
	 * @param xName
	 * @param yName
	 * @param dataset
	 * @return
	 */
	public static JFreeChart createBarChart(String chartTitle, String xName,  
            String yName, CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart(chartTitle, // 图表标题
				xName, // 目录轴的显示标签
				yName, // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		Font font = new Font("隶书", Font.BOLD, 25);
		Font labelFont = new Font("黑体", Font.BOLD, 15);

		chart.getTitle().setFont(font);
		//chart.getLegend().setItemFont(labelFont); //图例字体
		/*
		 * VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭,
		 * 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
		 */
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.white);
		// create plot
		CategoryPlot plot = chart.getCategoryPlot();
		// 设置横虚线可见
		plot.setRangeGridlinesVisible(true);
		// 虚线色彩
		plot.setRangeGridlinePaint(Color.gray);

		// 数据轴精度
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		// vn.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.00");
		vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
		// x轴设置

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// 轴标题

		domainAxis.setTickLabelFont(labelFont);// 轴数值

		// Lable（Math.PI/3.0）度倾斜
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions
		// .createUpRotationLabelPositions(Math.PI / 3.0));

		domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);// 横轴上的 Lable 是否完整显示

		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.1);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.1);
		// 设置 columnKey 是否间隔显示
		// domainAxis.setSkipCategoryLabelsToFit(true);

		plot.setDomainAxis(domainAxis);
		// 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
		plot.setBackgroundPaint(new Color(255, 255, 204));

		// y轴设置
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(labelFont);
		rangeAxis.setTickLabelFont(labelFont);
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);

		BarRenderer renderer = new BarRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.05);
		// 设置柱子高度
		renderer.setMinimumBarLength(0.2);
		// 设置柱子边框颜色
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱子边框可见
		renderer.setDrawBarOutline(true);

		// // 设置柱的颜色
		renderer.setSeriesPaint(0, new Color(204, 255, 255));
		renderer.setSeriesPaint(1, new Color(153, 204, 255));
		renderer.setSeriesPaint(2, new Color(51, 204, 204));

		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.0);

		// 显示每个柱的数值，并修改该数值的字体属性
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		plot.setRenderer(renderer);
		// 设置柱的透明度
		plot.setForegroundAlpha(1.0f);

		return chart;
	}


	/**
	 * 饼状图
	 * 
	 * @param dataset
	 *            数据集
	 * @param chartTitle
	 *            图标题
	 * @param pieKeys
	 *            分饼的名字集
	 * @return
	 */
	public static JFreeChart createPieChart(String chartTitle, PieDataset dataset) {
		
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, // chart
				// title
				dataset,// data
				true,// include legend
				true, false);

		// 使下说明标签字体清晰,去锯齿类似于
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);的效果
		chart.setTextAntiAlias(false);
		// 图片背景色
		chart.setBackgroundPaint(Color.white);
		// 设置图标题的字体重新设置title
		Font font = new Font("隶书", Font.BOLD, 25);
		Font labelFont = new Font("黑体", Font.BOLD, 15);

		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(labelFont);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 图片中显示百分比:默认方式

		// 指定饼图轮廓线的颜色
		// plot.setBaseSectionOutlinePaint(Color.BLACK);
		// plot.setBaseSectionPaint(Color.BLACK);

		// 设置无数据时的信息
		plot.setNoDataMessage("无对应的数据，请重新查询。");

		// 设置无数据时的信息显示颜色
		plot.setNoDataMessagePaint(Color.red);

		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));

		plot.setLabelFont(labelFont);

		// 指定图片的透明度(0.0-1.0)
		plot.setForegroundAlpha(0.65f);
		// 指定显示的饼图上圆形(false)还椭圆形(true)
		plot.setCircular(false, true);

		// 设置第一个 饼块section 的开始位置，默认是12点钟方向
		plot.setStartAngle(90);

		// // 设置分饼颜色
		//plot.setSectionPaint(pieKeys[0], new Color(244, 194, 144));
		//plot.setSectionPaint(pieKeys[1], new Color(144, 233, 144));

		return chart;
	}
	
	/**
	 * 判断文件夹是否存在，如果不存在则新建
	 * 
	 * @param chartPath
	 */
	private static void isChartPathExist(String chartPath) {
		File file = new File(chartPath);
		if (!file.exists()) {
			file.mkdirs();
			// log.info("CHART_PATH="+CHART_PATH+"create.");
		}
	}

	/**
	 * 生成到本地硬盘中磁盘
	 * 
	 * @param chart
	 * @param width
	 * @param height
	 * @return
	 */
	public static boolean generateToDisk(JFreeChart chart, int width, int height,String path) {
		FileOutputStream fos_jpg = null;
		boolean returnValue = true;
		try {
			isChartPathExist(path);
			String chartName = path + chart.getTitle().getText() + ".png";
			fos_jpg = new FileOutputStream(chartName);
			// 将报表保存为png文件
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, width, height, true,
					10);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = false;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}
	
	/**
	 * 生成到页面上
	 * @param chart
	 * @param width
	 * @param height
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public static void generateToPage(JFreeChart chart, int width, int height,HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, width, height);
	}
}