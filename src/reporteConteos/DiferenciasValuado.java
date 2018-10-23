package reporteConteos;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DiferenciasValuado 
{
	static String mensaje;
	//static String inventarios="jdbc:postgresql://201.149.89.164:5932/inventarios";
	//static String productivo="jdbc:postgresql://201.149.89.163:5932/openbravo";
	static String inventarios="jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
	static String usuario="postgres",contra="s3st2m1s4e";
	static String productivo="jdbc:postgresql://10.1.250.20:5932/openbravo";
	static Date date = new Date();
	static DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	public static String main(String almacenes, String repositorio){
		
		try {
			Class.forName("org.postgresql.Driver");
		Connection cn = DriverManager.getConnection(inventarios, usuario, contra);		  
		Connection co = DriverManager.getConnection(productivo, usuario, contra);
		String[] almacen;
		int h=0;
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null;
		  WritableWorkbook wb = Workbook.createWorkbook(new File("/INFORMES/"+repositorio+"DiferenciasValuado"+almacenes.replace("|","-")+hourFormat.format(date)+".xls"));
		  almacen=almacenes.split("\\|");
		  for(int x=0;x<almacen.length;x++)
		  {					  			  
			  PreparedStatement ps = cn.prepareStatement("select hueco,"
+ "codigo,"
+ "prod.description,"
+ "prod.coststd,"
+ "sum(cantidad) as cantidad,"
+ "sum(Cantidad*(case when prod.coststd is null or prod.coststd='' then '0' else prod.coststd end)::numeric) as monto "
+ "from ((select hueco,"
+ "codigo,"
+ "cantidad::numeric as cantidad,"
+ "'1' as tipo "
+ "from inventariofinal "
+ "where almacen like '"+almacen[x]+"') "
+ "union (select ubicacion,"
+ "codigo,"
+ "cantidad_original_uom::numeric*-1,'2' as tipo "
+ "from "
+ "inventario_teorico "
+ "where almacen like '"+almacen[x]+"')) as diferencias,"
+ "m_product as prod "
+ "where codigo=prod.value "
+ "group by hueco,"
+ "codigo,"
+ "prod.description,"
+ "prod.coststd "
+ "order by 1,1 asc");
			  rs = ps.executeQuery();
			  ArrayList<String> col=new ArrayList<String>(); 
			  while (rs.next())
			  {
				  col.add(rs.getString(1));
				  col.add(rs.getString(2));				  				  
				  col.add(rs.getString(3));
				  col.add(rs.getString(4));
				  col.add(rs.getString(5));
				  col.add(rs.getString(6));
				  col.add("sp");
			  }			
			  WritableSheet ws = wb.createSheet("FisicovsTeorico_"+almacen[x], h);
			  h++;
			  WritableFont wf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD);
			  WritableCellFormat cf = new WritableCellFormat(wf);	        
			  int column = 0;
			  int row = 0;
			  Iterator<String> i = col.iterator();	        
			  Label ec1 = new Label(column, row, "HUECO", cf);	        
			  ws.addCell(ec1);
			  column++;
			  Label ec2 = new Label(column, row, "CODIGO", cf);
			  ws.addCell(ec2);
			  column++;
			  Label ec3 = new Label(column, row, "DESCRIPCION", cf);
			  ws.addCell(ec3);
			  column++;
			  Label ec4 = new Label(column, row, "COSTOSTD", cf);
			  ws.addCell(ec4);
			  column++;
			  Label ec5 = new Label(column, row, "CANTIDAD", cf);
			  ws.addCell(ec5);
			  column++;
			  Label ec6 = new Label(column, row, "MONTO", cf);
			  ws.addCell(ec6);
			  
			  row = 1;
			  column = 0;
			  while (i.hasNext())
			  {
				  String campo = (String)i.next();
				  if (campo == null) 
				  {
					  campo = " ";
				  }
				  if (campo.equals("sp"))
				  {
					  i.remove();
					  row++;
					  column = 0;
				  }
				  else
				  {
					  Label l1 = new Label(column, row, campo, cf);
					  ws.addCell(l1);
					  column++;
				  }
			  }							 
			  							  
							  
		  }		
		  wb.write();
		  wb.close();
		  co.close();
		  cn.close();
		  mensaje="Informe Generado Correctamente";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  		  
		
		return mensaje;
	}

}
