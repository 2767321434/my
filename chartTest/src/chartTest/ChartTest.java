package chartTest;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class ChartTest {
    PiePlot plot;
    public ChartTest()
    {
	/*	//创建主题样式  
	StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
	//设置标题字体  
	mChartTheme.setExtraLargeFont(new Font("SansSerif", Font.BOLD, 20));  
	//设置轴向字体  
	mChartTheme.setLargeFont(new Font("SansSerif", Font.CENTER_BASELINE, 15));  
	//设置图例字体  
	mChartTheme.setRegularFont(new Font("SansSerif", Font.CENTER_BASELINE, 15));  
	//应用主题样式  
	ChartFactory.setChartTheme(mChartTheme);  */
	String imgPath = "D://psb.jpg";  
	BufferedImage image = null;
	try {
	    image = ImageIO.read(new FileInputStream(imgPath));
	} catch (FileNotFoundException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
	StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
	mChartTheme.setRegularFont(new Font("黑体", Font.BOLD, 20));
	ChartFactory.setChartTheme(mChartTheme); 
	
        DefaultPieDataset dpd=new DefaultPieDataset(); 
        dpd.setValue("一", 25);
        dpd.setValue("二", 25);
        dpd.setValue("三", 45);
        dpd.setValue("四", 10);
        
        JFreeChart chart=ChartFactory.createPieChart3D("yy如题",dpd,true,true,false); 
        plot = (PiePlot) chart.getPlot();
        PiePlot3D plot3D=(PiePlot3D) chart.getPlot();
        plot3D.setDirection(Rotation.ANTICLOCKWISE);
        plot.setBackgroundImage(image);
        plot.setBackgroundAlpha(0.9f);
    //    Rotator r=new Rotator(plot);
   //     r.start();
        
  /*     Font titleFont = new Font("SansSerif", Font.BOLD, 20);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(titleFont);// 为标题设置上字体  
          
        Font plotFont = new Font("Tahoma", Font.PLAIN, 16);  
        PiePlot plot = (PiePlot) chart.getPlot();  
        plot.setLabelFont(plotFont); // 为饼图元素设置上字体  
          
        Font LegendFont = new Font("Tahoma", Font.PLAIN, 18);  
        LegendTitle legend = chart.getLegend(0);  
        legend.setItemFont(LegendFont);// 为图例说明设置字体  
*/       
       ChartFrame chartFrame=new ChartFrame("同一人",chart); 

        
        chartFrame.pack(); 
        chartFrame.setVisible(true);
  
        
    
    }
    static class Rotator extends Timer
    implements ActionListener
  {
    private PiePlot plot;
    private int angle = 270;

    Rotator(PiePlot paramPiePlot)
    {
      super(20, null);
      this.plot = paramPiePlot;
      addActionListener(this);
    }

    public void actionPerformed(ActionEvent paramActionEvent)
    {
      this.plot.setStartAngle(this.angle);
      this.angle += 1;
      if (this.angle == 360)
        this.angle = 0;
    }
  }

    public static void main(String[] args)
    {
	new ChartTest();
    }
}
