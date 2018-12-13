package confirmarconteos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilerias.postgresql;
public class ConfirmarConteosPrueba
{
	public static final String SEPARATOR=",";
	public static final String QUOTE=",";
	public static int registros = 0;
	public static int fecha=0;
	public static String mensaje="";
	public static int hoy=0;
	static String resultMessage = "";
  public static String main(String almacenes)
  {
	  try
	  {
		  Class.forName("org.postgresql.Driver");		  
		  
		  Connection co = postgresql.getConexion();		  
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null;
		  //mensaje="NO RESTAN UBICACIONES POR CONTABILIZAR";
		  PreparedStatement ps= co.prepareStatement("SELECT * FROM tercerconteo WHERE almacen similar to ('"+almacenes+"')");
		  rs=ps.executeQuery();
		  //if(rs.next()){
			  
			  mensaje="CONTEOS REGISTRADOS PREVIAMENTE NO PUEDE REALIZARSE NUEVAMENTE";
		  //}
		  //else{		  		  		  				  
		  ps= co.prepareStatement("insert into tercerconteo ("
+ "SELECT '' AS CODIGO,"
+ "'' AS atributo,"
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
+ "null as fecha, "
+ "ubicaciones.hueco,"
+ "ubicaciones.almacen "
+ "FROM UBICACIONES LEFT OUTER JOIN "
+ "(SELECT distinct PRIMER.marbete "
+ "FROM primerconteo AS primer,"
+ "segundoconteo AS segundo,"
+ "(SELECT distinct primer.marbete as a, "
+ "STRING_AGG(CONCAT(primer.marbete,'.',primer.codigo,'.',primer.atributo,'.',round(primer.cantidad::numeric,2)),'.' "
+ "ORDER BY marbete,codigo,cantidad) AS marbete "
+ "FROM primerconteo AS primer "
+ "GROUP BY primer.marbete) as cantidadp,"
+ "(SELECT distinct segundo.marbete as as,"
+ "STRING_AGG(CONCAT(segundo.marbete,'.',segundo.codigo,'.',segundo.atributo,'.',round(segundo.cantidad::numeric,2)),'.' "
+ "ORDER BY marbete,codigo,cantidad) AS marbete "
+ "FROM segundoconteo AS segundo "
+ "GROUP BY segundo.marbete) as cantidads "
+ "where (primer.marbete||primer.codigo||primer.atributo||round(primer.cantidad::NUMERIC,2))!="
+ "(segundo.marbete||segundo.codigo||segundo.atributo||round(segundo.cantidad::NUMERIC,2)) "
+ "AND primer.marbete=cantidadp.a "
+ "AND primer.marbete=cantidads.as "
+ "AND cantidads.marbete=cantidadp.marbete "
+ "AND primer.almacen similar to ('"+almacenes+"')) AS TERCER "
+ "ON ubicaciones.marbete=tercer.marbete "
+ "where ubicaciones.almacen similar to ('"+almacenes+"') "
+ "and tercer.marbete is null) AS diferentes "
+ "WHERE primer.marbete=diferentes.marbete "
+ "AND seg.marbete=diferentes.marbete "
+ "AND diferentes.marbete  NOT IN (SELECT marbete FROM tercerconteo) "
+ "GROUP BY diferentes.marbete,"
+ "diferentes.hueco,diferentes.almacen);"); 
		  System.out.println("NO INSERTA");
		  ps.execute();
		  System.out.println("HIZO");
		  ps= co.prepareStatement("INSERT INTO inventariofinal ("
+ "SELECT upper(LOWER( REPLACE(CAST(uuid_generate_v4()AS varchar(50)),'-',''))),"
+ "tercer.almacen,"
+ "tercer.ubicacion,"
+ "tercer.marbete,"
+ "tercer.codigo,"
+ "tercer.atributo,"
+ "tercer.cantidad,"
+ "'' as campouno,"
+ "'' as campodos,"
+ "now() FROM "
+ "(SELECT distinct PRIMER.marbete,"
+ "primer.almacen,"
+ "primer.ubicacion,"
+ "primer.codigo,"
+ "primer.atributo,"
+ "primer.cantidad "
+ "FROM primerconteo AS primer,"
+ "segundoconteo AS segundo,"
+ "(SELECT distinct primer.marbete as a,"
+ "STRING_AGG(CONCAT(primer.marbete,'.',primer.codigo,'.',primer.atributo,'.',round(primer.cantidad::numeric,2)),'.' "
+ "ORDER BY marbete,codigo,cantidad) AS marbete FROM primerconteo AS primer "
+ "GROUP BY primer.marbete) as cantidadp,"
+ "(SELECT distinct segundo.marbete as as,"
+ "STRING_AGG(CONCAT(segundo.marbete,'.',segundo.codigo,'.',segundo.atributo,'.',round(segundo.cantidad::numeric,2)),'.' "
+ "ORDER BY marbete,codigo,cantidad) AS marbete "
+ "FROM segundoconteo AS segundo "
+ "GROUP BY segundo.marbete) as cantidads "
+ "where (primer.marbete||primer.codigo||primer.atributo||ROUND(primer.cantidad::NUMERIC,2))!="
+ "(segundo.marbete||segundo.codigo||segundo.atributo||ROUND(segundo.cantidad::NUMERIC,2)) "
+ "AND primer.marbete=cantidadp.a "
+ "AND primer.marbete=cantidads.as "
+ "AND cantidads.marbete=cantidadp.marbete "
+ "AND primer.almacen similar to ('"+almacenes+"')) AS TERCER "
+ "WHERE tercer.marbete  NOT IN (SELECT marbete FROM inventariofinal));"); 
		  System.out.println("NO INSERTA 2");
		  ps.execute();				  		
		  System.out.println("INSERTA 2");
		  rs.close();
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