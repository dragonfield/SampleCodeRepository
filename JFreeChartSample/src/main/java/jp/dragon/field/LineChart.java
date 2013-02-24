package jp.dragon.field;

import java.awt.BasicStroke;
import java.io.FileOutputStream;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

/**
 * 参考
 * http://java6.blog117.fc2.com/blog-entry-28.html
 */
public class LineChart {

	public static void main(String[] args) throws Exception {
		FileOutputStream result = null;
		
		// 日本語が文字化けしないテーマ
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		
		// グラフデータを設定する
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(145100, "NTTドコモ", "7月");
		dataset.addValue(51800, "au", "7月");
		dataset.addValue(279500, "ソフトバンク", "7月");
		dataset.addValue(125500, "NTTドコモ", "8月");
		dataset.addValue(56600, "au", "8月");
		dataset.addValue(288900, "ソフトバンク", "8月");
		dataset.addValue(109400, "NTTドコモ", "9月");
		dataset.addValue(914000, "au", "9月");
		dataset.addValue(332600, "ソフトバンク", "9月");
		dataset.addValue(57700, "NTTドコモ", "10月");
		dataset.addValue(58400, "au", "10月");
		dataset.addValue(324200, "ソフトバンク", "10月");
		dataset.addValue(88100, "NTTドコモ", "11月");
		dataset.addValue(82300, "au", "11月");
		dataset.addValue(276600, "ソフトバンク", "11月");		
		
		// グラフを生成する
		JFreeChart chart = ChartFactory.createLineChart("純増数", "キャリア", "契約数", dataset, PlotOrientation.VERTICAL, true, false, false);
		
		// 背景色を設定
		chart.setBackgroundPaint(ChartColor.WHITE);
		
		// 凡例の設定
		LegendTitle lt = chart.getLegend();
		lt.setFrame(new BlockBorder(1d, 2d, 3d, 4d, ChartColor.WHITE));
		lt.setPosition(RectangleEdge.BOTTOM);
	    
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setOutlineVisible(false);
		
		// X 軸の設定
		NumberAxis xAxis = (NumberAxis) plot.getRangeAxis();
		
		// Y 軸の設定
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		TickUnits tickUnits = new TickUnits();
		TickUnit unit = new NumberTickUnit(200000);
		tickUnits.add(unit);
		yAxis.setStandardTickUnits(tickUnits);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(1000000);
		
		// 透明度の設定
		plot.setForegroundAlpha(1);
		plot.setBackgroundAlpha(1);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		
		// シリーズの設定
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesPaint(1, ChartColor.ORANGE);
		renderer.setSeriesPaint(2, ChartColor.GRAY);
		for (int i = 0; i < dataset.getRowCount(); i++) {
			renderer.setSeriesStroke(i, new BasicStroke(2));
			renderer.setSeriesShapesVisible(i, true);
		}
		
		// ファイルへ出力する
		result = new FileOutputStream("result.png");
		ChartUtilities.writeChartAsPNG(result, chart, 600, 400);	    
		result.close();
	}
}
