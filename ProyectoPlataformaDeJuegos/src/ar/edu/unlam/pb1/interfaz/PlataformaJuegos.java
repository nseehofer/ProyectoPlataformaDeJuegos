package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Categoria;
import ar.edu.unlam.pb1.dominio.Juego;
import ar.edu.unlam.pb1.dominio.MenuPrincipal;
import ar.edu.unlam.pb1.dominio.MenuUsuario;
import ar.edu.unlam.pb1.dominio.Plataforma;
import ar.edu.unlam.pb1.dominio.Usuario;

public class PlataformaJuegos {
	private static final int SALIR = 99;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		Plataforma plataforma = new Plataforma();
		MenuPrincipal opcionMenuPrincipal = null;
		int numeroIngresado = 0;

		do {
			mostrarPorPantalla("\nHola! Que deseas hacer en la plataforma de juegos?");
			mostrarMenuPrincipal();

			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			opcionMenuPrincipal = obtenerOpcionDeMenuPrincipal(numeroIngresado);

			switch (opcionMenuPrincipal) {
			case INICIAR_SESION:
				iniciarSesionEn(plataforma);
				break;
			case REGISTRARME:
				registrarme(plataforma);
				break;
			}

			// TODO: Complete la condicion para que el programa funcione correctamente

		} while ((!opcionMenuPrincipal.equals(MenuPrincipal.INICIAR_SESION))
				|| (!opcionMenuPrincipal.equals(MenuPrincipal.REGISTRARME)));

	}

//__________________________________________________________________________________

	private static void registrarme(Plataforma plataforma) {
		// TODO: Pide el ingreso de los datos necesarios para crear un usuario
		// Debe verificar que el correo sea valido y que no exista otro usuario
		// registrado en la plataforma con dicho correo (se sugiere buscar el usuario
		// por correo)
		// Una vez obtenidos los datos, se procede a registrar el usuario en la
		// plataforma mostrando un mensaje en caso de exito y otro en caso de error.

		// CREO QUE SE DEBE IMPLEMENTAR EL DOWHILE

		String correo = ingresarString("\nIngrese correo: ");

		boolean validezDelCorreoIngresado = plataforma.esValidoEl(correo);

		if (plataforma.buscarUsuarioConCorreo(correo) == null && validezDelCorreoIngresado == true) {

			Usuario usuarioCreado = new Usuario(ingresarString("\nIngrese nombre: "),
					ingresarString("\nIngrese apellido: "), correo, ingresarString("\nIngrese contrasenia: "));

			plataforma.registrarUsuario(usuarioCreado);

			mostrarPorPantalla("\nUsuario creado con exito!");
		}

	}

	private static void iniciarSesionEn(Plataforma plataforma) {
		mostrarPorPantalla("\n\nIniciemos sesion!");
		// TODO: Pide el ingreso de credenciales (usuario y contrasenia)
		// Verifica si con esas credenciales se puede iniciar sesion en la plataforma
		// Si el inicio de sesion es exitoso, se muestra un mensaje de exito y seguido
		// el menu del usuario (metodo menuUsuario() ), caso
		// contrario, se muestra el mensaje de error: "Usuario y/o contrasenia invalido"

		// ENTIENDO QUE DEBO CREAR UN USUARIO PORQUE YA SE QUE SI EL INICIO FALLA
		// DEBERIA DEVOLVER "null"
		// PERO SE SUPONE QUE NO DEBO CREAR EN ESTE METODO QUE SOLO DEBE
		// "iniciarSesionEn(plataforma)"
		// ES LOGICO QUE SE CREE EN "registrarme(plataforma)"

		Usuario usuarioIniciado = null;

		String correo = ingresarString("\nIngrese su correo");
		if (plataforma.buscarUsuarioConCorreo(correo) == null) {
			// mostrarPorPantalla("\nRegistremos el correo ;)");
			// mostrarMenuPrincipal();
		}

		String contrasenia = ingresarString("\nIngrese su contrasenia");

		// HAY CORRECCION

		usuarioIniciado = plataforma.iniciarSesion(correo, contrasenia);

		if (usuarioIniciado == null) {
			mostrarPorPantalla("\nUsuario y/o contrasenia invalido ");
		} else {

			menuUsuario(usuarioIniciado, plataforma);
		}

	}

