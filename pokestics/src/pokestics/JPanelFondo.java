package pokestics;

import java.awt.Graphics;

import java.awt.Image;

import javax.swing.JPanel;

/**
 * Clase usada para establecer un fondo a un Jframe, extiende de un JPanel
 * @author Rafael Jimenez Villarreal
 *
 */

public class JPanelFondo extends JPanel {
	/**
	 * Atributo con la imagen para el fondo
	 */
	private Image imagen;

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	/**
	 * Metodo que pinta en el jpanel la imagen de fondo
	 */
	public void paint(Graphics g) {
		g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
		
		setOpaque(false);
		super.paint(g);
	}
	/**
	 * Constructor de la clase con parametro
	 * @param imagen Imagen para el fondo
	 */
	public JPanelFondo(Image imagen) {
		this.imagen = imagen;
	}
}
