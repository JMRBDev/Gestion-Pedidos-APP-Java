/*
 * De la clase Empleado se crea un objeto cuando un empleado se autentica correctamente en el sistema a través de gestionEmpleados.autenticar()
 * Esto sirve para poder recoger datos del empleado que se encuentra autenticado, y poder poner su nombre en la factura por ejemplo.
 * */
public class Empleado {
	private int codigo;
	private String login;
	private String password;
	private int ventas;
	
	public Empleado (String datos) {
		String[] datosList = datos.split(":");
		this.codigo = Integer.parseInt(datosList[0]);
		this.login = datosList[1];
		this.password = datosList[2];
		this.ventas = Integer.parseInt(datosList[3]);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}
	
	
}
