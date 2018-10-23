package confirmaconteos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import correo.EmailUtility;
public class ConfirmarConteosPrueba
{
	public static final String SEPARATOR=",";
	public static final String QUOTE=",";
	public static int registros = 0;
	public static String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
	public static String inventarios= "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
	//public static String url= "jdbc:postgresql://201.149.89.163:5932/openbravo";
	//public static String inventarios= "jdbc:postgresql://201.149.89.164:5932/inventarios_c5";
	public static String usuario="postgres";
	public static String contra="s3st2m1s4e";
	public static int fecha=0;
	public static String mensaje="";
	public static int hoy=0;
	static String resultMessage = "";
  public static String main(String almacenes)
  {
	  try
	  {
		  Class.forName("org.postgresql.Driver");		  
		  
		  Connection co = DriverManager.getConnection(inventarios, usuario, contra);		  
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null;
		  //mensaje="NO RESTAN UBICACIONES POR CONTABILIZAR";
		  PreparedStatement ps= co.prepareStatement("SELECT * FROM tercerconteo WHERE almacen similar to ('"+almacenes+"')");
		  rs=ps.executeQuery();
		  //if(rs.next()){
			  
			  mensaje="CONTEOS REGISTRADOS PREVIAMENTE NO PUEDE REALIZARSE NUEVAMENTE";
		  //}
		  //else{		  		  		  				  
		  ps= co.prepareStatement("insert into tercerconteo (SELECT '' AS CODIGO,"
+ "'' AS ATRIBUTO,"
+ "diferentes.marbete,"
+ "'' as cantidad,"
+ "null::date as fecha,"
+ "diferentes.hueco,"
+ "diferentes.almacen "
+ "FROM primerconteo AS primer,"
+ "segundoconteo AS seg,"
+ "(SELECT '' as codigo,"
+ "UBICACIONES.MARBETE as marbete,"
+ "'' as cantidad,"
+ "null as fecha,"
+ "ubicaciones.hueco,"
+ "ubicaciones.almacen "
+ "FROM UBICACIONES LEFT OUTER JOIN "
+ "(SELECT distinct PRIMER.marbete "
+ "FROM primerconteo AS primer,"
+ "segundoconteo AS segundo,"
+ "(SELECT distinct primer.marbete as a,"
+ "STRING_AGG(CONCAT(primer.marbete,'.',primer.codigo,'.',primer.atributo,'.',primer.cantidad),'.' "
+ "ORDER BY marbete,codigo,atributo,cantidad) AS marbete "
+ "FROM primerconteo AS primer "
+ "GROUP BY primer.marbete) as cantidadp,"
+ "(SELECT distinct segundo.marbete as as,"
+ "STRING_AGG(CONCAT(segundo.marbete,'.',segundo.codigo,'.',segundo.atributo,'.',segundo.cantidad),'.' "
+ "ORDER BY marbete,codigo,atributo,cantidad) AS marbete "
+ "FROM segundoconteo AS segundo "
+ "GROUP BY segundo.marbete) as cantidads "
+ "where (primer.marbete||primer.codigo||primer.atributo||primer.cantidad)=(segundo.marbete||segundo.codigo||segundo.atributo||segundo.cantidad) "
+ "AND primer.marbete=cantidadp.a "
+ "AND primer.marbete=cantidads.as "
+ "AND cantidads.marbete=cantidadp.marbete "
+ "AND primer.almacen similar to ('"+almacenes+"')) AS TERCER "
+ "ON ubicaciones.marbete=tercer.marbete "
+ "where ubicaciones.almacen similar to ('"+almacenes+"') "
+ "and tercer.marbete is null) AS diferentes "
+ "WHERE primer.marbete=diferentes.marbete "
+ "and seg.marbete=diferentes.marbete "
+ "AND diferentes.marbete NOT IN (SELECT marbete FROM tercerconteo) "
+ "GROUP BY diferentes.marbete,diferentes.hueco,diferentes.almacen);"); 
		  System.out.println("NO INSERTA");
		  ps.execute();
		  System.out.println("HIZO");
		  ps= co.prepareStatement("insert into inventariofinal (SELECT "
+ "upper(LOWER( REPLACE(CAST(uuid_generate_v4()AS varchar(50)),'-',''))),"
+ "tercer.almacen,"
+ "tercer.ubicacion,"
+ "tercer.marbete,"
+ "tercer.codigo,"
+ "tercer.atributo,"
+ "tercer.cantidad,"
+ "'' as campouno,"
+ "'' as campodos,"
+ "now() "
+ "FROM (SELECT distinct PRIMER.marbete, "
+ "primer.almacen,"
+ "primer.ubicacion,"
+ "primer.atributo,"
+ "primer.codigo,"
+ "primer.cantidad "
+ "FROM primerconteo AS primer,"
+ "segundoconteo AS segundo,"
+ "(SELECT distinct primer.marbete as a,"
+ "STRING_AGG(CONCAT(primer.marbete,'.',primer.codigo,'.',primer.atributo,'.',primer.cantidad),'.' "
+ "ORDER BY marbete,codigo,atributo,cantidad) AS marbete "
+ "FROM primerconteo AS primer "
+ "GROUP BY primer.marbete) as cantidadp,"
+ "(SELECT distinct segundo.marbete as as,"
+ "STRING_AGG(CONCAT(segundo.marbete,'.',segundo.codigo,'.',segundo.atributo,'.',segundo.cantidad),'.' "
+ "ORDER BY marbete,codigo,atributo,cantidad) AS marbete "
+ "FROM segundoconteo AS segundo "
+ "GROUP BY segundo.marbete) as cantidads "
+ "where (primer.marbete||primer.codigo||primer.atributo||primer.cantidad)=(segundo.marbete||segundo.codigo||segundo.atributo||segundo.cantidad) "
+ "AND primer.marbete=cantidadp.a "
+ "AND primer.marbete=cantidads.as "
+ "AND cantidads.marbete=cantidadp.marbete "
+ "AND primer.almacen similar to ('"+almacenes+"')) AS TERCER "
+ "WHERE tercer.marbete "
+ "NOT IN (SELECT marbete FROM inventariofinal));");
		  System.out.println("NO INSERTA 2");
		  ps.execute();				  		
		  System.out.println("INSERTA 2");
		  co.close();
		  
		  mensaje="CONTEOS REGISTRADOS";
		  //}  						  				  		  
				  		  
	  } catch(Exception e)
		  {
		  e.printStackTrace();		  
		  }
	  System.out.print("Registros:" +registros);	  
	  return mensaje;
  }
  
}