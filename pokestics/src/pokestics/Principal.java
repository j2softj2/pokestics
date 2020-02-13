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
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class Principal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoApuesta;
	private JTextField campoBote;
	private JTextField campoProbMano;
	private JTextField campoProbCarta;

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
		setBounds(100, 100, 1069, 685);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separadorCalculadora = new JSeparator();
		separadorCalculadora.setBounds(751, 5, 2, 619);
		separadorCalculadora.setBackground(new Color(255, 255, 255));
		separadorCalculadora.setOrientation(SwingConstants.VERTICAL);
		contentPanel.add(separadorCalculadora);
		
		JLabel etCalculadora = new JLabel("Calculadora");
		etCalculadora.setForeground(new Color(255, 255, 255));
		etCalculadora.setBounds(855, 5, 134, 24);
		etCalculadora.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		contentPanel.add(etCalculadora);
		
		JLabel etCartasPropias = new JLabel("Cartas propias");
		etCartasPropias.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etCartasPropias.setForeground(new Color(255, 255, 255));
		etCartasPropias.setBounds(763, 47, 101, 24);
		contentPanel.add(etCartasPropias);
		
		JSpinner spinnerCartaPropia1 = new JSpinner();
		spinnerCartaPropia1.setToolTipText("Introduzca la primera carta de su mano : C-Corazones P-Picas T-Treboles D-Diamantes");
		spinnerCartaPropia1.setModel(new SpinnerListModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCartaPropia1.setBounds(892, 50, 44, 20);
		contentPanel.add(spinnerCartaPropia1);
		
		JSpinner spinnerCartaPropia2 = new JSpinner();
		spinnerCartaPropia2.setToolTipText("Introduzca la segunda carta de su mano : C-Corazones P-Picas T-Treboles D-Diamantes");
		spinnerCartaPropia2.setModel(new SpinnerListModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCartaPropia2.setBounds(956, 50, 44, 20);
		contentPanel.add(spinnerCartaPropia2);
		
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
		
		JSpinner spinnerCarta1 = new JSpinner();
		spinnerCarta1.setModel(new SpinnerListModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCarta1.setToolTipText("Carta comunitaria 1 (Flop)");
		spinnerCarta1.setBounds(945, 111, 44, 20);
		contentPanel.add(spinnerCarta1);
		
		JSpinner spinnerCarta2 = new JSpinner();
		spinnerCarta2.setModel(new SpinnerListModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCarta2.setToolTipText("Carta comunitaria 2 (Flop)");
		spinnerCarta2.setBounds(945, 142, 44, 20);
		contentPanel.add(spinnerCarta2);
		
		JSpinner spinnerCarta3 = new JSpinner();
		spinnerCarta3.setModel(new SpinnerListModel(new String[] {"AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCarta3.setToolTipText("Carta comunitaria 3 (Flop)");
		spinnerCarta3.setBounds(945, 173, 44, 20);
		contentPanel.add(spinnerCarta3);
		
		JSpinner spinnerCarta4 = new JSpinner();
		spinnerCarta4.setModel(new SpinnerListModel(new String[] {"NO", "AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCarta4.setToolTipText("Carta comunitaria 4 (Turn)");
		spinnerCarta4.setBounds(945, 204, 44, 20);
		contentPanel.add(spinnerCarta4);
		
		JSpinner spinnerCarta5 = new JSpinner();
		spinnerCarta5.setModel(new SpinnerListModel(new String[] {"NO", "AC", "KC", "QC", "JC", "10C", "9C", "8C", "7C", "6C", "5C", "4C", "3C", "2C", "AT", "KT", "QT", "JT", "10T", "9T", "8T", "7T", "6T", "5T", "4T", "3T", "2T", "AP", "KP", "QP", "JP", "10P", "9P", "8P", "7P", "6P", "5P", "4P", "3P", "2P", "AD", "KD", "QD", "JD", "10D", "9D", "8D", "7D", "6D", "5D", "4D", "3D", "2D"}));
		spinnerCarta5.setToolTipText("Carta comunitaria 5 (River)");
		spinnerCarta5.setBounds(945, 235, 44, 20);
		contentPanel.add(spinnerCarta5);
		
		JLabel etApuesta = new JLabel("Apuesta");
		etApuesta.setForeground(Color.WHITE);
		etApuesta.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etApuesta.setBounds(769, 349, 60, 24);
		contentPanel.add(etApuesta);
		
		campoApuesta = new JTextField();
		campoApuesta.setToolTipText("Introduzca la cantidad a apostar");
		campoApuesta.setBounds(839, 352, 60, 20);
		contentPanel.add(campoApuesta);
		campoApuesta.setColumns(10);
		
		JLabel etBote = new JLabel("Bote");
		etBote.setForeground(Color.WHITE);
		etBote.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etBote.setBounds(926, 349, 44, 24);
		contentPanel.add(etBote);
		
		campoBote = new JTextField();
		campoBote.setToolTipText("Introduzca el bote de la mesa");
		campoBote.setColumns(10);
		campoBote.setBounds(976, 352, 60, 20);
		contentPanel.add(campoBote);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(773, 383, 263, 2);
		contentPanel.add(separator);
		
		JLabel etResultado = new JLabel("Resultado");
		etResultado.setForeground(Color.WHITE);
		etResultado.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 20));
		etResultado.setBounds(855, 396, 117, 24);
		contentPanel.add(etResultado);
		
		JLabel etProbabilidadMano = new JLabel("Probabilidad de mano");
		etProbabilidadMano.setForeground(Color.WHITE);
		etProbabilidadMano.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etProbabilidadMano.setBounds(833, 431, 144, 24);
		contentPanel.add(etProbabilidadMano);
		
		JLabel etProbabilidadCarta = new JLabel("Probabilidad de carta necesaria");
		etProbabilidadCarta.setForeground(Color.WHITE);
		etProbabilidadCarta.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		etProbabilidadCarta.setBounds(822, 508, 197, 24);
		contentPanel.add(etProbabilidadCarta);
		
		campoProbMano = new JTextField();
		campoProbMano.setBounds(779, 466, 257, 31);
		contentPanel.add(campoProbMano);
		campoProbMano.setColumns(10);
		
		campoProbCarta = new JTextField();
		campoProbCarta.setColumns(10);
		campoProbCarta.setBounds(779, 543, 257, 31);
		contentPanel.add(campoProbCarta);
		
		JButton botonCalcular = new JButton("CALCULAR");
		botonCalcular.setBounds(855, 585, 106, 23);
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
