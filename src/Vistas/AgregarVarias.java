package Vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import Class.Cinta;
import Class.Inventario;
import Controlador.Cont_Cinta;
import Controlador.Cont_Inventario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
public class AgregarVarias extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLetras;
	private JTextField txtRangoIni;
	private JTextField txtCant;
	private final static Logger log = Logger.getLogger(AgregarVarias.class);
	private Calendar fecha = Calendar.getInstance();
	private SimpleDateFormat sfd2 = new SimpleDateFormat(" [dd/MM/YYYY] - [HH:mm:ss]");
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JComboBox<String> cboCinta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarVarias frame = new AgregarVarias();
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
	public AgregarVarias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLetras = new JLabel("Nomenclatura cinta");
		lblLetras.setBounds(10, 11, 132, 20);
		contentPane.add(lblLetras);

		txtLetras = new JTextField();
		txtLetras.setBounds(190, 11, 86, 20);
		contentPane.add(txtLetras);
		txtLetras.setColumns(10);

		JLabel lblRangoInicial = new JLabel("Rango Inicial");
		lblRangoInicial.setBounds(10, 42, 120, 20);
		contentPane.add(lblRangoInicial);

		txtRangoIni = new JTextField();
		txtRangoIni.setBounds(190, 42, 86, 20);
		contentPane.add(txtRangoIni);
		txtRangoIni.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad de cintas");
		lblCantidad.setBounds(10, 76, 132, 17);
		contentPane.add(lblCantidad);

		txtCant = new JTextField();
		txtCant.setBounds(190, 73, 86, 20);
		contentPane.add(txtCant);
		txtCant.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					PropertyConfigurator.configure("log4j.properties");
					Inventario inv = new Inventario();
					Cont_Inventario cn = new Cont_Inventario();
					String Letras = txtLetras.getText().toUpperCase();
					if(Letras.length()>2) {
						JOptionPane.showMessageDialog(null, "Nomenclatura debe ser igual a 2 caracteres");
						return;
					}
					
					int num = Integer.parseInt(txtRangoIni.getText());
					int can = Integer.parseInt(txtCant.getText());
					
					int cinta = cboCinta.getSelectedIndex();
					
					ArrayList<Inventario> list = new ArrayList<>();
					
					
					String Ultima ="";
					for (int ex = 0; ex < can; ex++) {
						String id = "" + (num + ex);

						if (id.trim().length() < 4) {
							if (id.trim().length() == 3) {
								id = "0" + id.trim();
							} else if (id.trim().length() == 2) {
								id = "00" + id.trim();
							} else if (id.trim().length() == 1) {
								id = "000" + id.trim();
							}
						} else {
							id = id.trim();
						}
						
						inv.setIdInventario(Letras+id);
						inv.setCinta_idCinta(cinta);
						inv.setContenido("");
						inv.setRetencion("");
						inv.setContinuacion("");
						inv.setObservaciones("");
						inv.setResponsable("");
						inv.setSolicitado("");
						inv.setLugar_Requerido("");
						inv.setMes_anio("");
						inv.setValija("");
						inv.setPlataforma(1);
						inv.setPais_idPais(1);
						inv.setFecha_Plataforma(sdf.format(fecha.getTime()));
						inv.setFecha_Exp(sdf.format(fecha.getTime()));
						inv.setFecha_ultim(sdf.format(fecha.getTime()));
						inv.setFecha_Exp(sdf.format(fecha.getTime()));
						inv.setUbicacion_Bodega(1);
						inv.setDestino_Actual(1);
						inv.setServidor(1);
						inv.setEstado("DISPONIBLE");
						
						for (Inventario inventario : list) {
							if(inv.getIdInventario().equals(inventario.getIdInventario())) {
								JOptionPane.showMessageDialog(null, "Cinta ya Ingresada " + inventario.getIdInventario());
							return;
							}
						}
						
						Boolean valida = cn.ingresarInventario(inv);
						
						if(valida) {
							log.info(sfd2.format(fecha.getTime()) + " Se Ingresa nueva Cinta Disponibles" + inv.getIdInventario());
						}	
						
						Ultima = inv.getIdInventario();
					}
					JOptionPane.showMessageDialog(null, "Cintas Guardadas Hats la  " +Ultima );
					
					txtCant.setText("");
					txtLetras.setText("");
					txtRangoIni.setText("");

				} catch (Exception e) {
					log.error(sfd2.format(fecha.getTime()) + " -> "+ e.getMessage());
					throw new IllegalArgumentException(e.getMessage());
				}

			}
		});
		btnAgregar.setBounds(34, 135, 120, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(322, 135, 89, 23);
		contentPane.add(btnVolver);
		
		cboCinta = new JComboBox<String>();
		cboCinta.setBounds(357, 11, 113, 20);
		contentPane.add(cboCinta);
		
		JLabel lblCinta = new JLabel("Cinta");
		lblCinta.setBounds(286, 14, 46, 14);
		contentPane.add(lblCinta);
		
		CargarCbos();
	}
	
	private void CargarCbos() {


		cboCinta.addItem("Seleccionar");

		Cont_Cinta CC = new Cont_Cinta();
		ArrayList<Cinta> listCin = new ArrayList<>();
		listCin = CC.listar();
		
		for (Cinta cin : listCin) {
			cboCinta.addItem(cin.getIdCinta() + " - " + cin.getModelo() + " - " + cin.getMarca());
			
		}

		

	}

}
