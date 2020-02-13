package pokestics;
/**
 * Clase que realiza el calculo de probabilidades de los datos obtenidos con la calculadora
 * de probabilidades.
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
	
	private int apuesta;
	
	private int bote;

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

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public int getBote() {
		return bote;
	}

	public void setBote(int bote) {
		this.bote = bote;
	}
	
	/**
	 * Constructor de la clase Calculo 
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

	
	public void CalculoProbabilidad() {
		
	}
}
		
	
	

