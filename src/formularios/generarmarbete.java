package formularios;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JButton;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("deprecation")
public class generarmarbete extends JFrame {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Date date = new Date();
	  static DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	
	
	private JPanel contentPane;
	//la pao
	//dhadkjhdsfkjahfkjsdhfkahsf
	String almacenes="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					generarmarbete frame = new generarmarbete();
					frame.setVisible(true);
					frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/4e.jpg")).getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public generarmarbete() {
		usuarios.usuario gestionusuario = new usuarios.usuario();
		String usu = gestionusuario.getUsuario();
		System.out.println("Usuario Generar Marbetes: "+usu);
		setResizable(false);
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 468);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setForeground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 855, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Candara", Font.PLAIN, 14));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmRegresar = new JMenuItem("Regresar");
		mntmRegresar.setIcon(new ImageIcon(generarmarbete.class.getResource("/imagenes/regresar.png")));
		mntmRegresar.setFont(new Font("Candara", Font.PLAIN, 12));
		mntmRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.inicio inicio= new formularios.inicio();
				inicio.setVisible(true);
				
			}
		});
		mnArchivo.add(mntmRegresar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(generarmarbete.class.getResource("/imagenes/salir.png")));
		mntmSalir.setFont(new Font("Candara", Font.PLAIN, 12));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		JLabel lblNewLabel = new JLabel("Generar Marbetes");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		JButton btnGenerarMarbete = new JButton("Generar PDF");
		final JCheckBox c5_chbx = new JCheckBox("PRINCIPAL_C5");
		c5_chbx.setBackground(Color.BLACK);
		c5_chbx.setForeground(Color.WHITE);
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setForeground(Color.WHITE);
		c5_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		btnGenerarMarbete.setFont(new Font("Candara", Font.PLAIN, 14));
		c5_chbx.setBounds(23, 190, 241, 133);
		lblNewLabel.setBounds(0, 32, 855, 39);
		btnGenerarMarbete.setBounds(624, 367, 155, 23);
		lblNewLabel_5.setBounds(0, 0, 855, 429);
		
		
		btnGenerarMarbete.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setIcon(new ImageIcon(generarmarbete.class.getResource("/imagenes/fondo.jpg")));

		contentPane.add(btnGenerarMarbete);
		contentPane.add(c5_chbx);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_5);
		
		btnGenerarMarbete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			if(c5_chbx.isSelected())
			{
				if(c5_chbx.isSelected())
					almacenes = almacenes + "|" + c5_chbx.getText();
				System.out.println(almacenes);
				if(almacenes.charAt(0)=='|')
					almacenes=""+almacenes.substring(1, almacenes.length())+"";
				System.out.println(almacenes);			
				boolean listo = generarMarbete(almacenes);
				almacenes="";
				if(listo)
					JOptionPane.showMessageDialog(contentPane, "Archivo generado satisfactoriamente");	
				
				//SISI
				
				System.out.println("ARCHIVO GENERADO");
			}
			else
			{
				//Mensaje para seleccionar algo
				JOptionPane.showMessageDialog(contentPane, "Selecciona al menos un almacén");
			}
				
				
			}
		});
	}
	private boolean generarMarbete(String almacenes) 
	{
		System.out.println(almacenes);
		boolean listo = false;
		try
		{
		System.out.println("Se inicia conexion a bd");
	    Class.forName("org.postgresql.Driver");
	    Connection conexion = DriverManager.getConnection("jdbc:postgresql://10.1.250.24:5932/inventarios", "postgres", "s3st2m1s4e");
	    //Connection conexion = DriverManager.getConnection("jdbc:postgresql://201.149.89.163:5932/openbravo", "postgres", "s3st2m1s4e");
	    System.out.println("Se finaliza la prueba de conexion a postgresql");
	    System.out.println("Se inicia la solicitud del reporte");
	    Map<String,Object> parameters = new HashMap<String,Object>();
	    parameters.put("almacenes",new String(almacenes));	    
	    System.out.println(parameters.put("almacenes",new String(almacenes)));
	    parameters.put("IMG_DIR",new String("/INFORMES/imagenes/"));
	    System.out.println(parameters.put("IMG_DIR",new String("/INFORMES/imagenes/")));	    
	    JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile("/INFORMES/reportes/marbete.jasper");
	    System.out.println(reporte);
	    System.out.println(parameters);
	    System.out.println(conexion);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
	    System.out.println("Se solicita la impresion del reporte");
	    JRExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
	    System.out.println("Imprime reporte");
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("/INFORMES/"+"Marbetes"+almacenes+hourFormat.format(date)+".pdf"));
	    exporter.exportReport();
	    System.out.println("Termina ejecucion");	    
	    listo = true;
	    conexion.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception ex) {
		// TODO: handle exception
		ex.printStackTrace();
	}
		return listo;		
	}
}