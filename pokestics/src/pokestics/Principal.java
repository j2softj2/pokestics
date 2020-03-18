package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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

public class Principal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoApuesta;
	private JTextField campoBote;
	private JTextField campoProbMano;
	private JTextField campoRiesgo;
	private File archivo;

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
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setMinimumSize(new Dimension(500, 500));
		setBounds(100, 100, 1150, 762);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separadorCalculadora = new JSeparator();
		separadorCalculadora.setBounds(751, 5, 2, 696);
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
		botonCalcular.setFocusable(false);
		botonCalcular.setBackground(Color.WHITE);
		botonCalcular.setIcon(new ImageIcon(Principal.class.getResource("/botones/calculadora.png")));
		botonCalcular.addActionListener(new ActionListener() {
			
			//boton calcular inicia el calculo de probabilidades
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
		botonCalcular.setBounds(882, 585, 144, 65);
		contentPanel.add(botonCalcular);
		
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
		//abre una ventana para seleccionar ruta del historial de manos y obtiene la ruta a la carpeta
		JMenuItem menuItemHistorial = new JMenuItem("Ruta historial de manos ");
		menuItemHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser buscador = new JFileChooser();
					buscador.showOpenDialog(contentPanel);
					String direccion;
						if(buscador.getSelectedFile().exists()) {
							archivo = buscador.getSelectedFile();
							direccion = archivo.getPath();
							guardaRutaEnArchivo(direccion);
						};
					
			}
		});
		menuItemHistorial.setBackground(Color.WHITE);
		menuItemHistorial.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuConfiguracion.add(menuItemHistorial);
		//realizar lectura del historial
		JMenuItem menuItemIniciarEscaneo = new JMenuItem("Leer historial");
		menuItemIniciarEscaneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(archivo!=null) {
					DatosHistorial leer = new DatosHistorial(archivo);
						leer.lecturaHistorial();
				}
				else {
					System.out.print("no existe archivo");
				}
			}
		});
		menuItemIniciarEscaneo.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuConfiguracion.add(menuItemIniciarEscaneo);
		
		JMenu menuSelectorMesa = new JMenu("Selector de mesa");
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
	
	private void cerrarAplicacion() {
		this.dispose();
	}
	/**
	 * Devuelve el texto de la carta en el JComboBox
	 * @param comboBox
	 * @return
	 */
	private String obtenerDatosCartacomboBox(JComboBox combobox) {
		String textocomboBox = (String)combobox.getSelectedItem();
			if(textocomboBox.contains("10")) {
				textocomboBox = textocomboBox.substring(1,3);
			}
			return textocomboBox;
	}
	
	private String obtenerValor(String textoCarta) {
		String valor = textoCarta.substring(0, 1);
			return valor;
	}
	
	private String obtenerPalo(String textoCarta) {
		String palo = textoCarta.substring(1);
			return palo;
	}
	
	private void guardaRutaEnArchivo(String path){
		File archivoConfiguracion = new File("Archivos/conf.txt");
			//lee del fichero la ruta de la carpeta que contiene los historiales
		String linea;
			try {
				BufferedReader br = new BufferedReader(new FileReader(archivoConfiguracion));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConfiguracion,true));
						while((linea = br.readLine())!=null) {
							if(linea.contains("historial")) {
								bw.append(path);
								bw.close();
								br.close();
							}
						}
			}catch (IOException e) {
				System.out.print(e.getMessage());
			}
	}
}
