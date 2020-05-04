package pokestics;
/**
 * Clase que representa una carta de la baraja de poker
 * @author Rafael Jiménez Villarreal
 *
 */
public class Carta {
	
	/**
	 * atributo que indica el palo de la carta,
	 * pueden ser Corazones, Picas, Trebol, Diamante
	 */
	private String palo;
	/**
	 * atributo que indica el valor de la carta,
	 * en el siguiente orden 1,2,3,4,5,6,7,8,9,10,J,Q,K
	 */
	private String valor;
	/**
	 * Metodo get para el atributo valor
	 * @return El valor del atributo Valor
	 */
	public String getValor() {
		return this.valor;
	}
	/**
	 * Metodo get del atributo palo
	 * @return El valor del atributo Palo
	 */
	public String getPalo() {
		return this.palo;
	}
	/**
	 * Metodo set del atributo palo
	 * @param palo El valor del atributo palo
	 */
	public void setPalo(String palo) {
		this.palo = palo;
	}
	/**
	 * Metodo set del atributo valor
	 *
	 * @param valor El valor del atributo Valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * Constructor de la clase carta con parametros
	 * @param palo Representa el palo 
	 * @param valor Representa el valor
	 */
	public Carta(String palo, String valor) {
		this.palo = palo;
		this.valor =  valor;
	}
	
	
}
