package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public class Rook extends Pieza {

	/**
	 * Constructor de la pieza de tipo Torre
	 * @param color Para obtener el color del que queremos la pieza
	 * @param posicion Indica la posicion inicial de la pieza
	 * @param tablero Tablero en el que esta la pieza
	 */
	
	public Rook(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_ROOK;
		else
			tipo = Tipo.BLACK_ROOK;
		
		colocate(posicion);
		
	}

	/**
	 * Devuelve la lista de movimientos disponibles
	 * @return Lista de coordenadas
	 */
	
	@Override
	public Lista<Coordenada> getNextMovements() {
		
		return getNextMovements(this);
	}
	
	/**
	 * Comprueba las posiciones a las que se puede mover
	 * @param p Pieza de la que queremos obtener los movimientos
	 * @return Lista de coordenadas
	 */
	
	public static Lista<Coordenada> getNextMovements(Pieza p){
		
		Tablero t = p.tablero;
		Lista<Coordenada> lista = new Lista<>();
		Coordenada c;
		
		// UP 
		c= p.posicion.up();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.up();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		// Right
		c= p.posicion.right();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.right();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		// Down
		c= p.posicion.down();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.down();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		// Left
		c= p.posicion.left();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.left();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		return lista;
	}

}
