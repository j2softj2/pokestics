package pokestics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatosHistorial {
	File archivo;

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public DatosHistorial(File archivo) {
		super();
		this.archivo = archivo;
	}
	public DatosHistorial() {
		
	}
	
	
	
	public void lecturaHistorial() {
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(limite+fecha+nombre1+nombre2+nombre3+nombre4+nombre5+nombre6+nombre7+nombre8+nombre9+nombre10);
		
		
	}
}