//__________________________________________________________________________________

	private static void menuUsuario(Usuario usuario, Plataforma plataforma) {
		MenuUsuario opcionMenuUsuario = null;
		int numeroIngresado = 0;

		do {
			mostrarMenuUsuario();
			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			opcionMenuUsuario = obtenerOpcionDeMenuUsuario(numeroIngresado);
			int opcion = 0;

			switch (opcionMenuUsuario) {
			case MIS_JUEGOS:
				// TODO: Obtiene los juegos del usuario y los muestra.
				// Solicita el ingreso de un numero entero mostrando un mensaje. Dicho numero
				// corresponde con el ID de algun juego.
				// Se debe jugar al juego ingresado. Si se ingresa 99 se sale del menu actual.

				// Quise usar este metodo mostrarJuego(usuario.obtenerJuegoPorId(id));
				// int id = 0;
				// id = id = ingresarNumeroEntero("\nIngresar ID del juego que deseas jugar o 99
				// para ir al Menu Principal: ");
				// if()

				// NO CREE EL ARRAY, POR ESO NO LO MUESTRA
				// NO ESTABA ENTENDIENDO QUE TODAVIA PODIA APLICAR LA VARIABLE "opcion", ME
				// OLVIDE QUE ME ENCONTRABA DENTRO DEL SWITCH
				// UTILIZA UN DO WHILE

				Juego[] misJuegos = usuario.obtenerJuegosOrdenadosPorCategoria();
				do {
					mostrarJuegos(misJuegos);
					opcion = ingresarNumeroEntero(
							"\nIndique a que juego desea jugar ingresando su ID o ingrese 99 para salir");

					if (opcion != SALIR) {
						usuario.jugarAlJuegoDe(opcion);
					}

				} while (opcion != SALIR);

			

				break;
			case TIENDA:

				do {
					mostrarJuegos(plataforma.getJuegos());
					mostrarMenuTienda();
					opcion = ingresarNumeroEntero("\nIngrese opcion:");

					// TODO: Si el numero ingresado se encuentra entre 1 y 10 inclusive, entonces se
					// procede a adquirir el juego
					// Se sugiere obtener desde la plataforma el juego por su ID y luego agregarlo a
					// los juegos del usuario
					// Es necesario mostrar un mensaje de exito en caso de adquirir el juego.
					// Si no se pudo adquirir el juego, entonces mostrar el mensaje: "No fue posible
					// agregar el juego, verifica que no hayas adquirido antes."

					if (1 <= opcion && opcion <= 10) {

						Juego juego = plataforma.obtenerJuegoPorSuId(opcion);
						boolean agregado = usuario.agregarAMisJuegos(juego);

						// LE BORRE "= TRUE"
						if (agregado) {
							mostrarPorPantalla("\n Se agrego el juego! ");
						} else {

							mostrarPorPantalla(
									"\n No fue posible agregar el juego, verifica que no lo hayas adquirido antes. ");
						}

					}

				} while (opcion != SALIR);

				break;
			case JUEGO_MAS_JUGADO_POR_CATEGORIA:

				// TODO: Mostrar un titulo que indique para que categoria se mostrara el juego
				// mas jugado y luego mostrar el juego mas jugado de esa categoria.

				mostrarPorPantalla("\nJuego mas jugador de " + Categoria.AVENTURA);
				mostrarJuego(usuario.obtenerJuegoMasJugadoPorCategoria(Categoria.AVENTURA));
				mostrarPorPantalla("\nJuego mas jugador de " + Categoria.ACCION);
				mostrarJuego(usuario.obtenerJuegoMasJugadoPorCategoria(Categoria.ACCION));
				mostrarPorPantalla("\nJuego mas jugador de " + Categoria.DEPORTES);
				mostrarJuego(usuario.obtenerJuegoMasJugadoPorCategoria(Categoria.DEPORTES));
				
				break;
			case SALIR:
				//teclado.close();
				break;
			}

		} while (!opcionMenuUsuario.equals(MenuUsuario.SALIR));

	}

//__________________________________________________________________________________

	private static void mostrarJuego(Juego juego) {

		if (juego != null) {
			mostrarPorPantalla("\n" + juego.toString());
		} else {
			mostrarPorPantalla("\nSin juego");
		}
	}

	private static void mostrarJuegos(Juego[] juegos) {

		for (int i = 0; i < juegos.length; i++) {
			if (juegos[i] != null) {
				mostrarPorPantalla("\n" + juegos[i].toString());
			}
		}
	}

	private static MenuPrincipal obtenerOpcionDeMenuPrincipal(int numeroIngresado) {

		if (numeroIngresado > 2 && numeroIngresado < 0) {
			numeroIngresado = 2;
		}

		return MenuPrincipal.values()[numeroIngresado - 1];
	}

	private static MenuUsuario obtenerOpcionDeMenuUsuario(int numeroIngresado) {
		return MenuUsuario.values()[numeroIngresado - 1];
	}

	private static Categoria obtenerOpcionDeCategoria(int numeroIngresado) {
		return Categoria.values()[numeroIngresado - 1];
	}

	private static void mostrarCategorias() {
		for (int indice = 0; indice < Categoria.values().length; indice++) {
			System.out.println("\nCategorias" + (indice + 1) + "-" + Categoria.values()[indice]);
		}
	}

	private static int ingresarNumeroEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarMenuTienda() {
		mostrarPorPantalla("\n\nIngrese el ID del juego que desea adquirir o 99 para salir:");
	}

	private static void mostrarMenuUsuario() {
		mostrarPorPantalla("\n\n1) Mis juegos\n2) Tienda\n3) Juego mas jugado por categoria\n4) Salir");
	}

	private static void mostrarMenuPrincipal() {
		mostrarPorPantalla("\n\n1) Iniciar sesion\n2) Registrame");
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
