package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase Principal en la que se muestra los datos de la sesion actual, se encuentra la calculadora de probabilidades,
 * y se tiene acceso a las tablas de datos y graficas. Es la ventana principal del programa desde la que se administra todo.
 * @author Rafael Jimenez Villarreal
 *
 */
public class Principal extends JFrame {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField campoApuesta;
	private JTextField campoBote;
	private JTextField campoProbMano;
	private JTextField campoRiesgo;
	/**
	 * atributo que indica el archivo del historial de manos.
	 */
	private File archivo;
	
	
	

	/**
	 * Atributo con la conexion a la base de datos obtenida desde la ventana Inicio
	 */
	static Connection conexion = Inicio.getConexion();
	private JTextField campoUsuario;
	private JTextField campoCash;
	private JTextField campoManos;
	private JTextField campoFlop;
	private JTextField campoTurn;
	private JTextField campoRiver;
	private JTextField campoGanadas;
	private JTextField campoApuestaMedia;
	private JTextField campoRetiradas;

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal dialog = new Principal();
			dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		//establece el icono
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenesFondo/logoSimple.png")));
		addWindowListener(new WindowAdapter() {
			/**
			 * Cuando se abre la ventana lee los historiales que no hayan sido leidos y se guardan en la base de datos,
			 * una vez hecho esto cada 10 segundos y siempre que el usuario no sea observador obtiene los datos de la sesion en curso
			 * (por defecto el ultimo archivo) y los muestra actualizando cada 10 segundos
			 */
			@Override
			public void windowOpened(WindowEvent e) {
				//lee historiales
				leeHistorialesNoLeidos();	
				//ejecucion cada 10 segundos
					if(Inicio.getUsuario()!="observador") {
						Timer timer = new Timer (10000, new ActionListener ()
						{
						    public void actionPerformed(ActionEvent e)
						    {
								//actualiza datos sesion actual
								
						    	try{
						    		//lee los datos del ultimo historial
						    		DatosHistorial leer = new DatosHistorial(new File(buscaHistorialNuevo()));
						    		//realiza una copia 
									ArrayList<String> datos = new ArrayList<String>(leer.datosSesion());
									campoUsuario.setText(Inicio.getUsuario());
									campoCash.setText(datos.get(0));
									campoApuestaMedia.setText(datos.get(1));
									campoFlop.setText(datos.get(2));
									campoTurn.setText(datos.get(3));
									campoRiver.setText(datos.get(4));
									campoGanadas.setText(datos.get(5));
									campoRetiradas.setText(datos.get(6));
									campoManos.setText(datos.get(7));
						    	}
						    	catch(Exception e2) {
						    		System.out.print(e2.getMessage());
						    	}
						     }
						});
						timer.start();
						
					}
					
					
					
			}
			/**
			 * Cuando se realiza la accion de cerrar la ventana se cierra la conexion a la base de datos y muestra la ventana Inicio
			 */
			@Override
			public void windowClosed(WindowEvent e) {
				
				cerrarConexion(conexion);
				Inicio.ocultar(true);
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		setTitle("Principal");
		setResizable(false);
		setMinimumSize(new Dimension(1200, 800));
		setBounds(100, 100, 1150, 762);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separadorCalculadora = new JSeparator();
		separadorCalculadora.setBounds(751, 5, 2, 730);
		separadorCalculadora.setBackground(new Color(255, 255, 255));
		separadorCalculadora.setOrientation(SwingConstants.VERTICAL);
		contentPanel.add(separadorCalculadora);
		
		JLabel etCalculadora = new JLabel("Calculadora");
		etCalculadora.setForeground(new Color(255, 255, 255));
		etCalculadora.setBounds(892, 5, 134, 24);
		etCalculadora.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		contentPanel.add(etCalculadora);
		
		JLabel etCartasPropias = new JLabel("Cartas propias");
		etCartasPropias.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etCartasPropias.setForeground(new Color(255, 255, 255));
		etCartasPropias.setBounds(763, 47, 101, 24);
		contentPanel.add(etCartasPropias);
		
		JComboBox comboBoxCartaPropia1 = new JComboBox();
		comboBoxCartaPropia1.setToolTipText("Introduzca la primera carta de su mano : C-Corazones P-Picas T-Treboles D-Diamantes");
		comboBoxCartaPropia1.setModel(new DefaultComboBoxModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCartaPropia1.setBounds(892, 50, 50, 20);
		contentPanel.add(comboBoxCartaPropia1);
		
		JComboBox comboBoxCartaPropia2 = new JComboBox();
		comboBoxCartaPropia2.setToolTipText("Introduzca la segunda carta de su mano : C-Corazones P-Picas T-Treboles D-Diamantes");
		comboBoxCartaPropia2.setModel(new DefaultComboBoxModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCartaPropia2.setBounds(956, 50, 50, 20);
		contentPanel.add(comboBoxCartaPropia2);
		
		JLabel etPosicionMesa = new JLabel("Posici\u00F3n en mesa");
		etPosicionMesa.setForeground(Color.WHITE);
		etPosicionMesa.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etPosicionMesa.setBounds(763, 82, 117, 24);
		contentPanel.add(etPosicionMesa);
		
		JList listaPosicion = new JList();
		listaPosicion.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 0)), "Posici\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 0)));
		listaPosicion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPosicion.setModel(new AbstractListModel() {
			String[] values = new String[] {"DEALER", "SB", "BB", "3", "4", "5", "6", "7", "8", "9"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaPosicion.setBounds(779, 111, 85, 206);
		contentPanel.add(listaPosicion);
		
		JLabel etCartasComunitarias = new JLabel("Cartas comunitarias");
		etCartasComunitarias.setForeground(Color.WHITE);
		etCartasComunitarias.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etCartasComunitarias.setBounds(902, 81, 134, 24);
		contentPanel.add(etCartasComunitarias);
		
		JComboBox comboBoxCarta1 = new JComboBox();
		comboBoxCarta1.setModel(new DefaultComboBoxModel(new String[] {"NO","AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCarta1.setToolTipText("Carta comunitaria 1 (Flop)");
		comboBoxCarta1.setBounds(945, 111, 50, 20);
		contentPanel.add(comboBoxCarta1);
		
		JComboBox comboBoxCarta2 = new JComboBox();
		comboBoxCarta2.setModel(new DefaultComboBoxModel(new String[] {"NO","AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCarta2.setToolTipText("Carta comunitaria 2 (Flop)");
		comboBoxCarta2.setBounds(945, 142, 50, 20);
		contentPanel.add(comboBoxCarta2);
		
		JComboBox comboBoxCarta3 = new JComboBox();
		comboBoxCarta3.setModel(new DefaultComboBoxModel(new String[] {"NO","AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCarta3.setToolTipText("Carta comunitaria 3 (Flop)");
		comboBoxCarta3.setBounds(945, 173, 50, 20);
		contentPanel.add(comboBoxCarta3);
		
		JComboBox comboBoxCarta4 = new JComboBox();
		comboBoxCarta4.setModel(new DefaultComboBoxModel(new String[] {"NO","AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCarta4.setToolTipText("Carta comunitaria 4 (Turn)");
		comboBoxCarta4.setBounds(945, 204, 50, 20);
		contentPanel.add(comboBoxCarta4);
		
		JComboBox comboBoxCarta5 = new JComboBox();
		comboBoxCarta5.setModel(new DefaultComboBoxModel(new String[] {"NO","AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		comboBoxCarta5.setToolTipText("Carta comunitaria 5 (River)");
		comboBoxCarta5.setBounds(945, 235, 50, 20);
		contentPanel.add(comboBoxCarta5);
		
		JLabel etApuesta = new JLabel("Apuesta");
		etApuesta.setForeground(Color.WHITE);
		etApuesta.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etApuesta.setBounds(769, 349, 60, 24);
		contentPanel.add(etApuesta);
		
		campoApuesta = new JTextField();
		campoApuesta.setToolTipText("Introduzca la cantidad a apostar");
		campoApuesta.setBounds(839, 352, 85, 20);
		contentPanel.add(campoApuesta);
		campoApuesta.setColumns(10);
		
		JLabel etBote = new JLabel("Bote");
		etBote.setForeground(Color.WHITE);
		etBote.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etBote.setBounds(992, 349, 44, 24);
		contentPanel.add(etBote);
		
		campoBote = new JTextField();
		campoBote.setToolTipText("Introduzca el bote de la mesa");
		campoBote.setColumns(10);
		campoBote.setBounds(1049, 352, 85, 20);
		contentPanel.add(campoBote);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(773, 383, 361, 2);
		contentPanel.add(separator);
		
		JLabel etResultado = new JLabel("Resultado");
		etResultado.setForeground(Color.WHITE);
		etResultado.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etResultado.setBounds(902, 396, 117, 24);
		contentPanel.add(etResultado);
		
		JLabel etProbabilidadMano = new JLabel("Probabilidad de mano");
		etProbabilidadMano.setForeground(Color.WHITE);
		etProbabilidadMano.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etProbabilidadMano.setBounds(892, 431, 144, 24);
		contentPanel.add(etProbabilidadMano);
		
		JLabel etProbabilidadCarta = new JLabel("Riesgo de la jugada");
		etProbabilidadCarta.setForeground(Color.WHITE);
		etProbabilidadCarta.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etProbabilidadCarta.setBounds(899, 508, 137, 24);
		contentPanel.add(etProbabilidadCarta);
		
		campoProbMano = new JTextField();
		campoProbMano.setEditable(false);
		campoProbMano.setFont(new Font("Tahoma", Font.BOLD, 11));
		campoProbMano.setBounds(779, 466, 355, 31);
		contentPanel.add(campoProbMano);
		campoProbMano.setColumns(10);
		
		campoRiesgo = new JTextField();
		campoRiesgo.setEditable(false);
		campoRiesgo.setFont(new Font("Tahoma", Font.BOLD, 11));
		campoRiesgo.setColumns(10);
		campoRiesgo.setBounds(779, 543, 355, 31);
		contentPanel.add(campoRiesgo);
		
		JButton botonCalcular = new JButton("");
		botonCalcular.setBorder(null);
		botonCalcular.setContentAreaFilled(false);
		botonCalcular.setFocusable(false);
		botonCalcular.setBackground(Color.WHITE);
		botonCalcular.setIcon(new ImageIcon(Principal.class.getResource("/botones/calculadora.png")));
		botonCalcular.addActionListener(new ActionListener() {
			
			/**
			 * Accion a realiar al pulsar el boton de calcular en la calculadora
			 */
			public void actionPerformed(ActionEvent e) {
				//obtiene el valor del comboBox 1
				String cartacomboBoxP1 = obtenerDatosCartacomboBox(comboBoxCartaPropia1);
					String paloP1 = obtenerPalo(cartacomboBoxP1);
					String valorP1 = obtenerValor(cartacomboBoxP1);
				//obtiene el valor del comboBox 2
				String cartacomboBoxP2 = obtenerDatosCartacomboBox(comboBoxCartaPropia2);
					String paloP2 = obtenerPalo(cartacomboBoxP2);
					String valorP2 = obtenerValor(cartacomboBoxP2);
				//obtiene el valor del comboBox carta comunitaria 1
				String cartacomboBox1 = obtenerDatosCartacomboBox(comboBoxCarta1);
					String palo1 = obtenerPalo(cartacomboBox1);
					String valor1 = obtenerValor(cartacomboBox1);
				//obtiene el valor del comboBox carta comunitaria 2
				String cartacomboBox2 = obtenerDatosCartacomboBox(comboBoxCarta2);
					String palo2 = obtenerPalo(cartacomboBox2);
					String valor2 = obtenerValor(cartacomboBox2);
				//obtiene el valor del comboBox carta comunitaria 3
				String cartacomboBox3 = obtenerDatosCartacomboBox(comboBoxCarta3);
					String palo3 = obtenerPalo(cartacomboBox3);
					String valor3 = obtenerValor(cartacomboBox3);
				//obtiene el valor del comboBox carta comunitaria 4
				String cartacomboBox4 = obtenerDatosCartacomboBox(comboBoxCarta4);
					String palo4 = obtenerPalo(cartacomboBox4);
					String valor4 = obtenerValor(cartacomboBox4);
				//obtiene el valor del comboBox carta comunitaria 5
				String cartacomboBox5 = obtenerDatosCartacomboBox(comboBoxCarta5);
					String palo5 = obtenerPalo(cartacomboBox5);
					String valor5 = obtenerValor(cartacomboBox5);
				//creacion de cartas
				Carta cp1 = new Carta(paloP1,valorP1);
				Carta cp2 = new Carta(paloP2,valorP2);
				Carta c1 = new Carta(palo1,valor1);
				Carta c2 = new Carta(palo2,valor2);
				Carta c3 = new Carta(palo3,valor3);
				Carta c4 = new Carta(palo4,valor4);
				Carta c5 = new Carta(palo5,valor5);
				//realiza calculo
				Calculo calculo = new Calculo(cp1,cp2,c1,c2,c3,c4,c5,null,0,0);
				campoProbMano.setText(calculo.CalculoProbabilidadMano());
				//calculo de riesgo en caso de introducir todos los datos
				String posicion = (String) listaPosicion.getSelectedValue();
				double bote = 0;
				double apuesta = 0;
				try {
					if(campoBote.getText().equals("")==false) {
					bote = Double.valueOf(campoBote.getText());
					}
					if(campoApuesta.getText().equals("")==false) {
					apuesta = Double.valueOf(campoApuesta.getText());
					}
				}
				catch(Exception excepcionFormato) {
					campoRiesgo.setText("El formato de número no es correcto, no usar coma (x.xx)");
				}
				if(posicion!=null && bote != 0 && apuesta != 0 ) {
					calculo.setPosicion(posicion);
					calculo.setApuesta(apuesta);
					calculo.setBote(bote);
					campoRiesgo.setText(calculo.calcularRiesgo());
				}
				
			}
		});
		botonCalcular.setBounds(892, 603, 134, 132);
		contentPanel.add(botonCalcular);
		
		JSeparator separadorGraficas = new JSeparator();
		separadorGraficas.setBounds(10, 543, 731, 2);
		contentPanel.add(separadorGraficas);
		
		JLabel etMostrarDatos = new JLabel("Mostrar datos sesion");
		etMostrarDatos.setForeground(Color.WHITE);
		etMostrarDatos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etMostrarDatos.setBounds(54, 558, 214, 31);
		contentPanel.add(etMostrarDatos);
		
		JLabel etListadoDatos = new JLabel("Listado sesiones");
		etListadoDatos.setForeground(Color.WHITE);
		etListadoDatos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etListadoDatos.setBounds(110, 599, 102, 31);
		contentPanel.add(etListadoDatos);		
		JComboBox comboBoxListadoSesion = new JComboBox();
		insertaComboBox(comboBoxListadoSesion);

		comboBoxListadoSesion.setBounds(54, 640, 214, 31);
		contentPanel.add(comboBoxListadoSesion);
		/**
		 * Muestra la ventana de la tabla de datos una vez seleccionada una sesion y presionado el boton
		 */
		JButton botonMostrarDatos = new JButton("");
		botonMostrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo de la sesion
				int codigoSesion;
				//texto
				String textoCombo = comboBoxListadoSesion.getSelectedItem().toString();
				//donde se cortara el texto para obtener la sesion
				int lugarCortar = textoCombo.indexOf("-");
				codigoSesion = Integer.parseInt(textoCombo.substring(0,lugarCortar));
				//abre la ventana con los datos de la sesion seleccionada
				VisualizadorDatos vd = new VisualizadorDatos(codigoSesion);
				vd.setMinimumSize(new Dimension(980,600));
				vd.setLocationRelativeTo(null);
				vd.pack();
				vd.setVisible(true);
			}
		});
		botonMostrarDatos.setBorder(null);
		botonMostrarDatos.setContentAreaFilled(false);
		botonMostrarDatos.setSelectedIcon(new ImageIcon(Principal.class.getResource("/botones/cuadricula.png")));
		botonMostrarDatos.setIcon(new ImageIcon(Principal.class.getResource("/botones/cuadricula.png")));
		botonMostrarDatos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		botonMostrarDatos.setForeground(Color.BLACK);
		botonMostrarDatos.setBounds(131, 681, 60, 41);
		contentPanel.add(botonMostrarDatos);
		
		JSeparator separadorGrafica = new JSeparator();
		separadorGrafica.setOrientation(SwingConstants.VERTICAL);
		separadorGrafica.setBounds(332, 555, 2, 186);
		contentPanel.add(separadorGrafica);
		
		JLabel etMostrarGrafica = new JLabel("Mostrar Graficas");
		etMostrarGrafica.setForeground(Color.WHITE);
		etMostrarGrafica.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etMostrarGrafica.setBounds(450, 552, 174, 31);
		contentPanel.add(etMostrarGrafica);
		JButton btnNewButton = new JButton("");
		/**
		 * Muestra la ventana de graficas al realizar la accion del boton mostrarGrafica
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizadorGraficas vg = new VisualizadorGraficas();
					vg.setMinimumSize(new Dimension(930,500));
					vg.setLocationRelativeTo(null);
					vg.pack();
					vg.setVisible(true);
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/botones/grafica.png")));
		btnNewButton.setBounds(453, 585, 169, 150);
		contentPanel.add(btnNewButton);
		
		JLabel etUsuario = new JLabel("Usuario");
		etUsuario.setForeground(Color.WHITE);
		etUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etUsuario.setBounds(87, 82, 68, 24);
		contentPanel.add(etUsuario);
		
		JLabel etStack = new JLabel("Stack");
		etStack.setForeground(Color.WHITE);
		etStack.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etStack.setBounds(349, 81, 50, 24);
		contentPanel.add(etStack);
		
		JLabel etTotalManos = new JLabel("Total de manos");
		etTotalManos.setForeground(Color.WHITE);
		etTotalManos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etTotalManos.setBounds(567, 82, 123, 24);
		contentPanel.add(etTotalManos);
		
		JLabel etFlop = new JLabel("Flop vistos");
		etFlop.setForeground(Color.WHITE);
		etFlop.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etFlop.setBounds(69, 231, 101, 24);
		contentPanel.add(etFlop);
		
		JLabel etTurn = new JLabel("Turn vistos");
		etTurn.setForeground(Color.WHITE);
		etTurn.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etTurn.setBounds(233, 231, 101, 24);
		contentPanel.add(etTurn);
		
		JLabel etRiver = new JLabel("River visto");
		etRiver.setForeground(Color.WHITE);
		etRiver.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etRiver.setBounds(417, 231, 101, 24);
		contentPanel.add(etRiver);
		
		JLabel etGanadas = new JLabel("Ganadas");
		etGanadas.setForeground(Color.WHITE);
		etGanadas.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etGanadas.setBounds(588, 231, 76, 24);
		contentPanel.add(etGanadas);
		
		campoUsuario = new JTextField();
		campoUsuario.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		campoUsuario.setText(Inicio.getUsuario());
		campoUsuario.setBounds(45, 116, 144, 38);
		contentPanel.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoCash = new JTextField();
		campoCash.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoCash.setHorizontalAlignment(SwingConstants.CENTER);
		campoCash.setColumns(10);
		campoCash.setBounds(298, 115, 144, 38);
		contentPanel.add(campoCash);
		
		campoManos = new JTextField();
		campoManos.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoManos.setHorizontalAlignment(SwingConstants.CENTER);
		campoManos.setColumns(10);
		campoManos.setBounds(556, 116, 144, 38);
		contentPanel.add(campoManos);
		
		campoFlop = new JTextField();
		campoFlop.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoFlop.setHorizontalAlignment(SwingConstants.CENTER);
		campoFlop.setColumns(10);
		campoFlop.setBounds(45, 277, 144, 38);
		contentPanel.add(campoFlop);
		
		campoTurn = new JTextField();
		campoTurn.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoTurn.setHorizontalAlignment(SwingConstants.CENTER);
		campoTurn.setColumns(10);
		campoTurn.setBounds(216, 279, 144, 38);
		contentPanel.add(campoTurn);
		
		campoRiver = new JTextField();
		campoRiver.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoRiver.setHorizontalAlignment(SwingConstants.CENTER);
		campoRiver.setColumns(10);
		campoRiver.setBounds(386, 279, 144, 38);
		contentPanel.add(campoRiver);
		
		campoGanadas = new JTextField();
		campoGanadas.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		campoGanadas.setColumns(10);
		campoGanadas.setBounds(554, 279, 144, 38);
		contentPanel.add(campoGanadas);
		
		JLabel etApuestaMedia = new JLabel("Apuesta media");
		etApuestaMedia.setForeground(Color.WHITE);
		etApuestaMedia.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etApuestaMedia.setBounds(138, 396, 130, 24);
		contentPanel.add(etApuestaMedia);
		
		JLabel etRetiradas = new JLabel("Retiradas");
		etRetiradas.setForeground(Color.WHITE);
		etRetiradas.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 16));
		etRetiradas.setBounds(472, 396, 85, 24);
		contentPanel.add(etRetiradas);
		
		campoApuestaMedia = new JTextField();
		campoApuestaMedia.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoApuestaMedia.setHorizontalAlignment(SwingConstants.CENTER);
		campoApuestaMedia.setColumns(10);
		campoApuestaMedia.setBounds(124, 435, 144, 38);
		contentPanel.add(campoApuestaMedia);
		
		campoRetiradas = new JTextField();
		campoRetiradas.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoRetiradas.setHorizontalAlignment(SwingConstants.CENTER);
		campoRetiradas.setColumns(10);
		campoRetiradas.setBounds(442, 435, 144, 38);
		contentPanel.add(campoRetiradas);
		
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.setBackground(new Color(255, 255, 255));
		setJMenuBar(barraMenu);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('a');
		menuArchivo.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.setBackground(Color.WHITE);
		menuArchivo.setIcon(new ImageIcon(Principal.class.getResource("/botones/archivo10x10.png")));
		menuArchivo.setSelectedIcon(null);
		barraMenu.add(menuArchivo);
		
		JMenuItem menuItemCerrar = new JMenuItem("Cerrar sesi\u00F3n");
		menuItemCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		//cierra la ventana y vuelve a inicio
		menuItemCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarConexion(Inicio.getConexion());
				Inicio.ocultar(true);
				cerrarAplicacion();
			}
		});
		menuItemCerrar.setBackground(Color.WHITE);
		menuItemCerrar.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.add(menuItemCerrar);
		//cierra la aplicación
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarAplicacion();
			}
		});
		mntmSalir.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.add(mntmSalir);
		
		JMenu menuConfiguracion = new JMenu("Configuraci\u00F3n");
		menuConfiguracion.setIcon(new ImageIcon(Principal.class.getResource("/botones/configuracion10x10.png")));
		menuConfiguracion.setBackground(Color.WHITE);
		menuConfiguracion.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		barraMenu.add(menuConfiguracion);
		//abre una ventana para seleccionar ruta del historial de manos y obtiene la ruta a la carpeta
		JMenuItem menuItemHistorial = new JMenuItem("Ruta historial de manos ");
		menuItemHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ventana del buscador
				JFileChooser buscador = new JFileChooser();
					buscador.showOpenDialog(contentPanel);
					//variable donde se guardara la ruta del archivo
					String direccion;
					//obtencion de la ruta del archivo en caso de existir
						if(buscador.getSelectedFile().exists()) {
							archivo = buscador.getSelectedFile();
							direccion = archivo.getParent();
							//guarda la ruta en el archivo de configuracion
							guardaRutaEnArchivo(direccion);
							if(archivo!=null) {
								if(guardaNombreEnArchivo(new File(buscaHistorialNuevo()))==true) {
									guardaNombreEnArchivo(new File(buscaHistorialNuevo()));
								}
							}
							else {
								System.out.print("No existe archivo");
							}
						}
						else{
							
						};
					
			}
		});
		menuItemHistorial.setBackground(Color.WHITE);
		menuItemHistorial.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuConfiguracion.add(menuItemHistorial);
		//abre la ventana del selector de mesa
		JMenu menuSelectorMesa = new JMenu("Selector de mesa");
		menuSelectorMesa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectorMesa sm = new SelectorMesa();
				sm.setMinimumSize(new Dimension(800,500));
				sm.setLocationRelativeTo(null);
				sm.pack();
				sm.setVisible(true);
			}
		});
		menuSelectorMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuSelectorMesa.setIcon(new ImageIcon(Principal.class.getResource("/botones/seleccionarMesa.png")));
		menuSelectorMesa.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		barraMenu.add(menuSelectorMesa);
		
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
	/**
	 * Metodo que cierra la ventana
	 */
	private void cerrarAplicacion() {
		this.dispose();
		
	}
	/**
	 * Devuelve el texto de la carta en el JComboBox
	 * @param comboBox del que obtener los datos
	 * @return datos del item del combobbox
	 */
	private String obtenerDatosCartacomboBox(JComboBox combobox) {
		String textocomboBox = (String)combobox.getSelectedItem();
			if(textocomboBox.contains("10")) {
				textocomboBox = textocomboBox.substring(1,3);
			}
			return textocomboBox;
	}
	/**
	 * Metodo que obtiene el valor de la carta
	 * @param textoCarta carta de la que obtener el valor
	 * @return valor de la carta
	 */
	private String obtenerValor(String textoCarta) {
		String valor = textoCarta.substring(0, 1);
			return valor;
	}
	/**
	 * Metodo que obtiene el palo de la carta
	 * @param textoCarta carta de la que obtener el palo
	 * @return palo de la carta en cadena de texto
	 */
	private String obtenerPalo(String textoCarta) {
		String palo = textoCarta.substring(1);
			return palo;
	}
	/**
	 * Metodo que guarda la ruta para la posterior obtencion de los historiales
	 * @param path ruta del archivo seleccionado
	 */
	private void guardaRutaEnArchivo(String path){
		File archivoConfiguracion = new File("pokestics/Archivos/conf.txt");
			//lee del fichero la ruta de la carpeta que contiene los historiales
		String linea;
			try {
				archivoConfiguracion.delete();
				archivoConfiguracion.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConfiguracion,true));
					bw.append("Ruta historial -- " + path);
					bw.close();
			}catch (IOException e) {
				System.out.print(e.getMessage());
			}
	}
	
	/**
	 * Metodo que busca el historial mas reciente para utilizarlo en la obtencion de los datos para la ventana principal
	 * @return ruta del ultimo archivo
	 */
	private String buscaHistorialNuevo() {
		File archivoConfiguracion = new File("pokestics/Archivos/conf.txt");
		String linea;
		String rutaCarpeta = null;
		try {
			//obtiene la ruta desde el archivo de configuracion
			BufferedReader br = new BufferedReader(new FileReader(archivoConfiguracion));
					while((linea = br.readLine())!=null) {
						if(linea.contains("historial")) {
							int inicio;
							inicio = linea.indexOf("-");
							rutaCarpeta = linea.substring(inicio+3);
							
						}
					}
					br.close();
		}catch (IOException e) {
			System.out.print(e.getMessage());
		}
		// obtiene todos los archivos de historiales
		File directorio = new File(rutaCarpeta);
			File[] listaArchivos = directorio.listFiles();
			//cuenta los archivos que no son de torneos
			int elementos =0;
			for(int i=0; i<listaArchivos.length;i++) {
				if(listaArchivos[i].getName().contains("EUR"))elementos++;
			}
		//crea un nuevo array donde se guardan los archivo que no son de torneos
			File[] listaArchivosSinTorneos = new File[elementos];
			int pos=0;
			for(int i=0;i<listaArchivos.length;i++) {
				if(listaArchivos[i].getName().contains("EUR")) {
					listaArchivosSinTorneos[pos] = listaArchivos[i];
					pos++;
				}
			}
			   //array donde se guardaran todas las fechas de modificacion de los archivos
			long[] fecha = new long[listaArchivosSinTorneos.length];
			//convierte a fecha
			Date[] fechaDate = new Date[listaArchivosSinTorneos.length];
			
			//obtiene le fecha
			for(int i=0; i<listaArchivosSinTorneos.length;i++) {
				fecha[i] = listaArchivosSinTorneos[i].lastModified();
				fechaDate[i] = new Date(fecha[i]);
			}
			//obtiene el mas nuevo
			Date menor = fechaDate[0];
			for(int i=0;i<fechaDate.length;i++) {
				if(fechaDate[i].after(menor)){
					menor = fechaDate[i];
				}
			}
			//consigue el nombre del archivo que tiene esa fecha 
			String nombreUltimoArchivo ="" ;
			for(int i=0;i<listaArchivosSinTorneos.length;i++) {
				if(listaArchivosSinTorneos[i].lastModified() == menor.getTime()) {
					nombreUltimoArchivo = listaArchivosSinTorneos[i].getName();
				}
			}
			return rutaCarpeta+"/"+nombreUltimoArchivo;
			
	}
	/**
	 * Metodo que guarda el nombre del archivo leido en el archivo de historiales leidos
	 * @param archivo -archivo leido
	 * @return true en caso de escribir o false en caso de existir ya el archivo
	 */
	private static boolean guardaNombreEnArchivo(File archivo) {
		boolean ok = false;
		File historialLeidos = new File("pokestics/Archivos/HistorialesLeidos.txt");
		try {
			//lee el archivo de historiales leidoss
			BufferedWriter bw = new BufferedWriter(new FileWriter(historialLeidos,true));
			BufferedReader br = new BufferedReader(new FileReader(historialLeidos));
			String linea = "";
			boolean escribir = true;
				while((linea = br.readLine())!=null) {
					if(linea.contains(archivo.getName())) escribir = false;
				}
				//en caso de no existir se escribe el nombre del archivo
			if(escribir == true) {
				bw.newLine();
				bw.append(archivo.getName());
				bw.close();
				ok = true;
			}
			bw.close();
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ok;
	}
	/**
	 * Metodo que lee los historiales que no hayan sido leidos
	 */
	private static void leeHistorialesNoLeidos() {
		File rutaArchivoConf;// ruta del archivo de configuracion
		File rutaHistoriales = null;//ruta de los historiales
		BufferedReader br;
		String linea;
		
		rutaArchivoConf = new File("pokestics/Archivos/conf.txt");
		//obtiene la ruta del archivo de configuracion
			try {
				br = new BufferedReader(new FileReader(rutaArchivoConf));
					while((linea = br.readLine())!=null) {
						if(linea.contains("historial"))rutaHistoriales = new File(linea.substring(linea.lastIndexOf("-")+2));
					}
					br.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			//obtiene el nombre de los archivos ya leidos del archivo de historiales leidos
			File[] listaHistoriales = rutaHistoriales.listFiles();
			File historialesLeidos = new File("pokestics/Archivos/HistorialesLeidos.txt");
			ArrayList<String> nombreArchivos = new ArrayList<String>(); 
			try {
				BufferedReader brr = new BufferedReader(new FileReader(historialesLeidos));
				String lineaHistorial;
				
				while((lineaHistorial = brr.readLine())!=null) {
					nombreArchivos.add(lineaHistorial);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());

			} catch (IOException e) {
				System.out.println(e.getMessage());

			}
			//comprueba si ya esta leido y en caso de no estarlo lo lee
			
			for(int i=0;i<listaHistoriales.length;i++) {
				if(nombreArchivos.contains(listaHistoriales[i].getName()) == false && listaHistoriales[i].getName().contains("EUR")) {
					DatosHistorial leer = new DatosHistorial(new File(rutaHistoriales+"/"+listaHistoriales[i].getName()));
					leer.lecturaHistorial();
					guardaNombreEnArchivo(listaHistoriales[i]);
				}
			}
	}
	
	/**
	 * Metodo que cierra la conexion a la base de datos
	 */
	private static void cerrarConexion(Connection con) {
		try {
			con.close();
			System.out.println("Conexion cerrada");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * MEtodo que consulta el nombre completo del usuario en la base de datos
	 * @param usuario- usuario(alias en la sala de poker) del que obtener el nombre
	 * @return el nombre completo en una cadena de texto
	 */
	private String consultaNombre(String usuario) {
		Connection con = Inicio.getConexion();
		Statement st;
		ResultSet rs;
		String nombreCompleto = "";
		
		//realiza la consulta dl nombre completo
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT nombre FROM usuario WHERE usuariosala = '"+usuario+"'");
			while(rs.next()) {
				nombreCompleto = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nombreCompleto;
	}
	
	
	/**
	 * Metodo que inserta en el combobox de datos las sesiones para seleccionar una, insertando la fecha y el codigo de la sesion
	 * @param box El comboBox al que insertar los datos
	 */
	private void insertaComboBox(JComboBox box) {
		//inserta en el comboBox el listado de sesiones
		
				Connection con = Inicio.getConexion();
				
				Statement st;
				ResultSet rs;
				ArrayList<Integer> codigosSesiones = new ArrayList<>();
				ArrayList<Date> fechaSesiones = new ArrayList<>();
				ArrayList<String> muestra = new ArrayList<>();
				
				//en caso de ser el observador se obtienen todos
				if(Inicio.getUsuario() == "observador") {
					//obtiene los codios y fechas
					try {
						st = con.createStatement();
						rs = st.executeQuery("SELECT codigo,fecha FROM sesion");
							while(rs.next()) {
								codigosSesiones.add(rs.getInt(1));
								fechaSesiones.add(rs.getDate(2));
							}
						//inserta en codigo y fecha en un array para mostrar
							for(int i=0;i<codigosSesiones.size();i++) {
								muestra.add(codigosSesiones.get(i).toString() + "--" + fechaSesiones.get(i).toString());
							}
						//inserta en el combobox
							for(int i=0;i<muestra.size();i++) {
								box.addItem(muestra.get(i));
							}
							
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());

					}
				}
				// en caso de ser un usuario normal se realiza por usuario
				else {
					try {
						st = con.createStatement();
						rs = st.executeQuery("SELECT codigo,fecha FROM sesion WHERE usuario = '"+consultaNombre(Inicio.getUsuario())+"'");
							while(rs.next()) {
								codigosSesiones.add(rs.getInt(1));
								fechaSesiones.add(rs.getDate(2));
							}
						//inserta en codigo y fecha en un array para mostrar
							for(int i=0;i<codigosSesiones.size();i++) {
								muestra.add(codigosSesiones.get(i).toString() + "--" + fechaSesiones.get(i).toString());
							}
						//inserta en el combobox
							for(int i=0;i<muestra.size();i++) {
								box.addItem(muestra.get(i));
							}
							
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				}
	}
	
	
}
