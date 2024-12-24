package ar.edu.unlam.pb1.dominio;

public class Juego {
	// TODO: Completar la clase con lo necesario para garantizar el correcto
	// funcionamiento

	private int id;
	private String nombre;
	private Categoria categoria;
	private double horasJugadas;

	public Juego(int id, String nombre, Categoria categoria) {
		this.id = id;
		this.categoria = categoria;
		this.nombre = nombre;
		this.horasJugadas = 0;

	}

	// ______________S&G_________________________

	public void setCantidadDeHorasJugadas(double horas) {

		this.horasJugadas += horas;

	}

	public double getCantidadDeHorasJugadas() {
		return horasJugadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", horasJugadas=" + horasJugadas
				+ "]";
	}

}
