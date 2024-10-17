package vista;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;


public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtFNombre;
	private JTextField txtFApellido;
	private JTextField txtFEmail;
	private JButton btnDarDeAlta;
	private JTextField txtFContrasena;
	private JLabel lblContrasena;
	private JLabel lblFechaNa;
	private JDateChooser FechaNaCalendar;

	/**
	 * Create the panel.
	 */
	public PanelRegistro() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(155, 237, 101, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(155, 268, 101, 14);
		add(lblApellido);

		txtFNombre = new JTextField();
		txtFNombre.setBounds(266, 234, 200, 20);
		add(txtFNombre);
		txtFNombre.setColumns(10);

		txtFApellido = new JTextField();
		txtFApellido.setBounds(266, 265, 200, 20);
		add(txtFApellido);
		txtFApellido.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(155, 168, 97, 14);
		add(lblEmail);

		txtFEmail = new JTextField();
		txtFEmail.setBounds(266, 165, 200, 20);
		add(txtFEmail);
		txtFEmail.setColumns(10);

		btnDarDeAlta = new JButton("Dar de alta");
		btnDarDeAlta.setBounds(149, 361, 199, 23);
		add(btnDarDeAlta);

		txtFContrasena = new JTextField();
		txtFContrasena.setBounds(266, 203, 200, 20);
		add(txtFContrasena);
		txtFContrasena.setColumns(10);

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(155, 206, 101, 14);
		add(lblContrasena);

		lblFechaNa = new JLabel("Fecha Nacimiento:");
		lblFechaNa.setBounds(155, 310, 137, 14);
		add(lblFechaNa);
		
		FechaNaCalendar = new JDateChooser();
		FechaNaCalendar.setBounds(266, 304, 200, 20);
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

	public JButton getBtnDarDeAlta() {
		return btnDarDeAlta;
	}

	public void setBtnDarDeAlta(JButton btnDarDeAlta) {
		this.btnDarDeAlta = btnDarDeAlta;
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
