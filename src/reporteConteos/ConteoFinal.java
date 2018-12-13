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

import utilerias.postgresql;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ConteoFinal {
	static String mensaje;
	static Date date = new Date();
	static DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	public static String main(String almacenes, String repositorio){
		
		try {
			Class.forName("org.postgresql.Driver");
		Connection cn = postgresql.getConexion();		  
		Connection co = postgresql.getConexionOpen();
		String[] almacen;
		int h=0;
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null,rsp = null;
		  WritableWorkbook wb = Workbook.createWorkbook(new File("/INFORMES/"+repositorio+"InventarioFinal"+almacenes.replace("|","-")+hourFormat.format(date)+".xls"));
		  almacen=almacenes.split("\\|");
		  for(int x=0;x<almacen.length;x++)
		  {		
			  			  
			  PreparedStatement ps = cn.prepareStatement("SELECT "
+ "ub.almacen,"
+ "ub.hueco,"
+ "ub.marbete,"
+ "CASE WHEN invent.codigo IS NULL THEN 'FALTA CONFIRMACION' ELSE invent.codigo END AS cod,"
+ "CASE WHEN invent.atributo IS NULL THEN 'FALTA CONFIRMACION' ELSE invent.atributo END AS atributo,"
+ "CASE WHEN invent.cantidad IS NULL THEN 'FALTA CONFIRMACION' ELSE invent.cantidad END AS cant,"
+ "to_char(invent.fecha,'DD-MM-YYYY') AS fecha_confirmado "
+ "FROM ubicaciones AS ub LEFT JOIN "
+ "inventariofinal AS invent "
+ "ON ub.marbete=invent.marbete "
+ "WHERE ub.almacen similar to ('"+almacen[x]+"') ORDER BY almacen,ub.marbete");
			  rs = ps.executeQuery();
			  ArrayList<String> col=new ArrayList<String>(); 
			  while (rs.next())
			  {
				  col.add(rs.getString(1));
				  col.add(rs.getString(2));
				  col.add(rs.getString(3));				
				  col.add(rs.getString(4));
				  col.add(rs.getString(5));
				  rsp=null;
				  ps=co.prepareStatement("SELECT description FROM m_product WHERE value='"+rs.getString(4)+"'");
				  rsp=ps.executeQuery();
				  while (rsp.next())
				  {
					  col.add(rsp.getString(1));
				  
				  }
				  col.add(rs.getString(6));
				  col.add(rs.getString(7));				  				  				  
				  col.add("sp");
			  }			
			  WritableSheet ws = wb.createSheet("INVENTARIOFINAL"+almacen[x], h);
			  h++;
			  WritableFont wf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD);
			  WritableCellFormat cf = new WritableCellFormat(wf);	        
			  int column = 0;
			  int row = 0;
			  Iterator<String> i = col.iterator();	        
			  Label ec1 = new Label(column, row, "ALMACEN", cf);	        
			  ws.addCell(ec1);
			  column++;
			  Label ec2 = new Label(column, row, "HUECO", cf);
			  ws.addCell(ec2);
			  column++;
			  Label ec3 = new Label(column, row, "MARBETE", cf);
			  ws.addCell(ec3);
			  column++;
			  Label ec4 = new Label(column, row, "CODIGO OB3", cf);
			  ws.addCell(ec4);
			  column++;
			  Label ec8 = new Label(column, row, "ATRIBUTO", cf);
			  ws.addCell(ec8);
			  column++;
			  Label ec5 = new Label(column, row, "DESCRIPCION", cf);
			  ws.addCell(ec5);
			  column++;
			  Label ec6 = new Label(column, row, "CANTIDAD", cf);
			  ws.addCell(ec6);
			  column++;
			  Label ec7 = new Label(column, row, "FECHA CONFIRMADA", cf);
			  ws.addCell(ec7);
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
