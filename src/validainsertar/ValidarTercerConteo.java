package validainsertar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilerias.postgresql;

public class ValidarTercerConteo 
{
	static String mensaje;
	public static String main(String marbete)
	  {			  
		  try
		  {			  			  
			  Connection co = postgresql.getConexion();		  
			  System.out.println("Ejecutando Query.......");
			  ResultSet rs = null;
			  PreparedStatement ps= co.prepareStatement("SELECT marbete FROM inventariofinal WHERE marbete like '"+marbete+"'");
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
