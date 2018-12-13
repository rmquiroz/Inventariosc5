package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilerias.postgresql;
public class SumaCantidades {
	public static String main(String marbete,String codigo,String atributo) {
		String alhu="0";
		Connection con=postgresql.getConexion();
		try {			
			ResultSet rs = null;
			PreparedStatement ps= con.prepareStatement("SELECT cantidad "
+ "FROM tercerconteofinal WHERE marbete='"+marbete+"' "
+ "AND codigo='"+codigo+"' "
+ "AND atributo='"+atributo+"'");
			rs = ps.executeQuery();
			while(rs.next()){
				alhu=rs.getString(1);
			}
			System.out.println(" Termina Query.......");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alhu;
	}
}