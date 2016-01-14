package demo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DynamicDataDemo1 extends ApplicationFrame
{
  /**
     * 
     */
    private static final long serialVersionUID = 6067856547218889324L;

public DynamicDataDemo1(String paramString)
  {
    super(paramString);
    MyDemoPanel localMyDemoPanel = new MyDemoPanel();
    setContentPane(localMyDemoPanel);
  }

  public static JPanel createDemoPanel()
  {
    return new MyDemoPanel();
  }

  public static void main(String[] paramArrayOfString)
  {
    DynamicDataDemo1 localDynamicDataDemo1 = new DynamicDataDemo1("JFreeChart: DynamicDataDemo1.java");
    localDynamicDataDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localDynamicDataDemo1);
    localDynamicDataDemo1.setVisible(true);
  }

  static class MyDemoPanel extends DemoPanel
    implements ActionListener
  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    private TimeSeries series = new TimeSeries("Random Data");
    private double lastValue = 100.0D;

    public MyDemoPanel()
    {
      super();
      TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection(this.series);
      ChartPanel localChartPanel = new ChartPanel(createChart(localTimeSeriesCollection));
      localChartPanel.setPreferredSize(new Dimension(500, 270));
      addChart(localChartPanel.getChart());
      JPanel localJPanel = new JPanel();
      localJPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
      JButton localJButton = new JButton("Add New Data Item");
      localJButton.setActionCommand("ADD_DATA");
      localJButton.addActionListener(this);
      localJPanel.add(localJButton);
      add(localChartPanel);
      add(localJPanel, "South");
    }

    private JFreeChart createChart(XYDataset paramXYDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createTimeSeriesChart("Dynamic Data Demo", "Time", "Value", paramXYDataset);
      XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
      ValueAxis localValueAxis = localXYPlot.getDomainAxis();
      localValueAxis.setAutoRange(true);
      localValueAxis.setFixedAutoRange(60000.0D);
      localValueAxis = localXYPlot.getRangeAxis();
      localValueAxis.setRange(0.0D, 200.0D);
      return localJFreeChart;
    }

    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if (paramActionEvent.getActionCommand().equals("ADD_DATA"))
      {
	  java.util.Timer timer=new java.util.Timer();
	  timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		// TODO 自动生成的方法存根
		 double d = 0.9D + 0.2D * Math.random();
		 MyDemoPanel.this.lastValue *= d;
		        Millisecond localMillisecond = new Millisecond();
		        System.out.println("Now = " + localMillisecond.toString());
		        MyDemoPanel.this.series.add(new Millisecond(), MyDemoPanel.this.lastValue);
	    }
	}, 0,200);
	  
       
      }
    }
  }
}