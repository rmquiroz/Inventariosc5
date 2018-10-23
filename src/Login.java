
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import usuarios.usuario;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import formularios.inicio;
import gestionarusuario.gestionusuario;

import java.awt.SystemColor;

import javax.swing.DropMode;

import java.awt.TextField;

import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(Color.DARK_GRAY);
		setTitle("SISTEMA DE GESTION DE INVENTARIOS COPYRIGHT SISTEMAS ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 352);
		setSize(750,400); 
		setResizable(false); 
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(224, 236, 138, 34);
		lblUsuario.setFont(new Font("Candara", Font.PLAIN, 20));
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(219, 267, 143, 34);
		lblContrasea.setFont(new Font("Candara", Font.PLAIN, 20));
		contentPane.add(lblContrasea);

		txtusuario = new JTextField();
		txtusuario.setBounds(359, 244, 170, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);

		txtpass = new JPasswordField();
		txtpass.setBounds(359, 275, 170, 20);
		contentPane.add(txtpass);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Candara", Font.PLAIN, 14));
		btnIngresar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/candado.png")));
		btnIngresar.setBounds(153, 312, 159, 34);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresar();
			}
		});
		contentPane.add(btnIngresar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Candara", Font.PLAIN, 14));
		btnSalir.setIcon(new ImageIcon(Login.class.getResource("/imagenes/salir.png")));
		btnSalir.setBounds(427, 312, 152, 34);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		contentPane.add(btnSalir);
		
		JLabel lblSistemaDeToma_1 = new JLabel("Sistema de Toma de Inventarios Version 1.1 Copyright Desarrollo de Sistemas 4e Global SAPI de CV");
		lblSistemaDeToma_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeToma_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSistemaDeToma_1.setFont(new Font("Candara", Font.PLAIN, 9));
		lblSistemaDeToma_1.setBounds(0, 357, 744, 14);
		contentPane.add(lblSistemaDeToma_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/sistemainventariosv2.png")));
		lblNewLabel.setBounds(266, 0, 220, 232);
		contentPane.add(lblNewLabel);
	}

	protected void ingresar() {
		String usuario = txtusuario.getText();
		String pass = String.valueOf(txtpass.getPassword());

		gestionusuario gestionusuario = new  gestionusuario();

		usuarios.usuario usuario2 = new usuario();
		usuario2.setUsuario(usuario);
		usuario2.setPass(pass);

		String usu = gestionusuario.obtenerusuario(usuario2);

		if (usu != null) {
			JOptionPane.showMessageDialog(contentPane, "Bienvenido de nuevo");				
			this.dispose();
			if(usu.equals("1"))
			{
				formularios.primerconteo conteo=new formularios.primerconteo();
				conteo.setVisible(true);
			}
			if(usu.equals("2")){
				formularios.segundoconteo conteo=new formularios.segundoconteo();
				conteo.setVisible(true);
				
			}
			if(usu.equals("3"))
			{
				inicio bienvenido = new inicio();
				bienvenido.setVisible(true);
			}			
			if(usu.equals("4"))
			{
				formularios.tercerconteo  bienvenido = new formularios.tercerconteo();
				bienvenido.setVisible(true);
			}			
			
		} else {
			JOptionPane.showMessageDialog(contentPane, "Datos incorectos",
					"Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	protected void salir() {
		System.exit(0);

	}
}
