package modelo;

public class Workouts {
	private String nivel;
	private String nombre;
	private String numeroEjercicio;
	private String video;
	
	private static String collectionName = "GymApp";
	private static String fieldNivel = "nivelWorkout";
	private static String fieldNombre= "nombreWorkout";
	private static String fieldNumeroEjercicio = "numEjerWorkout";
	private static String fieldvideo = "video";

	
	// *** Constructores ***
	public Workouts(String nivel, String nombre, String numeroEjercicio, String video) {
		this.nivel = nivel;
		this.nombre = nombre;
		this.numeroEjercicio = numeroEjercicio;
		this.video = video;
	}

	
	public Workouts() {
		
	}

	// *** M�todos get-set ***
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroEjercicio() {
		return numeroEjercicio;
	}

	public void setNumeroEjercicio(String numeroEjercicio) {
		this.numeroEjercicio = numeroEjercicio;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	// *** M�todos CRUD ***
	

}
