package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Class.*;
import Controlador.Cont_Pais;
import Controlador.Cont_Plataforma;
import Controlador.Cont_Servidor;
import Controlador.Cont_Ubicacion;

import javax.swing.ScrollPaneConstants;
import java.awt.Component;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificarVarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn = Conexion.getConnectio();
	private static DefaultTableModel mod = new DefaultTableModel();
	private JScrollPane jScrollPane1;
	private JTable Tabla;
	private JTextField txtCod;
	private JButton btnLimpiar;
	private JTextField txtCont;
	private int cont = 0;
	private final static Logger log = Logger.getLogger(ModificarVarios.class);
	private Calendar fecha = Calendar.getInstance();
	private SimpleDateFormat sfd2 = new SimpleDateFormat(" [dd/MM/YYYY] - [HH:mm:ss]");
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JComboBox<String> cboPlata, cboubicac, cboDesti, cboServ, cboPais, cboMes;
	private JLabel lblMesYAo;
	private JTextField txtAnio;
	private JComboBox<Object> cboEstado;
	private JLabel lblEstadoCinta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarVarios frame = new ModificarVarios();
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
	public ModificarVarios() {
		setTitle("Modificacion Masiva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarVarios.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 766, 613);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(10, 45, 730, 255);
		Tabla = new javax.swing.JTable();
		Tabla.setSelectionBackground(Color.WHITE);
		Tabla.setMaximumSize(new Dimension(11, 5));
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(true);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {},
				new String[] { "Inventario", "Modelo", "Marca", "Categoria", "Contenido", "Retencion",
						"Nombre plataforma", "Fecha Respaldo", "Fecha Expiracion", "Fecha Ultima Verificacion ", "Pais",
						"Ubicacion", "Destino", "Valija", "Continuacion", "Observaciones", "Solicitado", "Responsable",
						"Servidor", "Lugar Requerido", "Mes y A�o", "Estado" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, true, false, true, true, true,
					false, false, false, true, true, true, true, true, false, true, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};

		Tabla.setModel(mod);
		Tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(7).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(8).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
		Tabla.getColumnModel().getColumn(10).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(12).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(13).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(14).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(15).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(16).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(17).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(18).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(19).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(20).setPreferredWidth(140);
		Tabla.getColumnModel().getColumn(21).setPreferredWidth(100);
		jScrollPane1.setViewportView(Tabla);
		contentPane.setLayout(null);

		contentPane.add(jScrollPane1);
		jScrollPane1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { Tabla }));

		JButton btnBuscar = new JButton("Buscar Codigo");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Cod = txtCod.getText().trim().toUpperCase();
				if (Cod.length() == 0) {
					JOptionPane.showMessageDialog(null, "Ingrese Codigo");
					return;
				}
				boolean valida = buscarinv(Cod);
				if (valida) {

				} else {
					boolean valida2 = buscarparte(Cod);
					if (valida2) {

					} else {
						buscarfin(Cod);
					}

				}

				txtCont.setText("" + cont);

				txtCod.setText("");

			}

		});
		btnBuscar.setBounds(164, 311, 139, 23);
		contentPane.add(btnBuscar);

		txtCod = new JTextField();
		txtCod.setBounds(20, 312, 142, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limipar();
			}
		});
		btnLimpiar.setBounds(574, 11, 89, 23);
		contentPane.add(btnLimpiar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnVolver.setBounds(621, 541, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblCintasEncontradas = new JLabel("Cintas Encontradas");
		lblCintasEncontradas.setBounds(473, 314, 128, 16);
		contentPane.add(lblCintasEncontradas);

		txtCont = new JTextField();
		txtCont.setEditable(false);
		txtCont.setBounds(611, 311, 69, 22);
		contentPane.add(txtCont);
		txtCont.setColumns(10);
		txtCont.setText("" + cont);

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				mod.removeRow(Tabla.getSelectedRow());

				cont = cont - 1;
				txtCont.setText("" + cont);
			}
		});
		btnQuitar.setBounds(468, 10, 94, 24);
		contentPane.add(btnQuitar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PropertyConfigurator.configure("log4j.properties");

					int plat = cboPlata.getSelectedIndex() + 1;
					int pais = cboPais.getSelectedIndex() + 1;
					int ubi = cboubicac.getSelectedIndex() + 1;
					int destino = cboDesti.getSelectedIndex() + 1;
					int serv = cboServ.getSelectedIndex() + 1;
					String fech = sdf.format(fecha.getTime());

					int Anio = Integer.parseInt(txtAnio.getText().trim());

					if (Anio >= 1980 && Anio <= 2150) {
						
					}else {
						JOptionPane.showMessageDialog(null, "A�o Fuera de rango");
						return;
					}

					
					String MesAnio = cboMes.getSelectedItem() + " - " + Anio;
					String Estado = cboEstado.getSelectedItem() + "";

					DefaultTableModel tm = (DefaultTableModel) Tabla.getModel();

					for (int i = 0; i < Tabla.getModel().getRowCount(); i++) {

						Inventario inv = new Inventario();
						String cod = String.valueOf(tm.getValueAt(i, 0)).toUpperCase();
						String cont = String.valueOf(((String) tm.getValueAt(i, 4)).toUpperCase()) + " ";
						inv.setContenido(cont);
						String reten = String.valueOf(((String) tm.getValueAt(i, 5)).toUpperCase()) + " ";
						inv.setRetencion(reten);
						String valija = String.valueOf(((String) tm.getValueAt(i, 13)).toUpperCase()) + " ";
						inv.setValija(valija);
						String conti = String.valueOf(((String) tm.getValueAt(i, 14)).toUpperCase()) + " ";
						inv.setContinuacion(conti);
						String obser = String.valueOf(((String) tm.getValueAt(i, 15)).toUpperCase()) + " ";
						inv.setObservaciones(obser);
						String Solici = String.valueOf(((String) tm.getValueAt(i, 16)).toUpperCase()) + " ";
						inv.setSolicitado(Solici);
						String resp = String.valueOf(((String) tm.getValueAt(i, 17)).toUpperCase()) + " ";
						inv.setResponsable(resp);
						String Luga = String.valueOf(((String) tm.getValueAt(i, 19)).toUpperCase()) + " ";
						inv.setLugar_Requerido(Luga);

						boolean valida = ActualizarInventario(inv, cod, plat,  fech, pais, ubi, destino, serv,
								MesAnio, Estado);
						if (valida) {
							log.warn(sfd2.format(fecha.getTime()) + " Se modifica la cinta " + cod);
						}

					}
					JOptionPane.showMessageDialog(null, "Se modificoaron Las tablas");

					cboDesti.setSelectedIndex(0);
					cboPais.setSelectedIndex(0);
					cboServ.setSelectedIndex(0);
					cboubicac.setSelectedIndex(0);
					cboPlata.setSelectedIndex(0);
					cboEstado.setSelectedIndex(0);
					SimpleDateFormat sd2 = new SimpleDateFormat("MM");
					cboMes.setSelectedIndex(Integer.parseInt(sd2.format(fecha.getTime()))-1);
					limipar();

				} catch (Exception ex) {
					log.warn(sfd2.format(fecha.getTime()) + " -> " + ex.getMessage());

				}
			}
		});
		btnModificar.setBounds(45, 518, 120, 23);
		contentPane.add(btnModificar);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(321, 412, 66, 14);
		contentPane.add(lblServidor);

		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setBounds(45, 412, 62, 14);
		contentPane.add(lblUbicacion);

		JLabel lblDestinoActual = new JLabel("Destino Actual");
		lblDestinoActual.setBounds(321, 357, 94, 14);
		contentPane.add(lblDestinoActual);

		JLabel lblPlataforma = new JLabel("Plataforma");
		lblPlataforma.setBounds(45, 357, 89, 14);
		contentPane.add(lblPlataforma);

		cboPlata = new JComboBox<String>();
		cboPlata.setBounds(144, 354, 138, 20);
		contentPane.add(cboPlata);

		cboubicac = new JComboBox<String>();
		cboubicac.setBounds(144, 409, 138, 20);
		contentPane.add(cboubicac);

		cboDesti = new JComboBox<String>();
		cboDesti.setBounds(452, 354, 149, 20);
		contentPane.add(cboDesti);

		cboServ = new JComboBox<String>();
		cboServ.setBounds(452, 409, 149, 20);
		contentPane.add(cboServ);

		cboPais = new JComboBox<String>();
		cboPais.setBounds(144, 454, 138, 20);
		contentPane.add(cboPais);

		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(45, 457, 46, 14);
		contentPane.add(lblPais);

		lblMesYAo = new JLabel("Mes y A\u00F1o ");
		lblMesYAo.setBounds(321, 457, 94, 14);
		contentPane.add(lblMesYAo);

		cboMes = new JComboBox<String>();
		cboMes.setModel(new DefaultComboBoxModel<String>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO",
				"JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
		cboMes.setBounds(454, 454, 149, 20);
		contentPane.add(cboMes);

		txtAnio = new JTextField();
		txtAnio.setBounds(624, 454, 56, 20);
		contentPane.add(txtAnio);
		txtAnio.setColumns(10);

		cboEstado = new JComboBox<Object>();
		cboEstado.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "DISPONIBLE", "ALMACENADO", "EN TRANCITO" }));
		cboEstado.setBounds(452, 506, 149, 20);
		contentPane.add(cboEstado);

		lblEstadoCinta = new JLabel("Estado Cinta");
		lblEstadoCinta.setBounds(321, 509, 81, 14);
		contentPane.add(lblEstadoCinta);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { jScrollPane1, Tabla }));
		CargarCbos();

	}

	private boolean buscarinv(String cod) {
		try {
			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio,\r\n"
					+ "	   Inventario.estado\r\n" + "from inventario \r\n" + "inner join Cinta\r\n"
					+ "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n" + "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  = ? AND Estado <> 'DE BAJA' ";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, cod);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			if (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
				cont++;
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private boolean buscarparte(String cod) {
		try {

			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio,\r\n"
					+ "	   Inventario.estado\r\n" + "from inventario \r\n" + "inner join Cinta\r\n"
					+ "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n" + "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  LIKE '" + cod + "%' AND Estado <> 'DE BAJA';";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
				cont++;
			}
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void buscarfin(String cod) {
		try {

			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio,\r\n"
					+ "	   Inventario.estado\r\n" + "from inventario \r\n" + "inner join Cinta\r\n"
					+ "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n" + "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  LIKE '%" + cod + "' AND Estado <> 'DE BAJA';";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
				cont++;
			}
			

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public boolean ActualizarInventario(Inventario inv, String cod, int plat,  String fechUl, int pais,
			int ubi, int dest, int serv, String mesAni, String esta) {

		try {
			String sql = "update Inventario \n" + "set Contenido = ?,\n" + "retencion = ?,\n"
					+ "Plataforma_idPlataforma = ?,\n"  + "Fecha_ultim = ?,\n"
					+ "Pais_idPais = ?,\n" + "Ubicacion_Bodega = ?,\n" + "Destino_Actual = ?,\n" + "Valija = ?,\n"
					+ "Continuacion = ?,\n" + "Observaciones = ?,\n" + "Solicitado = ?,\n" + "Responsable = ?,\n"
					+ "Servidor_idServidor =?,\n" + "Lugar_Requerido = ?,\n" + "mes_anio = ?,\n" + "Estado = ?\n"
					+ "where idInventario = ?";

			PreparedStatement pstm = conn.prepareCall(sql);

			pstm.setString(1, inv.getContenido());
			pstm.setString(2, inv.getRetencion());
			pstm.setInt(3, plat);
			pstm.setString(4, fechUl);
			pstm.setInt(5, pais);
			pstm.setInt(6, ubi);
			pstm.setInt(7, dest);
			pstm.setString(8, inv.getValija());
			pstm.setString(9, inv.getContinuacion());
			pstm.setString(10, inv.getObservaciones());
			pstm.setString(11, inv.getSolicitado());
			pstm.setString(12, inv.getResponsable());
			pstm.setInt(13, serv);
			pstm.setString(14, inv.getLugar_Requerido());
			pstm.setString(15, mesAni);
			pstm.setString(16, esta);
			pstm.setString(17, cod);

			int x = pstm.executeUpdate();
			return x > 0 ? true : false;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void CargarCbos() {

		SimpleDateFormat sd = new SimpleDateFormat("YYYY");
		SimpleDateFormat sd2 = new SimpleDateFormat("MM");
		cboMes.setSelectedIndex(Integer.parseInt(sd2.format(fecha.getTime()))-1);

		txtAnio.setText(sd.format(fecha.getTime()));

		Cont_Pais CP = new Cont_Pais();
		Cont_Plataforma CPL = new Cont_Plataforma();
		Cont_Servidor CS = new Cont_Servidor();
		Cont_Ubicacion CU = new Cont_Ubicacion();

		ArrayList<Pais> listpais = new ArrayList<>();
		listpais = CP.listar();
		ArrayList<Servidor> listServ = new ArrayList<>();
		listServ = CS.listar();
		ArrayList<Plataforma> listPlat = new ArrayList<>();
		listPlat = CPL.listar();
		ArrayList<Ubicacion> listUbi = new ArrayList<>();
		listUbi = CU.listUBI();

		for (Ubicacion ubicacion : listUbi) {
			cboubicac.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
			cboDesti.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
		}

		for (Plataforma plat : listPlat) {
			cboPlata.addItem(plat.getIdPlataforma() + " - " + plat.getNomPlataforma());

		}

		for (Servidor sserv : listServ) {
			cboServ.addItem(sserv.getIdServidor() + " - " + sserv.getNombre());
		}

		for (Pais pai : listpais) {

			cboPais.addItem(pai.getIdPais() + " - " + pai.getNombre());
		}

	}

	private void limipar() {
		try {
			while (mod.getRowCount() > 0) {

				mod.removeRow(0);
				cont = 0;
				txtCont.setText("" + cont);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}
}
