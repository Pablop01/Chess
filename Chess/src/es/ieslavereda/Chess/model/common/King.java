package es.ieslavereda.Chess.model.common;

import java.util.ArrayList;

/**
 * 
 * @author ppereaf
 *
 */

public class King extends Pieza {

	/**
	 * Constructor de la pieza de tipo Rey
	 * @param color Para obtener el color del que queremos la pieza
	 * @param posicion Indica la posicion inicial de la pieza
	 * @param tablero Tablero en el que esta la pieza
	 */
	
	public King(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		if (color == Color.WHITE)
			tipo = Tipo.WHITE_KING;
		else
			tipo = Tipo.BLACK_KING;
		
		colocate(posicion);
		
	}

	/**
	 * Devuelve la lista de movimientos disponibles despues de hacer todas las comprobaciones necesarias
	 * @return Lista de coordenadas
	 */
	
	@Override
	public ArrayList<Coordenada> getNextMovements() {

		ArrayList<Coordenada> lista = new ArrayList<Coordenada>();

		addCoordenada(posicion.up(), lista);
		addCoordenada(posicion.right(), lista);
		addCoordenada(posicion.down(), lista);
		addCoordenada(posicion.left(), lista);
		addCoordenada(posicion.diagonalUpRight(), lista);
		addCoordenada(posicion.diagonalUpLeft(), lista);
		addCoordenada(posicion.diagonalDownRight(), lista);
		addCoordenada(posicion.diagonalDownLeft(), lista);

		return lista;
	}

	/**
	 * Comprueba que se puede mover a la coordenada que le hemos pasado y la añade a la lista en el caso de que pueda
	 * @param c Coordenada que le pasamos para comprobar
	 * @param lista Lista de los siguientes movimientos
	 */
	
	private void addCoordenada(Coordenada c, ArrayList<Coordenada> lista) {
		if (tablero.contiene(c)) {
			if (tablero.getCeldaAt(c).contienePieza()) {
				if (tablero.getCeldaAt(c).getPieza().getColor() != getColor())
					lista.add(c);
			} else {
				lista.add(c);
			}
		}
	}
}
