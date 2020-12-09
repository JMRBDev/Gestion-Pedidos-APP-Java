import java.util.Scanner;

public class Aplicacion {
	public static void main(String[] args) {
		GestionEmpleados gestionEmpleados = new GestionEmpleados();

		try (Scanner sc = new Scanner(System.in)) {
			while (true) {
				/* Bucle principal para la autenticaci�n */
				System.out.println("Usuario:");
				String login = sc.nextLine();
				System.out.println("\nContrase�a:");
				String password = sc.nextLine();

				/* Se autentica el empleado mediante el objeto gestionEmpleados */
				Empleado empleadoActual = gestionEmpleados.autenticar(login, password);

				if (empleadoActual != null) { // En caso de que empleadoActual no sea null (es decir, que se haya autenticado correctamente)
					/*
					 * De nuevo uso un bucle infinito, para que no se vuelva a la pantalla de
					 * autenticaci�n a no ser que se use una opci�n determinada
					 */

					while (true) {
						/* Se pide al empledo que seleccione una opci�n */
						try {

							System.out.println("\n1. Hacer pedido\n2. Cambiar contrase�a\n3. Salir");
							int opcion = Integer.parseInt(sc.nextLine());
							switch (opcion) {
							case 1: {
								/* Hacer pedido */
								/*
								 * Se crea el objeto encargado de toda la gesti�n de los pedidos, pasando como
								 * par�metro el empleado que lo est� haciendo (para ponerlo luego en la factura)
								 */
								GestionPedidos gestionPedidos = new GestionPedidos(empleadoActual);
								while (true) {
									/* Bucle para a�adir un producto al pedido, ver el precio total o terminar el pedido */
									try {
										System.out.println(
												"\n1. A�adir producto\n2. Visualizar precio total\n3. Terminar pedido");
										int opcionPedido = Integer.parseInt(sc.nextLine());
										switch (opcionPedido) {
										case 1: {
											while (true) {
												/* A�adir producto */
												// Bucle para saber si es un producto perecedero o no perecedero
												try {
													System.out.println("\n1. Perecedero\n2. No perecedero");
													int opcionProducto = Integer.parseInt(sc.nextLine());
													switch (opcionProducto) {
													case (1): {
														/* Perecedero */
														gestionPedidos.listarProductos("perecederos");
														System.out.println("\nC�digo de producto: ");
														int codigo = Integer.parseInt(sc.nextLine());
														System.out.println("\nUnidades: ");
														int unidades = Integer.parseInt(sc.nextLine());
														System.out.println(gestionPedidos.anadirProducto(codigo,
																"perecederos", unidades));
													}
														break;
													case (2): {
														/* No perecedero */
														gestionPedidos.listarProductos("no perecederos");
														System.out.println("\nC�digo de producto: ");
														int codigo = Integer.parseInt(sc.nextLine());
														System.out.println("\nUnidades: ");
														int unidades = Integer.parseInt(sc.nextLine());
														System.out.println(gestionPedidos.anadirProducto(codigo,
																"no perecederos", unidades));
													}
														break;
													default: {
														/* Si el n�mero de opci�n no existe: */
														System.out.println(
																"\nLa opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
														continue;
													}
													}
													break;
												} catch (Exception e) {
													System.out
															.println("\nLa opci�n no es v�lida. Int�ntelo de nuevo.\n");
												}
											}
											continue;
										}
										case 2: {
											/* Visualizar precio total */
											System.out.println(gestionPedidos.visualizarPrecioTotal());
											continue;
										}
										case 3: {
											/* Terminar pedido */
											System.out.println("\nPedido finalizado.\n");
											if (gestionPedidos.getPedido().getProductos().size() > 0) {
												empleadoActual.setVentas(empleadoActual.getVentas() + 1);
											}
											gestionPedidos.mostrarFactura();
											break;
										}
										default: {
											/* Si el n�mero de opci�n no existe: */
											System.out.println(
													"\nLa opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
											continue;
										}
										}
										break;
									} catch (Exception e) {
										System.out.println("\nLa opci�n no es v�lida. Int�ntelo de nuevo.\n");
									}
								}
								continue;
							}
							case 2: {
								/* Cambiar la contrase�a del empleado */
								System.out.println("\nNueva contrase�a para el usuario " + login + ": ");
								String nuevaContrasena = sc.nextLine();
								gestionEmpleados.cambiarContrasena(empleadoActual, nuevaContrasena);
								continue;
							}
							case 3: {
								/* Finalizar la sesi�n y volver a la pantalla de autenticaci�n */
								System.out.println("\nHasta la pr�xima " + login);
								break;
							}
							default: {
								/* Si el n�mero de opci�n no existe: */
								System.out.println("\nLa opci�n que ha elegido no existe, int�ntelo de nuevo.\n");
								continue;
							}
							}
							break;
						} catch (Exception e) {
							System.out.println("\nLa opci�n no es v�lida. Int�ntelo de nuevo.\n");
						}
					}
				} else {
					/*
					 * En caso de que empleadoActual sea null (es decir, que las credenciales sean
					 * incorrectas)
					 */
					System.out.println("\nUsuario o contrase�a incorrectos. Int�ntelo de nuevo.\n");
				}
			}
		}
	}
}