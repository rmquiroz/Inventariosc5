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

public class TeoricovsFisicoCantidades 
{
	static String mensaje;
	static Date date = new Date();
	static DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	public static String main(String almacenes,String repositorio){
		
		try {
			Class.forName("org.postgresql.Driver");
		Connection cn = postgresql.getConexion();		  
		Connection co = postgresql.getConexionOpen();
		String[] almacen;
		int h=0;
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null,rsp = null;
		  WritableWorkbook wb = Workbook.createWorkbook(new File("/INFORMES/"+repositorio+"TeoricovsFinalCantidades"+almacenes.replace("|","-")+hourFormat.format(date)+".xls"));
		  almacen=almacenes.split("\\|");
		  for(int x=0;x<almacen.length;x++)
		  {		
			  			  
			  PreparedStatement ps = cn.prepareStatement("SELECT ubi.hueco,"
+ "sum(primer.cantidad::numeric) as primer,"
+ "sum(segundo.cantidad::numeric) as segundo,"
+ "sum(tercer.cantidad::numeric) as tercer,"
+ "sum(final.cantidad::numeric) as fisico,"
+ "sum(teorico.cantidad::numeric) as teorico,"
+ "sum(final.cantidad::numeric)-sum(teorico.cantidad::numeric) as ajuste "
+ "from ubicaciones ubi "
+ "left outer join (select marbete,"
+ "ubicacion,"
+ "cantidad "
+ "from primerconteo) as primer on "
+ "ubi.marbete=primer.marbete "
+ "Left outer join (select marbete,"
+ "ubicacion,"
+ "cantidad "
+ "from segundoconteo) as segundo "
+ "on ubi.marbete=segundo.marbete "
+ "left outer join (select marbete,"
+ "ubicacion,"
+ "cantidad "
+ "from tercerconteofinal) as tercer "
+ "on ubi.marbete=tercer.marbete "
+ "left outer join "
+ "(select marbete,"
+ "hueco,"
+ "cantidad "
+ "from inventariofinal) as final "
+ "on ubi.marbete=final.marbete "
+ "left outer join (select ubicacion,"
+ "cantidad_original_uom as cantidad "
+ "from inventario_teorico) as teorico "
+ "on ubi.hueco=teorico.ubicacion "
+ "where ubi.almacen like '"+almacen[x]+"' "
+ "GROUP BY ubi.hueco");
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
			  Label ec2 = new Label(column, row, "PRIMER CONTEO", cf);
			  ws.addCell(ec2);
			  column++;
			  Label ec3 = new Label(column, row, "SEGUNDO CONTEO", cf);
			  ws.addCell(ec3);
			  column++;
			  Label ec4 = new Label(column, row, "TERCER CONTEO", cf);
			  ws.addCell(ec4);
			  column++;
			  Label ec5 = new Label(column, row, "INVENTARIO FISICO", cf);
			  ws.addCell(ec5);
			  column++;
			  Label ec6 = new Label(column, row, "INVENTARIO TEORICO", cf);
			  ws.addCell(ec6);
			  column++;
			  Label ec7 = new Label(column, row, "AJUSTE", cf);
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
