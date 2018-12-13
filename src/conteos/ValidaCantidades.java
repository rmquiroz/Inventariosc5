package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilerias.postgresql;
public class ValidaCantidades {
	public static String main(String marbete,String codigo,String cantidad,String atributo) {
		String alhu="0";
		Connection con=postgresql.getConexion();
		try {			
			ResultSet rs = null;
			PreparedStatement ps= con.prepareStatement("SELECT "
+ "(marbete||'.'||codigo||'.'||atributo||'.'||round(cantidad::numeric,2)) "
+ "from primerconteo where "
+ "(marbete||'.'||codigo||'.'||atributo||'.'||round(cantidad::numeric,2))='"+marbete+"."+codigo+"."+atributo+".'||round('"+cantidad+"'::numeric,2) "
+ "UNION "
+ "SELECT "
+ "(marbete||'.'||codigo||'.'||atributo||'.'||round(cantidad::numeric,2)) "
+ "from SEGUNDOCONTEO where "
+ "(marbete||'.'||codigo||'.'||atributo||'.'||round(cantidad::numeric,2))='"+marbete+"."+codigo+"."+atributo+".'||round('"+cantidad+"'::numeric,2) ");
			System.out.println(""+ps);
							
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