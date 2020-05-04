package pokestics;
/**
 * Clase que realiza el calculo de probabilidades de los datos obtenidos con la calculadora
 * de probabilidades. Dependiendo del momento de la partida y de los datos introducidos.
 * @author Rafael Jiménez Villarreal
 *
 */
public class Calculo {
	
	private Carta cartaPropia1;
	
	private Carta cartaPropia2;
	
	private Carta cartaComunitaria1;
	
	private Carta cartaComunitaria2;
	
	private Carta cartaComunitaria3;
	
	private Carta cartaComunitaria4;
	
	private Carta cartaComunitaria5;
	
	private String posicion;
	
	private double apuesta;
	
	private double bote;

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

	public Carta getCartaComunitaria1() {
		return cartaComunitaria1;
	}

	public void setCartaComunitaria1(Carta cartaComunitaria1) {
		this.cartaComunitaria1 = cartaComunitaria1;
	}

	public Carta getCartaComunitaria2() {
		return cartaComunitaria2;
	}

	public void setCartaComunitaria2(Carta cartaComunitaria2) {
		this.cartaComunitaria2 = cartaComunitaria2;
	}

	public Carta getCartaComunitaria3() {
		return cartaComunitaria3;
	}

	public void setCartaComunitaria3(Carta cartaComunitaria3) {
		this.cartaComunitaria3 = cartaComunitaria3;
	}

	public Carta getCartaComunitaria4() {
		return cartaComunitaria4;
	}

	public void setCartaComunitaria4(Carta cartaComunitaria4) {
		this.cartaComunitaria4 = cartaComunitaria4;
	}

	public Carta getCartaComunitaria5() {
		return cartaComunitaria5;
	}

