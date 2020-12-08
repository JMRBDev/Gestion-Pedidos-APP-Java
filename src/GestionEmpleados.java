import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * La clase GestionEmpleados se usa para todo lo referente a los distintos empleados del sistema, tanto cargar sus datos desde el fichero "empleados.txt", como autenticarlos, y también cambiar la contraseña.
 * */
public class GestionEmpleados {
	ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	
	public GestionEmpleados() {
		/* Cargar empleados */
		try {
			File empleadosFile = new File("empleados.txt");
		    Scanner empleadosReader = new Scanner(empleadosFile);
		    while (empleadosReader.hasNextLine()) {
		    	String linea = empleadosReader.nextLine();
		    	empleados.add(new Empleado(linea));
		    }
		    empleadosReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public Empleado autenticar(String login, String password) {
		for (Empleado empleado: empleados) {
			if (empleado.getLogin().equals(login) && empleado.getPassword().equals(password)) {
				System.out.println("Bienvenido/a " + empleado.getLogin());
				return empleado;
			}
		}
		
		return null;
	}
	
	public void cambiarContrasena(Empleado empleado, String nuevaContrasena) {
		String antiguaContrasena = empleados.get(empleados.indexOf(empleado)).getPassword();
		empleados.get(empleados.indexOf(empleado)).setPassword(nuevaContrasena);
		System.out.println("Contraseña \"" + antiguaContrasena + "\" cambiada a \"" + nuevaContrasena + "\"");
	}
}
