package ar.com.casa.unittesting.model;

public class MiClase {
	private String unaCadena;
	private int unEntero;

	public MiClase() {

	}

	public MiClase(String unaCadena, int unEntero) {
		super();
		this.unaCadena = unaCadena;
		this.unEntero = unEntero;
	}

	public String getUnaCadena() {
		return unaCadena;
	}

	public void setUnaCadena(String unaCadena) {
		this.unaCadena = unaCadena;
	}

	public int getUnEntero() {
		return unEntero;
	}

	public void setUnEntero(int unEntero) {
		this.unEntero = unEntero;
	}

}
