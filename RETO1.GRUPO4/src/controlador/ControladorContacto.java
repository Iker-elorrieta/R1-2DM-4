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
	private Clientes clienteActual;
	private Clientes cliente;

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

		// Acciones del panel Inicio Sesion
		this.vistaPrincipal.getPanelInicioSesion().getBtnInicioSesion().addActionListener(this);
		this.vistaPrincipal.getPanelInicioSesion().getBtnInicioSesion()
				.setActionCommand(Principal.enumAcciones.INICIAR_SESION.toString());

		this.vistaPrincipal.getPanelInicioSesion().getBtnRegistrar().addActionListener(this);
		this.vistaPrincipal.getPanelInicioSesion().getBtnRegistrar()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_REGISTRO.toString());

		// Acciones del panel Registro
		this.vistaPrincipal.getPanelRegistro().getBtnDarDeAlta().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnDarDeAlta()
				.setActionCommand(Principal.enumAcciones.REGISTRO.toString());

		// Acciones del men�
		this.vistaPrincipal.getPanelWorkout().getBtnPerfil().addActionListener(this);
		this.vistaPrincipal.getPanelWorkout().getBtnPerfil()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_PERFIL.toString());

		this.vistaPrincipal.getPanelPerfil().getBtnModificarPerfil().addActionListener(this);
		this.vistaPrincipal.getPanelPerfil().getBtnModificarPerfil()
				.setActionCommand(Principal.enumAcciones.MODIFICAR_PERFIL.toString());

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
					JOptionPane.showMessageDialog(vistaPrincipal, "Inicio de sesión correcto.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
				} else {
					// Mostrar mensaje de error
					JOptionPane.showMessageDialog(vistaPrincipal, "Email o contraseña incorrectos.", "Error",
							JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(vistaPrincipal, "El usuario se ha registrado correctamente.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
				} else {
					JOptionPane.showMessageDialog(vistaPrincipal,
							"No se ha podidio registrar al usuario, email ya existente.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case CARGAR_PANEL_PERFIL:
			this.mCargarPerfil();
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_PERFIL);
			break;
		case MODIFICAR_PERFIL:
			try {
				this.mModificarPerfil();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUT);
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

	    cliente = new Clientes();
		boolean clienteValido = cliente.mVerificarCliente(email, contrasena);

		if (clienteValido) {
			// Si la autenticación es exitosa, recuperamos los datos del cliente
			this.clienteActual = cliente.mObtenerDatosCliente(email);
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
		boolean registroValido = cliente.mVerificarRegistroValido(email);

		if (registroValido) {
			try {
				// Si el email no existe, insertar el nuevo cliente en Firestore
				cliente.mInsertarCliente();
				
				// Actualizar el clienteActual con los datos nuevos
		        this.clienteActual = cliente;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false; // El email ya existe o hubo un error
	}

	private void mCargarPerfil() {
		if (clienteActual != null) {
			vistaPrincipal.getPanelPerfil().getTxtFNombre().setText(clienteActual.getNombre());
			vistaPrincipal.getPanelPerfil().getTxtFApellido().setText(clienteActual.getApellido());
			vistaPrincipal.getPanelPerfil().getTxtFEmail().setText(clienteActual.getEmail());
			vistaPrincipal.getPanelPerfil().getTxtFContrasena().setText(clienteActual.getContrasena());
			vistaPrincipal.getPanelPerfil().getFechaNaCalendar().setDate(clienteActual.getFechaNa());
		}
	}

	private void mModificarPerfil() throws Exception {
	    String nombre = vistaPrincipal.getPanelPerfil().getTxtFNombre().getText();
	    String apellido = vistaPrincipal.getPanelPerfil().getTxtFApellido().getText();
	    String email = vistaPrincipal.getPanelPerfil().getTxtFEmail().getText();
	    String contrasena = vistaPrincipal.getPanelPerfil().getTxtFContrasena().getText();
	    Date fechaNa = vistaPrincipal.getPanelPerfil().getFechaNaCalendar().getDate();

	    // Comprobar si el email ha cambiado
	    if (!email.equals(clienteActual.getEmail())) {
	        // Verificar si el nuevo email ya está registrado
	        boolean emailValido = cliente.mVerificarRegistroValido(email);
	        if (!emailValido) {
	            JOptionPane.showMessageDialog(vistaPrincipal, "El email ya está en uso.", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }

	    // Crear un objeto cliente con los datos modificados
	    Clientes clienteModificado = new Clientes(nombre, apellido, email, contrasena, fechaNa);

	    // Actualizar en Firestore
	    try {
	        clienteModificado.mModificarPerfil(nombre, apellido, email, contrasena, fechaNa);

	        // Actualizar el clienteActual con los datos nuevos
	        this.clienteActual = clienteModificado;

	        // Mostrar mensaje de confirmación al usuario
	        JOptionPane.showMessageDialog(vistaPrincipal, "Perfil modificado correctamente.", "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception e) {
	        // Mostrar mensaje de error si ocurre algún problema
	        JOptionPane.showMessageDialog(vistaPrincipal, "Error al modificar el perfil.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}