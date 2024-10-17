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
		lblNombre.setBounds(255, 218, 101, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(255, 268, 101, 14);
		add(lblApellido);

		txtFNombre = new JTextField();
		txtFNombre.setBounds(402, 215, 200, 20);
		add(txtFNombre);
		txtFNombre.setColumns(10);

		txtFApellido = new JTextField();
		txtFApellido.setBounds(402, 265, 200, 20);
		add(txtFApellido);
		txtFApellido.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(255, 123, 97, 14);
		add(lblEmail);

		txtFEmail = new JTextField();
		txtFEmail.setBounds(402, 120, 200, 20);
		add(txtFEmail);
		txtFEmail.setColumns(10);

		btnDarDeAlta = new JButton("Dar de alta");
		btnDarDeAlta.setBounds(403, 365, 199, 23);
		add(btnDarDeAlta);

		txtFContrasena = new JTextField();
		txtFContrasena.setBounds(402, 165, 200, 20);
		add(txtFContrasena);
		txtFContrasena.setColumns(10);

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(255, 168, 101, 14);
		add(lblContrasena);

		lblFechaNa = new JLabel("Fecha Nacimiento:");
		lblFechaNa.setBounds(255, 313, 137, 14);
		add(lblFechaNa);
		
		FechaNaCalendar = new JDateChooser();
		FechaNaCalendar.setBounds(402, 313, 200, 20);
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
