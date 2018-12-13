package gestionarusuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import utilerias.postgresql;

public class CrearUsuario
{
	static String mensaje;
	public static String main(String usuario, String nombre, String apellido, String contra, String jerarquia,String registra, String departamento)
	  {			  
		  try
		  {			  			  
			  Connection co = postgresql.getConexion();		  
			  System.out.println("Ejecutando Query.......");
			  PreparedStatement ps= co.prepareStatement("INSERT INTO usuarios VALUES ("
+ "(SELECT upper(LOWER( REPLACE(CAST(uuid_generate_v4()AS varchar(50)),'-','')))),"
+ "'"+usuario+"',"
+ "'"+nombre+"',"
+ "'"+apellido+"',"
+ "'"+contra+"',"
+ "(SELECT id_departamento FROM departamento WHERE nombre='"+departamento+"'),"
+ "(SELECT CASE "
+ "WHEN '"+jerarquia+"'='Primer Conteo' THEN 1 "
+ "WHEN '"+jerarquia+"'='Segundo Conteo' THEN 2 "
+ "WHEN '"+jerarquia+"'='Tercer Conteo' THEN 3"
+ "WHEN '"+jerarquia+"'='Administrador' THEN 4 END))");
			  System.out.println("V"+ps);
			  ps.execute();
			  
			  System.out.println("Valida"+mensaje);
			  co.close();
			  } catch(Exception e)
			  {			  
				  System.out.println("Error:"+e);
			  }		  	  
		  return mensaje;
	  }
}