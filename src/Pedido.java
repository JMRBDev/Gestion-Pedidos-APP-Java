import java.util.ArrayList;

public class Pedido {
	private float precioTotal;
	private ArrayList<Producto> productos = new ArrayList<Producto>(); 
	
	public Pedido() {}
	
	public void anadirProducto(Producto producto) {
		productos.add(producto);
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
}
