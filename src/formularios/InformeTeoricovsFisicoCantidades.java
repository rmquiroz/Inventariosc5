package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.SwingConstants;

import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JButton;
public class InformeTeoricovsFisicoCantidades extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306141461577980454L;
	private JPanel contentPane;
	public static String almacenes;
	/**
	 * Launch the application. VERSION CON CAMBIO 232
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeTeoricovsFisicoCantidades frame = new InformeTeoricovsFisicoCantidades();
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
	public InformeTeoricovsFisicoCantidades() {
		usuarios.usuario gestionusuario = new usuarios.usuario();
		String usu = gestionusuario.getUsuario();
		System.out.println("Usuario Informe Teorico vs FIsico: "+usu);
		setResizable(false);
		setTitle("CONTEOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 468);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 200, 0, 0));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo     ");
		mnArchivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/salir.png")));
		mntmSalir.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JMenuItem mntmRegresar = new JMenuItem("Regresar");
		mntmRegresar.setIcon(new ImageIcon(primerconteo.class.getResource("/imagenes/regresar.png")));
		mntmRegresar.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.inicio inicio= new formularios.inicio();
				inicio.setVisible(true);
				
			}
		});
		mnArchivo.add(mntmRegresar);
		
		mnArchivo.add(mntmSalir);
		
	
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);			
		final JCheckBox BRANDSEUA = new JCheckBox("4E BRANDS EUA");
		BRANDSEUA.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox ETIQUETAS = new JCheckBox("4G_1D ETIQUETAS");
		ETIQUETAS.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox UDMP = new JCheckBox("4G_1D MP");
		UDMP.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox QUIMICOS = new JCheckBox("4G_1F QUIMICOS");
		QUIMICOS.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox MPPLANTA = new JCheckBox("MP_PLANTA 2C");
		MPPLANTA.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox MPPLANTAB = new JCheckBox("MP_PLANTA 2B");
		MPPLANTAB.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox PRINCIPAL_C5 = new JCheckBox("PRINCIPAL_C5");
		PRINCIPAL_C5.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox SMO_J1_PT = new JCheckBox("SMO_J1_PT");
		SMO_J1_PT.setFont(new Font("Dialog", Font.BOLD, 12));
		final JCheckBox SMO_MATERIA = new JCheckBox("SMO_MATERIA PRIMA");
		SMO_MATERIA.setFont(new Font("Dialog", Font.BOLD, 12));
		
		BRANDSEUA.setBackground(Color.BLACK);
		BRANDSEUA.setForeground(Color.WHITE);
		BRANDSEUA.setBounds(23, 150, 168, 23);
		contentPane.add(BRANDSEUA);		
		
		ETIQUETAS.setBackground(Color.BLACK);
		ETIQUETAS.setForeground(Color.WHITE);
		ETIQUETAS.setBounds(23,180, 168, 23);		
		contentPane.add(ETIQUETAS);		
		
		UDMP.setBackground(Color.BLACK);
		UDMP.setForeground(Color.WHITE);
		UDMP.setBounds(23,210, 168, 23);
		contentPane.add(UDMP);		
		
		QUIMICOS.setBackground(Color.BLACK);
		QUIMICOS.setForeground(Color.WHITE);
		QUIMICOS.setBounds(23,240, 168, 23);		
		contentPane.add(QUIMICOS);
		
		MPPLANTA.setBackground(Color.BLACK);
		MPPLANTA.setForeground(Color.WHITE);
		MPPLANTA.setBounds(23,270, 168, 23);
		contentPane.add(MPPLANTA);
		
		
		PRINCIPAL_C5.setBackground(Color.BLACK);
		PRINCIPAL_C5.setForeground(Color.WHITE);
		PRINCIPAL_C5.setBounds(23,300, 168, 23);		
		contentPane.add(PRINCIPAL_C5);		
		
		SMO_J1_PT.setBackground(Color.BLACK);
		SMO_J1_PT.setForeground(Color.WHITE);
		SMO_J1_PT.setBounds(23,330, 168, 23);
		contentPane.add(SMO_J1_PT);		
		
		SMO_MATERIA.setBackground(Color.BLACK);
		SMO_MATERIA.setForeground(Color.WHITE);
		SMO_MATERIA.setBounds(23,360, 168, 23);		
		contentPane.add(SMO_MATERIA);
		
		MPPLANTAB.setBackground(Color.BLACK);
		MPPLANTAB.setForeground(Color.WHITE);
		MPPLANTAB.setBounds(23,390, 168, 23);
		contentPane.add(MPPLANTAB);
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(BRANDSEUA.isSelected() || UDMP.isSelected() || QUIMICOS.isSelected() || MPPLANTA.isSelected() ||
					MPPLANTAB.isSelected() ||
PRINCIPAL_C5.isSelected() || SMO_J1_PT.isSelected() || SMO_MATERIA.isSelected() || ETIQUETAS.isSelected()){
				almacenes="";
				if(BRANDSEUA.isSelected())
					almacenes=almacenes+"|"+BRANDSEUA.getText();
				if(UDMP.isSelected())
					almacenes=almacenes+"|"+UDMP.getText();
				if(QUIMICOS.isSelected())
					almacenes=almacenes+"|"+QUIMICOS.getText();
				if(MPPLANTA.isSelected())
					almacenes=almacenes+"|"+MPPLANTA.getText();
				if(MPPLANTAB.isSelected())
					almacenes=almacenes+"|"+MPPLANTAB.getText();
				if(PRINCIPAL_C5.isSelected())
					almacenes=almacenes+"|"+PRINCIPAL_C5.getText();
				if(SMO_J1_PT.isSelected())
					almacenes=almacenes+"|"+SMO_J1_PT.getText();
				if(SMO_MATERIA.isSelected())
					almacenes=almacenes+"|"+SMO_MATERIA.getText();
				if(ETIQUETAS.isSelected())
					almacenes=almacenes+"|"+ETIQUETAS.getText();
				almacenes=almacenes.substring(1);
				System.out.println("Variable:"+almacenes);
				//inventario.inventario i=new inventario.inventario();
				//reporteConteos.ConteosExcel i=new reporteConteos.ConteosExcel();
				reporteConteos.TeoricovsFisicoCantidades fvst=new reporteConteos.TeoricovsFisicoCantidades();
				String repositorio="";
				JOptionPane.showMessageDialog(contentPane,  fvst.main(almacenes,repositorio));
			}
			else{
				
			}
				
			}
		});
		btnAceptar.setBounds(530, 270, 185, 65);
		contentPane.add(btnAceptar);
		
		JLabel lblElegirLosAlmacenes = new JLabel("Elija los Almacenes de los cu\u00E1les desea obtener las cantidades de Inventario Fisico contra Teorico");
		lblElegirLosAlmacenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegirLosAlmacenes.setFont(new Font("Dialog", Font.BOLD, 17));
		lblElegirLosAlmacenes.setForeground(Color.WHITE);
		lblElegirLosAlmacenes.setBackground(Color.WHITE);
		lblElegirLosAlmacenes.setBounds(0, 0, 865, 80);
		contentPane.add(lblElegirLosAlmacenes);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 865, 419);
		contentPane.add(lblNewLabel);
		
	}
}
