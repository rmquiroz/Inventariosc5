package validainsertar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilerias.postgresql;

public class validatercerconteoinsertar 
{
	static String mensaje;
	public static String main(String marbete,String tabla,String tabla2)
	  {			  
		  try
		  {			  		  			 
			  Connection co = postgresql.getConexion();
			  
			  System.out.println("Ejecutando Query.......");
			  ResultSet rs = null;
			  //mensaje="NO RESTAN UBICACIONES POR CONTABILIZAR";
			  PreparedStatement ps= co.prepareStatement("SELECT marbete "
+ "FROM "+tabla+" "
+ "WHERE marbete like '"+marbete+"' "
+ "union "
+ "select marbete "
+ "from "+tabla2+" "
+ "where marbete like '"+marbete+"' ");
			  System.out.println("V"+ps);
			  rs=ps.executeQuery();
			  if(rs.next()){
				  mensaje="HAY";				  
			  }
			  else{
				  mensaje="NO";
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
