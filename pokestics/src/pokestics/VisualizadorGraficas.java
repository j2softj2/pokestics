package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VisualizadorGraficas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField comboBoxJugadores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizadorGraficas dialog = new VisualizadorGraficas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizadorGraficas() {
		setBounds(100, 100, 897, 604);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 863, 352);
		contentPanel.add(scrollPane);
		
		JLabel etJuego = new JLabel("Estadisticas juego");
		etJuego.setForeground(Color.WHITE);
		etJuego.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etJuego.setBounds(61, 372, 131, 24);
		contentPanel.add(etJuego);
		
		JLabel etCash = new JLabel("Estadisticas cash");
		etCash.setForeground(Color.WHITE);
		etCash.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etCash.setBounds(265, 372, 131, 24);
		contentPanel.add(etCash);
		
		JLabel etJugadores = new JLabel("Estadisticas jugadores");
		etJugadores.setForeground(Color.WHITE);
		etJugadores.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etJugadores.setBounds(461, 372, 163, 24);
		contentPanel.add(etJugadores);
		
		JLabel etBankroll = new JLabel("Estadisticas bankroll");
		etBankroll.setForeground(Color.WHITE);
		etBankroll.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etBankroll.setBounds(679, 372, 153, 24);
		contentPanel.add(etBankroll);
		
		JComboBox comboBoxJuego = new JComboBox();
		comboBoxJuego.setBounds(37, 406, 169, 34);
		contentPanel.add(comboBoxJuego);
		
		JComboBox comboBoxCash = new JComboBox();
		comboBoxCash.setBounds(243, 406, 169, 34);
		contentPanel.add(comboBoxCash);
		
		comboBoxJugadores = new JTextField();
		comboBoxJugadores.setBounds(449, 406, 188, 34);
		contentPanel.add(comboBoxJugadores);
		comboBoxJugadores.setColumns(10);
		
		JComboBox comboBoxBankroll = new JComboBox();
		comboBoxBankroll.setBounds(674, 406, 169, 34);
		contentPanel.add(comboBoxBankroll);
		
		JButton botonJuego = new JButton("New button");
		botonJuego.setBounds(77, 481, 85, 21);
		contentPanel.add(botonJuego);
		
		JButton botonCash = new JButton("New button");
		botonCash.setBounds(280, 481, 85, 21);
		contentPanel.add(botonCash);
		
		JButton botonJugadores = new JButton("New button");
		botonJugadores.setBounds(495, 481, 85, 21);
		contentPanel.add(botonJugadores);
		
		JButton botonBankroll = new JButton("New button");
		botonBankroll.setBounds(709, 481, 85, 21);
		contentPanel.add(botonBankroll);
	}
}
