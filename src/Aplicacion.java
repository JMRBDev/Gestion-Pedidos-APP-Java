import java.util.Scanner;

public class Aplicacion {
	public static void main(String[] args) {
		/* gestionEmpleados se encarga de todas las tareas referentes a organizar y autentificar a los empleados */
		GestionEmpleados gestionEmpleados = new GestionEmpleados();
		
		try (Scanner sc = new Scanner(System.in)) {
			/* Comienza el bucle infinito del programa */
			while (true) {
				System.out.println("Usuario:");
				String login = sc.nextLine();
				System.out.println("Contrase�a:");
				String password = sc.nextLine();
				
				/* Se autentica el empleado mediante el objeto gestionEmpleados */
				Empleado empleadoActual = gestionEmpleados.autenticar(login, password);
				
				if (empleadoActual != null) { // En caso de que empleadoActual no sea null (es decir, que se haya autenticado correctamente)
					/* De nuevo uso un bucle infinito, para que no se vuelva a la pantalla de autenticaci�n a no ser que se use una opci�n determinada */
					while (true) {
						/* Se pide al empledo que seleccione una opci�n */
						System.out.println("1. Hacer pedido\n2. Cambiar contrase�a\n3. Salir");
						int opcion = Integer.parseInt(sc.nextLine());
						switch (opcion) {
						case (1): {
							/* Hacer pedido */
							/* Se crea el objeto encargado de toda la gesti�n de los pedidos, pasando como par�metro el empleado que lo est� haciendo (para ponerlo luego en la factura) */
							GestionPedidos gestionPedidos = new GestionPedidos(empleadoActual);
							while (true) {
								System.out.println("1. A�adir producto\n2. Visualizar precio total\n3. Terminar pedido");
								int opcionPedido = Integer.parseInt(sc.nextLine());
								switch (opcionPedido) {
									case (1): {
										/*A�adir producto*/
										System.out.println("1. Perecedero\n2. No perecedero");
										int opcionProducto = Integer.parseInt(sc.nextLine());
										switch (opcionProducto) {
											case (1): {
												/* Perecedero */
												System.out.println(gestionPedidos.listarProductos("perecederos"));
												System.out.println("C�digo de producto: ");
												int codigo = Integer.parseInt(sc.nextLine());
												System.out.println("Unidades: ");
												int unidades = Integer.parseInt(sc.nextLine());
												System.out.println(gestionPedidos.anadirProducto(codigo, "perecederos", unidades));
												continue;
											}
											case (2): {
												/* No perecedero */
												System.out.println(gestionPedidos.listarProductos("no perecederos"));
												System.out.println("C�digo de producto: ");
												int codigo = Integer.parseInt(sc.nextLine());
												System.out.println("Unidades: ");
												int unidades = Integer.parseInt(sc.nextLine());
												System.out.println(gestionPedidos.anadirProducto(codigo, "no perecederos", unidades));
												continue;
											}
										}
										continue;
									}
									case (2): {
										/* Visualizar precio total */
										System.out.println(gestionPedidos.visualizarPrecioTotal());
										continue;
									}
									case (3): {
										/* Terminar pedido */
										System.out.println("Pedido finalizado.\n");
										System.out.println(gestionPedidos.mostrarFactura());
										empleadoActual.setVentas(empleadoActual.getVentas() + 1);
										break;
									}
									default:
										/* Si el n�mero de opci�n no existe: */
										System.out.println("La opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
										break;
									}
									break;
							}
						}
						continue;
						case (2): {
							/* Cambiar la contrase�a del empleado */
							System.out.println("Nueva contrase�a para el usuario " + login + ": ");
							String nuevaContrasena = sc.nextLine();
							gestionEmpleados.cambiarContrasena(empleadoActual, nuevaContrasena);
							continue;
						}
						case (3): {
							/* Finalizar la sesi�n y volver a la pantalla de autenticaci�n */
							System.out.println("Hasta la pr�xima " + login);
							break;
						}
						default:
							/* Si el n�mero de opci�n no existe: */
							System.out.println("La opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
							break;
						}
						break;
					}
				} else {
					/* En caso de que empleadoActual sea null (es decir, que las credenciales sean incorrectas) */
					System.out.println("Usuario o contrase�a incorrectos. Int�ntelo de nuevo.\n");
				}
			}
		}
	}
}
