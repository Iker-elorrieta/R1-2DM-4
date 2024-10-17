package vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class PanelPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtFNombre;
	private JTextField txtFApellido;
	private JTextField txtFEmail;
	private JButton btnModificarPerfil;
	private JTextField txtFContrasena;
	private JLabel lblContrasena;
	private JLabel lblFechaNa;
	private JDateChooser FechaNaCalendar;
	

	/**
	 * Create the panel.
	 */
	public PanelPerfil() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(287, 225, 101, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(287, 268, 101, 14);
		add(lblApellido);

		txtFNombre = new JTextField();
		txtFNombre.setBounds(413, 222, 200, 20);
		add(txtFNombre);
		txtFNombre.setColumns(10);

		txtFApellido = new JTextField();
		txtFApellido.setBounds(413, 265, 200, 20);
		add(txtFApellido);
		txtFApellido.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(287, 147, 97, 14);
		add(lblEmail);

		txtFEmail = new JTextField();
		txtFEmail.setBounds(413, 144, 200, 20);
		add(txtFEmail);
		txtFEmail.setColumns(10);

		btnModificarPerfil = new JButton("Modificar datos");
		btnModificarPerfil.setBounds(413, 363, 199, 23);
		add(btnModificarPerfil);

		txtFContrasena = new JTextField();
		txtFContrasena.setBounds(413, 181, 200, 20);
		add(txtFContrasena);
		txtFContrasena.setColumns(10);

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(287, 184, 101, 14);
		add(lblContrasena);

		lblFechaNa = new JLabel("Fecha Nacimiento:");
		lblFechaNa.setBounds(287, 310, 137, 14);
		add(lblFechaNa);
		
		FechaNaCalendar = new JDateChooser();
		FechaNaCalendar.setBounds(413, 310, 200, 20);
		add(FechaNaCalendar);

		JTextFieldDateEditor editor = (JTextFieldDateEditor) FechaNaCalendar.getDateEditor();

		editor.setEditable(false);
	}

	public JTextField getTxtFNombre() {
		return txtFNombre;
	}

	public void setTxtFNombre(JTextField txtFNombre) {
		this.txtFNombre = txtFNombre;
	}

	public JTextField getTxtFApellido() {
		return txtFApellido;
	}

	public void setTxtFApellido(JTextField txtFApellido) {
		this.txtFApellido = txtFApellido;
	}

	public JTextField getTxtFEmail() {
		return txtFEmail;
	}

	public void setTxtFEmail(JTextField txtFEmail) {
		this.txtFEmail = txtFEmail;
	}

	public JButton getBtnModificarPerfil() {
		return btnModificarPerfil;
	}

	public void setBtnModificarPerfil(JButton btnModificarPerfil) {
		this.btnModificarPerfil = btnModificarPerfil;
	}

	public JTextField getTxtFContrasena() {
		return txtFContrasena;
	}

	public void setTxtFContrasena(JTextField txtFContrasena) {
		this.txtFContrasena = txtFContrasena;
	}

	public JLabel getLblContrasena() {
		return lblContrasena;
	}

	public void setLblContrasena(JLabel lblContrasena) {
		this.lblContrasena = lblContrasena;
	}

	public JLabel getLblFechaNa() {
		return lblFechaNa;
	}

	public void setLblFechaNa(JLabel lblFechaNa) {
		this.lblFechaNa = lblFechaNa;
	}

	public JDateChooser getFechaNaCalendar() {
		return FechaNaCalendar;
	}

	public void setFechaNaCalendar(JDateChooser fechaNaCalendar) {
		FechaNaCalendar = fechaNaCalendar;
	}

}
