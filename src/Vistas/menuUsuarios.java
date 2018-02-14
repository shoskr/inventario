package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


public class menuUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuUsuarios frame = new menuUsuarios();
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
	public menuUsuarios() {
		
		
		setBackground(Color.WHITE);
		setTitle("Menu  Administrador  de Usuarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(menuUsuarios.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 233);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAgregarUsuario = new JButton("Agregar Usuario");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AgregarUsuario AU = new AgregarUsuario();
				AU.setVisible(true);
				
				
				
			}
		});
		btnAgregarUsuario.setBounds(72, 30, 180, 23);
		contentPane.add(btnAgregarUsuario);

		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarUsuario Eli = new EliminarUsuario();
				Eli.setVisible(true);
				
			}
		});
		btnEliminarUsuario.setBounds(72, 64, 180, 23);
		contentPane.add(btnEliminarUsuario);

		JButton btnNewButton = new JButton("Modificar Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario mod = new ModificarUsuario();
				mod.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setBounds(72, 98, 180, 23);
		contentPane.add(btnNewButton);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnSalir.setBounds(108, 147, 112, 23);
		contentPane.add(btnSalir);
	}
}