	public void setCartaComunitaria5(Carta cartaComunitaria5) {
		this.cartaComunitaria5 = cartaComunitaria5;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public double getApuesta() {
		return apuesta;
	}

	public void setApuesta(double apuesta) {
		this.apuesta = apuesta;
	}

	public double getBote() {
		return bote;
	}

	public void setBote(double bote) {
		this.bote = bote;
	}
	
	/**
	 * Constructor de la clase Calculo con parametros
	 * @param cartaPropia1 Carta del jugador
	 * @param cartaPropia2 Carta del jugador
	 * @param cartaComunitaria1 Carta comunitaria 1
	 * @param cartaComunitaria2 Carta comunitaria 2
	 * @param cartaComunitaria3 Carta comunitaria 3
	 * @param cartaComunitaria4 Carta comunitaria 4
	 * @param cartaComunitaria5 Carta comunitaria 5
	 * @param posicion posicion del jugador en la mesa
	 * @param apuesta valor de la apuesta a realizar
	 * @param bote valor del bote de la mesa
	 */

	public Calculo(Carta cartaPropia1, Carta cartaPropia2, Carta cartaComunitaria1, Carta cartaComunitaria2,
			Carta cartaComunitaria3, Carta cartaComunitaria4, Carta cartaComunitaria5, String posicion, int apuesta,
			int bote) {
		this.cartaPropia1 = cartaPropia1;
		this.cartaPropia2 = cartaPropia2;
		this.cartaComunitaria1 = cartaComunitaria1;
		this.cartaComunitaria2 = cartaComunitaria2;
		this.cartaComunitaria3 = cartaComunitaria3;
		this.cartaComunitaria4 = cartaComunitaria4;
		this.cartaComunitaria5 = cartaComunitaria5;
		this.posicion = posicion;
		this.apuesta = apuesta;
		this.bote = bote;
	}
	/**
	 * Constructor de la clase calculo sin parametros
	 */
	public Calculo() {
		
	}

	/**
	 * Metodo que calcula la probabilidad de obtener la mejor mano posible a partir de las cartas dadas.
	 * @return La probabilidad de las mejores manos posibles
	 */
	
	public String CalculoProbabilidadMano() {
		//variable en la que se especifica momento de la partida
		String momento = "";
		//variable en la que se especificara la mano
		String pMano = "";
		//comprueba si es preflop,turn o river
			if(this.cartaComunitaria1.getPalo().equals("O")) {
				momento = "preflop";
			}
			else if(this.cartaComunitaria4.getPalo().equals("O")) {
				momento = "flop";
			}
			else if(this.cartaComunitaria5.getPalo().equals("O")) {
				momento = "turn";
			}
			else {
				momento = "river";
			}
		//comprueba probabilidades en preflop
			if(momento.equals("preflop")) {
				//si las cartas son pareja  y del mismo palo
				if(this.cartaPropia1.getValor().equals(this.cartaPropia2.getValor()) && this.cartaPropia1.getPalo().equals(this.cartaPropia2.getPalo())) {
					pMano = "Color 118-1(0.84%)/Trío 7.5-1(13%)";
				}
				//pareja
				else if(this.cartaPropia1.getValor().equals(this.cartaPropia2.getValor())) {
					pMano = "Trío 7.5-1(13%)/Full 15-1(0,002%)";
				}
				//dos cartas del mismo palo
				else if(this.cartaPropia1.getPalo().equals(this.cartaPropia2.getPalo())) {
					pMano = "Color 118-1(0.84%)/4 cartas color 8.1-1(10,9%)";
				}
				//cartas cualquiera
				else {
					pMano = "Pareja 2.1-1(32,4%)/Doble P 48.5-1(2%)/Trío 7.5-1(0,6%)";
				}
			}
			//comprueba probabilidades de ligar manos en turn
			else if(momento.equals("flop")) {
				//comprueba mano actual con las cartas del flop
				Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3);
					String manoActual = mano.compruebaManoLigada();
					if(manoActual.equals("Trío")) {
						pMano = "Full o Poker 5.7-1(14,8%)";
					}
					else if(manoActual.equals("Proyecto de color")) {
						pMano = "Color 4.2-1(19,1%)";
					}
					else if(manoActual.equals("Doble Pareja")) {
						pMano = "Full 10.8-1(8,5%)";
					}
					else if(manoActual.equals("Pareja")) {
						pMano = "Trío 22.5-1(4,3%)";
					}
			}
			//comprueba probabilidades de ligar mano en river
			else if(momento.equals("turn")) {
				//comprueba mano actual con las cartas del turn
				Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3,this.cartaComunitaria4);
					String manoActual = mano.compruebaManoLigada();
					if(manoActual.equals("Trío")) {
						pMano = "Full o Poker 3.6-1(21,2%)";
					}
					else if(manoActual.equals("Proyecto de color")) {
						pMano = "Color 4.1-1(19,6%)";
					}
					else if(manoActual.equals("Doble Pareja")) {
						pMano = "Full 10.5-1(8,7%)";
					}
					else if(manoActual.equals("Pareja")) {
						pMano = "Trío 22-1(4,3%)";
					}
			}
			else if(momento.equals("river")) {
				//comprueba mano actual con las cartas del turn
				Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3
						,this.cartaComunitaria4,this.cartaComunitaria5);
					String manoActual = mano.compruebaManoLigada();
				
