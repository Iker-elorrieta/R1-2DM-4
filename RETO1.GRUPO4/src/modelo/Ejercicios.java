package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Ejercicios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// *** Atributos ***
	private String idEjercicio;
	private double cronometro;
	private double descansoEjer;
	private String descripcionEjer;
	private String nombreEjer;
	private ArrayList<Series> series;

	static String collectionName = "Workouts";
	static String fieldCronometro = "cronometro";
	static String fieldDescansoEjer = "descansoEjer";
	static String fieldDescripcionEjer = "descripcionEjer";
	static String fieldNombreEjer = "nombreEjer";

	// *** Constructores ***
	public Ejercicios(double cronometro, double descansoEjer, String descripcionEjer, String nombreEjer) {
		this.cronometro = cronometro;
		this.descansoEjer = descansoEjer;
		this.descripcionEjer = descripcionEjer;
		this.nombreEjer = nombreEjer;
	}

	public Ejercicios() {
		this.series = new ArrayList<>(); // Inicializa la lista de series
	}


	// Métodos get-set
	public String getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(String idEjercicio) {
		this.idEjercicio = idEjercicio;
	}
	
	public double getCronometro() {
		return cronometro;
	}

	public void setCronometro(double cronometro) {
		this.cronometro = cronometro;
	}

	public double getDescansoEjer() {
		return descansoEjer;
	}

	public void setDescansoEjer(double descansoEjer) {
		this.descansoEjer = descansoEjer;
	}

	public String getDescripcionEjer() {
		return descripcionEjer;
	}

	public void setDescripcionEjer(String descripcionEjer) {
		this.descripcionEjer = descripcionEjer;
	}

	public String getNombreEjer() {
		return nombreEjer;
	}

	public void setNombreEjer(String nombreEjer) {
		this.nombreEjer = nombreEjer;
	}

	public ArrayList<Series> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Series> series) {
		this.series = series;
	}

	/////////// FIELDS ///////////////////
	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		Ejercicios.collectionName = collectionName;
	}

	public static String getFieldCronometro() {
		return fieldCronometro;
	}

	public static void setFieldCronometro(String fieldCronometro) {
		Ejercicios.fieldCronometro = fieldCronometro;
	}

	public static String getFieldDescansoEjer() {
		return fieldDescansoEjer;
	}

	public static void setFieldDescansoEjer(String fieldDescansoEjer) {
		Ejercicios.fieldDescansoEjer = fieldDescansoEjer;
	}

	public static String getFieldDescripcionEjer() {
		return fieldDescripcionEjer;
	}

	public static void setFieldDescripcionEjer(String fieldDescripcionEjer) {
		Ejercicios.fieldDescripcionEjer = fieldDescripcionEjer;
	}

	public static String getFieldNombreEjer() {
		return fieldNombreEjer;
	}

	public static void setFieldNombreEjer(String fieldNombreEjer) {
		Ejercicios.fieldNombreEjer = fieldNombreEjer;
	}

}
