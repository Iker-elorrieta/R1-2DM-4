package modelo;

public class Series {
	
	// *** Atributos ***
	private String idSerie;
	private double cuentaRegresiva;
	private String foto;
	private String nombre;
	private double numeroRepeticiones;
	
    static String collectionName = "Workouts";
    static String fieldCuentaRegresiva = "cuentaRegresiva";
    static String fieldFotoSerie= "foto";
    static String fieldNombreSerie = "nombreSerie";
    static String fieldNumeroRepeticiones = "numeroRepeticiones";
	
	
	// *** Constructores ***
	public Series(double cuentaRegresiva, String foto, String nombre, double numeroRepeticiones) {
		this.cuentaRegresiva = cuentaRegresiva;
		this.foto = foto;
		this.nombre = nombre;
		this.numeroRepeticiones = numeroRepeticiones;
	}

	public Series() {
		
	}

	
	// *** Métodos get-set ***
	public String getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(String idSerie) {
		this.idSerie = idSerie;
	}
	
	public double getCuentaRegresiva() {
		return cuentaRegresiva;
	}

	public void setCuentaRegresiva(double cuentaRegresiva) {
		this.cuentaRegresiva = cuentaRegresiva;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNumeroRepeticiones() {
		return numeroRepeticiones;
	}

	public void setNumeroRepeticiones(double numeroRepeticiones) {
		this.numeroRepeticiones = numeroRepeticiones;
	}

    /////////// FIELDS ///////////////////
	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		Series.collectionName = collectionName;
	}

	public static String getFieldCuentaRegresiva() {
		return fieldCuentaRegresiva;
	}

	public static void setFieldCuentaRegresiva(String fieldCuentaRegresiva) {
		Series.fieldCuentaRegresiva = fieldCuentaRegresiva;
	}

	public static String getFieldFotoSerie() {
		return fieldFotoSerie;
	}

	public static void setFieldFotoSerie(String fieldFotoSerie) {
		Series.fieldFotoSerie = fieldFotoSerie;
	}

	public static String getFieldNombreSerie() {
		return fieldNombreSerie;
	}

	public static void setFieldNombreSerie(String fieldNombreSerie) {
		Series.fieldNombreSerie = fieldNombreSerie;
	}

	public static String getFieldNumeroRepeticiones() {
		return fieldNumeroRepeticiones;
	}

	public static void setFieldNumeroRepeticiones(String fieldNumeroRepeticiones) {
		Series.fieldNumeroRepeticiones = fieldNumeroRepeticiones;
	}
	
	// *** M�todos CRUD ***
}
