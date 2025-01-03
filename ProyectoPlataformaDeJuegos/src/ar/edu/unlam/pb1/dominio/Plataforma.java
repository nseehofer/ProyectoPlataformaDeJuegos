package ar.edu.unlam.pb1.dominio;

public class Plataforma {

	private Usuario[] usuarios;
	private Juego[] juegos;

	public Plataforma() {
		this.usuarios = new Usuario[1000];
		this.juegos = new Juego[10];
		this.inicializarPlataforma(); // :)
	}

	public Usuario buscarUsuarioConCorreo(String correo) {

		int indice = 0;
		Usuario usuarioDelCorreoIngresado = null;

		while (indice < this.usuarios.length && usuarioDelCorreoIngresado == null) {
			if (this.usuarios[indice] != null && this.usuarios[indice].getCorreo().equals(correo)) {
				usuarioDelCorreoIngresado = this.usuarios[indice];
			}

			indice++;
		}

		return usuarioDelCorreoIngresado;
	}

	public Usuario iniciarSesion(String correo, String contrasenia) {

		Usuario usuarioIngresado = null;

		buscarUsuarioConCorreo(correo);

		if (buscarUsuarioConCorreo(correo).getContrasenia().equals(contrasenia)) {

			usuarioIngresado = buscarUsuarioConCorreo(correo);
			System.out.println("\nInicio de sesion exitoso ");

		} else {
			System.out.println("\nERROR: Usuario y/o contraseia invalido ");
		}

		return usuarioIngresado;

	}

	public boolean esValidoEl(String correo) {

		boolean validado = false;

		if (correo.contains("@") && correo.endsWith(".com")) {
			validado = true;
		} else {
			validado = false;
		}

		return validado;
	}

	public boolean registrarUsuario(Usuario usuario) {

		int indice = 0;
		boolean seRegistro = false;
		while (indice < usuarios.length && !seRegistro) {
			if (usuarios[indice] == null) {

				usuarios[indice] = usuario;
				seRegistro = true;

			}

			indice++;
		}

		return seRegistro;
	}

	public Juego obtenerJuegoPorSuId(int id) {

		Juego juegoEncontrado = null;
		int posicion = 0;

		while (posicion < this.juegos.length && juegoEncontrado == null) {

			if (this.juegos[posicion] != null && this.juegos[posicion].getId() == id) {

				juegoEncontrado = this.juegos[posicion];
			}

			posicion++;

		}

		return juegoEncontrado;
	}

	public Juego[] getJuegos() {
		return this.juegos;
	}

	private Juego crearJuego(int id, String nombre, Categoria categoria) {
		return new Juego(id, nombre + " " + id, categoria);
	}

	private void inicializarPlataforma() {

		String nombre = "";
		Categoria categoria;

		for (int i = 0; i < this.juegos.length; i++) {

			if (i < 3) {
				nombre = "Aventura";
				categoria = Categoria.AVENTURA;
			} else if (i < 7) {
				nombre = "Accion";
				categoria = Categoria.ACCION;
			} else {
				nombre = "Deportes";
				categoria = Categoria.DEPORTES;
			}

			this.juegos[i] = crearJuego((i + 1), nombre, categoria);
		}

	}

}
