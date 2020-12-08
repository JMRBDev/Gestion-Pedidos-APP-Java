import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * La clase GestionPedidos es más compleja que GestionEmpleados ya que los pedidos tienen más opciones.
 * En esta clase ocurre todo lo referente a los pedidos, se crean nuevos pedidos, se cargan los productos de los ficheros "perecederos.txt" y "no_perecederos.txt", se listan todos los
 * productos, se añaden productos a los pedidos, se ve el precio total del pedido y se muestra la factura final.
 * */
public class GestionPedidos {
	private ArrayList<Producto> perecederos = new ArrayList<Producto>();
	private ArrayList<Producto> noPerecederos = new ArrayList<Producto>();
	private Pedido pedido = new Pedido();
	private Empleado empleado;
	
	public GestionPedidos(Empleado empleadoActual) {
		this.empleado = empleadoActual;
		
		/* Cargar productos perecederos */
		try {
			File perecederosFile = new File("perecederos.txt");
		    Scanner perecederosReader = new Scanner(perecederosFile);
		    while (perecederosReader.hasNextLine()) {
		    	String linea = perecederosReader.nextLine();
		    	perecederos.add(new Perecedero(linea));
		    }
		    perecederosReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		/* Cargar productos no perecederos */
		try {
			File noPerecederosFile = new File("no_perecederos.txt");
		    Scanner noPerecederosReader = new Scanner(noPerecederosFile);
		    while (noPerecederosReader.hasNextLine()) {
		    	String linea = noPerecederosReader.nextLine();
		    	noPerecederos.add(new Perecedero(linea));
		    }
		    noPerecederosReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public String listarProductos(String tipo) {
		String listaProductos = "";
		
		if (tipo == "perecederos") {			
			for (Producto producto: this.perecederos) {
				listaProductos += producto + "\n";
			}
		} else if (tipo == "no perecederos") {
			for (Producto producto: this.noPerecederos) {
				listaProductos += producto + "\n";
			}
		}
		
		return listaProductos;
	}
	
	public String anadirProducto(int codigo, String tipoProducto, int unidades) {
		ArrayList<Producto> productos = tipoProducto == "perecederos" ? this.perecederos : this.noPerecederos;
		boolean isAnadido = false;
		boolean existe = false;
		
		/* He recorrido los productos mediante un iterador, para poder eliminarlos a la vez que se está ejecutando el bucle en caso de que queden 0 unidades */
		for (Iterator<Producto> productosIterador = productos.iterator(); productosIterador.hasNext();) {
			Producto producto = productosIterador.next();
			if (producto.codigo == codigo) {
				isAnadido = this.pedido.anadirProducto(producto, unidades);
				existe = true;
				if (producto.unidades - unidades == 0) { // Si quedan 0 unidades:
					productosIterador.remove(); // Eliminar el producto de la lista
				} else if (producto.unidades - unidades < 0) {
					return "Hay menos de " + unidades + " unidades del producto. Inténtelo de nuevo.";
				} else {
					producto.setUnidades(unidades);
				}
			}
		}
		
		if(!existe) {
			return "El producto con identificador " + codigo + " no existe. Inténtelo de nuevo.";
		}
			
		if (!isAnadido) {
			return "Hay menos de " + unidades + " unidades del producto. Inténtelo de nuevo.";
		}
		
		return "Producto añadido con éxito";
	}
	
	public String visualizarPrecioTotal() {
		return "Precio del pedido: " + this.pedido.getPrecioTotal() + "€";
	}
	
	public String mostrarFactura() {
		return "[FACTURA - Pedido realizado por " + this.empleado.getLogin() + "]\nCódigo\t\tNombre\t\tPrecio unitario\t\tUnidades\n" + this.pedido + "\n------\nPrecio TOTAL: " + this.pedido.getPrecioTotal() + "€\n";
	}
}
