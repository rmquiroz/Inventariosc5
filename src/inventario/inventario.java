package inventario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilerias.postgresql;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import correo.EmailUtility;
public class inventario
{
	public static final String SEPARATOR=",";
	public static final String QUOTE=",";
	public static int registros = 0;
	
	public static int fecha=0;
	public static String mensaje="";
	public static int hoy=0;
	private static String host="smtp.gmail.com";
    private static String port="25";
    private static String user="informesob3@4eglobal.com";
    private static String pass="S1st3mas43S1st3mas43";
    static String resultMessage = "";
  public static String main(String almacenes)
  {
	  try
	  {		  		  		  
		  Connection cn = postgresql.getConexionOpen();
				  //DriverManager.getConnection(url, usuario, contra);
		  Connection co = postgresql.getConexion();
		  //DriverManager.getConnection(inventarios, usuario, contra);		  
		  System.out.println("Ejecutando Query.......");
		  ResultSet rs = null;
		  PreparedStatement ps= co.prepareStatement("SELECT (CASE WHEN max(extract(doy FROM fecha)) IS NULL THEN 0.0 ELSE "
+ "max(extract(doy FROM fecha)) END)::NUMERIC+to_char(now(),'YYYY')::numeric,extract(doy FROM now())+to_char(now(),'YYYY')::numeric FROM "
+ "inventario_teorico WHERE almacen similar to ('"+almacenes+"')");
		  rs=ps.executeQuery();
		  if(rs.next())
		  {
			  fecha=rs.getInt(1);
			  hoy=rs.getInt(2);
		  }
		  if(hoy-2>=fecha)
		  {
			  System.out.println("ENTRA AL IF");			
			  GeneraValuado.main(almacenes,"");			  			  			  	  			  			  			  
			  
			  System.out.println("ESCRITURA TERMINADA");	
	  ////////////////////////////////TERMINA ESCRITURA DE ARCHIVO///////////////////////////////////		  		  		  		  		 			 
			  UltimaRem.main(almacenes, "");
	  ////////////////////////////////COMIENZA LECTURA DE ARCHIVO////////////////////////////////////
			  String [] fields = null;		  
			  BufferedReader br = null;
			  ArrayList<String> arr=new ArrayList<String>();
			  br =new BufferedReader(new FileReader("/INFORMES/"+almacenes.replace(" ","-")+"/Inventario.csv"));
			  String line = br.readLine();
			  while (null!=line) 
			  {
				  fields = line.split(SEPARATOR);
				  System.out.println(Arrays.toString(fields));
				  fields = removeTrailingQuotes(fields);			  
				  arr.add(fields[0]);
				  arr.add(fields[1]);
				  arr.add(fields[2]);
				  arr.add(fields[3]);
				  arr.add(fields[4]);
				  arr.add(fields[5]);
				  arr.add(fields[6]);
				  arr.add(fields[7]);
				  arr.add(fields[8]);
				  arr.add(fields[9]);
				  line = br.readLine();
			  }
			  int a=9;
			  String val1 = null,val11=null;
			  String val2 = null,val22=null;
			  String val3 = null,val33=null;
			  String val4 = null;
			  String val5 = null;
			  String val6 = null;
			  String val7 = null;
			  String val8 = null;
			  String val9 = null;
			  String val = null;
	      /////////////////////////////////// TERMINA LECTURA DE ARCHIVO//////////////////////////////
			  Iterator<String> 	s = arr.iterator();		         
	      /////////////////////////////////// RECOLECTA DE DATOS DE CSV///////////////////////////////
			  while(s.hasNext())
			  {		
				  
				  String inter=(String)s.next();			  
				  if(a==9)
				  {
					  
					  val1 =null;			    	
					  val11=null;
					  val1=inter;
					  a=8;			    		
				  }		    
				  else if(a==8)
				  {
					  
					  val2 =null;
					  val22=null;
					  val2=inter;
					  a=7;			    		
				  }
				  else if(a==7)
				  {
					  
					  val3 =null;
					  val33=null;
					  val3=inter;
					  a=6;			    		
				  }
				  else if(a==6)
				  {
					  
					  val4 =null;
					  val4=inter;
					  a=5;			    		
				  }
				  else if(a==5)
				  {
					  
					  val5 =null;
					  val5=inter;
					  a=4;			    		
				  }
				  else if(a==4)
				  {
					  
					  val6 =null;
					  val6=inter;
					  a=3;			    		
				  }
				  else if(a==3)
				  {
					  
					  val7 =null;
					  val7=inter;
					  a=2;			    		
				  }
				  else if(a==2)
				  {
					  
					  val8 =null;
					  val8=inter;
					  a=1;			    		
				  }	
				  else if(a==1)
				  {					  
					  val =null;
					  val=inter;
					  a=0;			    		
				  }	
				  else if(a==0)
				  {
					  
					  a=9;
					  val9 =null;
					  val9=inter;
					  ResultSet rs1=null;
					  PreparedStatement pas=cn.prepareStatement("SELECT m_warehouse_id FROM m_warehouse  WHERE name ='"+val1+"'");
					  rs1=pas.executeQuery();
					  System.out.println("CERO");
					  if(rs1.next())
					  {
						  val11=rs1.getString(1);
					  }
					  else 
					  { 
						  val11="";
					  }
					  ps=cn.prepareStatement("SELECT m_locator_id FROM m_locator WHERE value ='"+val2+"'");
					  rs=ps.executeQuery();
					  System.out.println("UNO");
					  if(rs.next())
					  {
						  val22=rs.getString(1);
					  }
					  else 
					  { 
						  val22="";
					  }
					  ps=cn.prepareStatement("SELECT m_product_id FROM m_product WHERE value ='"+val3+"'");
					  rs=ps.executeQuery();
					  System.out.println("DOS");
					  if(rs.next())
					  {
						  val33=rs.getString(1);
					  }
					  else 
					  { 
						  val33="";
					  }
					  registros++;
					  if(val11.equals("")){}
					  else{
					  ps=co.prepareStatement("insert into inventario_teorico values((SELECT upper(LOWER( REPLACE("
+ "CAST(uuid_generate_v4()AS varchar(50)),'-','')))),"
+ "'"+val11+"','"+val1+"',"
+ "'"+val2+"','"+val22+"',"
+ "'"+val3+"','"+val33 
+"','"+val4.replace("'","")+"',"
+ "'"+val5+"',"
+ "'"+val6+"',"
+ "'"+val7+"',"
+ "'"+val8+"',"
+ "'"+val9+"','"+val+"'"
+ ",now());");
					  System.out.println("Inserta"+ps);
					  ps.execute();
					  }
				  }				        
			  }
			  br.close();
			  mensaje="Carga exitosa";
			  ActivaAtributos.main();			  
			  String recipient = "rquiroz@4eglobal.com lmartinez@4eglobal.com";			  
			  String direcciones[]=recipient.split(" ");
			  System.out.println(""+direcciones.length);
			  String alm=almacenes.replace(" ","-").replace("|", "-")+"";			 
			  String subject = "ALERTA ERP: SE HAN DETENIDO ALMACENES";
			  String content = "SE HAN INICIADO CONTEOS DE ALMACENES";		        
			  System.out.println("Recopilación de datos lista " +recipient+", "+ subject + ", "+content);    		    
			  try 
			  {
				  EmailUtility.sendEmail(host, port, user, pass, recipient, subject,content,alm);
			  } catch (AddressException e) 
			  {
				  e.printStackTrace();
			  } catch (MessagingException e) 
			  {				
				  e.printStackTrace();
			  }
			  resultMessage = "The e-mail was sent successfully";
			  System.out.println("Enviado"+alm);			  
		  }
		  else
		  {
			  mensaje="Almacen cargado en rango de tiempo inválido";
		  } 
		  cn.close();
		  co.close();
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  System.out.print("Registros:" +registros);	  
	  return mensaje;
  }
/////////////////////////////////////METODO DE SEPARACIÓN CORRECTA DE ARCHIVO CSV////////////////////////////
  public static String[] removeTrailingQuotes(String[] fields)
  {
	  String result[] = new String[fields.length];
	  for (int i=0;i<result.length;i++)
	  {
		  result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	  }
	  return result;
  }
  
}