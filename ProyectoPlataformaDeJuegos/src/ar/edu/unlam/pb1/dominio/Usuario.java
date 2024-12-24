package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;

public class Usuario {

	private Juego[] juego;
	private String nombre, apellido, correo, contrasenia;

	public Usuario(String nombre, String apellido, String correo, String contrasenia) {
		// Daremos un espacio de 1000 juegos a cada nuevo usuario
		this.apellido = apellido;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.juego = new Juego[1000];
	}

	public boolean agregarAMisJuegos(Juego juego) {
		// TODO: Verificar no tener el juego adquirido ( Ver el metodo tengoElJuegoDe())
		// En caso de no tenerlo, se agrega el juego suministrado a los juegos del
		// usuario

		boolean agregado = false;
		int indice = 0 ;
		
		if (!this.tengoElJuegoDe(juego.getId()))
			
		while(!agregado && indice < this.juego.length) {
			
			if(this.juego[indice] == null) {
				this.juego[indice] = juego;
				agregado = true;
			}
			
			indice++;
		}

		/* HICE ESTO, ESTOY CHIPEADO CON EL "!= NULL", ACA TENIA QUE SER SI O SI "== NULL"
		 * for (int indice = 0; indice < this.juego.length; indice++) {

			if (this.juego[indice] != null && this.juego[indice].equals(juego)) {

				agregar = false;

			} else if (this.juego[indice] != null) {
				this.juego[indice] = juego;
				agregar = true;
			}

		}*/

		return agregado;
	}

	public boolean tengoElJuegoDe(int id) {
		// TODO: Verifica si tengo un juego con el id suministrado en mis juegos
		// Devuelve verdadero en caso de poseer el juego.

		boolean loTengo = false;
		int posicion = 0;

		// CORRECCION EN LA CONDICION FALTA !loTengo

		while (posicion < this.juego.length && !loTengo) {

			if (this.juego[posicion] != null && this.juego[posicion].getId() == id) {

				loTengo = true;

			}

			posicion++;
		}

		return loTengo;
	}

	public Juego obtenerJuegoMasJugadoPorCategoria(Categoria categoria) {
		// TODO: Revisa los juegos que cumplen con la categoria suministrada y obtiene
		// el juego mas jugado de dicha categoria
		// Si no existe un juego para esa categoria, devuelve null

		// USO UN FOR FALTA COMPLETAR LA CONDICION Y DEMAS.. VER VIDEO

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

	// ___________agrego un metodo

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
		// Obtiene los juegos del usuario ordenados por categoria.
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
		/*{
		// TODO: Obtiene los juegos del usuario ordenados por categoria.
		Juego auxiliar = null;

		for (int i = 0; i < this.juego.length; i++) {
			for (int j = 0; j < this.juego.length - 1; j++) {

				if ((this.juego[j] != null && this.juego[j + 1] != null)
						&& (this.juego[j].getCategoria().ordinal() > this.juego[j + 1].getCategoria().ordinal())) {

					auxiliar = this.juego[j];
					this.juego[j] = this.juego[j + 1];
					this.juego[j + 1] = auxiliar;

				}

			}
		}

		return this.juego;
	}*/

		return this.juego;
	}

	public void jugarAlJuegoDe(int id) {
		// TODO: Revisa entre los juegos si alguno tiene el id suministrado. Si lo
		// encuentra, le agrega 1 hora y media (1.5) a la cantidad de horas jugadas.
		// Siempre deberia encontrar el juego con el id que llega como parametro

		if (tengoElJuegoDe(id) == true) {
			obtenerJuegoPorId(id).setCantidadDeHorasJugadas(1.5);
		}

	}

	// ______________S&G_________________________

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
