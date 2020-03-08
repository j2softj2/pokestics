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
		int posInicio;
		int posFinal;
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
				else if(linea.contains(usuario) && linea.contains("fichas")) {
					posInicio = linea.indexOf("o")+2;
					posFinal = linea.lastIndexOf(":");
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
				else if(linea.contains("bote principal") && linea.contains(usuario)) {
					resultado = true;
				}
				else if(linea.contains("Bote total")) {
					posInicio = linea.indexOf("l")+1;
					posFinal = linea.indexOf("€");
					boteTotal = Float.parseFloat(linea.substring(posInicio,posFinal));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(limite+fecha+nombre1+nombre2+nombre3+nombre4+nombre5+nombre6+nombre7+nombre8+nombre9+nombre10+cartasPropias+posicion+boteTotal);
	}
}
