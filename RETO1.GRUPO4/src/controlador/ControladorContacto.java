package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import modelo.Clientes;
import vista.Principal;

public class ControladorContacto implements ActionListener, ListSelectionListener {

	private vista.Principal vistaPrincipal;

	/*
	 * *** CONSTRUCTORES ***
	 */

	/*
	 * Contructor del objeto controlador
	 * 
	 * @param vistaPrincipal Objeto vista.
	 */
	public ControladorContacto(vista.Principal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;

		this.inicializarControlador();

	}

	private void inicializarControlador() {

		// Acciones del men�
		this.vistaPrincipal.getBtnUsuario().addActionListener(this);
		this.vistaPrincipal.getBtnUsuario().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_REGISTRO.toString());

		// Acciones del panel Inicio Sesion
		this.vistaPrincipal.getPanelInicioSesion().getBtnInicioSesion().addActionListener(this);
		this.vistaPrincipal.getPanelInicioSesion().getBtnInicioSesion()
				.setActionCommand(Principal.enumAcciones.INICIAR_SESION.toString());

		// Acciones del panel Registro
		this.vistaPrincipal.getPanelRegistro().getBtnDarDeAlta().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnDarDeAlta()
				.setActionCommand(Principal.enumAcciones.REGISTRO.toString());
	}

	/*** Tratamiento de las acciones ***/

	@Override
	public void actionPerformed(ActionEvent e) {

		Principal.enumAcciones accion = Principal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case CARGAR_PANEL_CONSULTA:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_CONSULTA);
			break;
		case CARGAR_PANEL_INICIOSESION:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_INICIOSESION);
			break;
		case INICIAR_SESION:
	        // Si el inicio de sesión es correcto, redirigir al panel de registro
	        try {
				if (this.mIniciarSesion()) {
				    // Mostrar mensaje de éxito
				    JOptionPane.showMessageDialog(vistaPrincipal, "Inicio de sesión correcto.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				    this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
				} else {
				    // Mostrar mensaje de error
				    JOptionPane.showMessageDialog(vistaPrincipal, "Email o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        break;
		case CARGAR_PANEL_REGISTRO:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_REGISTRO);
			break;
		case REGISTRO:
	        try {
				if (this.mRegistro()) {
				    JOptionPane.showMessageDialog(vistaPrincipal, "El usuario se ha registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				    this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
				} else {
				    JOptionPane.showMessageDialog(vistaPrincipal, "No se ha podidio registrar al usuario, email ya existente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        break;
		case CARGAR_PANEL_WORKOUT:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
			break;
		default:
			break;

		}
	}

	private boolean mIniciarSesion() throws Exception {
		// TODO Auto-generated method stub
		String email = this.vistaPrincipal.getPanelInicioSesion().getTxtFEmail().getText();
		String contrasena = this.vistaPrincipal.getPanelInicioSesion().getTxtFContrasena().getText();
		
		Clientes cliente = new Clientes();
		boolean clienteValido = cliente.verificarCliente(email, contrasena);
		
		if (clienteValido) {
	        return true;
	    } else {
	        return false;
	    }
	}

	private boolean mRegistro() throws Exception {
	    String email = this.vistaPrincipal.getPanelRegistro().getTxtFEmail().getText();
	    String contrasena = this.vistaPrincipal.getPanelRegistro().getTxtFContrasena().getText();
	    String nombre = this.vistaPrincipal.getPanelRegistro().getTxtFNombre().getText();
	    String apellido = this.vistaPrincipal.getPanelRegistro().getTxtFApellido().getText();
	    Date fechaNa = this.vistaPrincipal.getPanelRegistro().getFechaNaCalendar().getDate();

	    Clientes cliente = new Clientes(nombre, apellido, email, contrasena, fechaNa);

	    // Verificar si el email ya está registrado
	    boolean registroValido = cliente.verificarRegistroValido(email);

	    if (registroValido) {
	        try {
	            // Si el email no existe, insertar el nuevo cliente en Firestore
	            cliente.mInsertarCliente();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return false;  // El email ya existe o hubo un error
	}


	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
