package cierreFinal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilerias.postgresql;
public class CierreTercerConteo{
	static String mensaje;
	
	public static String main(String almacenes){
		String auxiliar="";
		try {
			Class.forName("org.postgresql.Driver");
		Connection cn = postgresql.getConexion();
		String[] almacen;
		
		  System.out.println("Ejecutando Query.......");
		  almacen=almacenes.split("\\|");
		  
		  for(int x=0;x<almacen.length;x++)
		  {
			  auxiliar="TERCER CONTEO CERRADO";
			  PreparedStatement   ps = cn.prepareStatement("insert into inventariofinal (select "
+ "upper(LOWER( REPLACE(CAST(uuid_generate_v4()AS varchar(50)),'-',''))),"
+ "almacen,"
+ "ubicacion,"
+ "marbete,"
+ "codigo,"
+ "atributo,"
+ "round(cantidad::numeric,'2'),"
+ "'' as campouno,"
+ "'' as campodos,"
+ "now() "
+ "FROM tercerconteofinal "
+ "WHERE marbete NOT IN (SELECT marbete FROM inventariofinal) "
+ "AND almacen = '"+almacen[x]+"')");
				ps.execute();			   			
		  }			 
		  cn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auxiliar;
	}
}