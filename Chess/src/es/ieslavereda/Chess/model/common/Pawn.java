package es.ieslavereda.Chess.model.common;

import java.util.ArrayList;

/**
 * 
 * @author ppereaf
 *
 */

public class Pawn extends Pieza {

	/**
	 * Constructor de la pieza de tipo Peon
	 * 
	 * @param color    Para obtener el color del que queremos la pieza
	 * @param posicion Indica la posicion inicial de la pieza
	 * @param tablero  Tablero en el que esta la pieza
	 */

	public Pawn(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_PAWN;
		else
			tipo = Tipo.BLACK_PAWN;

		colocate(posicion);

	}

	/**
	 * Devuelve la lista de movimientos disponibles despues de hacer todas las
	 * comprobaciones necesarias
	 * 
	 * @return Lista de coordenadas
	 */

	@Override
	public ArrayList<Coordenada> getNextMovements() {

		ArrayList<Coordenada> lista = new ArrayList<Coordenada>();

		if (getColor() == Color.WHITE) {

			if (posicion.getRow() == 2) {

				if (tablero.getCeldaAt(posicion.up()).contienePieza() == false
						&& tablero.getCeldaAt(posicion.up().up()).contienePieza() == false) {
					addCoordenada(posicion.up(), lista);
					addCoordenada(posicion.up().up(), lista);
				} else if (tablero.getCeldaAt(posicion.up().up()).contienePieza() == true) {
					addCoordenada(posicion.up(), lista);
				}

			} else {
				if (tablero.getCeldaAt(posicion.up()).contienePieza() == false) {
					addCoordenada(posicion.up(), lista);
				}
			}

			if (tablero.contiene(posicion.diagonalUpLeft()) && tablero.getPiezaAt(posicion.diagonalUpLeft()) != null) {
				addCoordenada(posicion.diagonalUpLeft(), lista);
			}

			if (tablero.contiene(posicion.diagonalUpRight())
					&& tablero.getPiezaAt(posicion.diagonalUpRight()) != null) {
				addCoordenada(posicion.diagonalUpRight(), lista);
			}

		} else if (getColor() == Color.BLACK) {

			if (posicion.getRow() == 7) {

				if (tablero.getCeldaAt(posicion.down()).contienePieza() == false
						&& tablero.getCeldaAt(posicion.down().down()).contienePieza() == false) {
					addCoordenada(posicion.down(), lista);
					addCoordenada(posicion.down().down(), lista);
				} else if (tablero.getCeldaAt(posicion.down().down()).contienePieza() == true) {
					addCoordenada(posicion.down(), lista);
				}

			} else {
				if (tablero.getCeldaAt(posicion.down()).contienePieza() == false) {
					addCoordenada(posicion.down(), lista);
				}
			}

			if (tablero.contiene(posicion.diagonalDownLeft())
					&& tablero.getPiezaAt(posicion.diagonalDownLeft()) != null) {
				addCoordenada(posicion.diagonalDownLeft(), lista);
			}
			if (tablero.contiene(posicion.diagonalDownRight())
					&& tablero.getPiezaAt(posicion.diagonalDownRight()) != null) {
				addCoordenada(posicion.diagonalDownRight(), lista);
			}

		}

		return lista;
	}

	/**
	 * Añade la coordenada a la lista de siguientes movimientos
	 * 
	 * @param c     Coordenada que queremos añadir
	 * @param lista a la que vamos a añadir la coordenada
	 */

	private void addCoordenada(Coordenada c, ArrayList<Coordenada> lista) {
		if (tablero.contiene(c)) {
			if (tablero.getCeldaAt(c).contienePieza()) {
				if (tablero.getPiezaAt(c).getColor() != getColor()) {
					lista.add(c);
				}
			} else {
				lista.add(c);
			}
		}
	}

	/**
	 * Metodo para cambiar el peon por una reina en caso de que llegue al final del
	 * tablero
	 */

	public void moveTo(Coordenada c) {
		super.moveTo(c);

		if (getColor() == Color.WHITE && posicion.getRow() == 8) {
			tablero.eliminarPieza(this);
			tablero.getBlancas().add(new Queen(Color.WHITE, c, tablero));
		} else if (getColor() == Color.BLACK && posicion.getRow() == 1) {
			tablero.eliminarPieza(this);
			tablero.getNegras().add(new Queen(Color.BLACK, c, tablero));
		}

	}

}
