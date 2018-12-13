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

public class ConteosExcel {
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
		  WritableWorkbook wb = Workbook.createWorkbook(new File("/INFORMES/"+repositorio+"Conteos"+almacenes.replace("|","-")+hourFormat.format(date)+".xls"));
		  almacen=almacenes.split("\\|");
		  for(int x=0;x<almacen.length;x++)
		  {		
			  			  
			  PreparedStatement ps = cn.prepareStatement("SELECT ubi.almacen,"
+ "ubi.marbete,"
+ "ubi.hueco,"
+ " usu.nombre || ' ' || usu.apellido AS CAPTURADO_POR,"
+ "CASE WHEN to_char(primer.fecha,'DD-MM-YYYY') IS NULL THEN 'SIN CAPTURA' ELSE to_char(primer.fecha,'DD-MM-YYYY') END as fecha__captura,"
+ "CASE WHEN primer.codigo IS NULL THEN 'SIN CAPTURA' else primer.codigo end as codigo_conteo1,"
+ "atributo AS atributo,"
+ "CASE when primer.cantidad is null then 'SIN CAPTURA' else primer.cantidad end as cantidad_conteo1 "
+ "FROM ubicaciones AS ubi LEFT JOIN primerconteo AS primer ON "
+ "ubi.almacen=primer.almacen AND ubi.marbete=primer.marbete "
+ "LEFT JOIN usuarios AS usu ON usu.id=primer.capturado "
+ "WHERE ubi.almacen similar to ('"+almacen[x]+"') ORDER BY ubi.almacen,primer.marbete ASC");
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
				  col.add(rs.getString(7));
				  rsp=null;
				  ps=co.prepareStatement("SELECT description FROM m_product WHERE value='"+rs.getString(6)+"'");
				  rsp=ps.executeQuery();
				  while (rsp.next())
				  {
					  col.add(rsp.getString(1));
				  }
				  col.add(rs.getString(8));
				  col.add("sp");
			  }			
			  WritableSheet ws = wb.createSheet("Primer_Conteo"+almacen[x], h);
			  h++;
			  WritableFont wf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD);
			  WritableCellFormat cf = new WritableCellFormat(wf);	        
			  int column = 0;
			  int row = 0;
			  Iterator<String> i = col.iterator();	        
			  Label ec1 = new Label(column, row, "ALMACEN", cf);	        
			  ws.addCell(ec1);
			  column++;
			  Label ec2 = new Label(column, row, "MARBETE", cf);
			  ws.addCell(ec2);
			  column++;
			  Label ec3 = new Label(column, row, "HUECO", cf);
			  ws.addCell(ec3);
			  column++;
			  Label ec8 = new Label(column, row, "CAPTURADO POR", cf);
			  ws.addCell(ec8);
			  column++;
			  Label ec4 = new Label(column, row, "FECHA DE CAPTURA", cf);
			  ws.addCell(ec4);
			  column++;
			  Label ec5 = new Label(column, row, "CODIGO DE CONTEO", cf);
			  ws.addCell(ec5);
			  column++;
			  Label ec9 = new Label(column, row, "ATRIBUTO", cf);
			  ws.addCell(ec9);
			  column++;
			  Label ec6 = new Label(column, row, "DESCRIPCION DE CONTEO", cf);
			  ws.addCell(ec6);
			  column++;
			  Label ec7 = new Label(column, row, "CANTIDAD CONTABILIZADA", cf);
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
			  ps = cn.prepareStatement("SELECT ubi.almacen,"
+ "ubi.marbete,"
+ "ubi.hueco,"
+ "usu.nombre || ' ' || usu.apellido AS CAPTURADO_POR,"
+ "CASE WHEN to_char(segundo.fecha,'DD-MM-YYYY') IS NULL THEN 'SIN CAPTURA' ELSE to_char(segundo.fecha,'DD-MM-YYYY') END as fecha__captura,"
+ "CASE WHEN segundo.codigo IS NULL THEN 'SIN CAPTURA' else segundo.codigo end as codigo_conteo2,"
+ "atributo AS ATRIBUTO,"
+ "CASE when segundo.cantidad is null then 'SIN CAPTURA' else segundo.cantidad end as cantidad_conteo2 FROM ubicaciones AS ubi "
+ "LEFT JOIN segundoconteo AS segundo ON ubi.almacen=segundo.almacen "
+ "AND ubi.marbete=segundo.marbete "
+ "LEFT JOIN usuarios AS usu ON usu.id=segundo.capturado "
+ "WHERE ubi.almacen similar to ('"+almacen[x]+"') ORDER BY "
+ "ubi.almacen,segundo.marbete ASC");
			  rs = ps.executeQuery();
			  ArrayList<String> colseg=new ArrayList<String>(); 
			  while (rs.next())
			  {
				  colseg.add(rs.getString(1));
				  colseg.add(rs.getString(2));
				  colseg.add(rs.getString(3));				
				  colseg.add(rs.getString(4));
				  colseg.add(rs.getString(5));
				  colseg.add(rs.getString(6));
				  colseg.add(rs.getString(7));
				  rsp=null;
				  ps=co.prepareStatement("SELECT description FROM m_product WHERE value='"+rs.getString(6)+"'");
				  rsp=ps.executeQuery();
				  while (rsp.next())
				  {
					  colseg.add(rsp.getString(1));
				  }
				  colseg.add(rs.getString(8));
				  colseg.add("sp");
			  }			
			  WritableSheet ws2 = wb.createSheet("Segundo_Conteo"+almacen[x], h);
			  h++;
			  column = 0;
			  row = 0;
			  Iterator<String> iseg = colseg.iterator();	        
			  Label ecseg1 = new Label(column, row, "ALMACEN", cf);	        
			  ws2.addCell(ecseg1);
			  column++;
			  Label ecseg2 = new Label(column, row, "MARBETE", cf);
			  ws2.addCell(ecseg2);
			  column++;
			  Label ecseg3 = new Label(column, row, "HUECO", cf);
			  ws2.addCell(ecseg3);
			  column++;
			  Label ecseg8 = new Label(column, row, "CAPTURADO POR", cf);
			  ws2.addCell(ecseg8);
			  column++;
			  Label ecseg4 = new Label(column, row, "FECHA DE CAPTURA", cf);
			  ws2.addCell(ecseg4);
			  column++;
			  Label ecseg5 = new Label(column, row, "CODIGO DE CONTEO", cf);
			  ws2.addCell(ecseg5);
			  column++;
			  Label ecseg9 = new Label(column, row, "ATRIBUTO", cf);
			  ws2.addCell(ecseg9);
			  column++;
			  Label ecseg6 = new Label(column, row, "DESCRIPCION DE PRODUCTO", cf);
			  ws2.addCell(ecseg6);
			  column++;
			  Label ecseg7 = new Label(column, row, "CANTIDAD CONTABILIZADA", cf);
			  ws2.addCell(ecseg7);
			  row = 1;
			  column = 0;
			  while (iseg.hasNext())
			  {
				  String campo = (String)iseg.next();
								  if (campo == null) 
								  {
									  campo = " ";
								  }	
								  if (campo.equals("sp"))
								  {
									  iseg.remove();
									  row++;
									  column = 0;
								  }
								  else
								  {
									  Label l1 = new Label(column, row, campo, cf);
									  ws2.addCell(l1);
									  column++;
								  }
							  }
							  ps = cn.prepareStatement("select almacen,"
+ "marbete,"
+ "ubicacion,"
+ "usu.nombre || ' ' || usu.apellido AS CAPTURADO_POR,"
+ "to_char(fecha,'DD-MM-YYYY'),"
+ "codigo,"
+ "atributo,"
+ "cantidad "
+ "from tercerconteofinal LEFT JOIN usuarios AS usu ON usu.id=tercerconteofinal.capturado "
+ "WHERE almacen like '"+almacen[x]+"'");
							  rs = ps.executeQuery();
							  ArrayList<String> colter=new ArrayList<String>(); 
							  while (rs.next())
							  {
								  colter.add(rs.getString(1));
								  colter.add(rs.getString(2));
								  colter.add(rs.getString(3));				
								  colter.add(rs.getString(4));
								  colter.add(rs.getString(5));
								  colter.add(rs.getString(6));
								  colter.add(rs.getString(7));
								  rsp=null;
								  ps=co.prepareStatement("SELECT description FROM m_product WHERE value='"+rs.getString(6)+"'");
								  rsp=ps.executeQuery();
								  while (rsp.next())
								  {
									  colter.add(rsp.getString(1));
								  }
								  colter.add(rs.getString(8));
								  colter.add("sp");
							  }			
							  WritableSheet ws3 = wb.createSheet("Tercer_Conteo"+almacen[x],h);
							  h++;
							  column = 0;
							  row = 0;
							  Iterator<String> iter = colter.iterator();	        
							  Label ecter1 = new Label(column, row, "ALMACEN", cf);	        
							  ws3.addCell(ecter1);
							  column++;
							  Label ecter2 = new Label(column, row, "MARBETE", cf);
							  ws3.addCell(ecter2);
							  column++;
							  Label ecter3 = new Label(column, row, "HUECO", cf);
							  ws3.addCell(ecter3);
							  column++;
							  Label ecter8 = new Label(column, row, "CAPTURADO POR", cf);
							  ws3.addCell(ecter8);
							  column++;
							  Label ecter4 = new Label(column, row, "FECHA DE CAPTURA", cf);
							  ws3.addCell(ecter4);
							  column++;
							  Label ecter5 = new Label(column, row, "CODIGO DE CONTEO", cf);
							  ws3.addCell(ecter5);
							  column++;
							  Label ecter9 = new Label(column, row, "ATRIBUTO", cf);
							  ws3.addCell(ecter9);
							  column++;
							  Label ecter6 = new Label(column, row, "DESCRIPCION DE PRODUCTO", cf);
							  ws3.addCell(ecter6);
							  column++;
							  Label ecter7 = new Label(column, row, "CANTIDAD CONTABILIZADA", cf);
							  ws3.addCell(ecter7);
							  row = 1;
							  column = 0;
							  while (iter.hasNext())
							  {
								  String campo = (String)iter.next();
								  if (campo == null) 
								  {
									  campo = " ";
								  }
								  if (campo.equals("sp"))
								  {
									  iter.remove();
									  row++;
									  column = 0;
								  }
								  else
								  {
									  Label l1 = new Label(column, row, campo, cf);
									  ws3.addCell(l1);
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
