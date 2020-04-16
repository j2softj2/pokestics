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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatosHistorial {
	private File archivo;
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

	public DatosHistorial(File archivo) {
		super();
		this.archivo = archivo;
	}
	public DatosHistorial() {
		
	}
	
	
	private int sesionActual;
	
	public int getSesionActual() {
		return sesionActual;
	}

	public void setSesionActual(int sesionActual) {
		this.sesionActual = sesionActual;
	}
	
	//conexion a la base de datos
	
	Connection con = Inicio.getConexion();
	
	
	
	
	
	public void lecturaHistorial() {
		//obtener sesion
		sesionActual = consultaSesion();
		//inserta la sesion
		insertarSesion();
		if(Inicio.getUsuario()!= "postgres" && Inicio.getUsuario() != "superusuario") usuario = Inicio.getUsuario();
		else { usuario = "anonimo";}
		//inserta en la tabla juega		
		insertarJuega(usuario);
		//
		int flopVisto = 0;
		int riverJugado = 0;
		int turnVisto = 0;
		int numeroRetiradas = 0;
		InputStreamReader fr;
		BufferedReader br;
		String linea;
		int posInicio=0;
		int posFinal = 0;
		String limite="";
		String fecha="";
		String nombre1="";
		String nombre2="";
		String nombre3="";
		String nombre4="";
		String nombre5="";
		String nombre6="";
		String nombre7="";
		String nombre8="";
		String nombre9="";
		String nombre10="";
		String cartasPropias="";
		String posicion="";
		boolean cp = false;
		boolean cg = false;
		boolean resultado= false;
		int asientoDealer = 0;
		float boteTotal = 0;
		float apuestaTotal = 0;
		float apuesta = 0;
		float ganancia = 0;
		float comision= 0;
		float stack = 0;
		float perdida = 0;
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
					//inserta en bd
					if(boteTotal != 0){
						System.out.println(limite+fecha+nombre1+nombre2+nombre3+nombre4+nombre5+nombre6+nombre7+nombre8+nombre9+nombre10+cartasPropias+posicion+boteTotal+"  "+apuestaTotal+"   "+comision+"  "+ganancia+"  "+stack);					
						
						insertarManos(cartasPropias,posicion,boteTotal,resultado,cg,cp,apuestaTotal,ganancia,perdida,limite,stack);
						
					}
					
					//reinicia valores
					 cp = false;
					 cg = false;
					 resultado= false;
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
				else if(linea.contains(usuario) && linea.contains("ciega pequeña")){
					cp = true;
				}
				else if(linea.contains(usuario) && linea.contains("ciega grande")){
					cg = true;
				}				
				else if(linea.contains("n.º")) {
					posInicio = linea.indexOf("º");
					posFinal = posInicio+3;
					String numeroAsientoDealer = linea.substring(posInicio+2, posFinal);
						asientoDealer = Integer.parseInt(numeroAsientoDealer);
						
				}
				else if(linea.contains(usuario) && linea.contains("fichas") && linea.contains("recompra") == false) {
					if(linea.contains("€")) {
						posInicio = linea.indexOf("o")+2;
						posFinal = linea.indexOf("o")+3;
					}
					/*else{
						posInicio = linea.indexOf("o")+2;
						posFinal = linea.indexOf("o")+3;
					}*/
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
						if(linea.contains("€")) {
							posInicio = linea.indexOf("(");
							posFinal = linea.indexOf("€");
							stack = Float.parseFloat(linea.substring(posInicio+1, posFinal-1));
						}
						/*else {
							posInicio = linea.indexOf("(");
							posFinal = linea.indexOf(")");
							stack = Float.parseFloat(linea.substring(posInicio+1, posFinal-10));
						}
						*/
					
				}
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
				else if(linea.contains("Repartidas")) {
					posInicio = linea.indexOf("[");
					posFinal = linea.lastIndexOf("]");
					cartasPropias = linea.substring(posInicio+1, posFinal);
				}
				else if(linea.contains("iguala") | linea.contains("apuesta") | linea.contains("sube") && linea.contains(usuario) && !linea.contains("igualada")){
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
				else if(linea.contains("bote principal") && linea.contains(usuario)) {
					resultado = true;
				}
				else if(linea.contains("Bote total")) {
					if(linea.contains("€")) {
						posInicio = linea.indexOf("l")+1;
						posFinal = linea.indexOf("€");
						boteTotal = Float.parseFloat(linea.substring(posInicio,posFinal));
						//comision
							if(linea.contains("Comisión 0 €") | resultado != true) {
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
							if(linea.contains("Comisión 0 €") | resultado != true) {
								comision += 0;
							}
							else {
								posInicio = linea.lastIndexOf("n");
								comision += Float.parseFloat(linea.substring(posInicio+2));
							}
					}
					
				}
				//comprobacion flop visto por jugadores
				if(linea.contains(nombre1) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre1);
				else if(linea.contains(nombre2) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre2);
				else if(linea.contains(nombre3) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre3);
				else if(linea.contains(nombre4) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre4);
				else if(linea.contains(nombre5) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre5);
				else if(linea.contains(nombre6) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre6);
				else if(linea.contains(nombre7) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre7);
				else if(linea.contains(nombre8) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre8);
				else if(linea.contains(nombre9) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre9);
				else if(linea.contains(nombre10) && linea.contains("Flop") && !linea.contains("antes"))insertarFlopVisto(nombre10);
				//comprobacion river jugado
				if(linea.contains(nombre1) && linea.contains("descartó"))insertarRiverJugado(nombre1);
				else if(linea.contains(nombre2) && linea.contains("descartó"))insertarRiverJugado(nombre2);
				else if(linea.contains(nombre3) && linea.contains("descartó"))insertarRiverJugado(nombre3);
				else if(linea.contains(nombre4) && linea.contains("descartó"))insertarRiverJugado(nombre4);
				else if(linea.contains(nombre5) && linea.contains("descartó"))insertarRiverJugado(nombre5);
				else if(linea.contains(nombre6) && linea.contains("descartó"))insertarRiverJugado(nombre6);
				else if(linea.contains(nombre7) && linea.contains("descartó"))insertarRiverJugado(nombre7);
				else if(linea.contains(nombre8) && linea.contains("descartó"))insertarRiverJugado(nombre8);
				else if(linea.contains(nombre9) && linea.contains("descartó"))insertarRiverJugado(nombre9);
				else if(linea.contains(nombre10) && linea.contains("descartó"))insertarRiverJugado(nombre10);
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
				if(linea.contains(usuario) && !linea.contains("antes del Flop") && linea.contains("retiró"))numeroRetiradas++;
				apuestaTotal = apuestaTotal + apuesta;
				apuesta = 0;
				if(resultado==true)ganancia = boteTotal - apuestaTotal - comision;
				else {ganancia = 0; perdida = apuestaTotal;}
			}
			insertarSesionCash(sesionActual);
			insertarSesionJuego(sesionActual,flopVisto,riverJugado,turnVisto,numeroRetiradas);
			insertarObtiene(sesionActual);
			insertarBank();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//metodos para insertar en la base de datos
	private void insertarSesion() {
		try {
			Date fechaDate;
			String fecha;
			Calendar fechaCal = new GregorianCalendar();
			int dia= fechaCal.get(Calendar.DATE);
			int mes = fechaCal.get(Calendar.MONTH)+1;
			int año = fechaCal.get(Calendar.YEAR);
			String usuario = Inicio.getUsuario();
				fecha = año + "-" + mes + "-" + dia;
				fechaDate = Date.valueOf(fecha);
				//consulta el nombre del usuario
				//consulta la sesion creada justo antes
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT nombre FROM usuario WHERE usuariosala = '"+usuario+"'");
				String nombreCompleto = "";
				while(rs.next()) {
					nombreCompleto = rs.getString(1);
				}
			PreparedStatement pst = con.prepareStatement("INSERT INTO sesion(fecha,usuario) VALUES (?,?)");
				pst.setDate(1, fechaDate);
				pst.setString(2,nombreCompleto);
				pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	private void insertarManos(String cartas ,String posicion, float bote, boolean resultado, boolean cg, boolean cp,float apuesta,float ganancia,float perdida,String limite, float stack) {
		try {
			//consulta la sesion creada justo antes
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(codigo) FROM sesion");
			//comienza la insercion en la tabla manos
			PreparedStatement pst= con.prepareStatement("INSERT INTO manos (cartas,pos,bote,resultado,cg,cp,apuesta,ganancia,perdida,limite,stack,sesion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1,cartas);
				pst.setString(2,posicion);
				pst.setDouble(3,Math.round(bote * 100d)/100d);
				pst.setBoolean(4,resultado);
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
			//
			PreparedStatement pst = con.prepareStatement(insertar);
				pst.setInt(1, codigoUsuario);
				pst.setInt(2, codigoSesion);
				pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//inserta jugador
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
				st.executeUpdate("INSERT INTO jugadores(nombre) VALUES ('"+nombre+"')");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return existe;
	}
	
	//inserta mano analizada en tabla jugadores
	private void insertarManoAnalizada(String nombre) {
		
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET manosanalizadas = manosanalizadas +1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			
	}
	
	//inserta flop visto en tabla jugadores al jugador
	private void insertarFlopVisto(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET flopvisto = flopvisto + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//inserta river jugado en la tabla jugadores
	private void insertarRiverJugado(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET riverjugado = riverjugado + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//inserta partida gana en tabla jugadores
	private void insertarGanadasJugadores(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET ganadas = ganadas + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//inserta partida perdida en tabla jugadores
	private void insertarPerdidaJugadores(String nombre) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE jugadores SET perdidas = perdidas + 1 WHERE nombre = '"+nombre+"'");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//inserta jugador y sesion en la tabla juegan
	private void insertarJuegan(String nombre,int codigoSesion){
		
		//int codigoSesion = 0;
		
		//consulta codigo sesion
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
	
	
	//consulta la sesion
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
	
	//inserta sesion en la tabla estadisticascash
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
	
	private void insertarSesionJuego(int sesion,int flopVisto, int riverJugado, int turnVisto, int numeroRetiradas) {
		
		Statement st;
		ResultSet rs;
		int numeroGanadas = 0;
		int numeroPerdidas = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT COUNT(resultado) FROM manos WHERE sesion = '"+sesion+"' and resultado = true");
			while(rs.next())numeroGanadas = rs.getInt(1);
			rs = st.executeQuery("SELECT COUNT(resultado) FROM manos WHERE sesion = '"+sesion+"' and resultado = false");
			while(rs.next())numeroPerdidas = rs.getInt(1);
			//inserta en la tabla
			st.executeUpdate("INSERT INTO estadisticasjuego VALUES ('"+sesion+"','"+flopVisto+"','"+riverJugado+"','"+turnVisto+"','"+numeroGanadas+"','"+numeroPerdidas+"','"+numeroRetiradas+"')");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
			
		
		
	}
	
	
	//inserta en la tabla obtiene
	
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
	
	
	//insertar en la tabla bankroll
	
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
	
}
