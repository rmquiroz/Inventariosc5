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
public class InformeFisicovsTeorico extends JFrame {

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
					InformeFisicovsTeorico frame = new InformeFisicovsTeorico();
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
	public InformeFisicovsTeorico() {
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
		final JCheckBox PRINCIPAL_C5 = new JCheckBox("PRINCIPAL_C5");
		PRINCIPAL_C5.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		PRINCIPAL_C5.setBackground(Color.BLACK);
		PRINCIPAL_C5.setForeground(Color.WHITE);
		PRINCIPAL_C5.setBounds(23,248, 220, 75);		
		contentPane.add(PRINCIPAL_C5);
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(PRINCIPAL_C5.isSelected()){
				almacenes="";
				if(PRINCIPAL_C5.isSelected())
					almacenes=almacenes+"|"+PRINCIPAL_C5.getText();
				almacenes=almacenes.substring(1);
				System.out.println("Variable:"+almacenes);
				//inventario.inventario i=new inventario.inventario();
				//reporteConteos.ConteosExcel i=new reporteConteos.ConteosExcel();
				reporteConteos.FisicovsTeorico fvst=new reporteConteos.FisicovsTeorico();
				String repositorio="";
				JOptionPane.showMessageDialog(contentPane,  fvst.main(almacenes,repositorio));
			}
			else{
				
			}
				
			}
		});
		btnAceptar.setBounds(530, 270, 185, 65);
		contentPane.add(btnAceptar);
		
		JLabel lblElegirLosAlmacenes = new JLabel("Elija los Almacenes de los cu\u00E1les \r\ndesea obtener el comparativo \r\nde Inventario Fisico contra el Teorico");
		lblElegirLosAlmacenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegirLosAlmacenes.setFont(new Font("Dialog", Font.BOLD, 16));
		lblElegirLosAlmacenes.setForeground(Color.WHITE);
		lblElegirLosAlmacenes.setBackground(Color.WHITE);
		lblElegirLosAlmacenes.setBounds(0, 0, 865, 90);
		contentPane.add(lblElegirLosAlmacenes);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 865, 419);
		contentPane.add(lblNewLabel);
		
	}
}
