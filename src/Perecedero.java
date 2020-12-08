
public class Perecedero extends Producto {
	private int tiempoParaCaducar;
	
	public Perecedero(String datos) {
		super(datos);
		this.tiempoParaCaducar = super.modificador;
	}
	
	float calcularPrecio(float precio, int tiempoParaCaducar) {
		switch (tiempoParaCaducar) {
		case (1):
			return (float) (precio - (0.25 * precio));
		case (2):
			return (float) (precio - (0.33 * precio));
		case (3):
			return (float) (precio - (0.5 * precio));
		default:
			return precio;
		}
	}

	public int getTiempoParaCaducar() {
		return tiempoParaCaducar;
	}

	public void setTiempoParaCaducar(int tiempoParaCaducar) {
		this.tiempoParaCaducar = tiempoParaCaducar;
	}
}
