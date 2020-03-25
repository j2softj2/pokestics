package pokestics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	
	
	
	public void lecturaHistorial() {
		usuario = "rafayjessi18";
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
		try {
			fr = new InputStreamReader(new FileInputStream(this.archivo),"UTF-8");
			br = new BufferedReader(fr);
			while((linea=br.readLine())!=null) {
				//comienzan las comprobaciones linea a linea
				//primera linea siempre es limite-fecha lo que debemos obtener
				if(linea.contains("Mano")) {	
					posInicio = linea.indexOf("(");
					posFinal = linea.lastIndexOf(")");
					limite = linea.substring(posInicio+1, posFinal);
					posInicio = linea.indexOf("-");
					posFinal = posInicio + 12;
					fecha = linea.substring(posInicio+1, posFinal);
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
				}
				else if(linea.contains("Asiento 2")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre2 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 3")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre3 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 4")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre4 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 5")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre5 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 5")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre5 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 6")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre6 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 7")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre7 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 8")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre8 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 9")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre9 = linea.substring(posInicio+1, posFinal-1);
				}
				else if(linea.contains("Asiento 10")&& linea.contains("fichas")) {
					posInicio = linea.indexOf(":");
					posFinal = linea.indexOf("(");
					nombre10 = linea.substring(posInicio+1, posFinal-1);
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
					/*else {
						if(linea.contains("sube") && linea.contains("all-in")) {
							posInicio = linea.lastIndexOf(" a ")+2;
							posFinal = linea.lastIndexOf("y")-2;
							apuesta = Float.parseFloat(linea.substring(posInicio,posFinal));
						}
						else if(linea.contains("sube")) {
							posInicio = linea.lastIndexOf("a")+2;
							apuesta = Float.parseFloat(linea.substring(posInicio));
						}
						else {
							if(linea.contains("all-in")) {
								posFinal = linea.lastIndexOf("y")-2;
								posInicio = linea.lastIndexOf("u")+6;
								apuesta = Float.parseFloat(linea.substring(posInicio,posFinal));
							}
							else {
								posInicio = linea.lastIndexOf("a")+2;
								apuesta = Float.parseFloat(linea.substring(posInicio));
							}
							
						}
					}*/
					
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
				
				apuestaTotal = apuestaTotal + apuesta;
				apuesta = 0;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resultado==true)ganancia = boteTotal - apuestaTotal - comision;
		else {ganancia = 0;}
		System.out.println(limite+fecha+nombre1+nombre2+nombre3+nombre4+nombre5+nombre6+nombre7+nombre8+nombre9+nombre10+cartasPropias+posicion+boteTotal+"  "+apuestaTotal+"   "+comision+"  "+ganancia+"  "+stack);
	}
}
