import java.util.Scanner;

public class Aplicacion {
	public static void main(String[] args) {
		GestionEmpleados gestionEmpleados = new GestionEmpleados();

		try (Scanner sc = new Scanner(System.in)) {
			while (true) {
				/* Bucle principal para la autenticación */
				System.out.println("Usuario:");
				String login = sc.nextLine();
				System.out.println("\nContraseña:");
				String password = sc.nextLine();

				/* Se autentica el empleado mediante el objeto gestionEmpleados */
				Empleado empleadoActual = gestionEmpleados.autenticar(login, password);

				if (empleadoActual != null) { // En caso de que empleadoActual no sea null (es decir, que se haya autenticado correctamente)
					/*
					 * De nuevo uso un bucle infinito, para que no se vuelva a la pantalla de
					 * autenticación a no ser que se use una opción determinada
					 */

					while (true) {
						/* Se pide al empledo que seleccione una opción */
						try {

							System.out.println("\n1. Hacer pedido\n2. Cambiar contraseña\n3. Salir");
							int opcion = Integer.parseInt(sc.nextLine());
							switch (opcion) {
							case 1: {
								/* Hacer pedido */
								/*
								 * Se crea el objeto encargado de toda la gestión de los pedidos, pasando como
								 * parámetro el empleado que lo está haciendo (para ponerlo luego en la factura)
								 */
								GestionPedidos gestionPedidos = new GestionPedidos(empleadoActual);
								while (true) {
									/* Bucle para añadir un producto al pedido, ver el precio total o terminar el pedido */
									try {
										System.out.println(
												"\n1. Añadir producto\n2. Visualizar precio total\n3. Terminar pedido");
										int opcionPedido = Integer.parseInt(sc.nextLine());
										switch (opcionPedido) {
										case 1: {
											while (true) {
												/* Añadir producto */
												// Bucle para saber si es un producto perecedero o no perecedero
												try {
													System.out.println("\n1. Perecedero\n2. No perecedero");
													int opcionProducto = Integer.parseInt(sc.nextLine());
													switch (opcionProducto) {
													case (1): {
														/* Perecedero */
														gestionPedidos.listarProductos("perecederos");
														System.out.println("\nCódigo de producto: ");
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
														System.out.println("\nCódigo de producto: ");
														int codigo = Integer.parseInt(sc.nextLine());
														System.out.println("\nUnidades: ");
														int unidades = Integer.parseInt(sc.nextLine());
														System.out.println(gestionPedidos.anadirProducto(codigo,
																"no perecederos", unidades));
													}
														break;
													default: {
														/* Si el número de opción no existe: */
														System.out.println(
																"\nLa opción que ha elegido no existe, inténtelo de nuevo.\n");
														continue;
													}
													}
													break;
												} catch (Exception e) {
													System.out
															.println("\nLa opción no es válida. Inténtelo de nuevo.\n");
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
											/* Si el número de opción no existe: */
											System.out.println(
													"\nLa opción que ha elegido no existe, inténtelo de nuevo.\n");
											continue;
										}
										}
										break;
									} catch (Exception e) {
										System.out.println("\nLa opción no es válida. Inténtelo de nuevo.\n");
									}
								}
								continue;
							}
							case 2: {
								/* Cambiar la contraseña del empleado */
								System.out.println("\nNueva contraseña para el usuario " + login + ": ");
								String nuevaContrasena = sc.nextLine();
								gestionEmpleados.cambiarContrasena(empleadoActual, nuevaContrasena);
								continue;
							}
							case 3: {
								/* Finalizar la sesión y volver a la pantalla de autenticación */
								System.out.println("\nHasta la próxima " + login);
								break;
							}
							default: {
								/* Si el número de opción no existe: */
								System.out.println("\nLa opción que ha elegido no existe, inténtelo de nuevo.\n");
								continue;
							}
							}
							break;
						} catch (Exception e) {
							System.out.println("\nLa opción no es válida. Inténtelo de nuevo.\n");
						}
					}
				} else {
					/*
					 * En caso de que empleadoActual sea null (es decir, que las credenciales sean
					 * incorrectas)
					 */
					System.out.println("\nUsuario o contraseña incorrectos. Inténtelo de nuevo.\n");
				}
			}
		}
	}
}