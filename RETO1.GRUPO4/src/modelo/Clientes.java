package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import conexion.Conexion;

public class Clientes {
	
	// *** Atributos ***
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private Date fechaNa;
	
	private static String collectionName = "GymApp";
	private static String fieldNombre = "Nombre";
	private static String fieldApellido = "Apellido";
	private static String fieldContraseña = "Contraseña";
	private static String fieldEmail = "Email";
	private static String fieldFechaNa = "FechaNa";
	
	
	// *** Constructores ***
	public Clientes(String nombre, String apellido, String email, String contrasena, Date fechaNa) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNa = fechaNa;
	}
	
	
	public Clientes () {
		
	}

	
	// *** M�todos get-set ***
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

	// *** M�todos CRUD ***
	
	public boolean mVerificarCliente(String emailIngresado, String contrasenaIngresada) throws Exception {
	    Firestore co = null;

	    try {
	        co = Conexion.conectar();
	        
	        // Accede a la subcolección "clientes" dentro del documento "Clientes"
	        CollectionReference clientesRef = co.collection(collectionName).document("Clientes").collection("clientes");
	        
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
	        CollectionReference clientesRef = co.collection(collectionName).document("Clientes").collection("clientes");

	        // Buscar el cliente con el email proporcionado en la subcolección "clientes"
	        ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, email).get();
	        QuerySnapshot querySnapshot = query.get();
	        List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

	        // Si se encuentra al menos un documento con ese email, el registro no es válido
	        if (!documentos.isEmpty()) {
	            return false;  // El email ya existe
	        }

	        co.close();
	        
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return true;  // El email no existe, registro válido
	}

	
	public Clientes mInsertarCliente() throws Exception {
		Firestore co = null;

		try {
			co = Conexion.conectar();

			CollectionReference clientesRef = co.collection(collectionName)
                    .document("Clientes")
                    .collection("clientes");

			Map<String, Object> data = new HashMap<>();
			data.put(fieldNombre, nombre);
			data.put(fieldApellido, apellido);
			data.put(fieldContraseña, contrasena);
			data.put(fieldEmail, email);
			data.put(fieldFechaNa, fechaNa);

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
	    CollectionReference clientesRef = co.collection(collectionName).document("Clientes").collection("clientes");
	    
	    ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, email).get();
	    QuerySnapshot querySnapshot = query.get();
	    List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();
	    
	    if (!documentos.isEmpty()) {
	        DocumentSnapshot clienteDoc = documentos.get(0);
	        this.nombre = clienteDoc.getString(fieldNombre);
	        this.apellido = clienteDoc.getString(fieldApellido);
	        this.email = clienteDoc.getString(fieldEmail);
	        this.contrasena = clienteDoc.getString(fieldContraseña);
	        this.fechaNa = clienteDoc.getDate(fieldFechaNa);
	    }
	    
	    co.close();
	    return this;
	}

	
	public void mModificarPerfil(String nombre, String apellido, String email, String contrasena, Date fechaNa) {
	    Firestore co = null;

	    try {
	        co = Conexion.conectar();
	        
	        // Referencia al documento del cliente basado en su email
	        CollectionReference clientesRef = co.collection(collectionName).document("Clientes").collection("clientes");
	        ApiFuture<QuerySnapshot> query = clientesRef.whereEqualTo(fieldEmail, email).get();
	        QuerySnapshot querySnapshot = query.get();
	        List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

	        if (!documentos.isEmpty()) {
	            DocumentReference docRef = documentos.get(0).getReference();
	            
	            Map<String, Object> updates = new HashMap<>();
	            updates.put(fieldNombre, nombre);
	            updates.put(fieldApellido, apellido);
	            updates.put(fieldContraseña, contrasena);
	            updates.put(fieldEmail, email);      
	            updates.put(fieldFechaNa, fechaNa);

	            docRef.update(updates);
	        }

	        co.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}