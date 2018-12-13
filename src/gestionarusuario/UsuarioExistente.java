package gestionarusuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilerias.postgresql;

public class UsuarioExistente
{
	static String mensaje;
	public static String main(String usuario)
	  {			  
		  try
		  {			  			  
			  Connection co = postgresql.getConexion();		  
			  System.out.println("Ejecutando Query.......");
			  ResultSet rs = null;
			  PreparedStatement ps= co.prepareStatement("SELECT * FROM usuarios WHERE usuario like '"+usuario+"'");
			  System.out.println("V"+ps);
			  rs=ps.executeQuery();
			  if(rs.next()){
				  mensaje="EXISTE";				  
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