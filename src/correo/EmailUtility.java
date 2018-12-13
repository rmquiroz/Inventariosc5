package correo;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	public static void sendEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message,String alm) throws AddressException,
            MessagingException {
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		System.out.println("Hora: "+hourFormat.format(date));
		//Caso 2: obtener la fecha y salida por pantalla con formato:		
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");        
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };        
        Session session = Session.getInstance(properties, auth);
        session.setDebug(true);
        // creates a new e-mail message
        
        
        
        BodyPart texto = new MimeBodyPart();              
        texto.setText("Inventario de almacen(es) y ultima Remision y Movimientos de"+alm);
                
        BodyPart adjunto = new MimeBodyPart();               
        adjunto.setDataHandler(new DataHandler(new FileDataSource("/INFORMES/"+alm+"/Inventario.csv")));
        System.out.println("dato"+adjunto.getDataHandler());
        adjunto.setFileName(alm+hourFormat.format(date)+".csv");
        
        BodyPart adjunto2 = new MimeBodyPart();               
        adjunto2.setDataHandler(new DataHandler(new FileDataSource("/INFORMES/"+alm+"/Ultimoscambios_almacen.xls")));        
        adjunto2.setFileName("RemsMovs"+alm+hourFormat.format(date)+".xls");                
        
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);
        multiParte.addBodyPart(adjunto2);
        
        MimeMessage msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress("informesob3@4eglobal.com"));
        //ogutierrez@kcc.com
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("sistemaserp@4eglobal.com"));
  //      msg.addRecipient(Message.RecipientType.TO, new InternetAddress("lmartinez@4eglobal.com"));
    //    msg.addRecipient(Message.RecipientType.TO, new InternetAddress("ogutierrez@kcc.com"));
      //  msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jfarias@4eglobal.com"));
        //msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jpgomez@4eglobal.com"));
//        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("lcao@4eglobal.com"));
  //      msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jvelazquez@4eglobal.com"));
    //    msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jcgonzalez@4eglobal.com"));
        msg.setSubject("PARALIZA ALMACEN(ES)"+alm);                                   
        msg.setContent(multiParte);


        
        
        
        
        //InternetAddress[] toAddresses = null;
//        String recipient= "rquiroz@4eglobal.com";
       // String recipient = "rquiroz@4eglobal.com";
        	
        //msg.setFrom(new InternetAddress(userName));
        //String direcciones[]=recipient.split(" ");
        //Address []destinos = new Address[direcciones.length];
        //System.out.println(""+direcciones.length);
		 
		//for(int correos=0;correos<=direcciones.length-1;correos++){
			 
//			 System.out.println(""+direcciones[correos]);
//			 destinos[correos] = new InternetAddress(direcciones[correos]) ;
			 
//		}
	//	msg.addRecipients(Message.RecipientType.TO, destinos);
        //msg.setRecipients(Message.RecipientType.TO, toAddresses);
    //    msg.setSubject(subject);
    //    msg.setSentDate(new Date());
    //    msg.setText(message);
        Transport t = session.getTransport("smtp");
        t.connect(userName,password);
        t.sendMessage(msg,msg.getAllRecipients());
        t.close();
        
        // sends the e-mail
        //Transport.sendMessage(msg,msg.getAllRecipients());
        
 
    }
}