					pMano = manoActual;
			}
			
		return pMano;	
	}
	/**
	 * Calcula el riesgo de la apuesta a partir de la posición, el bote, la apuesta a realizar y las cartas obtenidas.
	 * @return Nivel de riesgo. Bajo, medio o alto
	 */
	public String calcularRiesgo() {
		int nivelRiesgo = 0;//indicara el nivel de riesgo (1-9)
		String riesgo = "";
		String manoActual = "";
		//variable en la que se especifica momento de la partida
				String momento = "";
				//variable en la que se especificara la mano
				double ods = 0;
				//comprueba si es preflop,turn o river
					if(this.cartaComunitaria1.getPalo().equals("O")) {
						momento = "preflop";
					}
					else if(this.cartaComunitaria4.getPalo().equals("O")) {
						momento = "flop";
					}
					else if(this.cartaComunitaria5.getPalo().equals("O")) {
						momento = "turn";
					}
					else {
						momento = "river";
					}
					
					//comprueba probabilidades en preflop
					if(momento.equals("preflop")) {
						//si las cartas son pareja  y del mismo palo
						if(this.cartaPropia1.getValor().equals(this.cartaPropia2.getValor()) && this.cartaPropia1.getPalo().equals(this.cartaPropia2.getPalo())) {
							ods = 7.5;
						}
						//pareja
						else if(this.cartaPropia1.getValor().equals(this.cartaPropia2.getValor())) {
							ods = 7.5;
						}
						//dos cartas del mismo palo
						else if(this.cartaPropia1.getPalo().equals(this.cartaPropia2.getPalo())) {
							ods = 8.1;
						}
						//cartas cualquiera
						else {
							ods = 2.1;
						}
					}
					//comprueba probabilidades de ligar manos en turn
					else if(momento.equals("flop")) {
						//comprueba mano actual con las cartas del flop
						Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3);
							manoActual = mano.compruebaManoLigada();
							if(manoActual.equals("Trío")) {
								ods = 5.7;
							}
							else if(manoActual.equals("Proyecto de color")) {
								ods = 4.2;
							}
							else if(manoActual.equals("Doble Pareja")) {
								ods = 10.8;
							}
							else if(manoActual.equals("Pareja")) {
								ods = 22.5;
							}
					}
					//comprueba probabilidades de ligar mano en river
					else if(momento.equals("turn")) {
						//comprueba mano actual con las cartas del turn
						Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3,this.cartaComunitaria4);
							manoActual = mano.compruebaManoLigada();
							if(manoActual.equals("Trío")) {
								ods = 3.6;
							}
							else if(manoActual.equals("Proyecto de color")) {
								ods = 4.1;
							}
							else if(manoActual.equals("Doble Pareja")) {
								ods = 10.5;
							}
							else if(manoActual.equals("Pareja")) {
								ods = 22;
							}
					}
					else if(momento.equals("river")) {
						//comprueba mano actual con las cartas del turn
						Mano mano = new Mano(this.cartaPropia1,this.cartaPropia2,this.cartaComunitaria1,this.cartaComunitaria2,this.cartaComunitaria3
								,this.cartaComunitaria4,this.cartaComunitaria5);
							manoActual = mano.compruebaManoLigada();
					}
					
					//calculos de riesgo
					
					//primero comprueba si el bote es mayor el numero de odds -1 (ejemplo odds 4 el bote deberia ser mayor que 3 veces la apuesta) que la puesta
					
					if((ods-1)*this.apuesta>this.bote){
						nivelRiesgo += 3;
					}
					//comprueba la posicion en mesa las primeras 4 son inicio-siguientes 3 medio-2 ultimas final. Se considera mayor riesgo las primeras posiciones
					if(this.posicion.equals("SB") | this.posicion.equals("BB") | this.posicion.equals("3") | this.posicion.equals("4") | this.posicion.equals("5")) {
						nivelRiesgo += 3;
					}
					else if(this.posicion.equals("6") | this.posicion.equals("7") | this.posicion.equals("8")) {
						nivelRiesgo += 2;
					}
					else if(this.posicion.equals("DEALER") | this.posicion.equals("9")) {
						nivelRiesgo += 1;
					}
					//comprueba si la mano actual es carta alta o pareja y aumenta el  riesgo en caso de serlo
					if(manoActual.equals("Pareja") | manoActual.equals("")) {
						nivelRiesgo++;
					}
					
					//ahora dependiendo del riesgo sumado este sera bajo - medio - alto
					if(nivelRiesgo == 0) riesgo = "No Jugar";
					else if(nivelRiesgo<=4) riesgo = "Riesgo bajo";
					else if(nivelRiesgo>=5 && nivelRiesgo<7) riesgo = "Riesgo medio";
					else if(nivelRiesgo>6) riesgo = "Riesgo alto";
					return riesgo;
		
	}
	
	
}
		
	
	

