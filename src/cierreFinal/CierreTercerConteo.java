package cierreFinal;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CierreTercerConteo{
	static String mensaje,inventarios="jdbc:postgresql://10.1.250.24:5932/inventarios_c5",usuario="postgres",contra="s3st2m1s4e",productivo="jdbc:postgresql://10.1.250.20:5932/openbravo";
	public static String main(String almacenes){
		String auxiliar="";
		try {
			Class.forName("org.postgresql.Driver");
		Connection cn = DriverManager.getConnection(inventarios, usuario, contra);		  		
		String[] almacen;
		int h=0;
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null,rsp = null;
		  
		  almacen=almacenes.split("\\|");
		  
		  for(int x=0;x<almacen.length;x++)
		  {		
			  			  
			  PreparedStatement ps = cn.prepareStatement("SELECT * FROM tercerconteo as ter WHERE ter.marbete NOT IN (SELECT marbete FROM tercerconteofinal WHERE "
+ "almacen='"+almacen[x]+"') AND ter.almacen='"+almacen[x]+"'");
			  rs = ps.executeQuery();			   
			  
			  
		  }
			  
				  auxiliar="TERCER CONTEO CERRADO";
				  PreparedStatement  ps = cn.prepareStatement("insert into inventariofinal("
+ "select upper(LOWER( REPLACE(CAST(uuid_generate_v4()AS varchar(50)),'-',''))),"
+ "almacen,"
+ "ubicacion,"
+ "marbete,"
+ "codigo,"
+ "atributo,"
+ "cantidad,"
+ "'' as campouno,"
+ "'' as campodos,"
+ "now() "
+ "FROM tercerconteofinal "
+ "WHERE marbete not in (SELECT marbete FROM inventariofinal))");
				ps.execute();			   
			 
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
