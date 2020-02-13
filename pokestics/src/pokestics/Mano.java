package pokestics;
/**
 * Clase que representa la mano de cartas
 * @author Rafael Jimënez Villarreal
 *
 */
public class Mano {
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
	
	public Mano(Carta carta1,Carta carta2,Carta carta3,Carta carta4,Carta carta5) {
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.carta3 = carta3;
		this.carta4 = carta4;
		this.carta5 = carta5;
	}
}
