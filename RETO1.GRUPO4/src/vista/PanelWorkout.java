package vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelWorkout extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelWorkout() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);
		
		JButton btnHistorialWorkouts = new JButton("Historial Workouts");
		btnHistorialWorkouts.setBounds(706, 43, 197, 23);
		add(btnHistorialWorkouts);


	}
}
