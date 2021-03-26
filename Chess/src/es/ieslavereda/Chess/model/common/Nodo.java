package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 * @param <T> Tipo de dato del nodo
 */

public class Nodo<T> {
	
	private T info;
	private Nodo<T> siguiente;
	private Nodo<T> anterior;
	
	/**
	 * Constructor de Nodo
	 * @param info informacion que va a contener el nodo
	 */
	
	public Nodo(T info) {
		this.info=info;
		siguiente = null;
		anterior = null;
	}

	/**
	 * Devuelve el siguiente nodo
	 * @return Nodo
	 */
	
	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	/**
	 * Asigna el siguiente nodo
	 * @param siguiente indica que nodo es el siguiente
	 */
	
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * Devuelve el nodo anterior
	 * @return Nodo
	 */
	
	public Nodo<T> getAnterior() {
		return anterior;
	}

	/**
	 * Asigna el nodo anterior
	 * @param siguiente indica que nodo es el anterior
	 */
	
	public void setAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}

	/**
	 * Devuelve la informacion que contiene el nodo
	 * @return Informacion
	 */
	
	public T getInfo() {
		return info;
	}
	
	@Override
	public String toString() {
		return info.toString();
	}

}
