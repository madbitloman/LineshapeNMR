package ukr.plottingdevice;
import java.awt.Color;
import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;
import ukr.stochasticlineshape.*;

public class Fieldplot extends ApplicationFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int size =FieldSwept.size1;
//int dla= Complexmatan.dlinna(size);
public Fieldplot(final String title, double[][] all) {
	
	
	
	
    super(title);
    final XYSeries series = new XYSeries(title);
    
    for(int i=0;i<size;i++){
    	
    
    		series.add(all[i][1], all[i][0]);
    
    }
    
    final XYSeriesCollection data = new XYSeriesCollection(series);
    final JFreeChart chart = ChartFactory.createXYLineChart(
        "Intensity versus H field",
        "H field", 
        "Intensity", 
        data,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );
    XYPlot plot = chart.getXYPlot();
    //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
   // ValueAxis yAxis = plot.getRangeAxis();
    //yAxis.setRange(0, 1);
    //ValueAxis xAxis = plot.getRangeAxis();
    //xAxis.setRange(0, 4);
    
    plot.setBackgroundPaint(Color.DARK_GRAY);
   
    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.BLACK);
     
    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(Color.BLACK);
    final ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(980, 640));
    setContentPane(chartPanel);

    File imageFile = new File("FieldGraph.png");
    int width = 1024;
    int height = 768;
     
    try {
        ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
    } catch (IOException ex) {
        System.err.println(ex);
    }
}





}