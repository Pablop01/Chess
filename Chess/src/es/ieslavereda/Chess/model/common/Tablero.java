package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public class Tablero {

	private Celda[][] tablero;
	private Lista<Pieza> blancas;
	private Lista<Pieza> blancasEliminadas;
	private Lista<Pieza> negras;
	private Lista<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;

	/**
	 * Constructor del tablero
	 */
	
	public Tablero() {
		super();
		tablero = new Celda[8][8];
		blancas = new Lista<>();
		blancasEliminadas = new Lista<>();
		negras = new Lista<>();
		negrasEliminadas = new Lista<>();
		inicializar();
	}

	/**
	 * Inicializa el tablero con las piezas colocadas
	 */
	
	private void inicializar() {

		// Inicializamos el tablero
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int col = 0; col < tablero[0].length; col++)
				tablero[fila][col] = new Celda();
		}

		whiteKing = new King(Color.WHITE, new Coordenada('E', 1), this);
		blancas.addHead(whiteKing);

		blancas.addHead(new Rook(Color.WHITE, new Coordenada('A', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('B', 1), this));
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('C', 1), this));
		blancas.addHead(new Queen(Color.WHITE, new Coordenada('D', 1), this));
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('F', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('G', 1), this));
		blancas.addHead(new Rook(Color.WHITE, new Coordenada('H', 1), this));

		blackKing = new King(Color.BLACK, new Coordenada('E', 8), this);
		negras.addHead(blackKing);
		
		negras.addHead(new Rook(Color.BLACK, new Coordenada('A', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('B', 8), this));
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('C', 8), this));
		negras.addHead(new Queen(Color.BLACK, new Coordenada('D', 8), this));
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('F', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('G', 8), this));
		negras.addHead(new Rook(Color.BLACK, new Coordenada('H', 8), this));
		
		for (int i = 0; i < tablero.length; i++) {
			blancas.addHead(new Pawn(Color.WHITE, new Coordenada((char) ('A' + i), 2), this));
			negras.addHead(new Pawn(Color.BLACK, new Coordenada((char) ('A' + i), 7), this));
		}

	}

	/**
	 * Comprueba que la coordenada pasa por parametro esta en el tablero
	 * @param c coordenada que le pasamos
	 * @return Devuelve true si la coordenada esta en el tablero
	 */
	
	public boolean contiene(Coordenada c) {
		return !(c.getRow() > 8 || c.getRow() < 1 || c.getColumn() < 'A' || c.getColumn() > 'H');
	}

	/**
	 * Para obtener la pieza que hay en una coordenada en el casa de que la haya
	 * @param c Coordenada que le pasamos para comprobar
	 * @return Devuelve la pieza que hay o null si no hay nada
	 */
	
	public Pieza getPiezaAt(Coordenada c) {
		if (!contiene(c))
			return null;
		else
			return getCeldaAt(c).getPieza();
	}

	/**
	 * Para obtener el listado de piezas blancas que siguen vivas
	 * @return Lista de piezas
	 */
	
	public Lista<Pieza> getBlancas() {
		return blancas;
	}
	
	/**
	 * Para obtener el listado de piezas negraS que siguen vivas
	 * @return Lista de piezas
	 */
	
	public Lista<Pieza> getNegras() {
		return negras;
	}

	/**
	 * Elimina una pieza del tablero y la añade al listado de piezas muertas
	 * @param p Pieza que vamos a eliminar
	 */
	
	public void eliminarPieza(Pieza p) {

		if (p.getColor() == Color.WHITE) {
			blancasEliminadas.addHead(blancas.getAndRemove(p));
		} else
			negrasEliminadas.addHead(negras.getAndRemove(p));

	}

	/**
	 * Devuelve la celda que hay en la coordenada que le pasamos por parametro
	 * @param c Coordenada que le pasamos
	 * @return Devuelve la celda
	 */
	
	public Celda getCeldaAt(Coordenada c) {

		if (contiene(c))
			return tablero[8 - c.getRow()][c.getColumn() - 'A'];

		return null;
	}

	/**
	 * Metodo para pintar el tablero dependiendo del color que elijamos
	 * @param color Color que le pasamos por parametro
	 * @return devuelve el tablero 
	 */
	
	public String print(Color color) {

		switch (color) {
		case WHITE:
			return printAsWhite();
		default:
			return printAsBlack();
		}
	}

	/**
	 * Metodo para pintar el tablero en el caso de que juguemos con negras
	 * @return tablero negras
	 */
	
	private String printAsBlack() {
		String salida = "           H   G   F   E   D   C   B   A\n";

		salida += obtenerParteSuperior();

		for (int fila = tablero.length - 1; fila > 0; fila--) {
			salida += obtenerParteFichaNegra(fila);
			salida += obtenerParteDivisoria();
		}
		salida += obtenerParteFichaNegra(0);
		salida += obtenerParteInferior() + "\n";
		salida += "           H   G   F   E   D   C   B   A\n";

		return salida;
	}

	/**
	 * Metodo para pintar el tablero en el caso de que juguemos con blancas
	 * @return tablero blancas
	 */
	
	private String printAsWhite() {
		String salida = "           A   B   C   D   E   F   G   H\n";

		salida += obtenerParteSuperior();

		for (int fila = 0; fila < tablero.length - 1; fila++) {
			salida += obtenerParteFichaBlanca(fila);
			salida += obtenerParteDivisoria();
		}
		salida += obtenerParteFichaBlanca(tablero.length - 1);
		salida += obtenerParteInferior() + "\n";
		salida += "           A   B   C   D   E   F   G   H\n";
		return salida;
	}

	/**
	 * Obtener la parte superior del tablero
	 * @return parte superior tablero
	 */
	
	private String obtenerParteSuperior() {

		return "         ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n";
	}
	
	/**
	 * Obtener las partes intermedias del tablero 
	 * @param fila indica que el numero de fila del tablero
	 * @return devuelve parte del tablero
	 */

	private String obtenerParteFichaNegra(int fila) {
		// String I = "\u2502";
		String salida = "       " + (8 - fila) + " ║";

		for (int col = tablero[0].length - 1; col > 0; col--) {
			salida = salida + " " + tablero[fila][col] + " │";
		}

		salida = salida + " " + tablero[fila][0] + " ║ " + (8 - fila) + "\n";

		return salida;
	}

	/**
	 * Obtener las partes intermedias del tablero 
	 * @param fila indica que el numero de fila del tablero
	 * @return devuelve parte del tablero
	 */
	
	private String obtenerParteFichaBlanca(int fila) {
		// String I = "\u2502";
		String salida = "       " + (8 - fila) + " ║";

		for (int col = 0; col < tablero[0].length - 1; col++) {
			salida = salida + " " + tablero[fila][col] + " │";
		}

		salida = salida + " " + tablero[fila][tablero[0].length - 1] + " ║ " + (8 - fila) + "\n";

		return salida;
	}

	/**
	 * Obtener las partes que separan una celda de otra
	 * @return parte del tablero
	 */
	
	private String obtenerParteDivisoria() {

		return "         ╟───┼───┼───┼───┼───┼───┼───┼───╢ \n";
	}

	/**
	 * Obtener la parte inferior del tablero
	 * @return parte inferior tablero
	 */
	
	private String obtenerParteInferior() {

		return "         ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n";
	}

	/**
	 * Indica si el rey Negro esta vivo
	 * @return Devuelve true si sigue con vida o false si lo han matado
	 */
	
	public boolean blackKingIsAlive() {
		return negras.contains(blackKing);
	}

	/**
	 * Indica si el rey Blanco esta vivo
	 * @return Devuelve true si sigue con vida o false si lo han matado
	 */
	
	public boolean whiteKingIsAlive() {
		return blancas.contains(whiteKing);
	}
	
	/**
	 * Indica la coordenada donde esta el rey Negro
	 * @return Devuelve una coordenada
	 */
	
	public Coordenada blackKingCoordenada() {
		return blackKing.posicion;
	}
	
	/**
	 * Indica la coordenada donde esta el rey Blanco
	 * @return Devuelve una coordenada
	 */
	
	
	public Coordenada whiteKingCoordenada() {
		return whiteKing.posicion;
	}

}
