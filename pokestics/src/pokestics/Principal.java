package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class Principal extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal dialog = new Principal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setMinimumSize(new Dimension(500, 500));
		setBounds(100, 100, 1069, 685);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('a');
		menuArchivo.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.setBackground(Color.WHITE);
		menuArchivo.setIcon(new ImageIcon(Principal.class.getResource("/botones/archivo10x10.png")));
		menuArchivo.setSelectedIcon(null);
		barraMenu.add(menuArchivo);
		
		JMenuItem menuItemCerrar = new JMenuItem("Cerrar");
		menuItemCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		//cierra la ventana
		menuItemCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarAplicacion();
			}
		});
		menuItemCerrar.setBackground(Color.WHITE);
		menuItemCerrar.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuArchivo.add(menuItemCerrar);
		
		JMenu menuConfiguracion = new JMenu("Configuraci\u00F3n");
		menuConfiguracion.setIcon(new ImageIcon(Principal.class.getResource("/botones/configuracion10x10.png")));
		menuConfiguracion.setBackground(Color.WHITE);
		menuConfiguracion.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		barraMenu.add(menuConfiguracion);
		
		JMenuItem menuItemHistorial = new JMenuItem("Ruta historial de manos ");
		menuItemHistorial.setBackground(Color.WHITE);
		menuItemHistorial.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuConfiguracion.add(menuItemHistorial);
		
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setIcon(new ImageIcon(Principal.class.getResource("/botones/ayuda10x10.png")));
		menuAyuda.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuAyuda.setBackground(Color.WHITE);
		barraMenu.add(menuAyuda);
		
		JMenuItem menuItemManual = new JMenuItem("Manual de ayuda");
		menuItemManual.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuItemManual.setBackground(Color.WHITE);
		menuAyuda.add(menuItemManual);
		
		JMenuItem menuItemAcerca = new JMenuItem("Acerca de...");
		menuItemAcerca.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 12));
		menuItemAcerca.setBackground(Color.WHITE);
		menuAyuda.add(menuItemAcerca);
	}
	
	private void cerrarAplicacion() {
		this.dispose();
	}

}
