package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public class Queen extends Pieza{

	/**
	 * Constructor de la pieza de tipo Reina
	 * @param color Para obtener el color del que queremos la pieza
	 * @param posicion Indica la posicion inicial de la pieza
	 * @param tablero Tablero en el que esta la pieza
	 */
	
	public Queen(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_QUEEN;
		else
			tipo = Tipo.BLACK_QUEEN;
	}

	/**
	 * Junta los movimientos de la torre y del alfil, y luego devuelve la lista de movimientos disponibles
	 * @return Lista de coordenadas
	 */
	
	@Override
	public Lista<Coordenada> getNextMovements() {
		
		Lista<Coordenada> lista = Rook.getNextMovements(this);
		
		lista.addAll(Bishop.getNextMovements(this));
		
		return lista;
	}

}
