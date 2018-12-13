package validainsertar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilerias.postgresql;

public class ValidaInsAct 
{
	static String mensaje;
	public static String main(String codigo,String marbete,String conteo,String atributo)
	  {			  
		  try
		  {
			  Class.forName("org.postgresql.Driver");		  			  
			  Connection co = postgresql.getConexion();		  
			  System.out.println("Ejecutando Query.......");
			  ResultSet rs = null;
			  //mensaje="NO RESTAN UBICACIONES POR CONTABILIZAR";
			  PreparedStatement ps= co.prepareStatement("SELECT * "
+ "FROM "+conteo+" "
+ "where codigo like '"+codigo+"' "
+ "and marbete like '"+marbete+"' "
+ "AND atributo like '"+atributo+"'"
+ "AND fecha >  now()::DATE - CAST('4 days' AS INTERVAL)");
			  System.out.println("V"+ps);
			  rs=ps.executeQuery();
			  if(rs.next()){
				  mensaje="UPDATE";				  
			  }
			  else{
				  mensaje="INSERT";
			  }
			  System.out.println("Valida"+mensaje);
			  co.close();
			  } catch(Exception e)
			  {
			  
			  System.out.println("Error:"+e);
			  }
		  	  
		  return mensaje;
	  }
}
