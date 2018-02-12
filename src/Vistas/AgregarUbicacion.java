package Vistas;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import Class.*;
import Controlador.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AgregarUbicacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUbi;
	private Connection conn = Conexion.getConnectio();
	private static DefaultTableModel mod;
	private JScrollPane jScrollPane1;
	private JTable Tabla;
	private final static Logger log = Logger.getLogger(AgregarUbicacion.class);
	private Calendar fecha = Calendar.getInstance();
	private SimpleDateFormat sfd2 = new SimpleDateFormat(" [dd/MM/YYYY] - [HH:mm:ss]");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarUbicacion frame = new AgregarUbicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarUbicacion() {
		setTitle("Agregar Ubicacion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarUbicacion.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 282);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {

		}, new String[] { "id", "Nombre" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		contentPane.setLayout(null);

		llenarTabla();

		// ------------------------
		JLabel lblUbicacion = new JLabel("Ubicacion ");
		lblUbicacion.setBounds(12, 16, 65, 14);
		contentPane.add(lblUbicacion);

		txtUbi = new JTextField();
		txtUbi.setBounds(95, 13, 258, 20);
		contentPane.add(txtUbi);
		txtUbi.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ubic = txtUbi.getText().toUpperCase();
				PropertyConfigurator.configure("log4j.properties");
				if (ubic.length() > 0) {
					Cont_Ubicacion cnubi = new Cont_Ubicacion();
					Ubicacion ubi = new Ubicacion();
					ubi.setLugar(ubic);
					ArrayList<Ubicacion> list = new ArrayList<>();
					list = cnubi.listUBI();
					for (Ubicacion ubicacion : list) {
						if (ubicacion.getLugar().equals(ubic)) {
							JOptionPane.showMessageDialog(null, "Ubicacion ya Existente");
							return;
						}
					}

					boolean valida = cnubi.ingresarUbicacion(ubi);

					lblUbicacion.setText(valida + "");
					if (valida) {
						JOptionPane.showMessageDialog(null, "Ubicacion Ingresada");
						log.warn(sfd2.format(fecha.getTime()) + " Se agrega Nueva Ubicacion " + ubi.getLugar());
						while (mod.getRowCount() > 0) {

							mod.removeRow(0);
						}
						LimpiarTabla();
						llenarTabla();

						txtUbi.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Error Al guardar");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingresar Ubicacion");
				}

			}
		});
		btnGuardar.setBounds(31, 71, 113, 28);
		contentPane.add(btnGuardar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnVolver.setBounds(299, 71, 93, 26);
		contentPane.add(btnVolver);
		contentPane.setLayout(null);
		contentPane.add(lblUbicacion);
		contentPane.add(txtUbi);
		contentPane.add(btnGuardar);
		contentPane.add(btnVolver);

		// ---------------------
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(95, 124, 277, 100);
		Tabla = new javax.swing.JTable();
		Tabla.setSelectionBackground(Color.WHITE);
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(true);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Ubicacion", }){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		Tabla.setModel(mod);
		Tabla.getColumnModel().getColumn(0).setPreferredWidth(130);
		Tabla.getColumnModel().getColumn(1).setPreferredWidth(130);

		jScrollPane1.setViewportView(Tabla);
		contentPane.setLayout(null);

		llenarTabla();

		contentPane.add(jScrollPane1);
		jScrollPane1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { Tabla }));

		// --------------------------------

	}

	private void llenarTabla() {
		try {
			String sql = "SELECT * FROM Ubicacion;";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);
				}

				mod.addRow(raws);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void LimpiarTabla() {
		while (mod.getRowCount() > 0) {

			mod.removeRow(0);
		}
	}

}
