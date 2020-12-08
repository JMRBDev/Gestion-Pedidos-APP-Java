import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Pedido {
	private float precioTotal = 0;
	private LinkedHashMap<Producto, Integer> productos = new LinkedHashMap<Producto, Integer>(); // He usado un LinkedHashMap en productos para poder almacenar tanto el Producto como la cantidad añadida
	
	public Pedido() {}
	
	public boolean anadirProducto(Producto producto, int unidades) {
		if (unidades <= producto.unidades) { // Siempre y cuando la cantidad a añadir sea menor que la cantidad en stock:
			productos.put(producto, unidades);
			precioTotal += producto.precio * unidades;
			return true;
		}
		
		return false;
	}

	public float getPrecioTotal() {
		return (float) Math.round(precioTotal * 100) / 100; // Redondeo a dos decimales
	}
	
	public String toString() {
		String resultado = "";
		for (Entry<Producto, Integer> entry : productos.entrySet()) {
		    resultado += entry.getKey().codigo + "\t\t" + entry.getKey().nombre + "\t\t" + entry.getKey().precio + "\t\t\t" + entry.getValue() + "\n";
		}
		
		return resultado;
	}
}
