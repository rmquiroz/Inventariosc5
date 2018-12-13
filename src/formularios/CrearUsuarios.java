package formularios;
import java.awt.EventQueue;

import gestionarusuario.*;

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
import conteos.Insersion;
import conteos.Productos;
import utilerias.postgresql;
import validainsertar.ValidaInsAct;
import validainsertar.validatercerconteoinsertar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.List;
import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class CrearUsuarios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JPasswordField pswdContra;
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
					CrearUsuarios frame = new CrearUsuarios();
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
	public CrearUsuarios() 
	{
		usuarios.usuario gestionusuario = new usuarios.usuario();
		final String usu = gestionusuario.getUsuario();
		System.out.println("Usuario Usuarios: "+usu);
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
				formularios.CrearUsuarios CrearUsuarios = new formularios.CrearUsuarios();
				CrearUsuarios.setVisible(true);				 			
			}
		});
		btnLimpiarFormulario.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnLimpiarFormulario.setBounds(600, 32, 197, 26);
		contentPane.add(btnLimpiarFormulario);		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(165, 168, 127, 23);
		contentPane.add(txtApellido);		
		txtNombre = new JTextField();
		txtNombre.setBounds(165, 134, 127, 23);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);		
		JLabel lblUbicacin = new JLabel("Apellido:");
		lblUbicacin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUbicacin.setForeground(Color.WHITE);
		lblUbicacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblUbicacin.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUbicacin.setBounds(22, 166, 101, 28);
		contentPane.add(lblUbicacin);
		
		pswdContra = new JPasswordField();
		pswdContra.setBounds(165, 209, 127, 23);
		contentPane.add(pswdContra);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 865, 21);
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
		
		JMenuItem mntmVolver = new JMenuItem("Volver");
		mntmVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				formularios.InicioSuperAdmin InicioSuperAdmin = new formularios.InicioSuperAdmin();
				InicioSuperAdmin.setVisible(true);
			}
		});
		mntmVolver.setIcon(new ImageIcon(CrearUsuarios.class.getResource("/imagenes/regresar.png")));
		mnArchivo.add(mntmVolver);
		mnArchivo.add(mntmSalir);
		
		txtUsuario = new JTextField();
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String ex=UsuarioExistente.main(txtUsuario.getText());
				if(ex.equals("NO")){
					pswdContra.setText(txtUsuario.getText());
				}
				else{
				txtUsuario.setText("");
				txtUsuario.requestFocus();
				JOptionPane.showMessageDialog(contentPane, "USUARIO EXISTENTE");				
				}
			}
		});
		

		final Choice choice = new Choice();
		choice.setForeground(Color.BLACK);
		choice.setBackground(Color.LIGHT_GRAY);
		choice.setBounds(165, 312, 127, 20);
		contentPane.add(choice);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				Connection con = null;
				ResultSet rs=null;
				try {
					choice.removeAll();
					con = postgresql.getConexion();
					PreparedStatement ps=con.prepareStatement("select nombre from departamento ORDER by nombre");
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
			

		txtUsuario.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(165, 88, 127, 23);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel = new JLabel("Crear Usuario");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 32, 855, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(22, 87, 101, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setBounds(22, 125, 90, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblCodigo = new JLabel("Contrase\u00F1a: ");
		lblCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCodigo.setBounds(22, 205, 106, 21);
		contentPane.add(lblCodigo);
		
		JLabel lblNewLabel_4 = new JLabel("Conteo:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_4.setBounds(21, 249, 102, 19);
		contentPane.add(lblNewLabel_4);

		final JComboBox ComboConteo = new JComboBox();
		ComboConteo.setModel(new DefaultComboBoxModel(new String[] {"Primer Conteo", "Segundo Conteo ", "Tercer Conteo", "Administrador"}));
		ComboConteo.setBounds(165, 249, 127, 23);
		contentPane.add(ComboConteo);
	
		
		JButton btnConfirmarConteo = new JButton("Registrar Usuario");
		btnConfirmarConteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || pswdContra.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "COMPLETE LOS DATOS DEL USUARIO");					
				}
				else
				{
					
					CrearUsuario.main(txtUsuario.getText(),txtNombre.getText(),txtApellido.getText(),pswdContra.getText(),ComboConteo.getSelectedItem().toString(),usu,choice.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Usuario Registrado Correctamente");
					dispose();
					formularios.CrearUsuarios CrearUsuarios = new formularios.CrearUsuarios();
					CrearUsuarios.setVisible(true);
					
				}
			}
		});
		btnConfirmarConteo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}		
		});	
				
				JLabel lblDepartamento = new JLabel("Departamento:");
				lblDepartamento.setFont(new Font("Dialog", Font.BOLD, 16));
				lblDepartamento.setForeground(Color.WHITE);
				lblDepartamento.setBounds(21, 293, 127, 42);
				contentPane.add(lblDepartamento);
		
		
		
				btnConfirmarConteo.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnConfirmarConteo.setVerticalAlignment(SwingConstants.TOP);
		btnConfirmarConteo.setBounds(342, 374, 155, 28);
		contentPane.add(btnConfirmarConteo);
				JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(primerconteo.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel_5.setBounds(0, 0, 865, 440);
		contentPane.add(lblNewLabel_5);
	}
}
