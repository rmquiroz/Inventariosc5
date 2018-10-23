package inventario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GeneraValuado 
{
	public static String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
	public static String inventarios= "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
	//public static String url= "jdbc:postgresql://201.149.89.163:5932/openbravo";
	//public static String inventarios= "jdbc:postgresql://201.149.89.164:5932/inventarios";
	public static String usuario="postgres";
	public static String contra="s3st2m1s4e";
	public static String mensaje="HECHO";
	public static String main(String almacenes,String repositorio)
	{
		try 
		{
			Class.forName("org.postgresql.Driver");		
			Connection cn = DriverManager.getConnection(url, usuario, contra);				  
			System.out.println("Ejecutando Query.......");
			ResultSet rs = null;			
			PreparedStatement ps = cn.prepareStatement("SELECT m_warehouse.name,"
+ "m_locator.value,"
+ "m_product.value AS codigo,"
+ "REPLACE(m_product.description,',',' ') AS descripcion,"
+ "m_attributesetinstance.description AS atributo,"
+ "round(sum(m_storage_detail.qtyonhAND),2) AS cantidad,"
+ "REPLACE(REPLACE(c_uom.name, 'Unit', 'UNIDAD'),'Kilogram','KILOGRAMO') AS unidad,"
+ "round(sum(CASE WHEN m_product.value LIKE '1%' THEN 1*m_storage_detail.qtyonhAND WHEN m_product.value LIKE '2%' THEN 1*m_storage_detail.qtyonhAND "
+ "WHEN m_product.value LIKE '3%' THEN 1*m_storage_detail.qtyonhAND WHEN m_product.value LIKE '7%' THEN 1*"
+ "m_storage_detail.qtyonhAND WHEN m_product.value LIKE '8%' THEN 1*m_storage_detail.qtyonhAND WHEN m_product.value LIKE "
+ "'4%' THEN piezas_Caja::NUMERIC*m_storage_detail.qtyonhAND WHEN m_product.value LIKE '5%'  THEN piezas_pallet::NUMERIC*"
+ "m_storage_detail.qtyonhAND ELSE 1*m_storage_detail.qtyonhAND end ),2) AS piezas,  to_char(CASE WHEN (case when "
+ "m_Warehouse.name like '%4E BRANDS EUA%' THEN m_product.campoabcdeuno else to_Char(m_product.coststd) end) is null THEN "
+ "'FALTA VALUACION' ELSE (case when m_Warehouse.name like '%4E BRANDS EUA%' THEN m_product.campoabcdeuno else to_char("
+ "m_product.coststd) end)::varchar  END ) AS costostd,   (round(sum(CASE WHEN m_product.value LIKE '1%' THEN 1*"
+ "m_storage_detail.qtyonhAND   WHEN m_product.value LIKE '2%'  THEN 1*m_storage_detail.qtyonhAND WHEN m_product.value "
+ "LIKE '3%'  THEN 1*m_storage_detail.qtyonhAND  WHEN m_product.value LIKE '7%'  THEN 1*m_storage_detail.qtyonhAND WHEN "
+ "m_product.value LIKE '8%'  THEN 1*m_storage_detail.qtyonhAND WHEN m_product.value LIKE '4%'  THEN piezas_Caja::NUMERIC*"
+ "m_storage_detail.qtyonhAND  WHEN m_product.value LIKE '5%' THEN piezas_pallet::NUMERIC*m_storage_detail.qtyonhAND ELSE "
+ "1*m_storage_detail.qtyonhAND end ),2))*(case when m_Warehouse.name like '%4E BRANDS EUA%' THEN m_product.campoabcdeuno"
+ "::numeric else m_product.coststd end) AS std,chr(13) AS sp,to_char(now(),'DD-MM-YYYY HH:mi:ss') FROM m_storage_detail LEFT JOIN m_attributesetinstance ON "
+ "m_storage_detail.m_attributesetinstance_id=m_attributesetinstance.m_attributesetinstance_id,m_product,m_locator,"
+ "m_warehouse, c_uom  WHERE m_storage_detail.m_product_id = m_product.m_product_id AND m_storage_detail.qtyonhAND <> 0 "
+ "AND m_storage_detail.m_locator_id=m_locator.m_locator_id  AND m_locator.m_warehouse_id=m_warehouse.m_warehouse_id AND "
+ "c_uom.c_uom_id=m_product.c_uom_id  AND m_product.value NOT LIKE '9%' AND m_product.value NOT LIKE 'US%' AND "
+ "m_product.value NOT LIKE 'ES%' AND m_product.value NOT LIKE 'HERRA%' AND m_product.value NOT LIKE 'SERVI%' AND "
+ "m_warehouse.isactive='Y' AND m_warehouse.name similar to ('"+almacenes+"') AND m_Warehouse.name NOT LIKE '4E_MP TEMPORAL' "
+ "AND m_Warehouse.name NOT LIKE '4G_SMO m_productUCCION'  AND m_Warehouse.name NOT LIKE '4G_1D ADUANA' AND m_Warehouse.name NOT LIKE '4E_BRANDS' AND "
+ "m_Warehouse.name NOT LIKE '4G_1F REFACCIONES' AND m_Warehouse.name NOT LIKE 'ALMACEN_MERIDA' GROUP BY m_warehouse.name,"
+ "m_locator.value,m_product.value,m_product.description,unidad,categoria,m_product.upc,m_product.licenciante,m_attributesetinstance.description,"
+ "m_product.piezas_caja,m_product.piezas_pallet,c_uom.name,m_product.coststd,m_product.campoabcdeuno ORDER BY "
+ "m_warehouse.name,m_locator.value,codigo ASC");		 
			rs = ps.executeQuery();		  
			String valor = "",nombre="";		  		  
			System.out.println(" Termina Consulta.......");
			while (rs.next())
			{
				valor =valor +"" + rs.getString(11) + rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) 
+ ","+rs.getString(4)+ "," + rs.getString(5) + ","+ rs.getString(6) + "," + rs.getString(7) + "," + rs.getString(8) 
+ ","+rs.getString(9)+ " " + ","+rs.getString(10)+ " " ;
				nombre=rs.getString(11);
			}
			valor=valor.replace("null"," ");
			File folder = new File("/INFORMES/"+repositorio+almacenes.replace(" ","-").replace("|", "-")+"");
			System.out.print(folder.mkdirs());
			System.out.print("Existe: "+folder.exists());
			PrintWriter in = new PrintWriter(folder+"/Inventario.csv");
			in.write("ALMACEN,HUECO,CODIGO,DESCRIPCION,ATRIBUTO,CANTIDAD,UNIDAD,PIEZAS,COSTO ESTANDAR,COSTO TOTAL ESTANDAR" 
+ valor);
			in.close();
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
		}		  
		return mensaje;
	}
}