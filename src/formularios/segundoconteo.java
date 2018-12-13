package formularios;
import java.awt.EventQueue;
import java.awt.TextField;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import conteos.Actualizacion;
import conteos.Almacen;
import conteos.Atributos;
import conteos.Insersion;
import conteos.Productos;
import validainsertar.ValidaInsAct;
import validainsertar.validatercerconteoinsertar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Choice;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Iterator;
public class segundoconteo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private JTextField txtbuscar;
	private JTextField txtcodigo;
	private TextField txtcantidad;
	private JTextField txtdescripcion;
	private JTextField txtuom;
	private JTextField txtalmacen;
	private JTextField txtubicacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					segundoconteo frame = new segundoconteo();
					frame.setVisible(true);
					frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/4e.jpg")).getImage());
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public segundoconteo() 
	{
		usuarios.usuario gestionusuario = new usuarios.usuario();
		final String usu = gestionusuario.getUsuario();
		System.out.println("Usuario segundoconteo: "+usu);
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
		JButton btnLimpiarFormulario = new JButton("Limpiar Formulario");
		btnLimpiarFormulario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
				formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
				segundoconteo.setVisible(true);				 			
			}
		});
		final Choice choice = new Choice();
		choice.setForeground(Color.BLACK);
		choice.setBackground(Color.WHITE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				ArrayList<String> arr=new ArrayList<String>();
				arr=Atributos.main();
				Iterator<String> i = arr.iterator();
				choice.add("");
				while(i.hasNext()){
					 String campo = (String)i.next();
					 choice.add(campo);
				}
			}
		});
		choice.setBounds(580, 204, 131, 20);
		contentPane.add(choice);
		
		btnLimpiarFormulario.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnLimpiarFormulario.setBounds(600, 32, 197, 26);
		contentPane.add(btnLimpiarFormulario);		
		txtubicacion = new JTextField();
		txtubicacion.setEditable(false);
		txtubicacion.setColumns(10);
		txtubicacion.setBounds(495, 132, 127, 20);
		contentPane.add(txtubicacion);		
		txtalmacen = new JTextField();
		txtalmacen.setEditable(false);
		txtalmacen.setBounds(147, 132, 197, 20);
		contentPane.add(txtalmacen);
		txtalmacen.setColumns(10);		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		lblUbicacin.setForeground(Color.WHITE);
		lblUbicacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbicacin.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUbicacin.setBounds(379, 124, 127, 28);
		contentPane.add(lblUbicacin);		
		txtuom = new JTextField();
		txtuom.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtuom.setEditable(false);
		txtuom.setColumns(10);
		txtuom.setBounds(580, 236, 131, 21);
		contentPane.add(txtuom);
		JLabel UOM = new JLabel("UOM:");
		UOM.setForeground(Color.WHITE);
		UOM.setVerticalAlignment(SwingConstants.BOTTOM);
		UOM.setFont(new Font("Dialog", Font.BOLD, 16));
		UOM.setBounds(507, 236, 63, 19);
		contentPane.add(UOM);		
		txtdescripcion = new JTextField();
		txtdescripcion.setEditable(false);
		txtdescripcion.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtdescripcion.setColumns(10);
		txtdescripcion.setBounds(122, 235, 359, 21);
		contentPane.add(txtdescripcion);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(segundoconteo.class.getResource("/imagenes/salir.png")));
		mntmSalir.setFont(new Font("Dialog", Font.PLAIN, 12));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnArchivo.add(mntmSalir);
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String marbete = txtbuscar.getText();
				String detalleal=Almacen.main(marbete);
				if(detalleal.contains("|"))
				{									
				String[] campos=detalleal.split("\\|");								      
				txtalmacen.setText(campos[0]);
				txtubicacion.setText(campos[1]);				      
				txtbuscar.setEditable(false);
				txtalmacen.setEditable(false);
				txtubicacion.setEditable(false);				      
				btnBuscar.setEnabled(false);
				}
				else 
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Numero de Marbete Incorrecto Verifique el número");
					txtbuscar.setText("");
					txtbuscar.requestFocus();
				}
			}
		});
			
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String marbete = txtbuscar.getText();
				String detalleal=Almacen.main(marbete);
				if(detalleal.contains("|"))
				{									
				String[] campos=detalleal.split("\\|");								      
				txtalmacen.setText(campos[0]);
				txtubicacion.setText(campos[1]);				      
				txtbuscar.setEditable(false);
				txtalmacen.setEditable(false);
				txtubicacion.setEditable(false);				      
				btnBuscar.setEnabled(false);
				}
				else 
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Numero de Marbete Incorrecto Verifique el número");
					txtbuscar.setText("");
					txtbuscar.requestFocus();
				}	
				}
		});

		btnBuscar.setBounds(354, 85, 89, 28);
		contentPane.add(btnBuscar);
		
		txtbuscar = new JTextField();
		txtbuscar.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtbuscar.setColumns(10);
		txtbuscar.setBounds(217, 88, 127, 23);
		contentPane.add(txtbuscar);
		
		JLabel lblNewLabel = new JLabel("Segundo Conteo");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 32, 855, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de Marbete:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(24, 88, 197, 21);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("Almacen:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setBounds(34, 125, 127, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(342, 142, 106, 28);
		contentPane.add(label);
		
		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCodigo.setBounds(24, 203, 73, 21);
		contentPane.add(lblCodigo);
		
		txtcodigo = new JTextField();
		txtcodigo.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtcodigo.setBounds(112, 204, 106, 21);
		contentPane.add(txtcodigo);
		txtcodigo.setColumns(10);
		
		
	
		final JButton btnValidar = new JButton("Validar");
		btnValidar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String codigo = txtcodigo.getText();
				String producto=Productos.main(codigo);
				if(producto.isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Codigo");
					txtcodigo.setText("");
					txtcodigo.requestFocus();
				}
				else{
				String[] detallprod=producto.split("\\|");
				txtdescripcion.setText(detallprod[0]);
				txtuom.setText(detallprod[1]);				     
				txtdescripcion.setEditable(false);
				txtcodigo.setEditable(false);				      				      
				btnValidar.setEnabled(false);
				txtcantidad.requestFocus();
				}
				}
		});			
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				String codigo = txtcodigo.getText();
				String producto=Productos.main(codigo);
				if(producto.isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Codigo");
					txtcodigo.setText("");
					txtcodigo.requestFocus();
				}
				else{
					String[] detallprod=producto.split("\\|");
					txtdescripcion.setText(detallprod[0]);
					txtuom.setText(detallprod[1]);				     
					txtdescripcion.setEditable(false);
					txtcodigo.setEditable(false);				      				      
					btnValidar.setEnabled(false);
					txtcantidad.requestFocus();
				}
				}
		});
		btnValidar.setVerticalAlignment(SwingConstants.TOP);
		btnValidar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnValidar.setBounds(236, 202, 89, 28);
		contentPane.add(btnValidar);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescripcion.setBounds(24, 235, 106, 23);
		contentPane.add(lblDescripcion);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_4.setBounds(236, 286, 102, 19);
		contentPane.add(lblNewLabel_4);
		
		txtcantidad = new TextField();
		txtcantidad.setForeground(Color.BLACK);
		txtcantidad.setBackground(Color.WHITE);
		txtcantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent a) {
				char c = a.getKeyChar();
				if((c<'0' || c>'9') && c != KeyEvent.VK_BACK_SPACE && c != '.')  //<<>>
				{
					System.out.println("Char "+a.getKeyChar());
					getToolkit().beep();
					 a.consume();					 
				}
			}
		});
		txtcantidad.setFont(new Font("DFKai-SB", Font.PLAIN, 11));
		txtcantidad.setBounds(344, 286, 114, 20);
		contentPane.add(txtcantidad);
		txtcantidad.setColumns(10);
		
		JButton btnConfirmarConteo = new JButton("Confirmar Conteo");
		btnConfirmarConteo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String marbete=null;
				String atributo=null;
				String ubicacion= null;
				String almacen= null;
				String codigo=null;
				double cantidad;
				if(txtalmacen.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Marbete");
					txtbuscar.requestFocus();
				}
				else if(txtdescripcion.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Producto");
					txtcodigo.requestFocus();
				}
				else if(txtcantidad.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique la cantidad");
					txtcantidad.requestFocus();
				}
				else{
					atributo=choice.getSelectedItem();
					codigo= txtcodigo.getText();
					marbete= txtbuscar.getText();
					ubicacion= txtubicacion.getText();
					almacen= txtalmacen.getText();
					cantidad= Double.parseDouble(txtcantidad.getText());
					String tabla="tercerconteo";
					String tabla2="inventariofinal";				
					String result1=validatercerconteoinsertar.main(marbete,tabla,tabla2);
					System.out.println("IICIA" +result1);
					if(result1.equals("NO")){			
						System.out.println("Inicio Insercion------>>>");
						String conteo="segundoconteo";
						String result=ValidaInsAct.main(codigo, marbete,conteo,atributo);
						System.out.println("IICIA" +result);
						if(result.equals("UPDATE"))
						{
							System.out.println("UPDATE");
							Actualizacion.main(conteo,cantidad+"",codigo,marbete,usu,atributo);
						}
						else if(result.equals("INSERT"))
						{						
							System.out.println("INSERT");
							Insersion.main(conteo, cantidad+"", codigo, marbete, usu, ubicacion, almacen,atributo);						
						}	
						btnValidar.setEnabled(false); 
						JOptionPane.showMessageDialog(contentPane, "Registrado Correctamente");				      
						dispose();
						formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
						segundoconteo.setVisible(true);					 								
					}
					else 
					{
						JOptionPane.showMessageDialog(contentPane, "ESTE MARBETE ESTA YA ESTA REGISTRADO COMO TERCER CONTEO O INVENTARIO FINAL");						
						dispose();
						formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
						segundoconteo.setVisible(true);
					}
				}
			}		
		});		
		btnConfirmarConteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String marbete=null;
				String atributo=null;
				String ubicacion= null;
				String almacen= null;
				String codigo=null;
				double cantidad;
				if(txtalmacen.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Marbete");
					txtbuscar.requestFocus();
				}
				else if(txtdescripcion.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Producto");
					txtcodigo.requestFocus();
				}
				else if(txtcantidad.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique la cantidad");
					txtcantidad.requestFocus();
				}
				else{
					atributo=choice.getSelectedItem();
					codigo= txtcodigo.getText();
					marbete= txtbuscar.getText();
					ubicacion= txtubicacion.getText();
					almacen= txtalmacen.getText();
					cantidad= Double.parseDouble(txtcantidad.getText());
					String tabla="tercerconteo";
					String tabla2="inventariofinal";				
					String result1=validatercerconteoinsertar.main(marbete,tabla,tabla2);
					System.out.println("IICIA" +result1);
					if(result1.equals("NO")){			
						System.out.println("Inicio Insercion------>>>");
						String conteo="segundoconteo";
						String result=ValidaInsAct.main(codigo, marbete,conteo,atributo);
						System.out.println("IICIA" +result);
						if(result.equals("UPDATE"))
						{
							System.out.println("UPDATE");
							Actualizacion.main(conteo,cantidad+"",codigo,marbete,usu,atributo);
						}
						else if(result.equals("INSERT"))
						{						
							System.out.println("INSERT");
							Insersion.main(conteo, cantidad+"", codigo, marbete, usu, ubicacion, almacen,atributo);						
						}	
						btnValidar.setEnabled(false); 
						JOptionPane.showMessageDialog(contentPane, "Registrado Correctamente");				      
						dispose();
						formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
						segundoconteo.setVisible(true);					 								
					}
					else 
					{
						JOptionPane.showMessageDialog(contentPane, "ESTE MARBETE ESTA YA ESTA REGISTRADO COMO TERCER CONTEO O INVENTARIO FINAL");						
						dispose();
						formularios.segundoconteo segundoconteo = new formularios.segundoconteo();
						segundoconteo.setVisible(true);
					}
				}
			}		
		});
		
		
		
				btnConfirmarConteo.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnConfirmarConteo.setVerticalAlignment(SwingConstants.TOP);
		btnConfirmarConteo.setBounds(342, 374, 155, 28);
		contentPane.add(btnConfirmarConteo);
		
		JLabel lblAtributo = new JLabel("Atributo:");
		lblAtributo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAtributo.setForeground(Color.WHITE);
		lblAtributo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAtributo.setBounds(481, 203, 89, 19);
		contentPane.add(lblAtributo);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(segundoconteo.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel_5.setBounds(10, 0, 855, 429);
		contentPane.add(lblNewLabel_5);
	}
}