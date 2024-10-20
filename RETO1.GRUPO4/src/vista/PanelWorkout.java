package vista;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;

public class PanelWorkout extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton btnHistorialWorkouts;
    private JButton btnPerfil;
    private JComboBox<Integer> cmbNivel;  
    private JList<String> listWorkout;   
    private JList<String> listDetallesWorkout; 

    /**
     * Create the panel.
     */
    public PanelWorkout() {
        setBackground(Color.LIGHT_GRAY);
        setBounds(10, 71, 966, 481);
        setLayout(null);

        // Botón de historial de workouts
        btnHistorialWorkouts = new JButton("Historial Workouts");
        btnHistorialWorkouts.setBounds(741, 430, 197, 23);
        add(btnHistorialWorkouts);

        // Botón de perfil
        btnPerfil = new JButton("Perfil");
        btnPerfil.setBounds(849, 28, 89, 23);
        add(btnPerfil);

        // JList para mostrar los workouts
        listWorkout = new JList<>();
        listWorkout.setBounds(91, 117, 187, 291);
        add(listWorkout);

        // JList para mostrar los detalles del workout seleccionado
        listDetallesWorkout = new JList<>();
        listDetallesWorkout.setBounds(288, 117, 650, 291);
        add(listDetallesWorkout);

        // Etiqueta de "Workout"
        JLabel lblWorkout = new JLabel("Workout");
        lblWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblWorkout.setBounds(91, 92, 105, 14);
        add(lblWorkout);

        // Etiqueta de "Detalles del workout"
        JLabel lblDetallesWorkout = new JLabel("Detalles del workout");
        lblDetallesWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDetallesWorkout.setBounds(288, 92, 187, 14);
        add(lblDetallesWorkout);

        // ComboBox para seleccionar el nivel
        cmbNivel = new JComboBox<>();
        cmbNivel.setBounds(91, 48, 143, 22);
        add(cmbNivel);
    }

    // Getters y Setters para los componentes

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

    public JComboBox<Integer> getCmbNivel() {
		return cmbNivel;
	}

	public void setCmbNivel(JComboBox<Integer> cmbNivel) {
		this.cmbNivel = cmbNivel;
	}

	public JList<String> getListWorkout() {
        return listWorkout;
    }

    public void setListWorkout(JList<String> listWorkout) {
        this.listWorkout = listWorkout;
    }

    public JList<String> getListDetallesWorkout() {
        return listDetallesWorkout;
    }

    public void setListDetallesWorkout(JList<String> listDetallesWorkout) {
        this.listDetallesWorkout = listDetallesWorkout;
    }
}
