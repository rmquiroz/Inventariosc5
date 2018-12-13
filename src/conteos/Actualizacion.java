package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilerias.postgresql;
public class Actualizacion {
	public static String main(String conteo, String cantidad, String codigo, String marbete,String usuario,String atributo) {
		String alhu="";
		Connection con=postgresql.getConexion();		
		try {			
			PreparedStatement psinsert = con.prepareStatement("UPDATE "+conteo+" SET capturado=(SELECT id FROM usuarios "
+ "WHERE usuario='"+usuario+"'), cantidad=cantidad::numeric+"+cantidad+" where codigo like '"+codigo+"' "
+ "and marbete like '"+marbete+"' AND atributo like '"+atributo+"' "
+ "AND fecha >  now()::DATE - CAST('4 days' AS INTERVAL)");
			psinsert.execute();		
			System.out.println(psinsert);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alhu;
	}
}