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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * Ventana que da la opcion de mostrar diferentes graficos con los datos de la base de datos
 * @author Rafael Jimenez Villarreal
 *
 */
public class VisualizadorGraficas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoJugadores;
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
		setBounds(100, 100, 841, 461);
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
				
		campoJugadores = new JTextField();
		campoJugadores.setBounds(435, 74, 188, 34);
		contentPanel.add(campoJugadores);
		campoJugadores.setColumns(10);
		
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
		//boton para mostrar el grafico de la tabla estadisticascash
		JButton botonCash = new JButton("");
		botonCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtencion sesion
				int codigoSesion;
				String textoCombo = comboBoxCash.getSelectedItem().toString();
				int lugarCortar = textoCombo.indexOf("-");
				codigoSesion = Integer.parseInt(textoCombo.substring(0,lugarCortar));
				//realiza consulta
				insertarGraficaCash(codigoSesion);
			}
		});
		botonCash.setBorder(null);
		botonCash.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonCash.setContentAreaFilled(false);
		botonCash.setBounds(256, 118, 121, 85);
		contentPanel.add(botonCash);
		
		//boton para mostrar el grafico de la tabla jugadores
		JButton botonJugadores = new JButton("");
		botonJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(campoJugadores.getText()!=null && campoJugadores.getText().length()>3) {
					insertarGraficaJugadores(campoJugadores.getText());
				}
			}
		});
		botonJugadores.setBorder(null);
		botonJugadores.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonJugadores.setContentAreaFilled(false);
		botonJugadores.setBounds(464, 118, 131, 85);
		contentPanel.add(botonJugadores);
		
		//boton para mostrar el grafico de la tabla bankroll
		JButton botonBankroll = new JButton("");
		botonBankroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtencion sesion
				int codigoSesion;
				String textoCombo = comboBoxBankroll.getSelectedItem().toString();
				int lugarCortar = textoCombo.indexOf("-");
				codigoSesion = Integer.parseInt(textoCombo.substring(0,lugarCortar));
				//realiza consulta
				insertarGraficaBankroll(codigoSesion);
			}
		});
		botonBankroll.setBorder(null);
		botonBankroll.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonBankroll.setContentAreaFilled(false);
		botonBankroll.setBounds(672, 118, 121, 85);
		contentPanel.add(botonBankroll);
		
		JLabel etJuegoCompleto = new JLabel("Estadisticas juego del total de sesiones");
		etJuegoCompleto.setForeground(Color.WHITE);
		etJuegoCompleto.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etJuegoCompleto.setBounds(7, 270, 283, 24);
		contentPanel.add(etJuegoCompleto);
		
		JLabel etEstadisticasCashCompleta = new JLabel("Estadisticas cash del total de sesiones");
		etEstadisticasCashCompleta.setForeground(Color.WHITE);
		etEstadisticasCashCompleta.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etEstadisticasCashCompleta.setBounds(526, 270, 283, 24);
		contentPanel.add(etEstadisticasCashCompleta);
		//muestra grafica con los datos de todas las sesiones
		JButton botonJuegoTotal = new JButton("");
		botonJuegoTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadisticasTotales("juego");
			}
		});
		botonJuegoTotal.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonJuegoTotal.setContentAreaFilled(false);
		botonJuegoTotal.setBorder(null);
		botonJuegoTotal.setBounds(88, 304, 121, 82);
		contentPanel.add(botonJuegoTotal);
		
		JButton botonCashTotal = new JButton("");
		botonCashTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadisticasTotales("cash");
			}
		});
		botonCashTotal.setIcon(new ImageIcon(VisualizadorGraficas.class.getResource("/botones/mostrar.png")));
		botonCashTotal.setContentAreaFilled(false);
		botonCashTotal.setBorder(null);
		botonCashTotal.setBounds(607, 304, 121, 82);
		contentPanel.add(botonCashTotal);
	}
	
	/**
	 * Metodo que inserta en el ComboBox la sesiones disponibles para realizar los graficos
	 * @param box JComboBox en el que insertar los datos
	 */
	private void obtenerSesionesJuego(JComboBox box) {
		Statement st;
		ResultSet rs;
		
		try {
			ArrayList<Integer> codigosSesiones = new ArrayList<>();
			ArrayList<Date> fechaSesiones = new ArrayList<>();
			ArrayList<String> muestra = new ArrayList<>();
			
			//en caso de observador
			if(this.usuario == "observador") {
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
			//en caso de usuario normal
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
	
	
	/**
	 * MEtodo que consulta el nombre completo del usuario
	 * @return Nombre completo del usuario
	 */
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
				System.out.println(e.getMessage());

			}
			
			return nombreCompleto;
		}
		
		/**
		 * Metodo que genera la grafica de la tabla etadisticasjuego a partir de la sesion pasada
		 * @param sesion Sesion a partir de la que se genera el grafico
		 */
		private void insertarGraficaJuego(int sesion) {
			
			Statement st;
			ResultSet rs;
			JFreeChart grafica;
			DefaultCategoryDataset datos = new DefaultCategoryDataset();
			int totalManos = 0;
			//consulta de los datos
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT flopvisto,rivervisto,turnvisto,numeroganadas,numeroperdidas,numeroretiradas FROM estadisticasjuego WHERE sesion = "+sesion);
				//se añaden los datos obtenidos al arrayList
				while(rs.next()) {
					datos.addValue(rs.getInt(1), "Flop", "Flop visto");
					datos.addValue(rs.getInt(2), "River", "River visto");
					datos.addValue(rs.getInt(3), "Turn", "Turn visto");
					datos.addValue(rs.getInt(4), "Ganadas", "Número ganadas");
					datos.addValue(rs.getInt(5), "Perdidas", "Número perdidas");
					datos.addValue(rs.getInt(6), "Retiradas", "Número retiradas");
				}
				//obtiene el total de manos
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
				System.out.println(e.getMessage());

			}
			
		}
		
		
		
		/**
		 * Metodo que genera la grafica de la tabla estadisticascash a partir de la sesion pasada
		 * @param sesion Sesion a partir de la que se genera la grafica
		 */
			private void insertarGraficaCash(int sesion) {
				
				Statement st;
				ResultSet rs;
				JFreeChart grafica;
				DefaultCategoryDataset datos = new DefaultCategoryDataset();;
				//realiza la consulta
				try {
					st = con.createStatement();
					rs = st.executeQuery("SELECT ganancias100manos,apuestamedia FROM estadisticascash WHERE sesion = "+sesion);
					
					while(rs.next()) {
						datos.addValue(rs.getFloat(1), "Ganancias x 100", "Ganancias por 100 manos");
						datos.addValue(rs.getFloat(2), "Apuesta media", "Apuesta media");
					}
					//crea la grafica
					grafica = ChartFactory.createBarChart("Estadisticas Cash", "Datos", "Total", datos,PlotOrientation.VERTICAL,true,true,false);

					//la añade al panel y repaint
					ChartPanel panelGrafica = new ChartPanel(grafica);
						JFrame graficos = new JFrame("Grafico");
							graficos.getContentPane().add(panelGrafica);
							graficos.pack();
							graficos.setVisible(true);
							graficos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							
				} catch (SQLException e) {
					System.out.println(e.getMessage());

				}
				
			}
			
			
			/**
			 * Realiza la grafica de la tabla bankroll a partir de la sesion pasada
			 * @param sesion Sesion a partir de la que se genera la grafica
			 */
			private void insertarGraficaBankroll(int sesion) {
				
				Statement st;
				ResultSet rs;
				JFreeChart grafica;
				DefaultCategoryDataset datos = new DefaultCategoryDataset();
				
				try {
					//realiza la consulta de datos
					st = con.createStatement();
					rs = st.executeQuery("SELECT cash,manosjugadas,diferencia FROM bankroll WHERE sesion = "+sesion);
					
					while(rs.next()) {
						datos.addValue(rs.getFloat(1), "Cash", "Cash");
						datos.addValue(rs.getFloat(2), "Manos jugadas", "Manos jugadas");
						datos.addValue(rs.getFloat(3), "Diferencia", "Diferencia");
					}
					//crea la grafica
					grafica = ChartFactory.createBarChart("Estadisticas de Bankroll", "Datos", "Total", datos,PlotOrientation.VERTICAL,true,true,false);

					//la añade al panel y repaint
					ChartPanel panelGrafica = new ChartPanel(grafica);
						JFrame graficos = new JFrame("Grafico");
							graficos.getContentPane().add(panelGrafica);
							graficos.pack();
							graficos.setVisible(true);
							graficos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							
				} catch (SQLException e) {
					System.out.println(e.getMessage());

				}
				
			}
			
			
			
			/**
			 * Realiza la grafica de la tabla jugadores
			 * @param jugador Jugador del que se obtiene los datos
			 */
			private void insertarGraficaJugadores(String jugador) {
				
				Statement st;
				ResultSet rs;
				JFreeChart grafica;
				DefaultCategoryDataset datos = new DefaultCategoryDataset();
				
				try {
					//realiza la consulta
					st = con.createStatement();
					rs = st.executeQuery("SELECT manosanalizadas,flopvisto,riverjugado,ganadas,perdidas FROM jugadores WHERE nombre = ' "+jugador+"'");
					//añade datos
						while(rs.next()) {
							datos.addValue(rs.getInt(1), "Total manos", "Manos analizadas");
							datos.addValue(rs.getInt(2), "Flop visto", "Flop visto");
							datos.addValue(rs.getInt(3), "River jugado", "River jugado");
							datos.addValue(rs.getInt(4), "Ganadas", "Ganadas");
							datos.addValue(rs.getInt(5), "Perdidas", "Perdidas");
							
						}
						//crea la grafica
						grafica = ChartFactory.createBarChart("Estadisticas de Jugadores", "Datos", "Total", datos,PlotOrientation.VERTICAL,true,true,false);

						//la añade al panel
						ChartPanel panelGrafica = new ChartPanel(grafica);
							JFrame graficos = new JFrame("Grafico");
								graficos.getContentPane().add(panelGrafica);
								graficos.pack();
								graficos.setVisible(true);
								graficos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
		/**
		 * Realiza la grafica de la tabla estadisticascash o estadisticasjuego del total de sesiones
		 * @param tabla Tabla sobre la que realizar la grafica
		 */
			private void estadisticasTotales(String tabla) {
				
				Statement st;
				ResultSet rs;
				ArrayList<Integer> codigosSesion = new ArrayList<Integer>();
				int sesion = 0;
				int numeroRetiradas = 0;
				int numeroPerdidas = 0;
				int numeroGanadas = 0;
				int turnVisto = 0;
				int riverVisto = 0;
				int flopVisto = 0;
				float ganancias100manos = 0;
				float apuestaMedia = 0;
				int totalManos = 0;
				//consultas
				String consultaSesiones = "SELECT codigo FROM sesion WHERE usuario = '"+consultaNombre()+"'";
				String consultaJuego = "SELECT SUM(numeroretiradas),SUM(numeroperdidas),SUM(numeroganadas),SUM(turnvisto),SUM(rivervisto),SUM(flopvisto) FROM estadisticasjuego WHERE sesion = ";
				String consultaCash = "SELECT SUM(ganancias100manos),SUM(apuestamedia) FROM estadisticascash WHERE sesion = ";
				String consultaTotalManos = "SELECT COUNT(numero) FROM manos WHERE sesion = ";
				JFreeChart grafica;
				DefaultCategoryDataset datos = new DefaultCategoryDataset();
				
				try {
					//consulta las sesiones pertenecientes al usuario
					st = con.createStatement();
					rs = st.executeQuery(consultaSesiones);
					while(rs.next()) {
						codigosSesion.add(rs.getInt(1));
					}
					//consulta el total de manos jugadas por el usuario
					for(int i=0;i<codigosSesion.size();i++) {
						rs = st.executeQuery(consultaTotalManos+codigosSesion.get(i));
						while(rs.next()) {
							totalManos += rs.getInt(1);
						}
					}
					
					//en caso de ser la tabla juego realiza la consulta por cada sesion sumando el resultado en las variables
					if(tabla == "juego") {
						for(int i=0;i<codigosSesion.size();i++) {
							rs = st.executeQuery(consultaJuego+codigosSesion.get(i));
							while(rs.next()) {
								numeroRetiradas += rs.getInt(1);
								numeroPerdidas += rs.getInt(2);
								numeroGanadas += rs.getInt(3);
								turnVisto += rs.getInt(4);
								riverVisto += rs.getInt(5);
								flopVisto += rs.getInt(6);
							}
						}
						
						//añade datos
						datos.addValue(totalManos, "Número manos", "Total");
						datos.addValue(numeroGanadas, "Número ganadas", "Total");
						datos.addValue(numeroPerdidas, "Número perdidas", "Total");
						datos.addValue(numeroRetiradas, "Número retiradas", "Total");
						datos.addValue(turnVisto, "Turn visto", "Total");
						datos.addValue(riverVisto, "River visto", "Total");
						datos.addValue(flopVisto, "Flop visto", "Total");
						//crea la grafica
						grafica = ChartFactory.createBarChart("Estadisticas de juego totales", "Datos", "Total", datos,PlotOrientation.VERTICAL,true,true,false);

						//la añade al panel
						ChartPanel panelGrafica = new ChartPanel(grafica);
							JFrame graficos = new JFrame("Grafico");
								graficos.getContentPane().add(panelGrafica);
								graficos.pack();
								graficos.setVisible(true);
								graficos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					else if(tabla == "cash") {
						//añade datos
						 XYSeries series1 = new XYSeries("Ganancias x 100 manos");
						 XYSeries series2 = new XYSeries("Apuesta media");
						for(int i=0;i<codigosSesion.size();i++) {
							rs = st.executeQuery(consultaCash+codigosSesion.get(i));
							while(rs.next()) {
								series1.add(codigosSesion.get(i).floatValue(),rs.getFloat(1));
								series2.add(codigosSesion.get(i).floatValue(),rs.getFloat(2));
							}
						}
					     XYSeriesCollection dataset = new XYSeriesCollection();
					     	dataset.addSeries(series1);
					     	dataset.addSeries(series2);
					     	
					     	JFreeChart chart = ChartFactory.createXYLineChart(
					                "Estadísticas cash totales",
					                "Sesiones", // Etiqueta Coordenada X
					                "Cantidad", // Etiqueta Coordenada Y
					                dataset,
					                PlotOrientation.VERTICAL,
					                true, 
					                false,
					                false
					        );

					        // Mostramos la grafica en pantalla
					        ChartFrame frame = new ChartFrame("Grafica cash total", chart);
					        frame.pack();
					        frame.setVisible(true);
					        
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
				
			}
}
