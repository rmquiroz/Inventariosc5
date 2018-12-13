package marbetes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import utilerias.postgresql;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class TercerConteo {
	String mensaje;
	static Date date = new Date();
	static DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	public String generarMarbete(String almacenes) 
	{
		System.out.println(almacenes);		
		try
		{
		System.out.println("Se inicia conexion a bd");
	    Class.forName("org.postgresql.Driver");
	    Connection conexion = postgresql.getConexion();
	    //Connection conexion = DriverManager.getConnection("jdbc:postgresql://201.149.89.163:5932/openbravo", "postgres", "s3st2m1s4e");
	    System.out.println("Se finaliza la prueba de conexion a postgresql");
	    System.out.println("Se inicia la solicitud del reporte");
	    Map<String,Object> parameters = new HashMap<String,Object>();
	    parameters.put("almacenes",new String(almacenes));
	    System.out.println(parameters.put("almacenes",new String(almacenes)));
	    parameters.put("IMG_DIR",new String("/INFORMES/imagenes/"));
	    System.out.println(parameters.put("IMG_DIR",new String("/INFORMES/imagenes/")));
	    JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile("/INFORMES/reportes/tercerconteo.jasper");
	    System.out.println(reporte);
	    System.out.println(parameters);
	    System.out.println(conexion);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
	    System.out.println("Se solicita la impresion del reporte");
	    JRExporter exporter = new JRPdfExporter();
	    System.out.println("Imprime reporte");
	    
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("/INFORMES/"+"MarbetesTercer"+almacenes+hourFormat.format(date)+".pdf"));
	    exporter.exportReport();
	    System.out.println("Termina ejecucion");
	    conexion.close();
	    mensaje="MARBETES GENERADOS";
	} catch (SQLException e) {
		mensaje="ERROR DE BASE DE DATOS";
		e.printStackTrace();
	} catch (Exception ex) {
		mensaje="ERROR";
		ex.printStackTrace();
	}
		return mensaje;		
	}
}