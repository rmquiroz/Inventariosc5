package utilerias;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class postgresql {
	static final String inventario = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
	public static Connection getConexion() {

		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			String usuario = "postgres";
			String pass = "s3st2m1s4e";

			con = DriverManager.getConnection(inventario, usuario, pass);

		} catch (ClassNotFoundException e) {

			System.out.println("Conexion Fallida DRIVER------>>>");

			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Conexion BD NO CONECTA------>>>");
			e.printStackTrace();
		}
		return con;

	}
	
}