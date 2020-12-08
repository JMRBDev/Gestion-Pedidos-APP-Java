import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionPedidos {
	private ArrayList<Producto> perecederos = new ArrayList<Producto>();
	private ArrayList<Producto> noPerecederos = new ArrayList<Producto>();
	private Pedido pedido = new Pedido();
	
	public GestionPedidos() {
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
	
	public void anadirProducto(int codigo) {
		for (Producto producto: this.perecederos) {
			if (producto.codigo == codigo) {
				this.pedido.anadirProducto(producto);
			}
		}
	}
}
