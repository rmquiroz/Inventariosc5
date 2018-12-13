package inventario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilerias.postgresql;
public class ActivaAtributos 
{
	
	public static String mensaje="HECHO";
	public static String main()
	{
		try 
		{
			
			Class.forName("org.postgresql.Driver");		
			Connection cn =  postgresql.getConexion();	  
			System.out.println("Ejecutando Query.......");
			ResultSet rs = null;			
			PreparedStatement ps = cn.prepareStatement("SELECT atributo FROM inventario_teorico GROUP BY atributo");		 
			rs = ps.executeQuery();		  
			String valor = "";
			System.out.println(" Termina Consulta.......");
			while (rs.next())
			{
			valor=valor+""+rs.getString(1)+"|";
			}
			ps = cn.prepareStatement("UPDATE atributos SET ESTADO='Y' WHERE atributo similar to('"+valor+"')");
			System.out.println(""+ps);
			ps.execute();
			cn.close();
		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}	// TODO Auto-generated catch block
		
				return mensaje;
	}
}