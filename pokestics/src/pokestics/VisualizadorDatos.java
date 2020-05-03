package pokestics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VisualizadorDatos extends JDialog {
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	
	private int codigoSesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizadorDatos dialog = new VisualizadorDatos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public VisualizadorDatos(int codigoSesion) {
		setBounds(100, 100, 837, 552);
		getContentPane().setLayout(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 823, 515);
		scrollPane.setBackground(Color.LIGHT_GRAY);
		
		getContentPane().add(scrollPane);
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		modelo.addColumn("CARTAS");
		modelo.addColumn("POSICIÓN");
		modelo.addColumn("BOTE");
		modelo.addColumn("GAN/PERD/NO");
		modelo.addColumn("CG");
		modelo.addColumn("CP");		
		modelo.addColumn("APUESTA");
		modelo.addColumn("GANANCIA");
		modelo.addColumn("PERDIDA");
		modelo.addColumn("LM");
		modelo.addColumn("STACK");
		
		//rellena tabla
		Connection con = Inicio.getConexion();
		
		Statement st;
		ResultSet rs;
		try {			
				st = con.createStatement();
				rs = st.executeQuery("SELECT cartas,pos,bote,resultado,cg,cp,apuesta,ganancia,perdida,limite,stack FROM manos WHERE sesion = "+codigoSesion+"");
				//filas de la tabla
				while(rs.next()) {
				Object fila[] = new Object[11];
				//llenado de tabla
					for(int i=0;i<11;i++) {
						
						fila[i] = rs.getObject(i+1);
					}
				//se añade al modelo de la tabla
				modelo.addRow(fila);
				}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
	}
	/**
	 * Create the dialog.
	 */
	public VisualizadorDatos() {
		setBounds(100, 100, 913, 607);
		getContentPane().setLayout(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 899, 560);
		scrollPane.setBackground(Color.LIGHT_GRAY);
		
		getContentPane().add(scrollPane);
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		modelo.addColumn("CARTAS");
		modelo.addColumn("POSICIÓN");
		modelo.addColumn("BOTE");
		modelo.addColumn("GAN/PERD/NO");
		modelo.addColumn("CG");
		modelo.addColumn("CP");		
		modelo.addColumn("APUESTA");
		modelo.addColumn("GANANCIA");
		modelo.addColumn("PERDIDA");
		modelo.addColumn("LM");
		modelo.addColumn("STACK");
		
		
		//rellena tabla
		
		setFilas(modelo);		
		scrollPane.setViewportView(table);
	}
	
	private void setFilas(DefaultTableModel modelo) {
		Connection con = Inicio.getConexion();
		
		Statement st;
		ResultSet rs;
		try {			
				st = con.createStatement();
				rs = st.executeQuery("SELECT cartas,pos,bote,resultado,cg,cp,apuesta,ganancia,perdida,limite,stack FROM manos WHERE sesion = "+codigoSesion+"");
				//filas de la tabla
				while(rs.next()) {
				Object fila[] = new Object[11];
				//llenado de tabla
					for(int i=0;i<11;i++) {
						fila[i] = rs.getObject(i+1);
					}
				//se añade al modelo de la tabla
				modelo.addRow(fila);
				}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}

}
