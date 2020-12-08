
public abstract class Producto {
	int codigo;
	String nombre;
	float precio;
	int unidades;
	int modificador;
	
	public Producto(String datos) {
		String[] datosList = datos.split(":");
		this.codigo = Integer.parseInt(datosList[0]);
		this.nombre = datosList[1];
		this.precio = Float.parseFloat(datosList[2]);
		this.unidades = Integer.parseInt(datosList[3]);
		this.modificador = Integer.parseInt(datosList[4]);
	}
	
	abstract float calcularPrecio(float precio, int modificador);
	
	public String toString() {
		return this.codigo + " - " + this.nombre + " - " + this.precio + " - " +  this.unidades + " - " +  this.modificador;
	}
}
