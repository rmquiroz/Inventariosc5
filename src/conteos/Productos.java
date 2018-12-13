package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilerias.postgresql;
public class Productos {
	public static String main(String codigo) {
		String alhu="";
		Connection con=postgresql.getConexionOpen();
		try {			
			ResultSet rs = null;
			PreparedStatement ps = con.prepareStatement("SELECT prod.description,"
+ "uom.name "
+ "from m_product as prod,"
+ "c_uom as uom "
+ "where prod.c_uom_id=uom.c_uom_id "
+ "and prod.value like '"+codigo+"'");
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