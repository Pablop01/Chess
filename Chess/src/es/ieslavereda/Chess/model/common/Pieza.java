package es.ieslavereda.Chess.model.common;

import java.awt.Component;
import java.util.ArrayList;

/**
 * 
 * @author ppereaf
 *
 */

public abstract class Pieza {

	protected Tipo tipo;
	protected Tablero tablero;
	protected Coordenada posicion;
	
	/**
	 * Constructor de pieza
	 * @param posicion Indica la posicion inicial
	 * @param tablero Tablero en el que se encuentra la pieza
	 */
	
	public Pieza(Coordenada posicion, Tablero tablero) {
		super();
		this.posicion = posicion;
		this.tablero = tablero;
		
	}
	
	/**
	 * Coloca la pieza en la coordenada indicada
	 * @param c Coordenada obtenida por parametro
	 */
	
	protected void colocate(Coordenada c) {
		
		tablero.getCeldaAt(posicion).setPieza(null);
		posicion = c;
		tablero.getCeldaAt(posicion).setPieza(this);
		
	}
	
	/**
	 * Mueve la pieza a la coordenada indicada y elimina la pieza del rival si la hay
	 * @param c Coordenada obtenida por parametro
	 */
	
	public void moveTo(Coordenada c) {
		
		if(tablero.getPiezaAt(c)==null) {
			colocate(c);
		} else {
			tablero.eliminarPieza(tablero.getPiezaAt(c));
			colocate(c);
		}
	}
	
	/**
	 * Indica si la pieza si puede mover a la coordenada indicada
	 * @param c Coordenada obtenida por parametro
	 * @return True si se puede mover o false en el caso contrario
	 */
	
	public boolean canMoveTo(Coordenada c) {
		return this.getNextMovements().contains(c);
	}
	
	/**
	 * Indica el color de la pieza
	 * @return color
	 */

	public Color getColor() {
		return tipo.getColor();
	}
	
	/**
	 * Indica que tipo de pieza es
	 * @return tipo
	 */
	
	public String getFileName() {
		return tipo.getFilename();
	}
	
	@Override
	public String toString() {
		return tipo.getFilename();
	}
	
	public abstract ArrayList<Coordenada> getNextMovements();
	
}











