package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public class Celda {
	
	private Pieza pieza;

	/**
	 * Constructor de celda
	 */
	
	public Celda() {
		super();
		pieza = null;
	}

	/**
	 * Obtener pieza que hay en una celda
	 * @return Devuelve la pieza
	 */
	
	public Pieza getPieza() {
		return pieza;
	}

	/**
	 * Pone una pieza en la celda
	 * @param pieza Pieza que queremos poner
	 */
	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	/**
	 * Indica si la celda contiene pieza
	 * @return Devuelve true en caso de que haya pieza
	 */
	
	public boolean contienePieza() {
		return pieza!=null;
	}
	
	@Override
	public String toString() {
		if(pieza==null)
			return " ";
		else
			return pieza.toString();
	}
}
