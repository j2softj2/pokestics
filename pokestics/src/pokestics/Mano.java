package pokestics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que representa la mano de cartas
 * @author Rafael Jimënez Villarreal
 *
 */
public class Mano {
	
	private Carta cartaPropia1;
	
	
	private Carta cartaPropia2;
	
	/**
	 * Atributo que representa la 1ª carta de la mano flop
	 */
	private Carta carta1;
	
	/**
	 * Atributo que representa la 2ª carta de la mano flop
	 */
	private Carta carta2;
	/**
	 * Atributo que representa la 3ª carta de la mano flop
	 */
	private Carta carta3;
	/**
	 * Atributo que representa la 4ª carta de la mano turn
	 */
	private Carta carta4;
	/**
	 * Atributo que representa la 5ª carta de la mano river
	 */
	private Carta carta5;
	//metodos getter y setter de los atributos carta
	public Carta getCarta1() {
		return carta1;
	}
	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
	}
	public Carta getCarta2() {
		return carta2;
	}
	public void setCarta2(Carta carta2) {
		this.carta2 = carta2;
	}
	public Carta getCarta3() {
		return carta3;
	}
	public void setCarta3(Carta carta3) {
		this.carta3 = carta3;
	}
	public Carta getCarta4() {
		return carta4;
	}
	public void setCarta4(Carta carta4) {
		this.carta4 = carta4;
	}
	public Carta getCarta5() {
		return carta5;
	}
	public void setCarta5(Carta carta5) {
		this.carta5 = carta5;
	}
	
	public Carta getCartaPropia1() {
		return cartaPropia1;
	}
	public void setCartaPropia1(Carta cartaPropia1) {
		this.cartaPropia1 = cartaPropia1;
	}
	public Carta getCartaPropia2() {
		return cartaPropia2;
	}
	public void setCartaPropia2(Carta cartaPropia2) {
		this.cartaPropia2 = cartaPropia2;
	}
	/**
	 * Constructor de mano pre-flop con las dos cartas propias o dos de la mesa comunitarias
	 * @param carta1 Carta propia  o mesa 1
	 * @param carta2 Carta propia  o mesa 2
	 */
	public Mano(Carta carta1,Carta carta2) {
		this.carta1 = carta1;
		this.carta2 = carta2;
	}
	/**
	 * Constructor de la mano flop con las 3 cartas comunitarias
	 * @param carta1 carta comunitaria 1
	 * @param carta2 carta comunitaria 2
	 * @param carta3 carta comunitaria 3
	 */
	public Mano(Carta carta1,Carta carta2,Carta carta3) {
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
	}
	/**
	 * constructor de la mano turn
	 * @param carta1 carta comunitaria 1
	 * @param carta2 carta comunitaria 2
	 * @param carta3 carta comunitaria 3
	 * @param carta4 carta comunitaria 4
	 */
	public Mano(Carta carta1,Carta carta2,Carta carta3,Carta carta4) {
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
		this.carta4 = carta4;
	}
	/**
	 * Constructor de la mano river
	 * @param carta1 carta comunitaria 1
	 * @param carta2 carta comunitaria 2
	 * @param carta3 carta comunitaria 3
	 * @param carta4 carta comunitaria 4
	 * @param carta5 carta comunitaria 5
	 */
	
	public Mano(Carta cartaPropia1,Carta cartaPropia2,Carta carta1,Carta carta2,Carta carta3) {
		this.cartaPropia1 = cartaPropia1;
		this.cartaPropia2 = cartaPropia2;
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
	}
	
	public Mano(Carta cartaPropia1, Carta cartaPropia2, Carta carta1, Carta carta2, Carta carta3, Carta carta4) {
		
		this.cartaPropia1 = cartaPropia1;
		this.cartaPropia2 = cartaPropia2;
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
		this.carta4 = carta4;
	}
	
	
	
	public Mano(Carta cartaPropia1, Carta cartaPropia2, Carta carta1, Carta carta2, Carta carta3, Carta carta4,
			Carta carta5) {
		
		this.cartaPropia1 = cartaPropia1;
		this.cartaPropia2 = cartaPropia2;
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
		this.carta4 = carta4;
		this.carta5 = carta5;
	}
	public String compruebaManoLigada(){
		
		String mano = "";
		int repetido = 0;
		String dato = "";
		
		
		//lista cartas para comprobaciones de valores y palos
		
		List<Carta> listaCartas = new ArrayList<Carta>();
			listaCartas.add(cartaPropia1);
			listaCartas.add(cartaPropia2);
			listaCartas.add(carta1);
			listaCartas.add(carta2);
			listaCartas.add(carta3);
				if(this.carta4!=null)listaCartas.add(carta4);
				if(this.carta5!=null) {
					listaCartas.add(carta5);
					repetido--;//al ser 5 cartas comunitarias las repeticiones contadas se solapan
				}
				
				
		
		//comprueba repeticiones
		
		for(int i=0; i<listaCartas.size();i++) {
			dato = listaCartas.get(i).getValor();
				for(int a=0;a<listaCartas.size();a++) {
					if(dato.equals(listaCartas.get(a).getValor())) {
						repetido++;
					}
					
				}
				
		}
		//comprobacion color cartas para mano color
		
		int color = 0;
		String palo = listaCartas.get(0).getPalo();
			for(int i=0; i<listaCartas.size();i++) {
				if(listaCartas.get(i).getPalo().equals(palo)) color++;
			}
		//comprobacion de escalera
			
		boolean consecutivo1 = false;
		List<Integer> valoresCartas = new ArrayList<Integer>();
			//pasa los valores de la lista de cartas a la lista valoresCartas convirtiendolos a integer
			for(int i=0;i<listaCartas.size();i++) {
				int valorNumerico = 0;
					if(listaCartas.get(i).getValor().equals("A")) valorNumerico = 1;
					else if(listaCartas.get(i).getValor().equals("K")) valorNumerico = 13;
					else if(listaCartas.get(i).getValor().equals("Q")) valorNumerico = 12;
					else if(listaCartas.get(i).getValor().equals("J")) valorNumerico = 11;
					else if(listaCartas.get(i).getValor().equals("0")) valorNumerico = 10;
					else if(listaCartas.get(i).getValor().equals("N")) valorNumerico = 0;
					else valorNumerico = Integer.valueOf(listaCartas.get(i).getValor());
					valoresCartas.add(i,valorNumerico);			
			}
			//ordena el listado
			Collections.sort(valoresCartas);
			
			//en caso de ser escalera con A como mayor(el As puede ser tanto 1 como AS)
			if(valoresCartas.contains(1)){
				if(valoresCartas.contains(13)) {
					if(valoresCartas.contains(12)) {
						if(valoresCartas.contains(11)) {
							if(valoresCartas.contains(10)) {
								consecutivo1 = true;
							}
						}
					}
				}
			}
			//comprueba si son consecutivos
			boolean consecutivo2 = false;
			int aux = 0;
			for(int i=1; i<valoresCartas.size();i++) {
				if(valoresCartas.get(i)!=null) aux++;
			}
			//en caso de ser 5 numeros (al ordenarlos la lista omite los repetidos)
			if(aux == 4 |aux==5 | aux==6) consecutivo2 = true;
			//una vez comprueba que no se repite ningun numero comprobar que sean consecutivos
			boolean consecutivo3 = false;
			if(valoresCartas.get(valoresCartas.size()-1)==valoresCartas.get(valoresCartas.size()-2)+1 && valoresCartas.get(valoresCartas.size()-2)==valoresCartas.get(valoresCartas.size()-3)+1 && valoresCartas.get(valoresCartas.size()-3)==valoresCartas.get(valoresCartas.size()-4) +1 && 
			valoresCartas.get(valoresCartas.size()-4)==valoresCartas.get(valoresCartas.size()-5) +1 && consecutivo2 == true){
				consecutivo3 = true;
			}
			else if(this.carta4!=null && this.carta5 == null) {
				if(valoresCartas.get(valoresCartas.size()-2)==valoresCartas.get(valoresCartas.size()-3)+1 && valoresCartas.get(valoresCartas.size()-3)==valoresCartas.get(valoresCartas.size()-4)+1 && valoresCartas.get(valoresCartas.size()-4)==valoresCartas.get(valoresCartas.size()-5) +1 && 
						valoresCartas.get(valoresCartas.size()-5)==valoresCartas.get(valoresCartas.size()-6) +1 && consecutivo2 == true){
							consecutivo3 = true;
						}
			}
			else if(this.carta5!=null) {
				if(valoresCartas.get(valoresCartas.size()-2)==valoresCartas.get(valoresCartas.size()-3)+1 && valoresCartas.get(valoresCartas.size()-3)==valoresCartas.get(valoresCartas.size()-4)+1 && valoresCartas.get(valoresCartas.size()-4)==valoresCartas.get(valoresCartas.size()-5) +1 && 
						valoresCartas.get(valoresCartas.size()-5)==valoresCartas.get(valoresCartas.size()-6) +1 && consecutivo2 == true){
							consecutivo3 = true;
						}
				else if(valoresCartas.get(valoresCartas.size()-3)==valoresCartas.get(valoresCartas.size()-4)+1 && valoresCartas.get(valoresCartas.size()-4)==valoresCartas.get(valoresCartas.size()-5)+1 && valoresCartas.get(valoresCartas.size()-5)==valoresCartas.get(valoresCartas.size()-6) +1 && 
						valoresCartas.get(valoresCartas.size()-6)==valoresCartas.get(valoresCartas.size()-7) +1 && consecutivo2 == true) {
							consecutivo3 =true;
				}
			}
		//comprueba escalera real
		if(consecutivo1 == true && color >= 5) {
			mano = "Escalera Real";
		}
		//comprueba escalera de color
		else if(color >= 5  && consecutivo3 == true) {
			mano = "Escalera de color";
		}
		//comprobar poker
		else if(repetido == 17 | repetido == 18) {
			mano="Póker";
		}
		//comprobar full
		else if(repetido == 13 | repetido == 14) {
			mano = "Full";
		}		
		//comprobar color
		else if(color >= 5) {
			mano = "Color";
		}
		//comprobar proyecto color
		else if(color == 4) {
			mano = "Proyecto de color";
		}
		//comprueba escalera
		else if(consecutivo1 == true | consecutivo3 == true) {
			mano = "Escalera";
		}
		//comprobar trio
		else if(repetido == 11 | repetido == 12) {
			mano = "Trío";
		}
		//comprobar doble pareja
		else if(repetido == 9 | repetido == 10) {
			mano = "Doble Pareja";
		}
		//comprobar pareja
		else if(repetido == 7 | repetido == 8) {
			mano = "Pareja";
		}
		else {
			mano = "Sin mano";
		}
		return mano;
	}
}
