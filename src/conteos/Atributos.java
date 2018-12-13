package conteos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utilerias.postgresql;
public class Atributos {
	public static ArrayList main() {		
		Connection con=postgresql.getConexion();
		ArrayList<String> arr=new ArrayList<String>();
		try {			
			ResultSet rs = null;
			PreparedStatement ps = con.prepareStatement("select atributo from atributos WHERE estado='Y' order by atributo");
			rs = ps.executeQuery();
			System.out.println("CONSULTA"+ps);						
			while(rs.next()){
			arr.add(rs.getString(1));
			}
			System.out.println(" Termina Query.......");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
}
