package es.ieslavereda.Chess.model.common;

import java.util.ArrayList;

/**
 * 
 * @author ppereaf
 *
 */

public class Bishop extends Pieza {

	/**
	 * Constructor de la pieza de tipo Alfil
	 * @param color Para obtener el color del que queremos la pieza
	 * @param posicion Indica la posicion inicial de la pieza
	 * @param tablero Tablero en el que esta la pieza
	 */
	
	public Bishop(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_BISHOP;
		else
			tipo = Tipo.BLACK_BISHOP;
		
		colocate(posicion);
	}

	/**
	 * Devuelve la lista de movimientos disponibles despues de hacer todas las comprobaciones necesarias
	 * @return Lista de coordenadas
	 */
	
	@Override
	public ArrayList<Coordenada> getNextMovements() {
		// TODO Auto-generated method stub
		return getNextMovements(this);
	}

	/**
	 * Comprueba las posiciones a las que se puede mover
	 * @param p Pieza de la que queremos obtener los movimientos
	 * @return Lista de coordenadas
	 */
	
	public static ArrayList<Coordenada> getNextMovements(Pieza p) {

		Tablero t = p.tablero;
		ArrayList<Coordenada> lista = new ArrayList<>();
		Coordenada c;

		// UP Right
		c = p.posicion.diagonalUpRight();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.add(c);
			c = c.diagonalUpRight();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.add(c);

		// UP Left
		c = p.posicion.diagonalUpLeft();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.add(c);
			c = c.diagonalUpLeft();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.add(c);

		// Down Right
		c = p.posicion.diagonalDownRight();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.add(c);
			c = c.diagonalDownRight();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.add(c);

		// Down Left
		c = p.posicion.diagonalDownLeft();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.add(c);
			c = c.diagonalDownLeft();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.add(c);

		return lista;
	}
}
