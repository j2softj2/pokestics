package pokestics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase que obtiene los datos del archivo del historial de manos y los inserta en la base de datos y en caso de
 *  ser el ultimo los pasa a la ventana principal
 * @author Rafael Jimenez Villarreal
 *
 */
public class DatosHistorial {
	/**
	 * Atributo archivo que representa el archivo del historial de manos
	 */
	private File archivo;
	/**
	 * atributo que indica el usuario actual
	 */
	private String usuario;

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Constructor con parametros de la clase DatosHistorial
	 * @param archivo archivo a leer del historial
	 */
	public DatosHistorial(File archivo) {
		super();
		this.archivo = archivo;
	}
	/**
	 * Constructor de la clase DatosHIstorial sin parametros
	 */
	public DatosHistorial() {
		
	}
	
	/**
	 * Atributo con la sesion actual(codigo)
	 */
	private int sesionActual;
	
	public int getSesionActual() {
		return sesionActual;
	}

	public void setSesionActual(int sesionActual) {
		this.sesionActual = sesionActual;
	}
	
	/**
	 * conexion a la base de datos
	 */
	
	Connection con = Inicio.getConexion();
	
	
	
	
	/**
	 * Metodo que va obteniendo los datos necesarios para la base de datos e insertandolos leyendo el archivo linea por linea.
	 */
	public void lecturaHistorial() {
		//inserta la sesion
		insertarSesion();
		//obtener sesion
		sesionActual = consultaSesion();
		if(Inicio.getUsuario()!= "postgres" && Inicio.getUsuario() != "superusuario") usuario = Inicio.getUsuario();
		else { usuario = "anonimo";}
		//inserta en la tabla juega		
		insertarJuega(usuario);
		//
		int flopVisto = 0;//numero de flop vistos
		int riverJugado = 0;//numero de river visto
		int turnVisto = 0;//numero de turn visto
		int numeroRetiradas = 0;//numero de veces que se retira
		InputStreamReader fr;
		BufferedReader br;
		String linea;
		int posInicio=0;//posicion de inicio de la que obtener datos
		int posFinal = 0;//posicion final de la que obtener datos en la linea
		String limite="";//limite
		String fecha="";//fecha de la sesion
		String nombre1="";//nombre de los jugadores
		String nombre2="";
		String nombre3="";
		String nombre4="";
		String nombre5="";
		String nombre6="";
		String nombre7="";
		String nombre8="";
		String nombre9="";
		String nombre10="";
		String cartasPropias="";//cartas propias
		String posicion="";//posicion propia
		boolean cp = false;//pone ciega pequeña
		boolean cg = false;//pone ciega grande
		String resultado= "";//resultado de la mano
		int asientoDealer = 0;//el asiento que tiene el dealer
		float boteTotal = 0;//bote total de la mano
		float apuestaTotal = 0;//apuesta total de la mano
		float apuesta = 0;//apuesta
		float ganancia = 0;//ganancia de la mano
		float comision= 0;//comision que se lleva la sala
		float stack = 0;//stack del jugador
		float perdida = 0;//perdida de la mano
		try {
			fr = new InputStreamReader(new FileInputStream(this.archivo),"UTF-8");
			br = new BufferedReader(fr);
			while((linea=br.readLine())!=null) {
				//comienzan las comprobaciones linea a linea
				//primera linea siempre es limite-fecha lo que debemos obtener
				if(linea.contains("Mano")) {
					posInicio = linea.indexOf("(");
					posFinal = linea.lastIndexOf("€")+1;
					limite = linea.substring(posInicio+1, posFinal);
					posInicio = linea.indexOf("-");
					posFinal = posInicio + 12;
					fecha = linea.substring(posInicio+1, posFinal);
					//inserta en bd la mano leida
					if(boteTotal != 0){						
						insertarManos(cartasPropias,posicion,boteTotal,resultado,cg,cp,apuestaTotal,ganancia,perdida,limite,stack);
					}
					
					//reinicia valores
					 cp = false;
					 cg = false;
					 resultado= "Perdida";
					 asientoDealer = 0;
					 boteTotal = 0;
					 apuestaTotal = 0;
					 apuesta = 0;
					 ganancia = 0;
					 comision= 0;
					 stack = 0;
					 nombre1="";
				     nombre2="";
					 nombre3="";
					 nombre4="";
					 nombre5="";
					 nombre6="";
					 nombre7="";
					 nombre8="";
					 nombre9="";
					 nombre10="";
				}
				//comprueba si pones ciega pequeña
				else if(linea.contains(usuario) && linea.contains("ciega pequeña")){
					cp = true;
				}
				//comprueba si pones ciega grande
				else if(linea.contains(usuario) && linea.contains("ciega grande")){
					cg = true;
				}	
				//obtiene el asiento del dealer
				else if(linea.contains("n.º")) {
					posInicio = linea.indexOf("º");
					posFinal = posInicio+3;
					String numeroAsientoDealer = linea.substring(posInicio+2, posFinal);
						asientoDealer = Integer.parseInt(numeroAsientoDealer);
						
				}
				//obtiene el asiento y la posicion en la que estas a partir de este
				else if(linea.contains(usuario) && linea.contains("fichas") && linea.contains("recompra") == false) {
					if(linea.contains("€")) {
						posInicio = linea.indexOf("o")+2;
						posFinal = linea.indexOf("o")+3;
					}
					String asiento = linea.substring(posInicio,posFinal);
					int asientoN = Integer.parseInt(asiento);
						if(asientoN == asientoDealer) {
							posicion = "Dealer";
						}
						else if(asientoN == asientoDealer+1) {
							posicion = "SB";
						}
						else if(asientoN == asientoDealer+2) {
							posicion = "BB";
						}
						else if(asientoN == asientoDealer+3) {
							posicion = "3";
						}
						else if(asientoN == asientoDealer+4) {
							posicion = "4";
						}
						else if(asientoN == asientoDealer+5) {
							posicion = "5";
						}
						else if(asientoN == asientoDealer+6) {
							posicion = "6";
						}
						else if(asientoN == asientoDealer+7) {
							posicion = "7";
						}
						else if(asientoN == asientoDealer+8) {
							posicion = "8";
						}else if(asientoN == asientoDealer+9) {
							posicion = "9";
						}
						else {
							posicion = "X";
						}
						//obtiene el stack
						if(linea.contains("€")) {
							posInicio = linea.indexOf("(");
							posFinal = linea.indexOf("€");
							stack = Float.parseFloat(linea.substring(posInicio+1, posFinal-1));
						}
					
				}//inserta jugadores contrarios en la base de datos 
				else if(linea.contains("Asiento 1")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre1 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre1);
					insertarManoAnalizada(nombre1);
					insertarJuegan(nombre1,sesionActual);
				}
				else if(linea.contains("Asiento 2")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre2 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre2);
					insertarManoAnalizada(nombre2);
					insertarJuegan(nombre2,sesionActual);
				}
				else if(linea.contains("Asiento 3")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre3 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre3);
					insertarManoAnalizada(nombre3);
					insertarJuegan(nombre3,sesionActual);
				}
				else if(linea.contains("Asiento 4")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre4 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre4);
					insertarManoAnalizada(nombre4);
					insertarJuegan(nombre4,sesionActual);
				}
				else if(linea.contains("Asiento 5")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre5 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre5);
					insertarManoAnalizada(nombre5);
					insertarJuegan(nombre5,sesionActual);
				}
				else if(linea.contains("Asiento 6")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre6 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre6);
					insertarManoAnalizada(nombre6);
					insertarJuegan(nombre6,sesionActual);
				}
				else if(linea.contains("Asiento 7")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre7 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre7);
					insertarManoAnalizada(nombre7);
					insertarJuegan(nombre7,sesionActual);
				}
				else if(linea.contains("Asiento 8")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre8 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre8);
					insertarManoAnalizada(nombre8);
					insertarJuegan(nombre8,sesionActual);
				}
				else if(linea.contains("Asiento 9")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre9 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre9);
					insertarManoAnalizada(nombre9);
					insertarJuegan(nombre9,sesionActual);
				}
				else if(linea.contains("Asiento 10")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre10 = linea.substring(posInicio+1, posFinal-1);
					//inserta jugador en la base de datos y añade mano analizada
					insertarJugadores(nombre10);
					insertarManoAnalizada(nombre10);
					insertarJuegan(nombre10,sesionActual);
				}
				//obtiene las cartas propias
				else if(linea.contains("Repartidas")) {
					posInicio = linea.indexOf("[");
					posFinal = linea.lastIndexOf("]");
					cartasPropias = linea.substring(posInicio+1, posFinal);
				}
				//obtiene la apuesta-igualada de apuesta o subida
				else if(linea.contains("iguala") | linea.contains("apuesta") | linea.contains("sube") && linea.contains(usuario) && !linea.contains("igualada")){
					//en caso de ser all-in
					if(linea.contains("€") && !linea.contains("all-in")) {
						if(linea.contains("sube")) {
							posInicio = linea.indexOf("€");
							posFinal = linea.lastIndexOf("€");
							apuesta = Float.parseFloat(linea.substring(posInicio+4, posFinal-1));
						}
						else {
							posInicio = linea.lastIndexOf("a");
							posFinal = linea.indexOf("€");
							apuesta = Float.parseFloat(linea.substring(posInicio+2, posFinal-1));
						}
					}
					//en caso de no ser all-in
					else {
						if(linea.contains("sube")) {
							posInicio = linea.indexOf("€");
							posFinal = linea.lastIndexOf("€");
							apuesta = Float.parseFloat(linea.substring(posInicio+4, posFinal-2));
						}
						else if (linea.contains("iguala")){
							posInicio = linea.lastIndexOf("u");
							posFinal = linea.indexOf("€");
							apuesta = Float.parseFloat(linea.substring(posInicio+6, posFinal-2));
						}
						else {
							posInicio = linea.lastIndexOf("u");
							posFinal = linea.indexOf("€");
							apuesta = Float.parseFloat(linea.substring(posInicio+7, posFinal-2));
						}
					}
					
				}
				//comprueba si se gana la mano
				else if(linea.contains("bote principal") && linea.contains(usuario)) {
					resultado = "Ganada";
				}
				//obtiene el bote total 
				else if(linea.contains("Bote total")) {
					if(linea.contains("€")) {
						posInicio = linea.indexOf("l")+1;
						posFinal = linea.indexOf("€");
						boteTotal = Float.parseFloat(linea.substring(posInicio,posFinal));
						//comision
							if(linea.contains("Comisión 0 €") | resultado != "Ganada") {
								comision += 0;
							}
							else {
								posInicio = linea.lastIndexOf("n");
								posFinal = linea.lastIndexOf("n");
								comision += Float.parseFloat(linea.substring(posInicio+2,posFinal+7));
							}
					}
					else{
						if(linea.contains("secundario")) {
							posInicio = linea.indexOf("l")+1;
							posFinal = linea.indexOf(".")-22;
							boteTotal = Float.parseFloat(linea.substring(posInicio,posFinal));
						}
						else {
							posInicio = linea.indexOf("l")+1;
							posFinal = linea.indexOf("|")-2;
							boteTotal = Float.parseFloat(linea.substring(posInicio,posFinal));
						}
						
						//comision
							if(linea.contains("Comisión 0 €") | resultado != "Ganada") {
								comision += 0;
							}
							else {
								posInicio = linea.lastIndexOf("n");
								comision += Float.parseFloat(linea.substring(posInicio+2));
							}
					}
					
				}
				//comprobacion flop visto por jugadores
				if(linea.contains(nombre1) && linea.contains("Flop") && linea.contains("antes")==false)insertarFlopVisto(nombre1);
				else if(linea.contains(nombre2) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre2);
				else if(linea.contains(nombre3) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre3);
				else if(linea.contains(nombre4) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre4);
				else if(linea.contains(nombre5) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre5);
				else if(linea.contains(nombre6) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre6);
				else if(linea.contains(nombre7) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre7);
				else if(linea.contains(nombre8) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre8);
				else if(linea.contains(nombre9) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre9);
				else if(linea.contains(nombre10) && linea.contains("Flop") && !linea.contains("antes")==false)insertarFlopVisto(nombre10);
				//comprobacion river jugado
				if(linea.contains(nombre1) && linea.contains("descartó")) {
					insertarRiverJugado(nombre1);
					insertarFlopVisto(nombre1);
				}
				else if(linea.contains(nombre2) && linea.contains("descartó")) {
					insertarRiverJugado(nombre2);
					insertarFlopVisto(nombre2);
				}
				else if(linea.contains(nombre3) && linea.contains("descartó")) {
					insertarRiverJugado(nombre3);
					insertarFlopVisto(nombre3);
				}
				else if(linea.contains(nombre4) && linea.contains("descartó")) {
					insertarRiverJugado(nombre4);
					insertarFlopVisto(nombre4);
				}
				else if(linea.contains(nombre5) && linea.contains("descartó")) {
					insertarRiverJugado(nombre5);
					insertarFlopVisto(nombre5);
				}
				else if(linea.contains(nombre6) && linea.contains("descartó")) {
					insertarRiverJugado(nombre6);
					insertarFlopVisto(nombre6);
				}
				else if(linea.contains(nombre7) && linea.contains("descartó")) {
					insertarRiverJugado(nombre7);
					insertarFlopVisto(nombre7);
				}
				else if(linea.contains(nombre8) && linea.contains("descartó")) {
					insertarRiverJugado(nombre8);
					insertarFlopVisto(nombre8);
				}
				else if(linea.contains(nombre9) && linea.contains("descartó")) {
					insertarRiverJugado(nombre9);
					insertarFlopVisto(nombre9);
				}
				else if(linea.contains(nombre10) && linea.contains("descartó")) {
					insertarRiverJugado(nombre10);
					insertarFlopVisto(nombre10);
				}
				//comprobacion si gana o pierde
				if(linea.contains(nombre1) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre1);
				}
					else if(linea.contains(nombre1) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
						insertarPerdidaJugadores(nombre1);
					}
				else if(linea.contains(nombre2) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre2);
				}
					else if(linea.contains(nombre2) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
						insertarPerdidaJugadores(nombre2);
					}
				else if(linea.contains(nombre3) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre3);
					}
					else if(linea.contains(nombre3) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre3);
					}
				else if(linea.contains(nombre4) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre4);
					}
					else if(linea.contains(nombre4) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre4);
					}	
				else if(linea.contains(nombre5) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre5);
					}
					else if(linea.contains(nombre5) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre5);
					}
				else if(linea.contains(nombre6) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre6);
					}
					else if(linea.contains(nombre6) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre6);
					}
				else if(linea.contains(nombre7) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre7);
					}
					else if(linea.contains(nombre7) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
								insertarPerdidaJugadores(nombre7);
					}
				else if(linea.contains(nombre8) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre8);
					}
					else if(linea.contains(nombre8) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre8);
					}
				else if(linea.contains(nombre9) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre9);
					}
					else if(linea.contains(nombre9) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre9);
					}
				else if(linea.contains(nombre10) && linea.contains("ganó")) {
					insertarGanadasJugadores(nombre10);
					}
				else if(linea.contains(nombre10) && linea.contains("descartó") && !linea.contains("no apostó") && !linea.contains("ganó")) {
							insertarPerdidaJugadores(nombre10);
					}
				
				//conteo de flop river y turn jugados + numero retiradas
				if(linea.contains(usuario) && linea.contains("se retiró en el Flop")) flopVisto++;
				if(linea.contains(usuario) && linea.contains("se retiró en el River")) {
					flopVisto++;
					turnVisto++;
				}
				if(linea.contains(usuario) && linea.contains("se retiró en el Turn")) {
					flopVisto++;
					turnVisto++;
					riverJugado++;
				}
				if(linea.contains(usuario) && linea.contains("muestra") && linea.contains("perdió")) {
					flopVisto++;
					turnVisto++;
					riverJugado++;
				}
				if(linea.contains(usuario) && linea.contains("muestra") && linea.contains("ganó")) {
					flopVisto++;
					turnVisto++;
					riverJugado++;
				}
				if(linea.contains(usuario) && !linea.contains("antes del Flop") && linea.contains("retiró"))numeroRetiradas++;
				if(linea.contains(usuario) && linea.contains("antes del Flop") && linea.contains("retiró"))resultado = "No jugada";
				apuestaTotal = apuestaTotal + apuesta;
				apuesta = 0;
				if(resultado.equals("Ganada"))ganancia = boteTotal - apuestaTotal - comision;
				else {ganancia = 0; perdida = apuestaTotal;}
			}
			//inserta datos en la base de datos
			insertarSesionCash(sesionActual);
			insertarSesionJuego(sesionActual,flopVisto,riverJugado,turnVisto,numeroRetiradas);
			insertarObtiene(sesionActual);
			insertarBank();
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//metodos para insertar en la base de datos
	
	/**
	 * Metodo que inserta la sesion en la base de datos
	 */
	private void insertarSesion() {
		try {
			Date fechaDate;
			String fecha;
			//fecha actual
			Calendar fechaCal = new GregorianCalendar();
			int dia= fechaCal.get(Calendar.DATE);
			int mes = fechaCal.get(Calendar.MONTH)+1;
			int año = fechaCal.get(Calendar.YEAR);
			//usuario
			String usuario = Inicio.getUsuario();
				fecha = año + "-" + mes + "-" + dia;
				fechaDate = Date.valueOf(fecha);
				//consulta el nombre del usuario
				//consulta la sesion creada justo antes
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT nombre FROM usuario WHERE usuariosala = '"+usuario+"'");
				//obtiene el nombre completo del usuario
				String nombreCompleto = "";
				while(rs.next()) {
					nombreCompleto = rs.getString(1);
				}
				//realiza insercion
			PreparedStatement pst = con.prepareStatement("INSERT INTO sesion(fecha,usuario) VALUES (?,?)");
				pst.setDate(1, fechaDate);
				pst.setString(2,nombreCompleto);
				pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que realiza la insercion en la base de datos de la mano leida
	 * @param cartas cartas propias
	 * @param posicion posicion propia
	 * @param bote bote de la mano
	 * @param resultado Resultado ganada o perdida(False perdida, true ganada)
	 * @param cg Pone ciega grande
	 * @param cp Pone ciega pequeña
	 * @param apuesta Apuesta realizada
	 * @param ganancia Ganancia de la mano
	 * @param perdida Perdida de la mano
	 * @param limite Limite de la mesa
	 * @param stack Stack del jugador
	 */
	private void insertarManos(String cartas ,String posicion, float bote, String resultado, boolean cg, boolean cp,float apuesta,float ganancia,float perdida,String limite, float stack) {
		try {
			//consulta la sesion creada justo antes
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(codigo) FROM sesion");
			//comienza la insercion en la tabla manos
			PreparedStatement pst= con.prepareStatement("INSERT INTO manos (cartas,pos,bote,resultado,cg,cp,apuesta,ganancia,perdida,limite,stack,sesion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1,cartas);
				pst.setString(2,posicion);
				pst.setDouble(3,Math.round(bote * 100d)/100d);
				pst.setString(4,resultado);
				pst.setBoolean(5,cg);
				pst.setBoolean(6,cp);
				pst.setDouble(7, Math.round(apuesta * 100d)/100d);
				pst.setDouble(8, Math.round(ganancia * 100d)/100d);
				pst.setDouble(9, Math.round(perdida * 100d)/100d);
				pst.setString(10,limite);
				pst.setDouble(11, Math.round(stack * 100d)/100d);
				while(rs.next()) {
					pst.setInt(12, rs.getInt(1));
				}
				pst.executeUpdate();
				
				
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que realiza insercion en la tabla juega
	 * @param usuario Usuario actual
	 */
	private void insertarJuega(String usuario) {
		String insertar = "INSERT INTO juega (codigouser, codigosesion) VALUES (?,?)";
		int codigoSesion = 0;
		int codigoUsuario = 0;
		
		try {
			//consulta la sesion creada justo antes
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(codigo) FROM sesion");
			while(rs.next()) {
				codigoSesion=rs.getInt(1);
			}
			//consulta el codigo de usuario
			Statement st2 = con.createStatement();
			ResultSet rs2 = st2.executeQuery("SELECT codigo FROM usuario WHERE usuariosala = '"+usuario+"'");
			while(rs2.next()) {
				codigoUsuario=rs2.getInt(1);
			}
			//realiza la insercion
			PreparedStatement pst = con.prepareStatement(insertar);
				pst.setInt(1, codigoUsuario);
				pst.setInt(2, codigoSesion);
				pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Inserta un jugador en la tabla jugadores de la base de datos
	 * @param nombre Nombre del jugador
	 * @return True en caso de existir o false en caso de no existir
	 */
	private boolean insertarJugadores(String nombre) {
		//comprueba si existe el jugador en la base de datos
		boolean existe = false;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nombre FROM jugadores WHERE nombre = '"+nombre+"'");
				if(rs.next() == true) {
					existe = true;
				}
			
		//en caso de no existir realiza un insert en la tabla jugadores 
			if(existe == false) {
				st.executeUpdate("INSERT INTO jugadores(nombre,manosanalizadas,flopvisto,riverjugado,ganadas,perdidas) VALUES ('"+nombre+"',0,0,0,0,0)");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return existe;
	}
	
	/**
	 * Metodo que inserta una mano analizada al jugador contrario
	 * @param nombre Jugador contrario
	 */
	private void insertarManoAnalizada(String nombre) {
		
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET manosanalizadas = manosanalizadas +1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			
	}
	
	/**
	 * Inserta un flop visto en la tabla jugadores
	 * @param nombre Nombre del jugador
	 */
	private void insertarFlopVisto(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET flopvisto = flopvisto + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Inserta un river jugado en la tabla jugadores del jugador pasado
	 * @param nombre Nombre del jugador
	 */
	private void insertarRiverJugado(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET riverjugado = riverjugado + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Inserta una partida ganada en la tabla jugadores
	 * @param nombre Nombre del jugador
	 */
	private void insertarGanadasJugadores(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET ganadas = ganadas + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Inserta una partida perdida en la tabla jugadores
	 * @param nombre Nombre del jguador
	 */
	private void insertarPerdidaJugadores(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET perdidas = perdidas + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Inserta en la tabla juegan
	 * @param nombre Nombre del jugador
	 * @param codigoSesion Sesion en la que juega
	 */
	private void insertarJuegan(String nombre,int codigoSesion){
		//consulta la sesion creada justo antes
		Statement st;
		try {
			st = con.createStatement();
		//inserta en la tabla juegan en caso de no existir 
				
				
			st.executeUpdate("INSERT INTO juegan(codigo,nombre) VALUES ('"+codigoSesion+"','"+nombre+"') ON CONFLICT (codigo,nombre) DO NOTHING");	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	/**
	 * Consulta la sesion actual
	 * @return Codigo de la sesion actual
	 */
	private int consultaSesion() {
		int sesion=0;
		//consulta la sesion creada justo antes
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(codigo) FROM sesion");
			while(rs.next()) {
				sesion=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return sesion;
	}
	
	/**
	 * Inserta en la tabla sesionCash 
	 * @param sesion Codigo de la sesion
	 */
	private void insertarSesionCash(int sesion) {
		Statement st;
		ResultSet rs;
		int numeroApuestas = 0;
		float totalApuestas = 0;
		float ganancias = 0;
		int manos = 0;
		float ganancias100;
		float apuestaMedia;
		try {
			st = con.createStatement();
			//consulta las ganancias x100 manos
			rs = st.executeQuery("SELECT SUM(ganancia) FROM manos WHERE sesion = '"+sesion+"'");
			while(rs.next())ganancias = rs.getFloat(1);
			rs = st.executeQuery("SELECT COUNT(numero) FROM manos WHERE sesion = '"+sesion+"'");
			while(rs.next())manos = rs.getInt(1);
			//consulta el numero de apuestas
			rs = st.executeQuery("SELECT COUNT(apuesta) FROM manos WHERE sesion = '"+sesion+"'");
			while(rs.next())numeroApuestas = rs.getInt(1);
			//consulta el total de apuestas
			rs = st.executeQuery("SELECT SUM(apuesta) FROM manos WHERE sesion = '"+sesion+"'");
			while(rs.next())totalApuestas = rs.getFloat(1);
			//resultado a insertar en la tabla
			ganancias100 = (manos / 100) * ganancias;
			apuestaMedia = totalApuestas/numeroApuestas;
			//inserta en la tabla estadisticascash
			st.executeUpdate("INSERT INTO estadisticascash (sesion,ganancias100manos,apuestamedia) "
					+ "VALUES ('"+sesion+"','"+ganancias100+"','"+apuestaMedia+"')");
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		
	}
	/**
	 * MEtodo que inserta en la tabla sesionJuego
	 * @param sesion sesion actual
	 * @param flopVisto numero de flop vistos
	 * @param riverJugado numero de river jugados
	 * @param turnVisto Numero de turn vistos
	 * @param numeroRetiradas Numero de manos retiradas
	 */
	private void insertarSesionJuego(int sesion,int flopVisto, int riverJugado, int turnVisto, int numeroRetiradas) {
		
		Statement st;
		ResultSet rs;
		int numeroGanadas = 0;
		int numeroPerdidas = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT COUNT(resultado) FROM manos WHERE sesion = '"+sesion+"' and resultado = 'Ganada'");
			while(rs.next())numeroGanadas = rs.getInt(1);
			rs = st.executeQuery("SELECT COUNT(resultado) FROM manos WHERE sesion = '"+sesion+"' and resultado = 'Perdida'");
			while(rs.next())numeroPerdidas = rs.getInt(1);
			//inserta en la tabla
			st.executeUpdate("INSERT INTO estadisticasjuego VALUES ('"+sesion+"','"+flopVisto+"','"+riverJugado+"','"+turnVisto+"','"+numeroGanadas+"','"+numeroPerdidas+"','"+numeroRetiradas+"')");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			
		
		
	}
	
	
	/**
	 * Inserta en la tabla obtiene
	 * @param sesion codigo sesion actual
	 */
	private void insertarObtiene(int sesion) {
		
		int codigoJuego = 0;
		int codigoCash = 0;
		//obtiene codigo sesion de tablas  estadisticas cash y juego
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT MAX (sesion) FROM estadisticasjuego;");
			
			while(rs.next())codigoJuego = rs.getInt(1);
			
			rs = st.executeQuery("SELECT MAX (sesion) FROM estadisticascash");
			
			while(rs.next())codigoCash = rs.getInt(1);
			
		//inserta en la tabla
			
			st.executeUpdate("INSERT INTO obtiene(codigo,sesion,sesioncash) VALUES ('"+sesion+"','"+codigoJuego+"','"+codigoCash +"')");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		 
		
	}
	
	
	/**
	 * Metodo que inserta en la tabla bankroll
	 */
	
	private void insertarBank() {
			Statement st;
			ResultSet rs;
			float cashInicio = 0;
			Date fechaDate;
			String fecha;
			int manosJugadas = 0;
			float diferencia;
			String nombreUsuario = "";
			float cash;
			float ganancias = 0;
			float perdidas = 0;
			
			try {//consulta nombre del usuario
				st = con.createStatement();
				rs = st.executeQuery("SELECT nombre FROM usuario WHERE usuariosala = '"+usuario+"'");
				while(rs.next())nombreUsuario = rs.getString(1);
				//consulta cash de la sesion anterior
					
					rs = st.executeQuery("SELECT cash FROM bankroll WHERE usuario = '"+nombreUsuario+"' ORDER BY sesion DESC LIMIT 1");	
					while(rs.next())cashInicio = rs.getFloat(1);
				
				//fecha
				Calendar fechaCal = new GregorianCalendar();
				int dia= fechaCal.get(Calendar.DATE);
				int mes = fechaCal.get(Calendar.MONTH)+1;
				int año = fechaCal.get(Calendar.YEAR);
					fecha = año + "-" + mes + "-" + dia;
					fechaDate = Date.valueOf(fecha);
				//consulta manos jugadas
				rs = st.executeQuery("SELECT COUNT(numero) FROM manos WHERE sesion = '"+sesionActual+"'");
				while(rs.next())manosJugadas = rs.getInt(1);
					
				// consulta ganancias
				rs = st.executeQuery("SELECT SUM(ganancia) FROM manos WHERE sesion = '"+sesionActual+"'");
					while(rs.next())ganancias = rs.getFloat(1);
				// consulta perdidas
				rs = st.executeQuery("SELECT SUM(perdida) FROM manos WHERE sesion = '"+sesionActual+"'");
				while(rs.next())perdidas = rs.getFloat(1);
				//calculo de cash
				cash = (cashInicio + ganancias)- perdidas;
				//calculo diferencia
				diferencia = cash-cashInicio;
				
				//insert en la tabla
				st.executeUpdate("INSERT INTO bankroll VALUES('"+nombreUsuario+"','"+cash+"','"+sesionActual+"','"+fechaDate+"','"+manosJugadas+"','"+diferencia+"')");
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		
	}
	
	/**
	 * Metodo que devuelve un arraylist con los datos de la sesion actual para mostrar en la ventana principal
	 * @return ArrayList con los datos de la sesion actual obtenidos del historial
	 */
	public ArrayList<String> datosSesion() {
		ArrayList <String> listaDatos = new ArrayList<>(); 
		InputStreamReader fr;
		BufferedReader br;
		String linea, stack = "";
		Float apuestaMedia,apuestas = 0.0f;
		int flopVisto = 0,turnVisto = 0,riverVisto = 0,ganadas = 0,retiradas = 0,totalManos = 0;
		int posInicio,posFinal;
		try {
		fr = new InputStreamReader(new FileInputStream(this.archivo),"UTF-8");
		br = new BufferedReader(fr);
		while((linea=br.readLine())!=null) {
			//obtiene el stack
			if(linea.contains("Asiento") && linea.contains(Inicio.getUsuario()) && linea.contains("€") && linea.contains("muestra") == false && linea.contains("recaudó") == false) {
				posInicio = linea.indexOf("(")+1;
				posFinal = linea.indexOf(")");
				stack = linea.substring(posInicio,posFinal);
			}
			//obtiene apuesta media
			else if((linea.contains("apuesta") || linea.contains("sube") || linea.contains("iguala")) && linea.contains(Inicio.getUsuario()) && linea.contains("igualada") == false) {
				if(linea.contains("apuesta")) {
					if(linea.contains("all-in")) {
						posInicio = linea.lastIndexOf("apuesta")+9;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					else {
						posInicio = linea.lastIndexOf("a")+2;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					
				}
				else if(linea.contains("sube")) {
					if(linea.contains("all-in")) {
						posInicio = linea.lastIndexOf(" a ")+5;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					else {
						posInicio = linea.lastIndexOf("a")+1;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					
				}
				else {
					if(linea.contains("all-in")) {
						posInicio = linea.lastIndexOf("iguala")+7;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					else {
						posInicio = linea.lastIndexOf("a")+1;
						posFinal = linea.lastIndexOf("€")-1;
						apuestas += Float.parseFloat(linea.substring(posInicio,posFinal));
					}
					
				}
			}
			//obtiene flop visto
			else if(linea.contains("se retiró en el Flop") && linea.contains(Inicio.getUsuario())) {
				flopVisto += 1;
			}
			//obtiene turn visto
			else if(linea.contains("se retiró en el Turn") && linea.contains(Inicio.getUsuario())) {
				flopVisto += 1;
				turnVisto += 1;
			}
			//obtiene river visto
			else if(linea.contains("se retiró en el River") && linea.contains(Inicio.getUsuario())) {
				flopVisto += 1;
				turnVisto += 1;
				riverVisto += 1;
			}
			//obtiene ganadas
			else if(linea.contains(Inicio.getUsuario()) && linea.contains("ganó")) {
				flopVisto += 1;
				turnVisto += 1;
				riverVisto += 1;
				ganadas += 1;
			}
			//obtiene retiradas
			else if(linea.contains(Inicio.getUsuario()) && linea.contains("retiró")) {
				retiradas += 1;
			}
			//obtiene total de manos
			if(linea.contains("Mano n.º")) {
				totalManos += 1;
			}

		}
		br.close();
		//añade los datos al ArrayList
		apuestaMedia = apuestas/(float)totalManos;
		listaDatos.add(stack);
		listaDatos.add(String.valueOf(apuestaMedia));
		listaDatos.add(String.valueOf(flopVisto));
		listaDatos.add(String.valueOf(turnVisto));
		listaDatos.add(String.valueOf(riverVisto));
		listaDatos.add(String.valueOf(ganadas));
		listaDatos.add(String.valueOf(retiradas));
		listaDatos.add(String.valueOf(totalManos));
		
		for(int i=0;i<listaDatos.size();i++) {
			System.out.println(listaDatos.get(i));
		}
		
		
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return listaDatos;
		
	}
	
}
