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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.SwingConstants;

import java.awt.Insets;
public class inicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application. VERSION CON CAMBIO 232
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("hola");
					inicio frame = new inicio();
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
	public inicio() {
		setResizable(false);
		setTitle("VENTANA PRINCIPAL");
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
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mntmCerrarSesion.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnArchivo.add(mntmCerrarSesion);
		
		mnArchivo.add(mntmSalir);
		
		JMenu mnCapturaDeInventario = new JMenu("Inventario");
		mnCapturaDeInventario.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnCapturaDeInventario);
		
		JMenuItem mntmNuevaCaptura = new JMenuItem("Paralizar Inventario");
		mntmNuevaCaptura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.inventarioform  inventarios = new formularios.inventarioform();
				inventarios.setVisible(true);
			}
				
			
		});
		mntmNuevaCaptura.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/buscar.png")));
		mntmNuevaCaptura.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnCapturaDeInventario.add(mntmNuevaCaptura);
		
		JMenuItem mntmCapturaDeInventario = new JMenuItem("Captura de Inventario 1er Conteo");
		mntmCapturaDeInventario.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/uno.png")));
		mntmCapturaDeInventario.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmCapturaDeInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//dispose();
				//con esto muestro la ventana inicial
				formularios.primerconteo primerconteo = new formularios.primerconteo();
				primerconteo.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Reactivar Inventario");
		mntmNewMenuItem_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.InventarioCerrar ice=new formularios.InventarioCerrar();
				ice.setVisible(true);
			}
		});
		mntmNewMenuItem_6.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/buscar.png")));
		mnCapturaDeInventario.add(mntmNewMenuItem_6);
		mnCapturaDeInventario.add(mntmCapturaDeInventario);
		
		JMenuItem mntmCapturaDeInventario_1 = new JMenuItem("Captura de Inventario 2do Conteo");
		mntmCapturaDeInventario_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//dispose();
				//con esto muestro la ventana inicial
				formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
				segundoconteo.setVisible(true);
				
				
			}
		});
		mntmCapturaDeInventario_1.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/dos.png")));
		mntmCapturaDeInventario_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnCapturaDeInventario.add(mntmCapturaDeInventario_1);
		
		JMenuItem mntmCapturaDeInventario_2 = new JMenuItem("Captura de Inventario 3er Conteo");
		mntmCapturaDeInventario_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				

				//dispose();
				//con esto muestro la ventana inicial
				formularios.tercerconteo tercerconteo = new formularios.tercerconteo();
				tercerconteo.setVisible(true);
				
				
			}
		});
		mntmCapturaDeInventario_2.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/tres.png")));
		mntmCapturaDeInventario_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnCapturaDeInventario.add(mntmCapturaDeInventario_2);
		
		JMenu mnInformes = new JMenu("Acciones");
		mnInformes.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnInformes);
		
		JMenuItem mntmGeneracionDeMarbetes = new JMenuItem("Generacion de Marbetes conteo 1 y 2");
		mntmGeneracionDeMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.generarmarbete generarmarbete = new formularios.generarmarbete();
				generarmarbete.setVisible(true);
				
			}
		});
		mntmGeneracionDeMarbetes.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/tag.png")));
		mntmGeneracionDeMarbetes.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnInformes.add(mntmGeneracionDeMarbetes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Generacion Marbetes 3er Conteo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.MarbetesTercero MarbetesTercero = new formularios.MarbetesTercero();
				MarbetesTercero.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmNewMenuItem.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/tag.png")));
		mnInformes.add(mntmNewMenuItem);
		
		JMenuItem mntmInformeDeer = new JMenuItem("Ejecutar Diferencias Primer y Segundo Conteo");
		mntmInformeDeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.DiferenciasPrueba diferencias = new formularios.DiferenciasPrueba();
				diferencias.setVisible(true);
				
				
				
			}
		});
		mntmInformeDeer.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/informe.png")));
		mntmInformeDeer.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnInformes.add(mntmInformeDeer);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Confirmar 3er Conteo");
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.CerrarTercerConteo cerrartercer = new formularios.CerrarTercerConteo();
				cerrartercer.setVisible(true);
				
				
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/informe.png")));
		mnInformes.add(mntmNewMenuItem_2);
		
		JMenu mnInformes_1 = new JMenu("Informes");
		mnInformes_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnInformes_1);
		
		JMenuItem mntmInformeDeAvances = new JMenuItem("Informe de Avances de Conteos");
		mntmInformeDeAvances.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mntmInformeDeAvances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.ConteosAvance conteosavance = new formularios.ConteosAvance();
				conteosavance.setVisible(true);
				
				
			}
		});
		mnInformes_1.add(mntmInformeDeAvances);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Informe de Avances de  Inventario Fisico");
		mntmNewMenuItem_1.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.InformeFinal infomefinal = new formularios.InformeFinal();
				infomefinal.setVisible(true);
				
				
			}
		});
		mnInformes_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmInformeInventarioTeorico = new JMenuItem("Informe Inventario Te\u00F3rico");
		mntmInformeInventarioTeorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				//con esto muestro la ventana inicial
				formularios.InformeTeorico informeteorico = new formularios.InformeTeorico();
				informeteorico.setVisible(true);
					
			}
		});
		mntmInformeInventarioTeorico.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mnInformes_1.add(mntmInformeInventarioTeorico);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Informe de Diferencias Valuado");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.InformeDiferenciasValuado dv=new formularios.InformeDiferenciasValuado();
				dv.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mnInformes_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Informe comparativo Fisico-Te\u00F3rico");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.InformeFisicovsTeorico fis=new formularios.InformeFisicovsTeorico();
				fis.setVisible(true);
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mnInformes_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Informe comparativo cantidades Te\u00F3rico-Fisico ");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.InformeTeoricovsFisicoCantidades fi=new formularios.InformeTeoricovsFisicoCantidades();
				fi.setVisible(true);
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/inventariofinal.png")));
		mnInformes_1.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setForeground(Color.WHITE);
		lblBienvenido.setFont(new Font("Dialog", Font.PLAIN, 26));
		lblBienvenido.setBounds(-10, 0, 855, 58);
		contentPane.add(lblBienvenido);
		
		JLabel lblSistemaDeToma = new JLabel("SISTEMA DE TOMA DE INVENTARIO VENTANA PRINCIPAL");
		lblSistemaDeToma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeToma.setForeground(Color.WHITE);
		lblSistemaDeToma.setFont(new Font("Dialog", Font.PLAIN, 26));
		lblSistemaDeToma.setBounds(0, 45, 855, 58);
		contentPane.add(lblSistemaDeToma);
		
		JLabel lblDesarrolloDeSistemas = new JLabel("      DESARROLLO  DE  SISTEMAS  ERP");
		lblDesarrolloDeSistemas.setForeground(Color.WHITE);
		lblDesarrolloDeSistemas.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblDesarrolloDeSistemas.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/4E-DESARROLLODESISTEMAS-final.png")));
		lblDesarrolloDeSistemas.setBounds(158, 285, 540, 112);
		contentPane.add(lblDesarrolloDeSistemas);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(inicio.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 855, 408);
		contentPane.add(lblNewLabel);
		
		
		
		usuarios.usuario gestionusuario = new usuarios.usuario();
		String usu = gestionusuario.getUsuario();
		System.out.println("Usuario: "+usu);
		
		
		
		JLabel label = new JLabel("\n1.-Seleccionar el Menu \"Inventario\"---\"Paralizar Inventario\"");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 190, 422, 31);
		contentPane.add(label);
	}
}
