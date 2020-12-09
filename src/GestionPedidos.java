import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

/*
 * La clase GestionPedidos es m�s compleja que GestionEmpleados ya que los pedidos tienen m�s opciones.
 * En esta clase ocurre todo lo referente a los pedidos, se crean nuevos pedidos, se cargan los productos de los ficheros "perecederos.txt" y "no_perecederos.txt", se listan todos los
 * productos, se a�aden productos a los pedidos, se ve el precio total del pedido y se muestra la factura final.
 * */
public class GestionPedidos {
	private ArrayList<Producto> perecederos = new ArrayList<Producto>();
	private ArrayList<Producto> noPerecederos = new ArrayList<Producto>();
	private Pedido pedido = new Pedido();
	private Empleado empleado;

	public Pedido getPedido() {
		return pedido;
	}

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

	public void listarProductos(String tipo) {
		if (tipo == "perecederos") {
			System.out.printf("%-10s %15s %20s %15s %20s", "C�digo", "Nombre", "Precio unitario", "Cantidad",
					"Caduca en\n");
			for (Producto producto : this.perecederos) {
				System.out.printf("%-10s %15s %20s %15s %20s", producto.codigo, producto.nombre, producto.precio,
						producto.unidades, producto.modificador + " d�as\n");
			}
		} else if (tipo == "no perecederos") {
			System.out.printf("%-10s %15s %20s %15s %20s", "C�digo", "Nombre", "Precio unitario", "Cantidad",
					"Descuento\n");
			for (Producto producto : this.noPerecederos) {
				System.out.printf("%-10s %15s %20s %15s %20s", producto.codigo, producto.nombre, producto.precio,
						producto.unidades, producto.modificador + "%\n");
			}
		}
	}

	public String anadirProducto(int codigo, String tipoProducto, int unidades) {
		/* Si se intenta a�adir una cantidad negativa o cero */
		if (unidades < 0) {
			return "No se pueden a�adir cantidades negativas";
		} else if (unidades == 0) {
			return "No se ha a�adido ninguna unidad";
		}

		ArrayList<Producto> productos = tipoProducto == "perecederos" ? this.perecederos : this.noPerecederos;
		boolean isAnadido = false;
		boolean existe = false;

		/*
		 * He recorrido los productos mediante un iterador, para poder eliminarlos a la
		 * vez que se est� ejecutando el bucle en caso de que queden 0 unidades
		 */
		for (Iterator<Producto> productosIterador = productos.iterator(); productosIterador.hasNext();) {
			Producto producto = productosIterador.next();
			if (producto.codigo == codigo) {
				isAnadido = this.pedido.anadirProducto(producto, unidades);
				existe = true;
				if (producto.unidades - unidades == 0) { // Si quedan 0 unidades:
					productosIterador.remove(); // Eliminar el producto de la lista
				} else if (producto.unidades - unidades < 0) {
					return "\nHay menos de " + unidades + " unidades del producto. Int�ntelo de nuevo.";
				} else {
					producto.setUnidades(unidades);
				}
			}
		}

		if (!existe) {
			return "\nEl producto con identificador " + codigo + " no existe. Int�ntelo de nuevo.";
		}

		if (!isAnadido) {
			return "\nHay menos de " + unidades + " unidades del producto. Int�ntelo de nuevo.";
		}

		return "\nProducto a�adido con �xito";
	}

	public String visualizarPrecioTotal() {
		return "\nPrecio del pedido: " + this.pedido.getPrecioTotal() + "�";
	}

	public void mostrarFactura() {
		/* Uso el formateo de los printf para poder encuadrar bien los datos en una tabla */
		System.out.println("\n[FACTURA - " + this.empleado.getLogin() + " - " + this.empleado.getVentas() + " ventas]");
		if (this.pedido.getProductos().size() > 0) {
			System.out.printf("%-10s %15s %20s %20s %20s", "C�digo", "Nombre", "Precio unitario", "Cantidad", "Precio total\n");
			for (Entry<Producto, Integer> producto : this.pedido.getProductos().entrySet()) {
				System.out.printf("%-10s %15s %20s %20s %20s", producto.getKey().codigo, producto.getKey().nombre,
						producto.getKey().precio, producto.getValue(), (producto.getValue() * producto.getKey().calcularPrecio(producto.getKey().precio, producto.getKey().modificador)) + "\n");
			}
			System.out.println("\nTOTAL: " + this.pedido.getPrecioTotal());
		} else {
			System.out.println("\nNo se han a�adido productos en este pedido.");
		}
	}
}
