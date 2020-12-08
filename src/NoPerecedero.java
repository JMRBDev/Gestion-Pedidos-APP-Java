/*
 * NoPerecedero extiende de la clase abstracta Pedido, porque cuenta con atributos y métodos idénticos a los de Perecedero
 * */
public class NoPerecedero extends Producto {
	private int porcentajeOferta;
		
	public NoPerecedero(String datos) {
		super(datos);
		this.porcentajeOferta = super.modificador;
	}
	
	/* Calcular el precio según su porcentaje de oferta */
	float calcularPrecio(float precio, int porcentajeOferta) {
		return precio - ((precio * porcentajeOferta) / 100);
	}

	public int getPorcentajeOferta() {
		return porcentajeOferta;
	}

	public void setPorcentajeOferta(int porcentajeOferta) {
		this.porcentajeOferta = porcentajeOferta;
	}
}
