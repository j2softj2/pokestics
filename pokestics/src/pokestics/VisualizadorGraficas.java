package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class VisualizadorGraficas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField comboBoxJugadores;
	Connection con = Inicio.getConexion();
	String usuario = Inicio.getUsuario();

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
		setBounds(100, 100, 841, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel etJuego = new JLabel("Estadisticas juego");
		etJuego.setForeground(Color.WHITE);
		etJuego.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etJuego.setBounds(49, 40, 131, 24);
		contentPanel.add(etJuego);
		
		JLabel etCash = new JLabel("Estadisticas cash");
		etCash.setForeground(Color.WHITE);
		etCash.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etCash.setBounds(251, 40, 131, 24);
		contentPanel.add(etCash);
		
		JLabel etJugadores = new JLabel("Estadisticas jugadores");
		etJugadores.setForeground(Color.WHITE);
		etJugadores.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etJugadores.setBounds(448, 40, 163, 24);
		contentPanel.add(etJugadores);
		
		JLabel etBankroll = new JLabel("Estadisticas bankroll");
		etBankroll.setForeground(Color.WHITE);
		etBankroll.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etBankroll.setBounds(656, 40, 153, 24);
		contentPanel.add(etBankroll);
		
		JComboBox comboBoxJuego = new JComboBox();
		comboBoxJuego.setBounds(30, 74, 169, 34);
		contentPanel.add(comboBoxJuego);
		//inserta el listado
		obtenerSesionesJuego(comboBoxJuego);
		
		JComboBox comboBoxCash = new JComboBox();
		comboBoxCash.setBounds(232, 74, 169, 34);
		contentPanel.add(comboBoxCash);
		//inserta el listado
				obtenerSesionesJuego(comboBoxCash);
				
		comboBoxJugadores = new JTextField();
		comboBoxJugadores.setBounds(435, 74, 188, 34);
		contentPanel.add(comboBoxJugadores);
		comboBoxJugadores.setColumns(10);
		
		JComboBox comboBoxBankroll = new JComboBox();
		comboBoxBankroll.setBounds(648, 74, 169, 34);
		contentPanel.add(comboBoxBankroll);
		//inserta el listado
				obtenerSesionesJuego(comboBoxBankroll);
		//boton para mostrar el grafico de la tabla estadisticasjuego
		JButton botonJuego = new JButton("");
		botonJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtencion sesion
				int codigoSesion;
				String textoCombo = comboBoxJuego.getSelectedItem().toString();
				int lugarCortar = textoCombo.indexOf("-");
				codigoSesion = Integer.parseInt(textoCombo.substring(0,lugarCortar));
				//realiza consulta
				insertarGraficaJuego(codigoSesion);
				
			}
		});
		botonJuego.setBorder(null);
		botonJuego.setContentAreaFilled(false);
		botonJuego.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonJuego.setBounds(54, 118, 121, 82);
		contentPanel.add(botonJuego);
		
		JButton botonCash = new JButton("");
		botonCash.setBorder(null);
		botonCash.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonCash.setContentAreaFilled(false);
		botonCash.setBounds(256, 118, 121, 85);
		contentPanel.add(botonCash);
		
		JButton botonJugadores = new JButton("");
		botonJugadores.setBorder(null);
		botonJugadores.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonJugadores.setContentAreaFilled(false);
		botonJugadores.setBounds(464, 118, 131, 85);
		contentPanel.add(botonJugadores);
		
		JButton botonBankroll = new JButton("");
		botonBankroll.setBorder(null);
		botonBankroll.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonBankroll.setContentAreaFilled(false);
		botonBankroll.setBounds(672, 118, 121, 85);
		contentPanel.add(botonBankroll);
	}
	
	//inserta las sesiones del usuario ( codigo y fecha)
	private void obtenerSesionesJuego(JComboBox box) {
		Statement st;
		ResultSet rs;
		
		try {
			ArrayList<Integer> codigosSesiones = new ArrayList<>();
			ArrayList<Date> fechaSesiones = new ArrayList<>();
			ArrayList<String> muestra = new ArrayList<>();
			
			
			if(this.usuario == "invitado") {
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
			}
			else {
				
					st = con.createStatement();
					rs = st.executeQuery("SELECT codigo,fecha FROM sesion WHERE usuario = '"+consultaNombre()+"'");
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
			}		
		} catch (SQLException e1) {
				System.out.println(e1.getMessage());
		}

	}
	
	
	//consulta nombre completo usuario
		private String consultaNombre() {
			Connection con = Inicio.getConexion();
			Statement st;
			ResultSet rs;
			String nombreCompleto = "";
			
			
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT nombre FROM usuario WHERE usuariosala = '"+this.usuario+"'");
				while(rs.next()) {
					nombreCompleto = rs.getString(1);
				}
				
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
			return nombreCompleto;
		}
		
	//realiza consulta en la tabla estadisticasJuego
		
		private void insertarGraficaJuego(int sesion) {
			
			Statement st;
			ResultSet rs;
			JFreeChart grafica;
			DefaultCategoryDataset datos = new DefaultCategoryDataset();
			int totalManos = 0;
			
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT flopvisto,rivervisto,turnvisto,numeroganadas,numeroperdidas,numeroretiradas FROM estadisticasjuego WHERE sesion = "+sesion);
				
				while(rs.next()) {
					datos.addValue(rs.getInt(1), "Flop", "Flop visto");
					datos.addValue(rs.getInt(2), "River", "River visto");
					datos.addValue(rs.getInt(3), "Turn", "Turn visto");
					datos.addValue(rs.getInt(4), "Ganadas", "Número ganadas");
					datos.addValue(rs.getInt(5), "Perdidas", "Número perdidas");
					datos.addValue(rs.getInt(6), "Retiradas", "Número retiradas");
				}
				
				rs = st.executeQuery("SELECT COUNT(numero) FROM manos WHERE sesion = "+sesion);
					while(rs.next()) {
						totalManos = rs.getInt(1);
					}
					datos.addValue(totalManos, "Total de manos", "Total de manos");
				
				//crea la grafica
				grafica = ChartFactory.createBarChart("Estadisticas de juego", "Datos", "Total manos", datos,PlotOrientation.VERTICAL,true,true,false);

				//la añade al panel y repaint
				ChartPanel panelGrafica = new ChartPanel(grafica);
					JFrame graficos = new JFrame("Grafico");
						graficos.getContentPane().add(panelGrafica);
						graficos.pack();
						graficos.setVisible(true);
						graficos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
		}
}
