package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Acciones
	public static enum enumAcciones {
		CARGAR_PANEL_CONSULTA, 
		CARGAR_PANEL_INICIOSESION, 
		CARGAR_PANEL_REGISTRO,
		CARGAR_PANEL_PERFIL,
		CARGAR_PANEL_WORKOUT,


		INICIAR_SESION, 
		REGISTRO,
		MODIFICAR_PERFIL,

	}

	private JPanel panelContenedor;
	private PanelInicioSesion panelInicioSesion;
	private PanelRegistro panelRegistro;
	private PanelWorkout panelWorkout;
	private PanelPerfil panelPerfil;
	
	private JButton btnUsuario; 

	public Principal() {

		// Panel que contiene todo el contenido de la ventana
		mCrearPanelContenedor();

		// Panel izquierdo, contiene el men� del programa.
		mCrearPanelMenu();

		// Panel que contiene el listado de contactos.
		mCrearPanelInicioSesion();

		// Panel que contiene el listado de insertar.
		mCrearPanelRegistro();

		// Panel que contiene el listado de insertar.
		mCrearPanelPerfil();
				
		// Panel que contiene el listado de insertar.
		mCrearPanelWorkout();

	}

	// *** Creaci�n de paneles ***
	private void mCrearPanelContenedor() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenedor);
		panelContenedor.setLayout(null);

	}

	private void mCrearPanelMenu() {

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(10, 11, 966, 44);
		panelContenedor.add(panelMenu);
		panelMenu.setLayout(null);

	    btnUsuario = new JButton("Perfil");
		btnUsuario.setBounds(10, 11, 89, 23);
		panelMenu.add(btnUsuario);
	}

	private void mCrearPanelInicioSesion() {
		panelInicioSesion = new PanelInicioSesion();
		panelInicioSesion.setBounds(10, 71, 966, 481);
		panelContenedor.add(panelInicioSesion);
	}

	private void mCrearPanelRegistro() {
		panelRegistro = new PanelRegistro();
		panelRegistro.setBounds(10, 71, 966, 481);
		panelContenedor.add(panelRegistro);
		panelRegistro.setVisible(false);
	}
	
	private void mCrearPanelPerfil() {
		panelPerfil = new PanelPerfil();
		panelPerfil.setBounds(10, 71, 966, 481);
		panelContenedor.add(panelPerfil);
		panelPerfil.setVisible(false);
	}
	
	private void mCrearPanelWorkout() {
		panelWorkout = new PanelWorkout();
		panelWorkout.setBounds(10, 71, 966, 481);
		panelContenedor.add(panelWorkout);
		panelWorkout.setVisible(false);
	}

	// *** FIN creaci�n de paneles ***

	public void mVisualizarPaneles(enumAcciones panel) {

		panelInicioSesion.setVisible(false);
		panelRegistro.setVisible(false);
		panelWorkout.setVisible(false);

		switch (panel) {
        case CARGAR_PANEL_INICIOSESION:
            panelInicioSesion.setVisible(true);
            break;
        case CARGAR_PANEL_REGISTRO:
            panelRegistro.setVisible(true);
            break;
        case CARGAR_PANEL_PERFIL:
            panelPerfil.setVisible(true);
            break;
        case CARGAR_PANEL_WORKOUT:
            panelWorkout.setVisible(true);
            break;
        // Agregar otros casos si es necesario
        default:
            break;
    }
}

	// *** M�todos get-set ***
	public JPanel getPanelContenedor() {
		return panelContenedor;
	}

	public void setPanelContenedor(JPanel panelContenedor) {
		this.panelContenedor = panelContenedor;
	}
	
	public JButton getBtnUsuario() {
		return btnUsuario;
	}

	public void setBtnUsuario(JButton btnUsuario) {
		this.btnUsuario = btnUsuario;
	}

	/////////////////////////// PANELES //////////////////////////////////////
	public PanelInicioSesion getPanelInicioSesion() {
		return panelInicioSesion;
	}

	public void setPanelInicioSesion(PanelInicioSesion panelInicioSesion) {
		this.panelInicioSesion = panelInicioSesion;
	}

	public PanelRegistro getPanelRegistro() {
		return panelRegistro;
	}

	public void setPanelRegistro(PanelRegistro panelRegistro) {
		this.panelRegistro = panelRegistro;
	}

	public PanelWorkout getPanelWorkout() {
		return panelWorkout;
	}

	public void setPanelWorkout(PanelWorkout panelWorkout) {
		this.panelWorkout = panelWorkout;
	}

	public PanelPerfil getPanelPerfil() {
		return panelPerfil;
	}

	public void setPanelPerfil(PanelPerfil panelPerfil) {
		this.panelPerfil = panelPerfil;
	}
}
