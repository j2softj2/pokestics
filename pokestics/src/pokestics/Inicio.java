package pokestics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Inicio {

	/**
	 * Conexion a la base de datos
	 */
	private static Connection conexion = null;
	
	/**
	 * metodo getter conexion
	 */
	public static Connection getConexion() {
		return conexion;
	}
	
	
	private JFrame frmPokestics;
	private JTextField campoUsuario;
	private JTextField campoPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmPokestics.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPokestics = new JFrame();
		frmPokestics.setResizable(false);
		frmPokestics.getContentPane().setBackground(new Color(60, 179, 113));
		frmPokestics.getContentPane().setLayout(null);
		
		
		JLabel etUsuario = new JLabel("USUARIO");
		etUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		etUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		etUsuario.setForeground(new Color(255, 255, 255));
		etUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etUsuario.setBackground(new Color(0, 100, 0));
		etUsuario.setBounds(251, 29, 173, 34);
		frmPokestics.getContentPane().add(etUsuario);
		
		JLabel etPass = new JLabel("CONTRASE\u00D1A");
		etPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		etPass.setHorizontalAlignment(SwingConstants.CENTER);
		etPass.setForeground(Color.WHITE);
		etPass.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etPass.setBackground(new Color(0, 100, 0));
		etPass.setBounds(251, 162, 173, 34);
		frmPokestics.getContentPane().add(etPass);
		
		campoUsuario = new JTextField();
		campoUsuario.setAutoscrolls(false);
		campoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		campoUsuario.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campoUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoUsuario.setBounds(251, 92, 173, 41);
		frmPokestics.getContentPane().add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoPass = new JPasswordField();
		campoPass.setAutoscrolls(false);
		campoPass.setHorizontalAlignment(SwingConstants.CENTER);
		campoPass.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campoPass.setColumns(10);
		campoPass.setBounds(251, 225, 173, 41);
		frmPokestics.getContentPane().add(campoPass);
		
		JLabel etObservador = new JLabel("Entrar como observador");
		etObservador.setAlignmentX(Component.CENTER_ALIGNMENT);
		etObservador.setHorizontalAlignment(SwingConstants.CENTER);
		etObservador.setForeground(Color.WHITE);
		etObservador.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etObservador.setBackground(new Color(0, 100, 0));
		etObservador.setBounds(251, 353, 173, 34);
		frmPokestics.getContentPane().add(etObservador);
		
		JButton botonObservador = new JButton("");
		botonObservador.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonObservador.setBorderPainted(false);
		botonObservador.setIcon(new ImageIcon(Inicio.class.getResource("/botones/botonObservador.png")));
		botonObservador.setBorder(null);
		botonObservador.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 12));
		botonObservador.setForeground(new Color(0, 0, 0));
		botonObservador.setBackground(new Color(255, 255, 255));
		botonObservador.setBounds(277, 416, 121, 21);
		frmPokestics.getContentPane().add(botonObservador);
		/*accion boton entrar*/
		JButton botonEntrar = new JButton("");
		botonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = campoUsuario.getText();
				String pass = campoPass.getText();
					if(usuario.equals("superusuario")){usuario = "postgres";}
				conexionBaseDatos(usuario,pass);
			}
		});
		botonEntrar.setSelectedIcon(null);
		botonEntrar.setIcon(new ImageIcon(Inicio.class.getResource("/botones/botonEntrar.png")));
		botonEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonEntrar.setBorder(null);
		botonEntrar.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 16));
		botonEntrar.setBounds(252, 295, 171, 29);
		frmPokestics.getContentPane().add(botonEntrar);
		frmPokestics.setBackground(new Color(0, 100, 0));
		frmPokestics.setMinimumSize(new Dimension(690, 500));
		frmPokestics.setTitle("POKESTICS");
		frmPokestics.setBounds(100, 100, 693, 500);
		frmPokestics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void conexionBaseDatos(String usuario, String pass) {
		//registro del driver
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Error al registrar el driver de PostreSQL: "+ ex);
			//System.out.println("Error al registrar el driver de PostreSQL: "+ ex);
		}
		//conexion usando usuario y pass
		
			try {
				conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pokestics",usuario,pass);
					if(usuario.equals("postgres")) usuario = "superusuario";
				JOptionPane.showMessageDialog(null, "Conectado a la base de datos como "+usuario);
				//abre la ventana principal de la aplicacion para usuarios normales o la de administrador en caso de ser el admin
				if(usuario.equals("superusuario")) {
					Administrador adm = new Administrador();
						adm.setMinimumSize(new Dimension(1200,800));
						adm.setLocationRelativeTo(null);
						adm.pack();
						adm.setVisible(true);
						
				}
				else {
					Principal p = new Principal();
					p.setMinimumSize(new Dimension(1200,800));
						p.pack();
						p.setLocationRelativeTo(null);
						p.setVisible(true);		
				}
				
					
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos" + e);
				//System.out.println("Error al conectar a la base de datos " + e);
			}
	}
}
