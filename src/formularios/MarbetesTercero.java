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
		usuarios.usuario gestionusuario = new usuarios.usuario();
		String usu = gestionusuario.getUsuario();
		System.out.println("Usuario Marbetes Tercero: "+usu);
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
		
		final JCheckBox brandsEUA_chbx = new JCheckBox("4E BRANDS EUA");
		brandsEUA_chbx.setBackground(Color.BLACK);
		brandsEUA_chbx.setForeground(Color.WHITE);
		final JCheckBox etiquetas_chbx = new JCheckBox("4G_1D ETIQUETAS");
		etiquetas_chbx.setBackground(Color.BLACK);
		etiquetas_chbx.setForeground(Color.WHITE);
		final JCheckBox mp_chbx = new JCheckBox("4G_1D MP");
		mp_chbx.setBackground(Color.BLACK);
		mp_chbx.setForeground(Color.WHITE);
		final JCheckBox quimicos_chbx = new JCheckBox("4G_1F QUIMICOS");
		quimicos_chbx.setBackground(Color.BLACK);
		quimicos_chbx.setForeground(Color.WHITE);
		final JCheckBox smoMP_chbx = new JCheckBox("SMO_MATERIA PRIMA");
		smoMP_chbx.setBackground(Color.BLACK);
		smoMP_chbx.setForeground(Color.WHITE);
		final JCheckBox c5_chbx = new JCheckBox("PRINCIPAL_C5");
		c5_chbx.setBackground(Color.BLACK);
		c5_chbx.setForeground(Color.WHITE);
		final JCheckBox mpPlanta_chbx = new JCheckBox("MP_PLANTA 2C");
		mpPlanta_chbx.setBackground(Color.BLACK);
		mpPlanta_chbx.setForeground(Color.WHITE);
		final JCheckBox mpPlanta_chbxB = new JCheckBox("MP_PLANTA 2B");
		mpPlanta_chbxB.setBackground(Color.BLACK);
		mpPlanta_chbxB.setForeground(Color.WHITE);
		final JCheckBox smoPT_chbx = new JCheckBox("SMO_J1_PT");
		smoPT_chbx.setBackground(Color.BLACK);
		smoPT_chbx.setForeground(Color.WHITE);
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setForeground(Color.WHITE);
		
		brandsEUA_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		etiquetas_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		mp_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		quimicos_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		smoMP_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		c5_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		mpPlanta_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		mpPlanta_chbxB.setFont(new Font("Dialog", Font.BOLD, 14));
		smoPT_chbx.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		btnGenerarMarbete.setFont(new Font("Candara", Font.PLAIN, 14));
		
		brandsEUA_chbx.setBounds(48, 96, 210, 23);
		etiquetas_chbx.setBounds(48, 142, 210, 23);
		mp_chbx.setBounds(48, 188, 210, 23);
		quimicos_chbx.setBounds(48, 235, 210, 23);
		smoMP_chbx.setBounds(453, 235, 210, 23);
		c5_chbx.setBounds(453, 142, 210, 23);
		mpPlanta_chbx.setBounds(453, 96, 210, 23);		
		smoPT_chbx.setBounds(453, 188, 210, 23);
		lblNewLabel.setBounds(0, 32, 855, 39);
		btnGenerarMarbete.setBounds(624, 367, 155, 23);
		lblNewLabel_5.setBounds(0, 0, 855, 429);
		mpPlanta_chbxB.setBounds(453, 116, 210, 23);
		
		btnGenerarMarbete.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setIcon(new ImageIcon(generarmarbete.class.getResource("/imagenes/fondo.jpg")));

		contentPane.add(btnGenerarMarbete);		
		contentPane.add(brandsEUA_chbx);		
		contentPane.add(etiquetas_chbx);		
		contentPane.add(mp_chbx);		
		contentPane.add(quimicos_chbx);		
		contentPane.add(smoMP_chbx);		
		contentPane.add(c5_chbx);		
		contentPane.add(mpPlanta_chbx);	
		contentPane.add(mpPlanta_chbxB);	
		contentPane.add(smoPT_chbx);		
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_5);
		
		btnGenerarMarbete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			if(smoPT_chbx.isSelected() || mpPlanta_chbx.isSelected() || c5_chbx.isSelected() 
					|| smoMP_chbx.isSelected() || quimicos_chbx.isSelected()||mpPlanta_chbxB.isSelected() 
					|| mp_chbx.isSelected() || etiquetas_chbx.isSelected() || brandsEUA_chbx.isSelected())
			{
				if(smoPT_chbx.isSelected())
					almacenes = almacenes + "|" + smoPT_chbx.getText();
				if(mpPlanta_chbx.isSelected())
					almacenes = almacenes + "|" + mpPlanta_chbx.getText();
				if(mpPlanta_chbxB.isSelected())
					almacenes = almacenes + "|" + mpPlanta_chbxB.getText();
				if(c5_chbx.isSelected())
					almacenes = almacenes + "|" + c5_chbx.getText();
				if(smoMP_chbx.isSelected())
					almacenes = almacenes + "|" + smoMP_chbx.getText();
				if(quimicos_chbx.isSelected())
					almacenes = almacenes + "|" + quimicos_chbx.getText();
				if(mp_chbx.isSelected())
					almacenes = almacenes + "|" + mp_chbx.getText();
				if(etiquetas_chbx.isSelected())
					almacenes = almacenes + "|" + etiquetas_chbx.getText();
				if(brandsEUA_chbx.isSelected())
					almacenes = almacenes + "|" + brandsEUA_chbx.getText();				
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
