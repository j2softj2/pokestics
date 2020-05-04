package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que abre una ventana en la que se introducen los datos de dos mesas diferentes para obtener la mejor mesa para jugar
 * @author Rafael Jimenez Villarreal
 *
 */
public class SelectorMesa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoBote1;
	private JTextField campoManos1;
	private JTextField campoFlop1;
	private JTextField campoBote2;
	private JTextField campoManos2;
	private JTextField campoFlop2;
	private JTextField campoResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectorMesa dialog = new SelectorMesa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectorMesa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectorMesa.class.getResource("/imagenesFondo/logoSimple.png")));
		setBounds(100, 100, 556, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel etMesa1 = new JLabel("Mesa 1");
		etMesa1.setForeground(Color.WHITE);
		etMesa1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 18));
		etMesa1.setBounds(134, 10, 66, 39);
		contentPanel.add(etMesa1);
		
		JLabel etMesa2 = new JLabel("Mesa 2");
		etMesa2.setForeground(Color.WHITE);
		etMesa2.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 18));
		etMesa2.setBounds(348, 10, 66, 39);
		contentPanel.add(etMesa2);
		
		campoBote1 = new JTextField();
		campoBote1.setHorizontalAlignment(SwingConstants.CENTER);
		campoBote1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoBote1.setBounds(119, 119, 96, 39);
		contentPanel.add(campoBote1);
		campoBote1.setColumns(10);
		
		campoManos1 = new JTextField();
		campoManos1.setHorizontalAlignment(SwingConstants.CENTER);
		campoManos1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoManos1.setColumns(10);
		campoManos1.setBounds(119, 193, 96, 39);
		contentPanel.add(campoManos1);
		
		campoFlop1 = new JTextField();
		campoFlop1.setHorizontalAlignment(SwingConstants.CENTER);
		campoFlop1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoFlop1.setColumns(10);
		campoFlop1.setBounds(119, 274, 96, 39);
		contentPanel.add(campoFlop1);
		
		campoBote2 = new JTextField();
		campoBote2.setHorizontalAlignment(SwingConstants.CENTER);
		campoBote2.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoBote2.setColumns(10);
		campoBote2.setBounds(333, 119, 96, 39);
		contentPanel.add(campoBote2);
		
		campoManos2 = new JTextField();
		campoManos2.setHorizontalAlignment(SwingConstants.CENTER);
		campoManos2.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoManos2.setColumns(10);
		campoManos2.setBounds(333, 193, 96, 39);
		contentPanel.add(campoManos2);
		
		campoFlop2 = new JTextField();
		campoFlop2.setHorizontalAlignment(SwingConstants.CENTER);
		campoFlop2.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoFlop2.setColumns(10);
		campoFlop2.setBounds(333, 274, 96, 39);
		contentPanel.add(campoFlop2);
		
		JLabel etBoteMedio = new JLabel("Bote  medio");
		etBoteMedio.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etBoteMedio.setForeground(Color.WHITE);
		etBoteMedio.setBounds(119, 85, 96, 13);
		contentPanel.add(etBoteMedio);
		
		JLabel etManosHora = new JLabel("Manos/Hora");
		etManosHora.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etManosHora.setForeground(Color.WHITE);
		etManosHora.setBounds(119, 170, 96, 13);
		contentPanel.add(etManosHora);
		
		JLabel etFlopVisto = new JLabel("Flop visto");
		etFlopVisto.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etFlopVisto.setForeground(Color.WHITE);
		etFlopVisto.setBounds(123, 242, 77, 13);
		contentPanel.add(etFlopVisto);
		
		JLabel etBoteMedio_1 = new JLabel("Bote  medio");
		etBoteMedio_1.setForeground(Color.WHITE);
		etBoteMedio_1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etBoteMedio_1.setBounds(333, 85, 96, 13);
		contentPanel.add(etBoteMedio_1);
		
		JLabel etManosHora_1 = new JLabel("Manos/Hora");
		etManosHora_1.setForeground(Color.WHITE);
		etManosHora_1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etManosHora_1.setBounds(333, 170, 96, 13);
		contentPanel.add(etManosHora_1);
		
		JLabel etFlopVisto_1 = new JLabel("Flop visto");
		etFlopVisto_1.setForeground(Color.WHITE);
		etFlopVisto_1.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		etFlopVisto_1.setBounds(337, 242, 77, 13);
		contentPanel.add(etFlopVisto_1);
		//muestra le mejor mesa para jugar
		JButton botonCalcular = new JButton("Calcular");
		botonCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float bote1 = 0,bote2 = 0;
				int manos1 = 0,manos2 = 0;
				float flop1 = 0,flop2 = 0;
				//comprueba que el formato introducido sea xx.xx o xx,xx en los campos de bote medio de cada mesa
				Pattern pt = Pattern.compile("[0-9]{2,}(.|,)[0-9]{2}");
				Matcher mt = pt.matcher(campoBote1.getText());
					if(mt.matches())bote1 = Float.parseFloat(campoBote1.getText());
					else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Bote medio de la mesa 1");}
					
				mt = pt.matcher(campoBote2.getText());
					if(mt.matches())bote2 = Float.parseFloat(campoBote2.getText());
					else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Bote medio de la mesa 2");}
				
				//comprueba que se introduzcan solo numeros en los campos de manos/hora
					Pattern pt1 = Pattern.compile("[0-9]+");
					mt = pt1.matcher(campoManos1.getText());
						if(mt.matches())manos1 = Integer.parseInt(campoManos1.getText());
						else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Manos/Hora de la mesa 1");}
					mt = pt1.matcher(campoManos2.getText());	
						if(mt.matches())manos2 = Integer.parseInt(campoManos2.getText());
						else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Manos/Hora de la mesa 2");}

				//comprueba que se introduzca el formato correcto en el campo flop visto xx.xx--es un porcentaje
					Pattern pt2 = Pattern.compile("[0-9]{2}(.|,)[0-9]{2}");
					mt = pt2.matcher(campoFlop1.getText());
						if(mt.matches())flop1 = Float.parseFloat(campoFlop1.getText());
						else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Flop visto de la mesa 1");}
					mt = pt2.matcher(campoFlop2.getText());
						if(mt.matches())flop2 = Float.parseFloat(campoFlop2.getText());
						else {JOptionPane.showMessageDialog(null,"Formato incorrecto en el campo Flop visto de la mesa 2");}

						
				//realiza la comprobacion de la mejor mesa
				if(bote1 > 0 && bote2 > 0 && manos1 >0 && manos2 >0 && flop1 > 0 && flop2 >0) {
					int puntosMesa1 = 0;
					int puntosMesa2 = 0;
					
					if(bote1>bote2)puntosMesa1++;
					else {puntosMesa2++;}
					if(manos1>manos2)puntosMesa1++;
					else {puntosMesa2++;}
					if(flop1>flop2)puntosMesa1++;
					else {puntosMesa2++;}
					
					if(puntosMesa1>puntosMesa2) {
						campoResultado.setText("Mesa 1");
					}
					else {
						campoResultado.setText("Mesa 2");
					}
				}	
				else {
					campoResultado.setText("Faltan datos");
				}
			}
		});
		botonCalcular.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		botonCalcular.setBounds(119, 343, 96, 39);
		contentPanel.add(botonCalcular);
		
		campoResultado = new JTextField();
		campoResultado.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		campoResultado.setBounds(333, 343, 96, 39);
		contentPanel.add(campoResultado);
		campoResultado.setColumns(10);
	}
}
