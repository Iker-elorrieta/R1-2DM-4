package modelo;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import conexion.Conexion;

public class Workouts {
	
	// *** Atributos ***
	private String idWorkouts;
	private double nivelWorkout;
	private String nombreWorkout;
	private double numEjerWorkout;
	private String video;
	private ArrayList<Ejercicios> ejercicios;
	
	static String collectionName = "Workouts";
	static String fieldNivelWorkout = "nivelWorkout";
    static String fieldNombreWorkout = "nombreWorkout";
    static String fieldNumEjerWorkout = "numEjerWorkout";
    static String fieldVideo = "video";


	// *** Constructores ***
	public Workouts(double nivelWorkout, String nombreWorkout, double numEjerWorkout, String video) {
		this.nivelWorkout = nivelWorkout;
		this.nombreWorkout = nombreWorkout;
		this.numEjerWorkout = numEjerWorkout;
		this.video = video;
	}
	
	public Workouts() {
		this.ejercicios = new ArrayList<>(); // Inicializamos la lista de ejercicios en el constructor vacío
	}

	
	// *** Métodos get-set ***
	public String getIdWorkouts() {
		return idWorkouts;
	}

	public void setIdWorkouts(String idWorkouts) {
		this.idWorkouts = idWorkouts;
	}

	public double getNivelWorkout() {
		return nivelWorkout;
	}

	public void setNivelWorkout(double nivelWorkout) {
		this.nivelWorkout = nivelWorkout;
	}

	public String getNombreWorkout() {
		return nombreWorkout;
	}

	public void setNombreWorkout(String nombreWorkout) {
		this.nombreWorkout = nombreWorkout;
	}

	public double getNumEjerWorkout() {
		return numEjerWorkout;
	}

	public void setNumEjerWorkout(double numEjerWorkout) {
		this.numEjerWorkout = numEjerWorkout;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public ArrayList<Ejercicios> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(ArrayList<Ejercicios> ejercicios) {
		this.ejercicios = ejercicios;
	}

	
	/////////// FIELDS ///////////////////
	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		Workouts.collectionName = collectionName;
	}

	public static String getFieldNivelWorkout() {
		return fieldNivelWorkout;
	}

	public static void setFieldNivelWorkout(String fieldNivelWorkout) {
		Workouts.fieldNivelWorkout = fieldNivelWorkout;
	}

	public static String getFieldNombreWorkout() {
		return fieldNombreWorkout;
	}

	public static void setFieldNombreWorkout(String fieldNombreWorkout) {
		Workouts.fieldNombreWorkout = fieldNombreWorkout;
	}

	public static String getFieldNumEjerWorkout() {
		return fieldNumEjerWorkout;
	}

	public static void setFieldNumEjerWorkout(String fieldNumEjerWorkout) {
		Workouts.fieldNumEjerWorkout = fieldNumEjerWorkout;
	}

	public static String getFieldVideo() {
		return fieldVideo;
	}

	public static void setFieldVideo(String fieldVideo) {
		Workouts.fieldVideo = fieldVideo;
	}

	
	
	// *** Métodos CRUD ***
	public ArrayList<Workouts> mCargarWorkouts() { 
	    Firestore db = null;
	    try {
	        // Conexión a Firestore
	        db = Conexion.conectar();
	        ArrayList<Workouts> listaWorkouts = new ArrayList<>();

	        // Referencia a la colección "Workouts" utilizando el campo estático
	        CollectionReference workoutsRef = db.collection(collectionName);
	        List<QueryDocumentSnapshot> workoutsDocs = workoutsRef.get().get().getDocuments();

	        // Iterar sobre los documentos de la colección "Workouts"
	        for (QueryDocumentSnapshot workoutDoc : workoutsDocs) {
	            // Crear un objeto Workouts vacío y setear los campos
	            Workouts workout = new Workouts();
	            workout.setIdWorkouts(workoutDoc.getId());
	            workout.setNivelWorkout(workoutDoc.getDouble(fieldNivelWorkout));
	            workout.setNombreWorkout(workoutDoc.getString(fieldNombreWorkout));
	            workout.setNumEjerWorkout(workoutDoc.getDouble(fieldNumEjerWorkout));
	            workout.setVideo(workoutDoc.getString(fieldVideo));

	            // Lista para almacenar los ejercicios
	            ArrayList<Ejercicios> listaEjercicios = new ArrayList<>();

	            // Referencia a la colección "ejercicios" dentro de cada workout
	            CollectionReference ejerciciosRef = workoutDoc.getReference().collection("Ejercicios");
	            List<QueryDocumentSnapshot> ejerciciosDocs = ejerciciosRef.get().get().getDocuments();

	            // Iterar sobre los documentos de la colección "ejercicios"
	            for (QueryDocumentSnapshot ejercicioDoc : ejerciciosDocs) {
	                // Crear un objeto Ejercicios vacío y setear los campos
	                Ejercicios ejercicio = new Ejercicios();
	                ejercicio.setIdEjercicio(ejercicioDoc.getId());
	                ejercicio.setCronometro(ejercicioDoc.getDouble(Ejercicios.fieldCronometro));
	                ejercicio.setDescansoEjer(ejercicioDoc.getDouble(Ejercicios.fieldDescansoEjer));
	                ejercicio.setDescripcionEjer(ejercicioDoc.getString(Ejercicios.fieldDescripcionEjer));
	                ejercicio.setNombreEjer(ejercicioDoc.getString(Ejercicios.fieldNombreEjer));

	                // Lista para almacenar las series
	                ArrayList<Series> listaSeries = new ArrayList<>();

	                // Referencia a la colección "series" dentro de cada ejercicio
	                CollectionReference seriesRef = ejercicioDoc.getReference().collection("Series");
	                List<QueryDocumentSnapshot> seriesDocs = seriesRef.get().get().getDocuments();

	                // Iterar sobre los documentos de la colección "series"
	                for (QueryDocumentSnapshot serieDoc : seriesDocs) {
	                    // Crear un objeto Series vacío y setear los campos
	                    Series serie = new Series();
	                    serie.setIdSerie(serieDoc.getId());
	                    serie.setCuentaRegresiva(serieDoc.getDouble(Series.fieldCuentaRegresiva));
	                    serie.setFoto(serieDoc.getString(Series.fieldFotoSerie));
	                    serie.setNombre(serieDoc.getString(Series.fieldNombreSerie));
	                    serie.setNumeroRepeticiones(serieDoc.getDouble(Series.fieldNumeroRepeticiones));

	                    listaSeries.add(serie); // Agregar la serie a la lista de series
	                }

	                ejercicio.setSeries(listaSeries); // Setear la lista de series en el ejercicio
	                listaEjercicios.add(ejercicio); // Agregar el ejercicio a la lista de ejercicios
	            }

	            workout.setEjercicios(listaEjercicios); // Setear la lista de ejercicios en el workout
	            listaWorkouts.add(workout); // Agregar el workout a la lista de workouts
	        }

	        return listaWorkouts;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
