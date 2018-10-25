package inventario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UltimaRem
{
	//public static String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
	static String inventarios="jdbc:postgresql://201.149.89.164:5932/inventarios_c5";
	static String url="jdbc:postgresql://201.149.89.163:5932/openbravo";
	public static String usuario="postgres";
	public static String contra="s3st2m1s4e";
	public static String mensaje="HECHO";
	public static String main(String almacenes,String repositorio)
	{
		try 
		{
			int column = 0;
			int row = 0;
			Class.forName("org.postgresql.Driver");		
			Connection cn = DriverManager.getConnection(url, usuario, contra);				  
			System.out.println("Ejecutando Query.......");
			ResultSet rs = null;
			String documento="";
			int iteracion;
			  String[] alma=new String[9];
			  System.out.println("ALMACENES"+almacenes);
			  alma=almacenes.split("\\|");
			  iteracion=alma.length;
			  WritableWorkbook wb = Workbook.createWorkbook(new File("/INFORMES/"+repositorio+almacenes.replace(" ","-")+"/Ultimoscambios_almacen.xls"));
			  WritableFont wf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD);
			  WritableCellFormat cf = new WritableCellFormat(wf);	        
			  for(int xZ=0;xZ<iteracion;xZ++)
			  {					  
			  PreparedStatement ps = cn.prepareStatement("SELECT rem.documentno from m_inout AS rem,m_product AS prod,c_uom as uom,"
+ "m_locator AS loc,c_bpartner as cli,m_warehouse as alm,ad_user as usuario,ad_org as org,m_inoutline AS reml left join "
+ "m_attributesetinstance on reml.m_attributesetinstance_id=m_attributesetinstance.m_attributesetinstance_id where rem."
+ "c_bpartner_id=cli.c_bpartner_id and reml.m_inout_id=rem.m_inout_id and reml.m_product_id=prod.m_product_id and "
+ "prod.c_uom_id=uom.c_uom_id and reml.m_locator_id=loc.m_locator_id and rem.c_bpartner_id=cli.c_bpartner_id and loc."
+ "m_warehouse_id=alm.m_warehouse_id and rem.createdby=usuario.ad_user_id AND alm.name similar to ('"+alma[xZ]+"') and "
+ "rem.ad_org_id=org.ad_org_id ORDER by movementdate desc LIMIT 1");
			  rs = ps.executeQuery();
			  if(rs.next())
			  {
				  documento=rs.getString(1);
			  }
			  ps = cn.prepareStatement("SELECT REPLACE(cli.name,',','') as Tercero,rem.documentno,prod.value AS "
+ "Codigo_interno,REPLACE(prod.name,',','') AS nombre_producto,reml.description AS desclin,prod.categoria AS cat,"
+ "prod.subcategoria AS subcat,case when m_attributesetinstance.description is null then '-' else m_attributesetinstance."
+ "description end as atributo,reml.movementqty AS cantidad, uom.name as UOM,rem.updated AS fecha_movimiento,CASE WHEN "
+ "rem.description = NULL THEN '-' ELSE REPLACE(REPLACE(REPLACE(replace(rem.description,CHR(10),' '),CHR(13),'-'),'  ',"
+ "''),',','') END AS descripcion, REPLACE(loc.value,',','') as hueco,alm.name,case when rem.docstatus like 'CO' then '"
+ "COMPLETADO' else case when rem.docstatus like 'DR' then 'BORRADOR' ELSE '' END END as estatus,usuario.name as usuario,"
+ "case when rem.documentno similar to '(RLE|REM)%' and cli.name similar to  '%(4E DE MEXICO SA DE CV|4E GLOBAL, S.A.P.I "
+ "DE C.V)%' then (case when split_part((replace(rem.description,CHR(10),'|')),'|',2) is null then '-' else split_part(("
+ "replace(rem.description,CHR(10),'|')),'|',2) end) else '-' end as monta, to_char(rem.movementdate,'YYYY-MM-DD') as "
+ "fecha,replace(org.name,',',' ') as organizacion, 'sp' AS sp from m_inout AS rem,m_product AS prod,c_uom as uom,"
+ "m_locator AS loc,c_bpartner as cli,m_warehouse as alm,ad_user as usuario,ad_org as org,m_inoutline AS reml left join "
+ "m_attributesetinstance on reml.m_attributesetinstance_id=m_attributesetinstance.m_attributesetinstance_id where rem."
+ "c_bpartner_id=cli.c_bpartner_id and reml.m_inout_id=rem.m_inout_id and reml.m_product_id=prod.m_product_id and "
+ "prod.c_uom_id=uom.c_uom_id and reml.m_locator_id=loc.m_locator_id and rem.c_bpartner_id=cli.c_bpartner_id and loc."
+ "m_warehouse_id=alm.m_warehouse_id and rem.createdby=usuario.ad_user_id AND rem.documentno='"+documento+"' and "
+ "rem.ad_org_id=org.ad_org_id ORDER by movementdate desc");
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
				  col.add(rs.getString(8));
				  col.add(rs.getString(9));
				  col.add(rs.getString(10));
				  col.add(rs.getString(11));
				  col.add(rs.getString(12));
				  col.add(rs.getString(13));
				  col.add(rs.getString(14));
				  col.add(rs.getString(15));
				  col.add(rs.getString(16));
				  col.add(rs.getString(17));
				  col.add(rs.getString(18));
				  col.add(rs.getString(19));
				  col.add(rs.getString(20));
			  }			  
			  WritableSheet ws = wb.createSheet("ReporteEntradas-salidas:"+alma[xZ], 0);		  			  
			  column = 0;
			  row = 0;			  
			  Iterator<String> i = col.iterator();	        
			  Label ec1 = new Label(column, row, "TERCERO", cf);	        
			  ws.addCell(ec1);
			  column++;
			  Label ec2 = new Label(column, row, "NO_DOCUMENTO", cf);
			  ws.addCell(ec2);
			  column++;
			  Label ec3 = new Label(column, row, "CODIGO INTERNO", cf);
			  ws.addCell(ec3);
			  column++;
			  Label ec4 = new Label(column, row, "NOMBRE_PRODUCTO", cf);
			  ws.addCell(ec4);
			  column++;
			  Label ec5 = new Label(column, row, "DESCRIPCION DE LINEA", cf);
			  ws.addCell(ec5);
			  column++;
			  Label ec6 = new Label(column, row, "CATEGORIA", cf);
			  ws.addCell(ec6);
			  column++;
			  Label ec7 = new Label(column, row, "SUBCATEGORIA", cf);
			  ws.addCell(ec7);
			  column++;
			  Label ec8 = new Label(column, row, "ATRIBUTO", cf);
			  ws.addCell(ec8);
			  column++;
			  Label ec9 = new Label(column, row, "CANTIDAD", cf);
			  ws.addCell(ec9);
			  column++;
			  Label ec10 = new Label(column, row, "UOM", cf);
			  ws.addCell(ec10);
			  column++;
			  Label ec11 = new Label(column, row, "FECHA_MOVIMIENTO", cf);
			  ws.addCell(ec11);
			  column++;
			  Label ec12 = new Label(column, row, "DESCRIPCION", cf);
			  ws.addCell(ec12);
			  column++;
			  Label ec13 = new Label(column, row, "HUECO", cf);
			  ws.addCell(ec13);
			  column++;
			  Label ec14 = new Label(column, row, "ALMACEN", cf);
			  ws.addCell(ec14);
			  column++;
			  Label ec15 = new Label(column, row, "STATUS", cf);
			  ws.addCell(ec15);
			  column++;
			  Label ec16 = new Label(column, row, "USUARIO", cf);
			  ws.addCell(ec16);
			  column++;
			  Label ec17 = new Label(column, row, "MONTACARGAS", cf);
			  ws.addCell(ec17);
			  column++;
			  Label ec18 = new Label(column, row, "FECHA", cf);
			  ws.addCell(ec18);
			  column++;
			  Label ec19 = new Label(column, row, "ORGANIZACION", cf);
			  ws.addCell(ec19);		  
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
			  for(int x=0;x<iteracion;x++)
			  {
				  PreparedStatement ps = cn.prepareStatement("SELECT mov.documentno AS folio_documento,replace(mov.name,',','') AS "
+ "nombre_documento,"
+ "mov.updated AS fecha_movimiento, replace(mov.description,',','') AS descripcion,m_attributeinstance.value,prod.value,"
+ "replace(prod.name,',',''), movl.description,movl.movementqty, uom.name,alm.name AS Almacen_Salida,loc.value AS "
+ "hueco_salida,alm1.name AS Almacen_Entrada, loc1.value AS hueco_entrada, case when mov.processed = 'Y' then 'PROCESADO' "
+ "ELSE CASE WHEN mov.processed = 'N' then 'NO PROCESADO' ELSE '' END END AS estatus,usuario.name AS usuario FROM "
+ "m_movement AS mov, m_movementline AS movl LEFT JOIN m_attributesetinstance ON movl.m_attributesetinstance_id="
+ "m_attributesetinstance.m_attributesetinstance_id LEFT JOIN m_attributeinstance ON m_attributesetinstance."
+ "m_attributesetinstance_id=m_attributeinstance.m_attributesetinstance_id,m_product AS prod,c_uom as uom,m_locator as loc,"
+ "m_locator as loc1, ad_user as usuario,m_warehouse AS alm, m_warehouse AS alm1 WHERE movl.m_movement_id=mov."
+ "m_movement_id AND movl.m_product_id=prod.m_product_id AND prod.c_uom_id=uom.c_uom_id AND mov.createdby=usuario."
+ "ad_user_id AND movl.m_locator_id=loc.m_locator_id AND movl.m_locatorto_id=loc1.m_locator_id AND loc.m_warehouse_id=alm."
+ "m_warehouse_id AND loc1.m_warehouse_id=alm1.m_warehouse_id AND alm.name similar to ('"+alma[x]+"') ORDER BY mov."
+ "updated DESC LIMIT 1");
				  WritableSheet ws2 = wb.createSheet("Movimientos"+alma[x], 0+x);
				  System.out.println("LLEGAMOS HASTA AQUI "+alma[x]+x);
				  rs = ps.executeQuery();
				  ArrayList<String> col2=new ArrayList<String>();
				  String doc="";
				  while (rs.next())
				  {	
					  doc=rs.getString(2);
				  }
				  ps = cn.prepareStatement("SELECT mov.documentno AS folio_documento,replace(mov.name,',','') AS nombre_"
+ "documento,mov.updated AS fecha_movimiento, replace(mov.description,',','') AS descripcion,m_attributeinstance.value,"
+ "prod.value,replace(prod.name,',',''), movl.description,movl.movementqty, uom.name,alm.name AS Almacen_Salida,loc.value "
+ "AS hueco_salida,alm1.name AS Almacen_Entrada, loc1.value AS hueco_entrada, case when mov.processed = 'Y' then "
+ "'PROCESADO' ELSE CASE WHEN mov.processed = 'N' then 'NO PROCESADO' ELSE '' END END AS estatus,usuario.name AS usuario "
+ "FROM m_movement AS mov, m_movementline AS movl LEFT JOIN m_attributesetinstance ON movl.m_attributesetinstance_id="
+ "m_attributesetinstance.m_attributesetinstance_id LEFT JOIN m_attributeinstance ON m_attributesetinstance."
+ "m_attributesetinstance_id=m_attributeinstance.m_attributesetinstance_id,m_product AS prod,c_uom as uom,m_locator as "
+ "loc,m_locator as loc1, ad_user as usuario,m_warehouse AS alm, m_warehouse AS alm1 WHERE movl.m_movement_id=mov."
+ "m_movement_id AND movl.m_product_id=prod.m_product_id AND prod.c_uom_id=uom.c_uom_id AND mov.createdby=usuario."
+ "ad_user_id AND movl.m_locator_id=loc.m_locator_id AND movl.m_locatorto_id=loc1.m_locator_id AND loc.m_warehouse_id="
+ "alm.m_warehouse_id AND loc1.m_warehouse_id=alm1.m_warehouse_id AND mov.name='"+doc+"' ORDER BY mov.updated DESC ");
				  System.out.println("Consulta "+ps);
				  rs = ps.executeQuery();
				  while (rs.next())
				  {
					  col2.add(rs.getString(1));
					  col2.add(rs.getString(2));
					  col2.add(rs.getString(3));
					  col2.add(rs.getString(4));
					  col2.add(rs.getString(5));
					  col2.add(rs.getString(6));
					  col2.add(rs.getString(7));
					  col2.add(rs.getString(8));
					  col2.add(rs.getString(9));
					  col2.add(rs.getString(10));
					  col2.add(rs.getString(11));
					  col2.add(rs.getString(12));
					  col2.add(rs.getString(13));
					  col2.add(rs.getString(14));
					  col2.add(rs.getString(15));
					  col2.add(rs.getString(16)); 
					  col2.add("sp");          		      
				  }
				  System.out.println("Consulta2 "+ps);
				  column = 0;
				  row = 0;
				  Iterator<String> i2 = col2.iterator();	        
				  Label ecs1 = new Label(column, row, "FOLIO DOCUMENTO", cf);	        
				  ws2.addCell(ecs1);
				  column++;
				  Label ecs2 = new Label(column, row, "NOMBRE DOCUMENTO", cf);
				  ws2.addCell(ecs2);
				  column++;
				  Label ecs3 = new Label(column, row, "FECHA DOCUMENTO", cf);
				  ws2.addCell(ecs3);
				  column++;
				  Label ecs4 = new Label(column, row, "DESCRIPCION MOVIMIENTO", cf);
				  ws2.addCell(ecs4);
				  column++;
				  Label ecs5 = new Label(column, row, "ATRIBUTOS", cf);
				  ws2.addCell(ecs5);
				  column++;
				  Label ecs6 = new Label(column, row, "CODIGO PRODUCTO", cf);
				  ws2.addCell(ecs6);
				  column++;
				  Label ecs7 = new Label(column, row, "PRODUCTO", cf);
				  ws2.addCell(ecs7);
				  column++;
				  Label ecs8 = new Label(column, row, "ATRIBUTO", cf);
				  ws2.addCell(ecs8);
				  column++;
				  Label ecs9 = new Label(column, row, "CANTIDAD", cf);
				  ws2.addCell(ecs9);
				  column++;
				  Label ecs10 = new Label(column, row, "UOM", cf);
				  ws2.addCell(ecs10);
				  column++;
				  Label ecs11 = new Label(column, row, "FECHA_MOVIMIENTO", cf);
				  ws2.addCell(ecs11);
				  column++;
				  Label ecs12 = new Label(column, row, "DESCRIPCION", cf);
				  ws2.addCell(ecs12);
				  column++;
				  Label ecs13 = new Label(column, row, "HUECO", cf);
				  ws2.addCell(ecs13);
				  column++;
				  Label ecs14 = new Label(column, row, "ALMACEN", cf);
				  ws2.addCell(ecs14);
				  column++;
				  Label ecs15 = new Label(column, row, "STATUS", cf);
				  ws2.addCell(ecs15);
				  column++;
				  Label ecs16 = new Label(column, row, "USUARIO", cf);
				  ws2.addCell(ecs16);
				  column++;
				  Label ecs17 = new Label(column, row, "MONTACARGAS", cf);
				  ws2.addCell(ecs17);
				  column++;
				  Label ecs18 = new Label(column, row, "FECHA", cf);
				  ws2.addCell(ecs18);
				  column++;
				  Label ecs19 = new Label(column, row, "ORGANIZACION", cf);
				  ws2.addCell(ecs19);				  
				  row = 1;
				  column = 0;
				  while (i2.hasNext())
				  {
					  String campo = (String)i2.next();
					  if (campo == null) 
					  {
						  campo = " ";
					  }
					  if (campo.equals("sp"))
					  {
						  i2.remove();
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
			  }	  		  
			  wb.write();
			  wb.close();
			  cn.close();
		} catch (ClassNotFoundException e) 
		{	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  
		return mensaje;
	}
}