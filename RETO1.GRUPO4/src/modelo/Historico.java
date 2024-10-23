package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import conexion.Conexion;

public class Historico {
	// *** Atributos ***
	private String nombreWorkout;
	private double nivelWorkout;
	private double tiempoTotal;
	private double tiempoPrevisto;
	private Date fecha;
	private double ejerciciosRealizados;

	static String collectionName = "Clientes";
	static String fieldNombreWorkout = "nombreWorkout";
	static String fieldNivelWorkout = "nivelWorkout";
	static String fieldTiempoTotal = "tiempoTotal";
	static String fieldTiempoPrevisto = "tiempoPrevisto";
	static String fieldFecha = "fecha";
	static String fieldEjerciciosRealizados = "%ejerciciosRealizados";
	
	
	// *** Constructores ***
	public Historico(String nombreWorkout, double nivelWorkout, double tiempoTotal, double tiempoPrevisto, Date fecha,
			double ejerciciosRealizados) {
		this.nombreWorkout = nombreWorkout;
		this.nivelWorkout = nivelWorkout;
		this.tiempoTotal = tiempoTotal;
		this.tiempoPrevisto = tiempoPrevisto;
		this.fecha = fecha;
		this.ejerciciosRealizados = ejerciciosRealizados;
	}

	public Historico() {
		
	}

	
	// *** Métodos get-set ***
	public String getNombreWorkout() {
		return nombreWorkout;
	}

	public void setNombreWorkout(String nombreWorkout) {
		this.nombreWorkout = nombreWorkout;
	}

	public double getNivelWorkout() {
		return nivelWorkout;
	}

	public void setNivelWorkout(double nivelWorkout) {
		this.nivelWorkout = nivelWorkout;
	}

	public double getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public double getTiempoPrevisto() {
		return tiempoPrevisto;
	}

	public void setTiempoPrevisto(double tiempoPrevisto) {
		this.tiempoPrevisto = tiempoPrevisto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getEjerciciosRealizados() {
		return ejerciciosRealizados;
	}

	public void setEjerciciosRealizados(double ejerciciosRealizados) {
		this.ejerciciosRealizados = ejerciciosRealizados;
	}

	/////////// FIELDS ///////////////////
	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		Historico.collectionName = collectionName;
	}

	public static String getFieldNombreWorkout() {
		return fieldNombreWorkout;
	}

	public static void setFieldNombreWorkout(String fieldNombreWorkout) {
		Historico.fieldNombreWorkout = fieldNombreWorkout;
	}

	public static String getFieldNivelWorkout() {
		return fieldNivelWorkout;
	}

	public static void setFieldNivelWorkout(String fieldNivelWorkout) {
		Historico.fieldNivelWorkout = fieldNivelWorkout;
	}

	public static String getFieldTiempoTotal() {
		return fieldTiempoTotal;
	}

	public static void setFieldTiempoTotal(String fieldTiempoTotal) {
		Historico.fieldTiempoTotal = fieldTiempoTotal;
	}

	public static String getFieldTiempoPrevisto() {
		return fieldTiempoPrevisto;
	}

	public static void setFieldTiempoPrevisto(String fieldTiempoPrevisto) {
		Historico.fieldTiempoPrevisto = fieldTiempoPrevisto;
	}

	public static String getFieldFecha() {
		return fieldFecha;
	}

	public static void setFieldFecha(String fieldFecha) {
		Historico.fieldFecha = fieldFecha;
	}

	public static String getFieldEjerciciosRealizados() {
		return fieldEjerciciosRealizados;
	}

	public static void setFieldEjerciciosRealizados(String fieldEjerciciosRealizados) {
		Historico.fieldEjerciciosRealizados = fieldEjerciciosRealizados;
	}

	// *** M�todos CRUD ***
	public ArrayList<Historico> mObtenerHistorico(String clienteId) {
	    Firestore co = null;
	    ArrayList<Historico> listaHistorico = new ArrayList<Historico>();

	    try {
	        co = Conexion.conectar();

	        // Obtener la colección 'Historico' para el cliente dado
	        ApiFuture<QuerySnapshot> query = co.collection(collectionName).document(clienteId).collection("Historico").get();
	        QuerySnapshot querySnapshot = query.get();
	        List<QueryDocumentSnapshot> historicos = querySnapshot.getDocuments();
	        
	        for (QueryDocumentSnapshot historico : historicos) {
	            Historico h = new Historico();

	            // Obtener las referencias a nombreWorkout y nivelWorkout como DocumentReference
	            DocumentReference nombreWorkoutRef = (DocumentReference) historico.get("nombreWorkout");
	            DocumentReference nivelWorkoutRef = (DocumentReference) historico.get("nivelWorkout");

	            // Consultar los datos del workout referenciado
	            DocumentSnapshot nombreWorkoutSnapshot = nombreWorkoutRef.get().get();
	            DocumentSnapshot nivelWorkoutSnapshot = nivelWorkoutRef.get().get();

	            // Asignar los valores reales desde la referencia
	            h.setNombreWorkout(nombreWorkoutSnapshot.getString("nombreWorkout"));
	            h.setNivelWorkout(nivelWorkoutSnapshot.getDouble("nivelWorkout"));

	            // Otros campos
	            h.setTiempoTotal(historico.getDouble(fieldTiempoTotal)); 
	            h.setTiempoPrevisto(historico.getDouble(fieldTiempoPrevisto)); 

	            Timestamp timestamp = historico.getTimestamp("fecha");
	            if (timestamp != null) {
	                Date fecha = timestamp.toDate();
	                h.setFecha(fecha);
	            }

	            h.setEjerciciosRealizados(historico.getDouble(fieldEjerciciosRealizados));
	            listaHistorico.add(h);
	        }

	    } catch (InterruptedException | ExecutionException e) {
	        System.out.println("Error: Clase Historico, metodo mObtenerHistorico");
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return listaHistorico;
	}

}
