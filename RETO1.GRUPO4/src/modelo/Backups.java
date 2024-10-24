package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Backups {
	
	public void guardarClientes(ArrayList<Clientes> clientes) {
	    FileOutputStream fos = null;
	    ObjectOutputStream salida = null;

	    try {
	        // Crear directorio si no existe
	        File db = new File("archivos");
	        if (!db.exists()) {
	            db.mkdirs();
	        }

	        // Guardar en el archivo Workouts.dat
	        fos = new FileOutputStream("archivos/Clientes.dat");
	        salida = new ObjectOutputStream(fos);

	        // Guardar cada workout en el archivo
	        for (int i = 0; i < clientes.size(); i++) {
	            salida.writeObject(clientes.get(i));
	        }

	        JOptionPane.showMessageDialog(null,
	                "Se han guardado " + clientes.size() + " clientes correctamente en el archivo.",
	                "Guardar Workouts", JOptionPane.INFORMATION_MESSAGE);
	    } catch (FileNotFoundException e) {
	        System.out.println("Archivo no encontrado: " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error al guardar los workouts: " + e.getMessage());
	    } finally {
	        try {
	            if (salida != null) {
	                salida.close();
	            }
	            if (fos != null) {
	                fos.close();
	            }
	        } catch (IOException e) {
	            System.out.println("Error al cerrar el archivo: " + e.getMessage());
	        }
	    }
	}

	public void cargarClientes(ArrayList<Clientes> clientes) {
	    File fichero = new File("archivos/resultados.dat");
	    FileInputStream fi = null;
	    ObjectInputStream datals = null;
	    Clientes cliente; // Defino la variable partido

	    try {
	        fi = new FileInputStream(fichero);
	        datals = new ObjectInputStream(fi);

	        // Leer el archivo mientras no se haya alcanzado el final
	        while (fi.getChannel().position() < fi.getChannel().size()) {
	            cliente = (Clientes) datals.readObject(); // Leer un objeto de tipo Partidos
	            clientes.add(cliente); // Añadir el partido al ArrayList
	        }

	        JOptionPane.showMessageDialog(null, "Partidos cargados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	    } catch (EOFException eo) {
	        System.out.println("FIN DE LECTURA.");
	    } catch (FileNotFoundException fnf) {
	        JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + fnf.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (StreamCorruptedException sce) {
	        JOptionPane.showMessageDialog(null, "Error en el flujo de datos: " + sce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (IOException ioe) {
	        JOptionPane.showMessageDialog(null, "Error al leer los partidos: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (ClassNotFoundException cnf) {
	        JOptionPane.showMessageDialog(null, "Error al convertir el objeto: " + cnf.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (datals != null) {
	                datals.close(); // Cerrar el ObjectInputStream
	            }
	            if (fi != null) {
	                fi.close(); // Cerrar el FileInputStream
	            }
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Error al cerrar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	public void guardarWorkouts(ArrayList<Workouts> workouts) {
	    FileOutputStream fos = null;
	    ObjectOutputStream salida = null;

	    try {
	        // Crear directorio si no existe
	        File db = new File("archivos");
	        if (!db.exists()) {
	            db.mkdirs();
	        }

	        // Guardar en el archivo Workouts.dat
	        fos = new FileOutputStream("archivos/Workouts.dat");
	        salida = new ObjectOutputStream(fos);

	        // Guardar cada workout en el archivo
	        for (int i = 0; i < workouts.size(); i++) {
	            salida.writeObject(workouts.get(i));
	        }

	        JOptionPane.showMessageDialog(null,
	                "Se han guardado " + workouts.size() + " workouts correctamente en el archivo.",
	                "Guardar Workouts", JOptionPane.INFORMATION_MESSAGE);
	    } catch (FileNotFoundException e) {
	        System.out.println("Archivo no encontrado: " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error al guardar los workouts: " + e.getMessage());
	    } finally {
	        try {
	            if (salida != null) {
	                salida.close();
	            }
	            if (fos != null) {
	                fos.close();
	            }
	        } catch (IOException e) {
	            System.out.println("Error al cerrar el archivo: " + e.getMessage());
	        }
	    }
	}

	public void cargarWorkouts(ArrayList<Workouts> workouts) {
	    File fichero = new File("archivos/Workouts.dat");
	    FileInputStream fi = null;
	    ObjectInputStream datals = null;
	    Workouts workout; // Defino la variable partido

	    try {
	        fi = new FileInputStream(fichero);
	        datals = new ObjectInputStream(fi);

	        // Leer el archivo mientras no se haya alcanzado el final
	        while (fi.getChannel().position() < fi.getChannel().size()) {
	            workout = (Workouts) datals.readObject(); // Leer un objeto de tipo Partidos
	            workouts.add(workout); // Añadir el workout al ArrayList
	        }

	        JOptionPane.showMessageDialog(null, "Partidos cargados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	    } catch (EOFException eo) {
	        System.out.println("FIN DE LECTURA.");
	    } catch (FileNotFoundException fnf) {
	        JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + fnf.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (StreamCorruptedException sce) {
	        JOptionPane.showMessageDialog(null, "Error en el flujo de datos: " + sce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (IOException ioe) {
	        JOptionPane.showMessageDialog(null, "Error al leer los partidos: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (ClassNotFoundException cnf) {
	        JOptionPane.showMessageDialog(null, "Error al convertir el objeto: " + cnf.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (datals != null) {
	                datals.close(); // Cerrar el ObjectInputStream
	            }
	            if (fi != null) {
	                fi.close(); // Cerrar el FileInputStream
	            }
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Error al cerrar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
}
