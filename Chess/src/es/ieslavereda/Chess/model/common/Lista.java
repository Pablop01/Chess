package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 * @param <T> Tipo de datos de la lista
 */

public class Lista<T> {

	private int size;
	private Nodo<T> cabeza;
	private Nodo<T> cola;

	/**
	 * Constructor por defecto de la lista
	 */
	
	public Lista() {
		super();

		size = 0;
		cabeza = null;
		cola = null;
		
	}

	/**
	 * Indica el tamaño de la lista
	 * @return Entero con el tamaño de la lista
	 */
	
	public int getSize() {
		return size;
	}

	/**
	 * Indica si la lista esta vacia
	 * @return Devuelve True si esta vacia o false en caso contrario
	 */
	
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Añade un nodo a la cabeza de la lista
	 * @param info Informacion que contiene el nuevo nodo
	 */
	
	public void addHead(T info) {

		Nodo<T> nodo = new Nodo<T>(info);

		if (cabeza == null) {
			cabeza = nodo;
			cola = nodo;
		} else {
			nodo.setSiguiente(cabeza);
			cabeza.setAnterior(nodo);
			cabeza = nodo;
		}

		size++;
	}

	/**
	 * Añade una lista de nodos a la lista de nodos que ya tenemos
	 * @param lista lista que queremos añadir
	 * @return Devuelve la lista
	 */
	
	public Lista<T> addAll(Lista<T> lista) {

		Nodo<T> aux = lista.cabeza;
		while (aux != null) {
			addHead(aux.getInfo());
			aux = aux.getSiguiente();
		}

		return this;

	}

	/**
	 * Indica si contiene el elemento que le hemos pasado por parametro
	 * @param elemento Elemento que queremos buscar
	 * @return Devuelve true en caso de que lo contenga
	 */
	
	public boolean contains(T elemento) {
		boolean contiene = false;
		Nodo<T> nodo = cabeza;
		while (nodo != null && !contiene) {
			if (nodo.getInfo().equals(elemento))
				contiene = true;
			nodo = nodo.getSiguiente();
		}

		return contiene;
	}

	/**
	 * Añade un nodo a la cola de la lista
	 * @param info Informacion que contiene el nuevo nodo
	 */
	
	public void addTail(T info) {

		Nodo<T> aux = new Nodo<T>(info);

		if (cabeza == null) {
			cabeza = aux;
			cola = aux;
		} else {
			aux.setAnterior(cola);
			cola.setSiguiente(aux);
			cola = aux;
		}
		size++;
	}

	/**
	 * Obtener el Nodo que hay en la cabeza de la lista y eliminarlo
	 * @return Nodo 
	 */
	
	public T getHead() {
		T valor = null;

		if (cabeza == null) {
			return null;
		} else if (cabeza.equals(cola)) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size--;
		} else {
			valor = cabeza.getInfo();
			cabeza = cabeza.getSiguiente();
			cabeza.setAnterior(null);
			size--;
		}

		return valor;
	}
	
	/**
	 * Obtener el nodo que hay ne la cabeza de la lista
	 * @return Nodo
	 */

	public Nodo<T> getNodoHead() {
		return cabeza;
	}
	
	/**
	 * Obtener el Nodo que hay en la cola de la lista y eliminarlo
	 * @return Nodo 
	 */
	
	public T getTail() {
		T valor = null;

		if (cabeza == null) {
			return null;
		} else if (cabeza == cola) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size--;
		} else {
			valor = cola.getInfo();
			cola.getAnterior().setSiguiente(null);
			cola = cola.getAnterior();
			size--;
		}

		return valor;

	}

	/**
	 * Busca un nodo que contenga la informacion pasada por parametro
	 * @param info Informacion que queremos busca
	 * @return Entero con la posicion del nodo en caso de que contenga la informacion
	 */
	
	public int search(T info) {

		int posicion = -1;

		Nodo<T> aux = cabeza;
		int i = 0;

		while (aux != null && posicion == -1) {
			if (info.equals(aux.getInfo()))
				posicion = i;
			i++;
			aux = aux.getSiguiente();
		}

		return posicion;
	}

	/**
	 * Obtener y eliminar el nodo que este en la posicion que le pasamos por parametro
	 * @param index Posicion del nodo
	 * @return La informacion del nodo
	 */
	
	public T get(int index) {

		if (index == 0)
			return getHead();
		else if (index == size - 1)
			return getTail();
		else if (index > 0 && index < size) {

			Nodo<T> aux = cabeza;
			int i = 0;
			while (i < index) {
				aux = aux.getSiguiente();
				i++;
			}

			aux.getAnterior().setSiguiente(aux.getSiguiente());
			aux.getSiguiente().setAnterior(aux.getAnterior());
			size--;

			return aux.getInfo();

		} else
			return null;
	}
	
	/**
	 * Obtener y eliminar el nodo que este en la posicion que le pasamos por parametro
	 * @param index Posicion del nodo
	 * @return La informacion del nodo
	 */
	
	public T getAndRemove(T elemento) {

		if (elemento.equals(cabeza.getInfo()))
			return getHead();
		else if (elemento.equals(cola.getInfo()))
			return getTail();
		else {

			Nodo<T> aux = cabeza;
			T valor = null;

			while (aux != null && valor == null) {
				if (aux.getInfo().equals(elemento))
					valor = aux.getInfo();
				else
					aux = aux.getSiguiente();
			}
			if (valor != null) {
				aux.getAnterior().setSiguiente(aux.getSiguiente());
				aux.getSiguiente().setAnterior(aux.getAnterior());
				size--;
			}
			return valor;

		}
	}

	@Override
	public String toString() {

		String salida = "";
		salida = "size: " + size + "\n";
		salida += "valores:\n ";

		Nodo<T> aux = cabeza;

		while (aux != null) {
			salida += aux.toString() + " ";
			aux = aux.getSiguiente();
		}

		return salida;
	}

}
