package reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.view.JasperViewer;

public class TestReports {

    public TestReports() throws SQLException, ClassNotFoundException
    {
	
	
	    try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
			"jdbc:sqlserver://192.163.20.5:14331;databaseName=bhiot10"
			, "sa" , "sa");
		
		String sourceFileName = "D:\\jfreechart\\jxml\\report4.jrxml";
		String str=JasperCompileManager.compileReportToFile(sourceFileName);
		System.out.println(str);
		File src=new File(str);
	
		JasperReport jasperReport=(JasperReport)JRLoader.loadObject(src);
		//JasperDesignViewer.viewReportDesign(jasperReport);
		JRCsvDataSource jcsv = null;
		try {
		    jcsv = new JRCsvDataSource(new File("D:\\jfreechart\\jxml\\personLocator_EntryLeaveStationTimeHistory.csv"));
		    jcsv.setUseFirstRowAsHeader(true);
		} catch (FileNotFoundException e) {
		    // TODO 自动生成的 catch 块
		    e.printStackTrace();
		}
		
		JasperPrint print=JasperFillManager.fillReport(jasperReport, null,conn );
		
		//JasperPrint print= JasperFillManager.fillReport(jasperReport, null, (JRDataSource)null);
		File destFile = new File(src.getParent(), jasperReport.getName() + ".jrprint");
		JasperViewer.viewReport(print);
		JRSaver.saveObject(print, destFile);
		
		//xls导出
		/*JRXlsExporter jxls=new JRXlsExporter();
		jxls.setExporterInput(new SimpleExporterInput(print));
		jxls.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("D:\\jfreechart\\jxml\\report4.xls")));
		jxls.exportReport();*/
		//JasperPrintManager.printReport(print, true);
		//pdf导出
		JasperExportManager.exportReportToPdfFile("D:\\jfreechart\\jxml\\report4.jrprint");
		
	    } catch (JRException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    } 
    }
    public static void main(String[] args) {
	try {
	    new TestReports();
	} catch (ClassNotFoundException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
    }
}
