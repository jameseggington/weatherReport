package userinterface;

import java.awt.Color;
import javax.swing.JPanel;

import model.Location;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

public class GraphCreator extends JPanel{
	
	protected static Location loc;
	private static boolean showRain;
	private static boolean showTemp;

	public GraphCreator(Location loc, boolean showRain, boolean showTemp){
		this.loc = loc;
		this.showRain = showRain;
		this.showTemp = showTemp;
		
		XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(200, 150));	
        
        this.add(chartPanel);
	}
	
	private static JFreeChart createChart(XYDataset dataset) {
		String yCoAxis;
		if(showRain == true && showTemp == true){
			yCoAxis = "cm/Kelvin";
		}else if(showRain == true && showTemp == false){
			yCoAxis = "cm";
		}else{
			yCoAxis = "Kelvin";
		}

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            loc.getName(),  // title
            "Time",             // x-axis label
            yCoAxis,   // y-axis label
            dataset,            // data
            false,               // create legend?
            false,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            r.setSeriesPaint(0, Color.BLUE);
            r.setSeriesPaint(1, Color.RED);
        }
        
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setAutoRange(true);
        
        return chart;

    }
    
    /**
     * Creates a dataset, consisting of two series of data.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("RainFall");
        
        for(int i=0;i<loc.getRainfallList().size();i++){
        	char[] charTime = loc.getTimeList().get(i).toCharArray();
			String rainString = loc.getRainfallList().get(i);
			
			for(int j=0;j<charTime.length;j++){
				System.out.println(charTime[j]);
			}
			System.out.println(charTime.length);
			
			int second,minute,hour,day,month,year;
			
			if(charTime.length == 18){
				second = Integer.parseInt(charTime[16]+""+charTime[17]);
				minute = Integer.valueOf(charTime[13]+""+charTime[14]);
				hour = Integer.valueOf(charTime[11]+"");
				day = Integer.valueOf(charTime[0]+""+charTime[1]);
				month = Integer.valueOf(charTime[3]+""+charTime[4]);
				year = Integer.valueOf(charTime[6]+""+charTime[7]+""+charTime[8]+""+charTime[9]);
			}
			else{
				second = Integer.parseInt(charTime[17]+""+charTime[18]);
				minute = Integer.valueOf(charTime[14]+""+charTime[15]);
				hour = Integer.valueOf(charTime[11]+""+charTime[12]);
				day = Integer.valueOf(charTime[0]+""+charTime[1]);
				month = Integer.valueOf(charTime[3]+""+charTime[4]);
				year = Integer.valueOf(charTime[6]+""+charTime[7]+""+charTime[8]+""+charTime[9]);
			}
			
			try{
				Double.valueOf(rainString);
			}
			catch(NumberFormatException e){
				continue;
			}
			
			if(showRain){
				s1.addOrUpdate(new Second(second, minute, hour, day, month, year),Double.valueOf(rainString));
			}
        }
        
        TimeSeries s2 = new TimeSeries("Temperature");
        
        for(int i=0;i<loc.getTempList().size();i++){
        	char[] charTime = loc.getTimeList().get(i).toCharArray();
			String tempString = loc.getTempList().get(i);
			
			for(int j=0;j<charTime.length;j++){
				System.out.println(charTime[j]);
			}
			System.out.println(charTime.length);
			
			int second,minute,hour,day,month,year;
			
			if(charTime.length == 18){
				second = Integer.parseInt(charTime[16]+""+charTime[17]);
				minute = Integer.valueOf(charTime[13]+""+charTime[14]);
				hour = Integer.valueOf(charTime[11]+"");
				day = Integer.valueOf(charTime[0]+""+charTime[1]);
				month = Integer.valueOf(charTime[3]+""+charTime[4]);
				year = Integer.valueOf(charTime[6]+""+charTime[7]+""+charTime[8]+""+charTime[9]);
			}
			else{
				second = Integer.parseInt(charTime[17]+""+charTime[18]);
				minute = Integer.valueOf(charTime[14]+""+charTime[15]);
				hour = Integer.valueOf(charTime[11]+""+charTime[12]);
				day = Integer.valueOf(charTime[0]+""+charTime[1]);
				month = Integer.valueOf(charTime[3]+""+charTime[4]);
				year = Integer.valueOf(charTime[6]+""+charTime[7]+""+charTime[8]+""+charTime[9]);
			}
			
			try{
				Double.valueOf(tempString);
			}
			catch(NumberFormatException e){
				continue;
			}
			
			if(showTemp){
				s2.addOrUpdate(new Second(second, minute, hour, day, month, year),Double.valueOf(tempString));
			}
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

}
