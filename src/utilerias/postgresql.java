package utilerias;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class postgresql {
	public static Connection getConexion() {

		Connection con = null;
			try {
			//String conOp=Conexion.Openbravo();
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
			String usuario = "postgres";
			String pass = "s3st2m1s4e";
			
			con = DriverManager.getConnection(url, usuario, pass);

		} catch (ClassNotFoundException e) {
			System.out.println("Conexion Fallida DRIVER------>>>");
			e.printStackTrace();
		} catch (SQLException e) {
			try{
			
				
				
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://201.149.89.164:5932/inventarios_c5";
			String usuario = "postgres";
			String pass = "s3st2m1s4e";		
			con = DriverManager.getConnection(url, usuario, pass);
			} catch (ClassNotFoundException ex) {
				System.out.println("Conexion Fallida DRIVER------>>>");
				e.printStackTrace();
			} catch (SQLException ex) {
				System.out.println("Conexion BD NO CONECTA------>>>");
			}
		} 
		return con;
	}
	public static Connection getConexionOpen() {
		Connection con = null;
			try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
			String usuario = "postgres";
			String pass = "s3st2m1s4e";			
			con = DriverManager.getConnection(url, usuario, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Conexion Fallida DRIVER------>>>");
			e.printStackTrace();
		} catch (SQLException e) {
			try{			
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://201.149.89.163:5932/openbravo";
			String usuario = "postgres";
			String pass = "s3st2m1s4e";			
			con = DriverManager.getConnection(url, usuario, pass);
			} catch (ClassNotFoundException ex) {
				System.out.println("Conexion Fallida DRIVER------>>>");
				e.printStackTrace();
			} catch (SQLException ex) {
				System.out.println("Conexion BD NO CONECTA------>>>");
			}
		} 
		return con;
	}
}