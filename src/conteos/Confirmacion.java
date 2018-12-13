package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilerias.postgresql;
public class Confirmacion {
	public static String main(String marbete) {
		String alhu="";
		Connection con=postgresql.getConexion();
		
		try {			
			ResultSet rs = null;
			PreparedStatement ps = con.prepareStatement("select almacen,ubicacion from tercerconteo where marbete like '"+marbete+"'");
			rs = ps.executeQuery();
			while(rs.next()){
				alhu=""+rs.getString(1);
				alhu=alhu+"|"+rs.getString(2);
			}
			System.out.println(" Termina Query.......");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alhu;
	}
}
