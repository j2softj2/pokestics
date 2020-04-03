package pokestics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class JPanelFondo extends JPanel {
	private Image imagen;

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	public void paint(Graphics g) {
		g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
		
		setOpaque(false);
		super.paint(g);
	}
	
	public JPanelFondo(Image imagen) {
		this.imagen = imagen;
	}
}
