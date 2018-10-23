package validainsertar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidaInsAct 
{
	static String mensaje,inventarios="jdbc:postgresql://10.1.250.24:5932/inventarios_c5",usuario="postgres",contra="s3st2m1s4e";
	public static String main(String codigo,String marbete,String conteo,String atributo)
	  {			  
		  try
		  {
			  Class.forName("org.postgresql.Driver");		  			  
			  Connection co = DriverManager.getConnection(inventarios, usuario, contra);		  
			  System.out.println("Ejecutando Query.......");
			  ResultSet rs = null;
			  //mensaje="NO RESTAN UBICACIONES POR CONTABILIZAR";
			  PreparedStatement ps= co.prepareStatement("SELECT * FROM "+conteo+" where codigo like '"+codigo+"' and marbete like '"+marbete+"' "
+ "AND fecha >  now()::DATE - CAST('4 days' AS INTERVAL) AND atributo='"+atributo+"'");
			  System.out.println("V"+ps);
			  rs=ps.executeQuery();
			  if(rs.next()){
				  mensaje="UPDATE";				  
			  }
			  else{
				  mensaje="INSERT";
			  }
			  System.out.println("Valida"+mensaje);
			  co.close();
			  } catch(Exception e)
			  {
			  
			  System.out.println("Error:"+e);
			  }
		  	  
		  return mensaje;
	  }
}
