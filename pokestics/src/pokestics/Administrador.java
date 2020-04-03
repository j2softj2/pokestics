package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Administrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoUsuario;
	private JTextField campoUsuarioBorrarBd;
	private JTextField campoUsuarioCrear;
	private JTextField campoContraseña;
	private JTextField campoStackInicial;
	
	
	static Connection conexion = Inicio.getConexion();
	private JTextField campoNombreCompleto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Administrador dialog = new Administrador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Administrador() {
		setTitle("Administrador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/imagenes/logoSimple.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cerrarConexion(conexion);
			}
		});
		setModalityType(ModalityType.APPLICATION_MODAL);
		setMinimumSize(new Dimension(500, 500));
		setBounds(100, 100, 1069, 685);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel etBorrarUsuario = new JLabel("Borrar usuario");
		etBorrarUsuario.setForeground(Color.WHITE);
		etBorrarUsuario.setBackground(new Color(0, 100, 0));
		etBorrarUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etBorrarUsuario.setBounds(10, 11, 164, 42);
		contentPanel.add(etBorrarUsuario);
		
		JLabel etUsuario = new JLabel("Usuario");
		etUsuario.setForeground(Color.WHITE);
		etUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etUsuario.setBounds(10, 64, 72, 35);
		contentPanel.add(etUsuario);
		
		JSeparator separardorBorrar = new JSeparator();
		separardorBorrar.setForeground(Color.WHITE);
		separardorBorrar.setBounds(10, 51, 155, 2);
		contentPanel.add(separardorBorrar);
		
		campoUsuario = new JTextField();
		campoUsuario.setBounds(92, 64, 155, 26);
		contentPanel.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		JButton botonBorrarUsuario = new JButton("");
		//borra usuario introducido
		botonBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st;
				String usuario = campoUsuario.getText();
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar al usuario "+usuario+"?", "Advertencia",JOptionPane.YES_NO_OPTION);
					if(respuesta == 0) {
						try {
							st = conexion.createStatement();
							st.executeUpdate("REVOKE ALL PRIVILEGES ON TABLE USUARIO,JUEGA,MANOS,ESTADISTICASJUEGO,ESTADISTICASCASH,OBTIENE,JUGADORES,JUEGAN,BANKROLL,SESION FROM "+usuario);
							st.executeUpdate("DROP USER "+ usuario);
							JOptionPane.showMessageDialog(null,"Usuario borrado correctamente");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					}
				
			}
		});
		botonBorrarUsuario.setBackground(Color.WHITE);
		botonBorrarUsuario.setIcon(new ImageIcon(Administrador.class.getResource("/botones/borrar.png")));
		botonBorrarUsuario.setBounds(282, 64, 155, 26);
		contentPanel.add(botonBorrarUsuario);
		
		JLabel etBorrarBaseDatos = new JLabel("Borrar base de datos");
		etBorrarBaseDatos.setForeground(Color.WHITE);
		etBorrarBaseDatos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etBorrarBaseDatos.setBackground(new Color(0, 100, 0));
		etBorrarBaseDatos.setBounds(10, 110, 237, 42);
		contentPanel.add(etBorrarBaseDatos);
		
		JSeparator separadorBorrarBd = new JSeparator();
		separadorBorrarBd.setForeground(Color.WHITE);
		separadorBorrarBd.setBounds(10, 150, 211, 2);
		contentPanel.add(separadorBorrarBd);
		
		JLabel etUsuarioBd = new JLabel("Usuario");
		etUsuarioBd.setForeground(Color.WHITE);
		etUsuarioBd.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etUsuarioBd.setBounds(10, 163, 72, 35);
		contentPanel.add(etUsuarioBd);
		
		campoUsuarioBorrarBd = new JTextField();
		campoUsuarioBorrarBd.setColumns(10);
		campoUsuarioBorrarBd.setBounds(92, 163, 155, 26);
		contentPanel.add(campoUsuarioBorrarBd);
		
		JButton botonBorrarBd = new JButton("");
		botonBorrarBd.setIcon(new ImageIcon(Administrador.class.getResource("/botones/borrar.png")));
		botonBorrarBd.setBackground(Color.WHITE);
		botonBorrarBd.setBounds(282, 163, 155, 26);
		contentPanel.add(botonBorrarBd);
		
		JLabel etFecha = new JLabel("Fecha");
		etFecha.setForeground(Color.WHITE);
		etFecha.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etFecha.setBounds(10, 212, 72, 35);
		contentPanel.add(etFecha);
		
		JSpinner spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerDateModel(new Date(1581202800000L), new Date(949618837000L), new Date(3474658800000L), Calendar.DAY_OF_YEAR));
		spinnerDia.setBounds(92, 221, 98, 20);
		contentPanel.add(spinnerDia);
		
		JLabel etCrearUsuario = new JLabel("Crear usuario");
		etCrearUsuario.setForeground(Color.WHITE);
		etCrearUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etCrearUsuario.setBackground(new Color(0, 100, 0));
		etCrearUsuario.setBounds(10, 259, 169, 42);
		contentPanel.add(etCrearUsuario);
		
		JSeparator separadorCrearUser = new JSeparator();
		separadorCrearUser.setForeground(Color.WHITE);
		separadorCrearUser.setBounds(10, 299, 155, 2);
		contentPanel.add(separadorCrearUser);
		
		JLabel etUsuarioCrear = new JLabel("Usuario");
		etUsuarioCrear.setForeground(Color.WHITE);
		etUsuarioCrear.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etUsuarioCrear.setBounds(10, 312, 72, 35);
		contentPanel.add(etUsuarioCrear);
		
		campoUsuarioCrear = new JTextField();
		campoUsuarioCrear.setColumns(10);
		campoUsuarioCrear.setBounds(113, 312, 155, 26);
		contentPanel.add(campoUsuarioCrear);
		
		JLabel etContraseña = new JLabel("Contrase\u00F1a");
		etContraseña.setForeground(Color.WHITE);
		etContraseña.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etContraseña.setBounds(10, 358, 88, 35);
		contentPanel.add(etContraseña);
		
		campoContraseña = new JTextField();
		campoContraseña.setColumns(10);
		campoContraseña.setBounds(113, 364, 155, 26);
		contentPanel.add(campoContraseña);
		
		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setToolTipText("Tipo de usuario: Invitado solo observador, Usuario uso normal ");
		comboBoxRol.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] {"Invitado", "Usuario"}));
		comboBoxRol.setBounds(113, 457, 155, 26);
		contentPanel.add(comboBoxRol);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			//obtencion de la fecha actual
			Calendar calendario = new GregorianCalendar();
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			int mes = calendario.get(Calendar.MONTH)+1;
			int año = calendario.get(Calendar.YEAR);
			String fecha = año+"/"+mes+"/"+dia;
			//crear usuario
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				String usuario = campoUsuarioCrear.getText();
				String contraseña = campoContraseña.getText();
				String tipo = (String)comboBoxRol.getSelectedItem();
				String nombreCompleto = campoNombreCompleto.getText();
				Double bankrol = 0.0;
				String combinacion = "[0-9]{1,10}.[0-9]{1,2}";
				Pattern p = Pattern.compile(combinacion);
				Matcher m = p.matcher(campoStackInicial.getText());
				if(m.matches()) {
					if(Double.parseDouble(campoStackInicial.getText())>0){
						bankrol = Double.parseDouble(campoStackInicial.getText());
					};
				}
				
				String creacionUsuario = "CREATE USER "+usuario+" WITH PASSWORD '"+ contraseña +"'";
				String creacionInvitado = "CREATE USER "+usuario; 
				String privilegiosObservador = "GRANT SELECT ON TABLE usuario,juega,sesion,manos,obtiene,estadisticasJuego,"
						+ "estadisticasCash,juegan,jugadores,bankroll TO "+usuario;
				String privilegiosUsuario = "GRANT SELECT,INSERT,UPDATE ON TABLE usuario,juega,sesion,manos,obtiene,estadisticasJuego,"
						+ "estadisticasCash,juegan,jugadores,bankroll TO "+usuario;
				String revocarTodo = "REVOKE ALL ON DATABASE pokestics FROM "+usuario;	
				String insertarBankrol = "INSERT INTO BANKROLL (CASH,USUARIO,FECHA) VALUES ('"+ bankrol + "','"+nombreCompleto+"','"+fecha+"')"; 
				String insertarUsuario = "INSERT INTO USUARIO (NOMBRE,USUARIOSALA) VALUES ('"+nombreCompleto+"','"+usuario+"')";
				
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de crear al usuario "+usuario+"?", "Advertencia",JOptionPane.YES_NO_OPTION);
				if(respuesta == 0){
					try {
						st = conexion.createStatement();
					if(tipo.equals("Invitado") && usuario!=null){
						st.executeUpdate(creacionInvitado);
						st.executeUpdate(revocarTodo);
						st.executeUpdate(privilegiosObservador);
						st.executeUpdate(insertarUsuario);
					}
					else if(tipo.equals("Usuario") && usuario!=null && nombreCompleto !=null && bankrol!=null && contraseña!=null) {
						st.executeUpdate(creacionUsuario);
						st.executeUpdate(revocarTodo);
						st.executeUpdate(privilegiosUsuario);
						st.executeUpdate(insertarUsuario);
						st.executeUpdate(insertarBankrol);
					}
					else {
						JOptionPane.showMessageDialog(null,"Es necesario rellenar los datos para cada tipo de usuario (Invitado solo usuario)");
					}
				JOptionPane.showMessageDialog(null,"Usuario creado correctamente");
					}
					catch(SQLException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}
				
			}
		});
		button.setIcon(new ImageIcon(Administrador.class.getResource("/botones/crear.png")));
		button.setBackground(Color.WHITE);
		button.setBounds(282, 338, 155, 26);
		contentPanel.add(button);
		
		JLabel etModificar = new JLabel("Modificar BDD");
		etModificar.setForeground(Color.WHITE);
		etModificar.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etModificar.setBackground(new Color(0, 100, 0));
		etModificar.setBounds(727, 11, 169, 42);
		contentPanel.add(etModificar);
		
		JSeparator separadorCrear = new JSeparator();
		separadorCrear.setForeground(Color.WHITE);
		separadorCrear.setBounds(713, 51, 183, 2);
		contentPanel.add(separadorCrear);
		
		JSeparator separardor2 = new JSeparator();
		separardor2.setOrientation(SwingConstants.VERTICAL);
		separardor2.setForeground(Color.WHITE);
		separardor2.setBounds(608, 11, 12, 459);
		contentPanel.add(separardor2);
		
		JTextArea campoConsulta = new JTextArea();
		campoConsulta.setText("Introduzca modificaci\u00F3n de la base de datos en lenguaje SQL\r\n");
		campoConsulta.setBounds(640, 71, 346, 399);
		contentPanel.add(campoConsulta);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Administrador.class.getResource("/botones/modificar.png")));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(741, 500, 155, 26);
		contentPanel.add(button_1);
		
		JLabel etStackInicial = new JLabel("Bankroll");
		etStackInicial.setForeground(Color.WHITE);
		etStackInicial.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etStackInicial.setBounds(10, 404, 93, 35);
		contentPanel.add(etStackInicial);
		
		campoStackInicial = new JTextField();
		campoStackInicial.setColumns(10);
		campoStackInicial.setBounds(113, 413, 155, 26);
		contentPanel.add(campoStackInicial);
		
		JLabel etRol = new JLabel("Rol");
		etRol.setForeground(Color.WHITE);
		etRol.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etRol.setBounds(10, 451, 93, 35);
		contentPanel.add(etRol);
		
		JLabel etNombreCompleto = new JLabel("Nombre Completo");
		etNombreCompleto.setForeground(Color.WHITE);
		etNombreCompleto.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		etNombreCompleto.setBounds(10, 493, 140, 35);
		contentPanel.add(etNombreCompleto);
		
		campoNombreCompleto = new JTextField();
		campoNombreCompleto.setColumns(10);
		campoNombreCompleto.setBounds(10, 538, 258, 33);
		contentPanel.add(campoNombreCompleto);
		
		
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('a');
		menuArchivo.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.setBackground(Color.WHITE);
		menuArchivo.setIcon(new ImageIcon(Principal.class.getResource("/botones/archivo10x10.png")));
		menuArchivo.setSelectedIcon(null);
		barraMenu.add(menuArchivo);
		
		JMenuItem menuItemCerrar = new JMenuItem("Cerrar");
		menuItemCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		//cierra la ventana
		menuItemCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarAplicacion();
				cerrarConexion(conexion);
			}
		});
		menuItemCerrar.setBackground(Color.WHITE);
		menuItemCerrar.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.add(menuItemCerrar);
		
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setIcon(new ImageIcon(Principal.class.getResource("/botones/ayuda10x10.png")));
		menuAyuda.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuAyuda.setBackground(Color.WHITE);
		barraMenu.add(menuAyuda);
		
		JMenuItem menuItemManual = new JMenuItem("Manual de ayuda");
		menuItemManual.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuItemManual.setBackground(Color.WHITE);
		menuAyuda.add(menuItemManual);
		
		JMenuItem menuItemAcerca = new JMenuItem("Acerca de...");
		menuItemAcerca.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuItemAcerca.setBackground(Color.WHITE);
		menuAyuda.add(menuItemAcerca);
	}
	
	private void cerrarAplicacion() {
		this.dispose();
	}
	
	/**
	 * Cerrar conexion a base de datos
	 */
	private static void cerrarConexion(Connection con) {
		try {
			con.close();
			System.out.println("Conexion cerrada");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
}
