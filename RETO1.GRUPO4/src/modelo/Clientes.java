package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import conexion.Conexion;

public class Clientes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// *** Atributos ***
	private String idCliente;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private Date fechaNa;
	private double nivelUsuario;
	private boolean esEntrenador;
	private ArrayList<Workouts> workouts;

	private static String collectionName = "Clientes";
	private static String fieldNombre = "Nombre";
	private static String fieldApellido = "Apellido";
	private static String fieldContraseña = "Contraseña";
	private static String fieldEmail = "Email";
	private static String fieldFechaNa = "FechaNa";
	private static String fieldEsEntrenador = "esEntrenador";
	private static String fieldNivelUsuario = "nivelUsuario";

	// *** Constructores ***
	public Clientes(String nombre, String apellido, String email, String contrasena, Date fechaNa, double nivelUsuario,
			boolean esEntrenador, ArrayList<Workouts> workouts) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNa = fechaNa;
		this.nivelUsuario = nivelUsuario;
		this.esEntrenador = esEntrenador;
		this.workouts = workouts;
	}

	public Clientes(String nombre, String apellido, String email, String contrasena, Date fechaNa, double nivelUsuario,
			boolean esEntrenador) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNa = fechaNa;
		this.nivelUsuario = nivelUsuario;
		this.esEntrenador = esEntrenador;
	}

	public Clientes() {
		this.workouts = new ArrayList<>(); // Inicializa la lista de series
	}

	// *** M�todos get-set ***
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFechaNa() {
		return fechaNa;
	}

	public void setFechaNa(Date fechaNa) {
		this.fechaNa = fechaNa;
	}

	public double getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(double nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}

	public boolean isEsEntrenador() {
		return esEntrenador;
	}

	public void setEsEntrenador(boolean esEntrenador) {
		this.esEntrenador = esEntrenador;
	}

	public ArrayList<Workouts> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(ArrayList<Workouts> workouts) {
		this.workouts = workouts;
	}

	// *** M�todos CRUD ***
	public ArrayList<Clientes> mCargarClientes() { 
	    Firestore db = null;
	    try {
	        db = Conexion.conectar();
	        ArrayList<Clientes> listaClientes = new ArrayList<>();
	        CollectionReference clientesRef = db.collection("Clientes");
	        List<QueryDocumentSnapshot> clientesDocs = clientesRef.get().get().getDocuments();

	        // Iterar a través de los documentos de los clientes
	        for (QueryDocumentSnapshot clienteDoc : clientesDocs) {
	            Clientes cliente = new Clientes();
	            cliente.setIdCliente(clienteDoc.getId());
	            cliente.setNombre(clienteDoc.getString(fieldNombre));
	            cliente.setApellido(clienteDoc.getString(fieldApellido));
	            cliente.setEmail(clienteDoc.getString(fieldEmail));
	            cliente.setContrasena(clienteDoc.getString(fieldContraseña)); 
	            
	            // Aquí se obtiene `fechaNa`
	            Timestamp timestamp = clienteDoc.getTimestamp(fieldFechaNa);
	            if (timestamp != null) {
	                // Convertir Timestamp a Date y asignar a fechaNa
	                cliente.setFechaNa(timestamp.toDate()); // Asignar directamente a la propiedad
	            } else {
	                cliente.setFechaNa(null); // Manejo de caso donde no hay fecha
	            }

	            // Obtener el nivel de usuario y si es entrenador
	            cliente.setNivelUsuario(clienteDoc.getDouble(fieldNivelUsuario)); 
	            cliente.setEsEntrenador(clienteDoc.getBoolean(fieldEsEntrenador)); 

	            listaClientes.add(cliente); 
	        }

	        return listaClientes;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Manejo de errores general
	    }
	}

	public boolean mVerificarCliente(String emailIngresado, String contrasenaIngresada) throws Exception {
		Firestore co = null;

		try {
			co = Conexion.conectar();

			CollectionReference clientesRef = co.collection(collectionName);

			// Buscar el cliente con el email proporcionado en la subcolección "clientes"
			ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, emailIngresado).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

			if (!documentos.isEmpty()) {
				// Obtener el primer documento (email debería ser único)
				DocumentSnapshot cliente = documentos.get(0);
				String contrasenaGuardada = cliente.getString(fieldContraseña);
				if (contrasenaGuardada != null && contrasenaGuardada.equals(contrasenaIngresada)) {
					return true; // Inicio de sesión exitoso
				}
			}

			co.close();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // Email o contraseña incorrectos
	}

	public boolean mVerificarRegistroValido(String email) throws Exception {
		Firestore co = null;

		try {
			co = Conexion.conectar();

			// Accede a la subcolección "clientes" dentro del documento "Clientes"
			CollectionReference clientesRef = co.collection(collectionName);

			// Buscar el cliente con el email proporcionado en la subcolección "clientes"
			ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, email).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

			// Si se encuentra al menos un documento con ese email, el registro no es válido
			if (!documentos.isEmpty()) {
				return false; // El email ya existe
			}

			co.close();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true; // El email no existe, registro válido
	}

	public Clientes mInsertarCliente() throws Exception {
		Firestore co = null;
		
		try {
			co = Conexion.conectar();

			CollectionReference clientesRef = co.collection(collectionName);

			Map<String, Object> data = new HashMap<>();
			data.put(fieldNombre, nombre);
			data.put(fieldApellido, apellido);
			data.put(fieldContraseña, contrasena);
			data.put(fieldEmail, email);
			data.put(fieldFechaNa, fechaNa);
			data.put(fieldEsEntrenador, false);
			data.put(fieldNivelUsuario, 0);

			clientesRef.add(data);

			co.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public Clientes mObtenerDatosCliente(String email) throws Exception {
		Firestore co = Conexion.conectar();
		CollectionReference clientesRef = co.collection(collectionName);

		ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, email).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

		if (!documentos.isEmpty()) {
			DocumentSnapshot clienteDoc = documentos.get(0);
			this.idCliente = clienteDoc.getId();
			this.nombre = clienteDoc.getString(fieldNombre);
			this.apellido = clienteDoc.getString(fieldApellido);
			this.email = clienteDoc.getString(fieldEmail);
			this.contrasena = clienteDoc.getString(fieldContraseña);
			this.fechaNa = clienteDoc.getDate(fieldFechaNa);
			this.nivelUsuario = clienteDoc.getDouble(fieldNivelUsuario);
			this.esEntrenador = clienteDoc.getBoolean(fieldEsEntrenador);
		}

		co.close();
		return this;
	}

	public Clientes mModificarPerfil(String idCliente, String nombre, String apellido, String email, String contrasena,
			Date fechaNa) throws Exception {
		Firestore co = null;

		try {
			co = Conexion.conectar();

			// Obtén la referencia al documento específico utilizando el idCliente
			DocumentReference docRef = co.collection(collectionName).document(idCliente);
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();

			if (document.exists()) {
				Map<String, Object> updates = new HashMap<>();
				updates.put(fieldNombre, nombre);
				updates.put(fieldApellido, apellido);
				updates.put(fieldContraseña, contrasena);
				updates.put(fieldEmail, email);
				updates.put(fieldFechaNa, fechaNa);

				// Actualizar el documento
				docRef.update(updates);
			}
			co.close();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return this;
	}
}