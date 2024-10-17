package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


public class PanelInicioSesion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFEmail;
	private JTextField txtFContrasena;
	private JButton btnInicioSesion, btnRegistrar;

	/**
	 * Create the panel.
	 */
	public PanelInicioSesion() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);
		
		txtFEmail = new JTextField();
		txtFEmail.setBounds(154, 131, 221, 20);
		add(txtFEmail);
		txtFEmail.setColumns(10);
		
		txtFContrasena = new JTextField();
		txtFContrasena.setBounds(154, 174, 221, 20);
		add(txtFContrasena);
		txtFContrasena.setColumns(10);
		
		btnInicioSesion = new JButton("Iniciar Sesion");
		btnInicioSesion.setBounds(154, 219, 221, 23);
		add(btnInicioSesion);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(450, 324, 89, 23);
		add(btnRegistrar);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(50, 134, 97, 14);
		add(lblEmail);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(50, 177, 97, 14);
		add(lblContrasena);
		
		JLabel lblRegistro = new JLabel("No tienes cuenta todavia? Registrate en nuestro gimasio.");
		lblRegistro.setBounds(154, 328, 383, 14);
		add(lblRegistro);
			
	}


	
	public JTextField getTxtFEmail() {
		return txtFEmail;
	}
	

	public void setTxtFEmail(JTextField txtFEmail) {
		this.txtFEmail = txtFEmail;
	}


	public JTextField getTxtFContrasena() {
		return txtFContrasena;
	}


	public void setTxtFContrasena(JTextField txtFContrasena) {
		this.txtFContrasena = txtFContrasena;
	}


	public JButton getBtnInicioSesion() {
		return btnInicioSesion;
	}


	public void setBtnInicioSesion(JButton btnInicioSesion) {
		this.btnInicioSesion = btnInicioSesion;
	}


	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}
}
