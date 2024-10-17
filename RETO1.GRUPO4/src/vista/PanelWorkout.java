package vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelWorkout extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnHistorialWorkouts;
	private JButton btnPerfil;

	/**
	 * Create the panel.
	 */
	public PanelWorkout() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);
		
	    btnHistorialWorkouts = new JButton("Historial Workouts");
		btnHistorialWorkouts.setBounds(706, 111, 197, 23);
		add(btnHistorialWorkouts);
		
	    btnPerfil = new JButton("Perfil");
		btnPerfil.setBounds(851, 24, 89, 23);
		add(btnPerfil);
	}



	public JButton getBtnHistorialWorkouts() {
		return btnHistorialWorkouts;
	}

	public void setBtnHistorialWorkouts(JButton btnHistorialWorkouts) {
		this.btnHistorialWorkouts = btnHistorialWorkouts;
	}

	public JButton getBtnPerfil() {
		return btnPerfil;
	}

	public void setBtnPerfil(JButton btnPerfil) {
		this.btnPerfil = btnPerfil;
	}
}
