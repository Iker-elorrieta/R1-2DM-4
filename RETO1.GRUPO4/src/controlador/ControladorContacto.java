package controlador;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import conexion.Conexion;
import modelo.Clientes;
import modelo.Ejercicios;
import modelo.Series;
import modelo.Workouts;
import vista.Principal;

public class ControladorContacto implements ActionListener {

	private vista.Principal vistaPrincipal;
	private Clientes clienteActual;
	private Clientes cliente;
	private Workouts workout;

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
		
		/*
		 * this.vistaPrincipal.getPanelEliminar().getTablaContactos().getSelectionModel().addListSelectionListener(this);
		this.vistaPrincipal.getPanelEliminar().getTablaContactos().getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/

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
					this.mCargarPanelWorkout();
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
					this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_INICIOSESION);
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
		workout = new Workouts();
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

		Clientes cliente = new Clientes(nombre, apellido, email, contrasena, fechaNa, 0, false);

		if (email.isEmpty() || contrasena.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fechaNa == null) {
			// Muestra un mensaje de error si algún campo está vacío
			JOptionPane.showMessageDialog(vistaPrincipal, "Todos los campos deben estar completos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false; // No proceder con el registro
		}

		// Verificar si el email ya está registrado
		boolean registroValido = cliente.mVerificarRegistroValido(email);

		if (registroValido) {
			try {
				// Si el email no existe, insertar el nuevo cliente en Firestore
				cliente.mInsertarCliente();

				// Actualizar el clienteActual con los datos nuevos
				this.clienteActual = cliente.mObtenerDatosCliente(email);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JOptionPane.showMessageDialog(vistaPrincipal, "No se ha podido registrar al usuario, email ya existente.", "Error", JOptionPane.ERROR_MESSAGE);
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

		// Actualizar el clienteActual con los datos nuevos
		try {
			clienteActual.mModificarPerfil(clienteActual.getIdCliente(), nombre, apellido, email, contrasena, fechaNa);

			// Actualizar el clienteActual con los datos nuevos
			this.clienteActual.setNombre(nombre);
			this.clienteActual.setApellido(apellido);
			this.clienteActual.setEmail(email);
			this.clienteActual.setContrasena(contrasena);
			this.clienteActual.setFechaNa(fechaNa);

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

	private void mCargarPanelWorkout() {
	    try {
	        Clientes cl = this.clienteActual;

	        ////////////// COMBOBOX NIVEL //////////////
	        DefaultComboBoxModel<Integer> nivelClienteModel = new DefaultComboBoxModel<>();
	        int nivelUsuario = (int) cl.getNivelUsuario();
	        for (int i = 0; i <= nivelUsuario; i++) {
	            nivelClienteModel.addElement(i);
	        }
	        vistaPrincipal.getPanelWorkout().getCmbNivel().setModel(nivelClienteModel);

	        ////////////// Cargar workouts //////////////
	        ArrayList<Workouts> listaWorkouts = workout.mCargarWorkouts();
	        HashMap<String, Workouts> workoutMap = new HashMap<>();
	        for (Workouts workout : listaWorkouts) {
	            String workoutId = workout.getIdWorkouts();
	            workoutMap.put(workoutId, workout);
	        }

	        ////////////// Cargar JList //////////////
	        vistaPrincipal.getPanelWorkout().getCmbNivel().addActionListener(e -> {
	            int nivelSeleccionado = (int) vistaPrincipal.getPanelWorkout().getCmbNivel().getSelectedItem();
	            DefaultListModel<String> workoutModel = new DefaultListModel<>();
	            for (Workouts workout : listaWorkouts) {
	                if (workout.getNivelWorkout() <= nivelSeleccionado) {
	                    workoutModel.addElement(workout.getIdWorkouts());
	                }
	            }
	            vistaPrincipal.getPanelWorkout().getListWorkout().setModel(workoutModel);
	        });

	        ////////////// Cargar JLabel detalles workout //////////////
	        vistaPrincipal.getPanelWorkout().getListWorkout().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	                String selectedWorkoutId = vistaPrincipal.getPanelWorkout().getListWorkout().getSelectedValue();

	                if (selectedWorkoutId != null) {
	                    Workouts selectedWorkout = workoutMap.get(selectedWorkoutId);
	                    if (selectedWorkout != null) {
	                        // Crear detalles del workout en una sola línea
	                        String workoutDetails =
	                                "Nombre del Workout: " + selectedWorkout.getNombreWorkout() + " | " +
	                                "Nivel del Workout: " + selectedWorkout.getNivelWorkout() + " | " +
	                                "Ejercicios: ";

	                        // lista de ejercicios de cada workout
	                        ArrayList<Ejercicios> listaEjercicios = selectedWorkout.getEjercicios();
	                        for (int i = 0; i < listaEjercicios.size(); i++) {
	                            Ejercicios ejercicio = listaEjercicios.get(i);
	                            workoutDetails += ejercicio.getNombreEjer();
	                            if (i < listaEjercicios.size() - 1) {
	                                workoutDetails += ", ";
	                            }
	                        }
	                        workoutDetails += " | ";

	                        // Setear los detalles en el JLabel
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().setText(workoutDetails);

	                        // Redimensionar la imagen
	                        ImageIcon originalIcon = new ImageIcon("img/video.png");
	                        Image image = originalIcon.getImage(); // Obtener la imagen original
	                        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar a 30x30
	                        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crear un nuevo ImageIcon con la imagen redimensionada

	                        // Añadir la imagen al final del texto
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().setIcon(scaledIcon);
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().setHorizontalTextPosition(SwingConstants.LEFT); // El texto a la izquierda
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().setVerticalTextPosition(SwingConstants.CENTER); // Imagen centrada verticalmente

	                        // Remover el MouseListener anterior (para que no se abran muchas pantallas de vidoe)
	                        for (MouseListener listener : vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().getMouseListeners()) {
	                            vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().removeMouseListener(listener);
	                        }

	                        // Añadir acción para abrir el video al hacer clic en la imagen
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().addMouseListener(new MouseAdapter() {
	                            @Override
	                            public void mouseClicked(MouseEvent e) {
	                                try {
	                                    Desktop.getDesktop().browse(new URI(selectedWorkout.getVideo())); // Abre el enlace en el navegador
	                                } catch (Exception ex) {
	                                    ex.printStackTrace();
	                                }
	                            }
	                        });
	                    } else {
	                        vistaPrincipal.getPanelWorkout().getLblDetallesWorkout().setText("No se encontraron detalles para el workout seleccionado.");
	                    }
	                }
	            }
	        });

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



}