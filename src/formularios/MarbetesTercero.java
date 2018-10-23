package formularios;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Component;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.Box;
import javax.swing.SwingConstants;

import primerconteo.primer;

import javax.swing.JCheckBox;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class MarbetesTercero extends JFrame {

	public static LinkedList contenedor=new LinkedList();
	
	private JPanel contentPane;
	
	
	String almacenes="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarbetesTercero frame = new MarbetesTercero();
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
	public MarbetesTercero() {
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
		c5_chbx.setBounds(179, 140, 244, 50);
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
				marbetes.TercerConteo cont=new marbetes.TercerConteo();
				String listo=cont.generarMarbete(almacenes);
				almacenes="";
								
					JOptionPane.showMessageDialog(contentPane,listo);	
			}	
				//SISI
				
				
			
			else
			{
				//Mensaje para seleccionar algo
				JOptionPane.showMessageDialog(contentPane, "Selecciona al menos un almacén");
			}
				
				
			}
		});
	}
}
