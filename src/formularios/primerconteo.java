package formularios;

import java.awt.BorderLayout;
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
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.SwingConstants;

import primerconteo.primer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Choice;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class primerconteo extends JFrame {

	public static LinkedList contenedor=new LinkedList();
	
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					primerconteo frame = new primerconteo();
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
	public primerconteo() {
		final Choice choice = new Choice();
		choice.setForeground(Color.BLACK);
		choice.setBackground(Color.WHITE);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				Connection con = null;
				ResultSet rs=null;
				try {
					choice.removeAll();
					choice.add("");
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";				
					con = DriverManager.getConnection(url, usuario, pass);
					PreparedStatement ps=con.prepareStatement("SELECT atributo FROM inventario_teorico GROUP BY atributo");
					rs=ps.executeQuery();
					while (rs.next()){
						choice.add(rs.getString(1));
					}
					con.close();	
				}catch(Exception e){
					e.printStackTrace();
				}
			
			
			
			
			}
		});
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
		btnLimpiarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 dispose();
			      formularios.primerconteo primerconteo = new formularios.primerconteo();
					primerconteo.setVisible(true);
				 
				
			}
		});
		btnLimpiarFormulario.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnLimpiarFormulario.setBounds(601, 32, 197, 26);
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
		txtuom.setBounds(580, 236, 78, 21);
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
		menuBar.setBounds(0, 0, 855, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(primerconteo.class.getResource("/imagenes/salir.png")));
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
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					//String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
					String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					 ResultSet rs = null;
					/* PreparedStatement ps = con.prepareStatement("SELECT m_warehouse.name as almacen,m_locator.value as ubicacion,m_locator.barcode  as marbete "
 +" FROM m_locator, "
 +" m_warehouse  "
 +" WHERE  "
 +" m_locator.m_warehouse_id=m_warehouse.m_warehouse_id " 
 +" AND m_warehouse.isactive='Y'  "
 +" and m_locator.isactive='Y' "
 +" AND m_Warehouse.name NOT LIKE '4E_MP TEMPORAL' " 
 +" AND m_Warehouse.name NOT LIKE '4G_SMO m_productUCCION' "  
 +" AND m_Warehouse.name NOT LIKE '4G_1D ADUANA'  "
 +" AND m_Warehouse.name NOT LIKE '4E BRANDS EUA'  "
 +" AND m_Warehouse.name NOT LIKE '4G_1F REFACCIONES' " 
 +" AND m_Warehouse.name NOT LIKE 'ALMACEN_MERIDA' "
 +" AND m_locator.barcode like '"+marbete+"'"
 +" and m_locator.value not like '----------------------------------------' "
 +" and m_Warehouse.ad_Client_id not like '23C59575B9CF467C9620760EB255B389' "
 +" ORDER BY m_warehouse.name,m_locator.value ASC");*/
					 PreparedStatement ps = con.prepareStatement("select almacen,hueco from ubicaciones where marbete like '"+marbete+"'");
					 rs = ps.executeQuery();
				      System.out.println(" Termina Query.......");
					 
				      rs.next();
				      
				      txtalmacen.setText(rs.getString(1));
				      txtubicacion.setText(rs.getString(2));
				      
				      txtbuscar.setEditable(false);
				      txtalmacen.setEditable(false);
				      txtubicacion.setEditable(false);
				      
				      btnBuscar.setEnabled(false); 
				      con.close();
				      
					 
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Numero de Marbete no existe");
					txtbuscar.setText("");
					e.printStackTrace();
				}
			}
		});
			
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String marbete = txtbuscar.getText();
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					/*String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";*/
					String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					 ResultSet rs = null;
					 /*PreparedStatement ps = con.prepareStatement("SELECT m_warehouse.name as almacen,m_locator.value as ubicacion,m_locator.barcode  as marbete "
 +" FROM m_locator, "
 +" m_warehouse  "
 +" WHERE  "
 +" m_locator.m_warehouse_id=m_warehouse.m_warehouse_id " 
 +" AND m_warehouse.isactive='Y'  "
 +" and m_locator.isactive='Y' "
 +" AND m_Warehouse.name NOT LIKE '4E_MP TEMPORAL' " 
 +" AND m_Warehouse.name NOT LIKE '4G_SMO m_productUCCION' "  
 +" AND m_Warehouse.name NOT LIKE '4G_1D ADUANA'  "
 +" AND m_Warehouse.name NOT LIKE '4E BRANDS EUA'  "
 +" AND m_Warehouse.name NOT LIKE '4G_1F REFACCIONES' " 
 +" AND m_Warehouse.name NOT LIKE 'ALMACEN_MERIDA' "
 +" AND m_locator.barcode like '"+marbete+"'"
 +" and m_locator.value not like '----------------------------------------' "
 +" and m_Warehouse.ad_Client_id not like '23C59575B9CF467C9620760EB255B389' "
 +" ORDER BY m_warehouse.name,m_locator.value ASC");*/
					 PreparedStatement ps = con.prepareStatement("select almacen,hueco from ubicaciones where marbete like '"+marbete+"'");
					 rs = ps.executeQuery();
				      System.out.println(" Termina Query.......");
					 
				      rs.next();
				      
				      txtalmacen.setText(rs.getString(1));
				      txtubicacion.setText(rs.getString(2));
				      
				      txtbuscar.setEditable(false);
				      txtalmacen.setEditable(false);
				      txtubicacion.setEditable(false);
				      
				      btnBuscar.setEnabled(false);
				      con.close();
					 
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Numero de Marbete no existe");
					txtbuscar.setText("");
					e.printStackTrace();
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
		
		JLabel lblNewLabel = new JLabel("Primer Conteo");
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
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					 ResultSet rs = null;
					 PreparedStatement ps = con.prepareStatement("SELECT prod.description,uom.name from m_product as prod,c_uom as uom  where prod.c_uom_id=uom.c_uom_id and prod.value like '"+codigo+"'");
					 rs = ps.executeQuery();
				      System.out.println(" Termina Query.......");
					 
				      rs.next();
				      
				      txtdescripcion.setText(rs.getString(1));
				      txtuom.setText(rs.getString(2));
				      
				      txtdescripcion.setEditable(false);
				      txtcodigo.setEditable(false);
				      
				      
				      btnValidar.setEnabled(false); 

				      con.close();
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
					JOptionPane.showMessageDialog(contentPane, "Error ------>>>Codigo OB3 no existe");
					txtcodigo.setText("");
					e.printStackTrace();
				}
			}
		});

			
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String codigo = txtcodigo.getText();
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					 ResultSet rs = null;
					 PreparedStatement ps = con.prepareStatement("SELECT prod.description,uom.name from m_product as prod,c_uom as uom  where prod.c_uom_id=uom.c_uom_id and prod.value like '"+codigo+"'");
					 rs = ps.executeQuery();
				      System.out.println(" Termina Query.......");
					 
				      rs.next();
				      
				      txtdescripcion.setText(rs.getString(1));
				      txtuom.setText(rs.getString(2));				      
				      txtdescripcion.setEditable(false);
				      txtcodigo.setEditable(false);				      				     				      
				      btnValidar.setEnabled(false); 
				      con.close();
					 
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
					JOptionPane.showMessageDialog(contentPane, "Error ------>>>Codigo OB3 no existe");
					txtcodigo.setText("");
					e.printStackTrace();
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
		
		choice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
					
			}
		});
		choice.setBounds(659, 286, 127, 20);
		contentPane.add(choice);
		
		JButton btnConfirmarConteo = new JButton("Confirmar Conteo");
		btnConfirmarConteo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String codigo= txtcodigo.getText();
				String marbete= txtbuscar.getText();
				//String cantidad= txtcantidad.getText();
				int cantidad= Integer.parseInt(txtcantidad.getText());
				String ubicacion= txtubicacion.getText();
				String almacen= txtalmacen.getText();
				String atributo= choice.getSelectedItem();
				
				validainsertar.validatercerconteoinsertar vins1=new validainsertar.validatercerconteoinsertar();
				String tabla="tercerconteo";
				String tabla2="inventariofinal";
				String result1=vins1.main(marbete,tabla,tabla2);
				System.out.println("IICIA" +result1);
				if(result1.equals("NO")){
					System.out.println("NO HAY NADA");
				
				
				
				
				 if(codigo.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Codigo");
			
				} else if(marbete.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Marbete");
				//} else if(String.valueOf(Integer.parseInt(txtcantidad.getText())) != null) {
				} else if(String.valueOf(Integer.parseInt(txtcantidad.getText())).isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Cantidad");
				} else {
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					int rsupdate;
					Statement stmtupdate = con.createStatement();
					
					System.out.println("Inicio Insercion------>>>");
					validainsertar.ValidaInsAct vins=new validainsertar.ValidaInsAct();
					String conteo="primerconteo";
					String result=vins.main(codigo, marbete,conteo,atributo);
					PreparedStatement psinsert = null ;
					System.out.println("IICIA" +result);
					if(result.equals("UPDATE")){
						System.out.println("UPDATE");
						psinsert = con.prepareStatement("UPDATE primerconteo SET  cantidad=cantidad::numeric+"+cantidad+" where codigo like '"+codigo+"' and marbete like '"+marbete+"' "
+ "AND atributo='"+atributo+"' AND fecha >  now()::DATE - CAST('4 days' AS INTERVAL) ");
					}
					else if(result.equals("INSERT")){
						System.out.println("INSERT");
						psinsert = con.prepareStatement("insert into primerconteo values('"+codigo+"','"+atributo+"','"+marbete+"','"+cantidad+"',now(),'"+ubicacion+"','"+almacen+"')");
					}
										 							
				rsupdate = psinsert.executeUpdate();
				System.out.println("Termine la Insercion------>>>");
				stmtupdate.close();
				
				con.close();
				System.out.println("Cerre la conexion------>>>");
				
				      btnValidar.setEnabled(false); 
				      JOptionPane.showMessageDialog(contentPane, "Registrado Correctamente");
				      
				      dispose();
				      formularios.primerconteo primerconteo = new formularios.primerconteo();
						primerconteo.setVisible(true);
					 
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
				//	JOptionPane.showMessageDialog(contentPane, "Infomacion Erronea favor de Verificar");
					
					e.printStackTrace();
				}
				
			}
				} else {
					JOptionPane.showMessageDialog(contentPane, "ESTE MARBETE ESTA YA ESTA REGISTRADO COMO TERCER CONTEO O INVENTARIO FINAL");
					 
				      dispose();
				      formularios.primerconteo primerconteo = new formularios.primerconteo();
						primerconteo.setVisible(true);
				}
				}
					
			});	
		
		
		
		
		
		
		
		
		
		
		
		btnConfirmarConteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {								
				
				
				
				
				String codigo= txtcodigo.getText();
				String marbete= txtbuscar.getText();
				String atributo=choice.getSelectedItem();
				//String cantidad= txtcantidad.getText();
				int cantidad= Integer.parseInt(txtcantidad.getText());
				String ubicacion= txtubicacion.getText();
				String almacen= txtalmacen.getText();
				
				validainsertar.validatercerconteoinsertar vins1=new validainsertar.validatercerconteoinsertar();
				String tabla="tercerconteo";
				String tabla2="inventariofinal";
				String result1=vins1.main(marbete,tabla,tabla2);
				System.out.println("IICIA" +result1);
				if(result1.equals("NO")){
					System.out.println("NO HAY NADA");
				
				
				 if(codigo.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Codigo");
			
				} else if(marbete.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Marbete");
				//} else if(String.valueOf(Integer.parseInt(txtcantidad.getText())) != null) {
				} else if(String.valueOf(Integer.parseInt(txtcantidad.getText())).isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Error ------>>> Verifique el contenido de Cantidad");
				} else {
				Connection con = null;
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://10.1.250.24:5932/inventarios_c5";
					String usuario = "postgres";
					String pass = "s3st2m1s4e";
					

					con = DriverManager.getConnection(url, usuario, pass);
					
					int rsupdate;
					Statement stmtupdate = con.createStatement();
					
					System.out.println("Inicio Insercion------>>>");
					validainsertar.ValidaInsAct vins=new validainsertar.ValidaInsAct();
					String conteo="primerconteo";
					String result=vins.main(codigo, marbete,conteo,atributo);
					PreparedStatement psinsert = null ;
					System.out.println("IICIA" +result);
					if(result.equals("UPDATE")){
						System.out.println("UPDATE");
						psinsert = con.prepareStatement("UPDATE primerconteo SET  cantidad=cantidad::numeric+"+cantidad+" where codigo like '"+codigo+"' and marbete like '"+marbete+"' "
+ "AND fecha >  now()::DATE - CAST('4 days' AS INTERVAL) AND atributo='"+atributo+"'");
					}
					else if(result.equals("INSERT")){
						System.out.println("INSERT");
						psinsert = con.prepareStatement("insert into primerconteo values('"+codigo+"','"+atributo+"','"+marbete+"','"+cantidad+"',now(),'"+ubicacion+"','"+almacen+"')");
					}										 							
				rsupdate = psinsert.executeUpdate();
				System.out.println("Termine la Insercion------>>>");
				stmtupdate.close();
				con.close();
				
				System.out.println("Cerre la conexion------>>>");
				
				      btnValidar.setEnabled(false); 
				      JOptionPane.showMessageDialog(contentPane, "Registrado Correctamente");
				      
				      dispose();
				      formularios.primerconteo primerconteo = new formularios.primerconteo();
						primerconteo.setVisible(true);
					 
				} catch (ClassNotFoundException e) {

					System.out.println("Conexion Fallida DRIVER------>>>");

					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Conexion BD NO CONECTA------>>>");
				//	JOptionPane.showMessageDialog(contentPane, "Infomacion Erronea favor de Verificar");
					
					e.printStackTrace();
				}
				
			}
			} else {
				JOptionPane.showMessageDialog(contentPane, "ESTE MARBETE ESTA YA ESTA REGISTRADO COMO TERCER CONTEO O INVENTARIO FINAL");
				 
			      dispose();
			      formularios.primerconteo primerconteo = new formularios.primerconteo();
					primerconteo.setVisible(true);
			}
			}
				
		});		
		btnConfirmarConteo.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnConfirmarConteo.setVerticalAlignment(SwingConstants.TOP);
		btnConfirmarConteo.setBounds(342, 374, 155, 28);
		contentPane.add(btnConfirmarConteo);
		
		txtcantidad = new TextField();
		txtcantidad.setForeground(Color.BLACK);
		txtcantidad.setBackground(Color.WHITE);
		txtcantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent a) {
				char c = a.getKeyChar();
				if((c<'0' || c>'9') && c != a.VK_BACK_SPACE && c != '.')  //<<>>
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
		
		JLabel lblAtributo = new JLabel("Atributo:");
		lblAtributo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAtributo.setForeground(Color.WHITE);
		lblAtributo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAtributo.setBounds(507, 286, 89, 19);
		contentPane.add(lblAtributo);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(primerconteo.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel_5.setBounds(0, 11, 855, 429);
		contentPane.add(lblNewLabel_5);
	}
}
