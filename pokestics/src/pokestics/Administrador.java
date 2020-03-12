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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Administrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoUsuario;
	private JTextField campoUsuarioBorrarBd;
	private JTextField campoUsuarioCrear;
	private JTextField campoContraseña;
	private JTextField campoStackInicial;

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
		
		JButton button = new JButton("");
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
			}
		});
		menuItemCerrar.setBackground(Color.WHITE);
		menuItemCerrar.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.add(menuItemCerrar);
		
		JMenu menuConfiguracion = new JMenu("Configuraci\u00F3n");
		menuConfiguracion.setIcon(new ImageIcon(Principal.class.getResource("/botones/configuracion10x10.png")));
		menuConfiguracion.setBackground(Color.WHITE);
		menuConfiguracion.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		barraMenu.add(menuConfiguracion);
		
		JMenuItem menuItemHistorial = new JMenuItem("Ruta historial de manos ");
		menuItemHistorial.setBackground(Color.WHITE);
		menuItemHistorial.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuConfiguracion.add(menuItemHistorial);
		
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
}
