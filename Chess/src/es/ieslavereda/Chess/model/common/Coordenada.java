package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public class Coordenada {

	private int row;
	private char column;
	
	/**
	 * Constructor de coordenada
	 * @param column Columna en la que esta
	 * @param row Fila en la que esta
	 */
	
	public Coordenada( char column,int row) {
		super();
		this.row = row;
		this.column = column;
		
	}

	/**
	 * Mueve una celda hacia arriba respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada up() {
		return new Coordenada( column,row + 1);
	}

	/**
	 * Mueve una celda hacia bajo respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada down() {
		return new Coordenada( column,row - 1);
	}

	/**
	 * Mueve una celda hacia la izquierda respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada left() {
		return new Coordenada((char) (column - 1),row);
	}

	/**
	 * Mueve una celda hacia la derecha respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada right() {
		return new Coordenada( (char) (column + 1),row);
	}
	
	/**
	 * Mueve una celda en diagonal, hacia arriba a la izquierda respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada diagonalUpLeft() {
		return this.up().left();
	}
	
	/**
	 * Mueve una celda en diagonal, hacia arriba a la derecha respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada diagonalUpRight() {
		return up().right();
	}
	
	/**
	 * Mueve una celda en diagonal, hacia bajo a la derecha respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada diagonalDownRight() {
		return down().right();
	}
	
	/**
	 * Mueve una celda en diagonal, hacia bajo a la izquierda respecto a la que se encuentra
	 * @return La nueva coordenada
	 */
	
	public Coordenada diagonalDownLeft() {
		return down().left();
	}
	
	/**
	 * Indica la Fila en la que se encuentra
	 * @return Entero que indica la fila
	 */
	
	public int getRow() {
		return row;
	}

	/**
	 * Indica la Columna en la que se encuentra
	 * @return Entero que indica la columna
	 */
	
	public char getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return String.valueOf(column) + row;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	
	
}
