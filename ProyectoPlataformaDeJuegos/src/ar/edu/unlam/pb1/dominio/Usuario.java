package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;

public class Usuario {

	private Juego[] juego;
	private String nombre, apellido, correo, contrasenia;

	public Usuario(String nombre, String apellido, String correo, String contrasenia) {

		this.apellido = apellido;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.juego = new Juego[1000];
	}

	public boolean agregarAMisJuegos(Juego juego) {

		boolean agregado = false;
		int indice = 0;

		if (!this.tengoElJuegoDe(juego.getId()))

			while (!agregado && indice < this.juego.length) {

				if (this.juego[indice] == null) {
					this.juego[indice] = juego;
					agregado = true;
				}

				indice++;
			}

		return agregado;
	}

	public boolean tengoElJuegoDe(int id) {

		boolean loTengo = false;
		int posicion = 0;

		while (posicion < this.juego.length && !loTengo) {

			if (this.juego[posicion] != null && this.juego[posicion].getId() == id) {

				loTengo = true;

			}

			posicion++;
		}

		return loTengo;
	}

	public Juego obtenerJuegoMasJugadoPorCategoria(Categoria categoria) {

		Juego juegoMasJugado = null;

		double mayorCantidadDeHoras = 0;

		for (int indice = 1; indice < this.juego.length; indice++) {
			if (this.juego[indice] != null && this.juego[indice].getCategoria().equals(categoria)) {
				mayorCantidadDeHoras = this.juego[0].getCantidadDeHorasJugadas();
				if (this.juego[indice].getCantidadDeHorasJugadas() > mayorCantidadDeHoras) {
					mayorCantidadDeHoras = this.juego[indice].getCantidadDeHorasJugadas();
				}

				juegoMasJugado = this.juego[indice];
			}
		}

		return juegoMasJugado;
	}

	public Juego obtenerJuegoPorId(int id) {

		Juego juegoPorId = null;
		boolean encontrado = false;
		int posicion = 0;

		while (posicion < this.juego.length && !encontrado) {
			if (this.juego[posicion] != null && this.juego[posicion].getId() == id) {

				juegoPorId = this.juego[posicion];
			}

			posicion++;
		}

		return juegoPorId;
	}

	// ___________________________

	public Juego[] obtenerJuegosOrdenadosPorCategoria() {

		Juego auxiliar = null;

		for (int i = 1; i < this.juego.length; i++) {

			for (int j = 0; j < this.juego.length - 1; j++) {

				if (this.juego[j] != null && this.juego[j + 1] != null) {

					if (this.juego[j].getCategoria().compareTo(this.juego[j + 1].getCategoria()) > 0) {
						auxiliar = this.juego[j];
						this.juego[j] = this.juego[j + 1];
						this.juego[j + 1] = auxiliar;
					}
				}
			}
		}

		return this.juego;
	}

	public void jugarAlJuegoDe(int id) {

		if (tengoElJuegoDe(id) == true) {
			obtenerJuegoPorId(id).setCantidadDeHorasJugadas(1.5);
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Juego[] getJuego() {
		return juego;
	}

	public void setJuego(Juego[] juego) {
		this.juego = juego;
	}

	public String toString() {
		return "Usuario [juego=" + Arrays.toString(juego) + "]";
	}

}
