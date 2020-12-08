import java.util.Scanner;

public class Aplicacion {
	public static void main(String[] args) {
		GestionEmpleados gestionEmpleados = new GestionEmpleados();
		
		try (Scanner sc = new Scanner(System.in)) {
			while (true) {
				System.out.println("Usuario:");
				String login = sc.nextLine();
				System.out.println("Contrase�a:");
				String password = sc.nextLine();
				
				Empleado empleadoActual = gestionEmpleados.autenticar(login, password);
				
				if (empleadoActual != null) {
					while (true) {
						System.out.println("1. Hacer pedido\n2. Cambiar contrase�a\n3. Salir");
						int opcion = Integer.parseInt(sc.nextLine());
						switch (opcion) {
						case (1): {
							while (true) {
								System.out.println("1. A�adir producto\n2. Visualizar precio total\n3. Terminar pedido");
								int opcionPedido = Integer.parseInt(sc.nextLine());
								switch (opcionPedido) {
									case (1): {
										GestionPedidos gestionPedidos = new GestionPedidos();
										System.out.println("1. Perecedero\n2. No perecedero");
										int opcionProducto = Integer.parseInt(sc.nextLine());
										switch (opcionProducto) {
											case (1): {
												System.out.println(gestionPedidos.listarProductos("perecederos"));
												System.out.println("Perecedero seleccionado");
												continue;
											}
											case (2): {
												System.out.println(gestionPedidos.listarProductos("no perecederos"));
												System.out.println("No perecedero seleccionado");
												continue;
											}
										}
										continue;
									}
									case (2): {
										System.out.println("Opci�n 2 seleccionada");
										continue;
									}
									case (3): {
										System.out.println("Opci�n 3 seleccionada");
										break;
									}
									default:
										System.out.println("La opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
										break;
									}
									break;
							}
						}
						continue;
						case (2): {
							System.out.println("Nueva contrase�a para el usuario " + login + ": ");
							String nuevaContrasena = sc.nextLine();
							gestionEmpleados.cambiarContrasena(empleadoActual, nuevaContrasena);
							continue;
						}
						case (3): {
							System.out.println("Hasta la pr�xima " + login);
							break;
						}
						default:
							System.out.println("La opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
							break;
						}
						break;
					}
				}
			}
		}
	}
}
