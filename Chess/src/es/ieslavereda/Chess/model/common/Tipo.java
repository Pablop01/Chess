package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public enum Tipo {
	
	WHITE_KING(Color.WHITE,"♔" ),
	WHITE_QUEEN(Color.WHITE,"♕" ),
	WHITE_ROOK(Color.WHITE,"♖" ),
	WHITE_KNIGHT(Color.WHITE,"♘" ),
	WHITE_BISHOP(Color.WHITE,"♗" ),
	WHITE_PAWN(Color.WHITE,"♙" ),
	BLACK_KING(Color.BLACK,"♚" ),
	BLACK_QUEEN(Color.BLACK,"♛" ),
	BLACK_ROOK(Color.BLACK,"♜" ),
	BLACK_KNIGHT(Color.BLACK,"♞" ),
	BLACK_BISHOP(Color.BLACK,"♝" ),
	BLACK_PAWN(Color.BLACK,"♟︎" )
	;
	
	private Color color;
	private String forma;
	
	/**
	 * Define el tipo de pieza
	 * @param color Define el color de la pieza
	 * @param forma Indica la forma de la pieza
	 */
	
	Tipo(Color color, String forma){
		this.color = color;
		this.forma = forma;
	}

	/**
	 * Devuelve el color de la pieza
	 * @return color
	 */
	
	public Color getColor() {
		return color;
	}

	/**
	 * Devuelve la forma de la pieza
	 * @return forma
	 */
	
	public String getForma() {
		return forma;
	}
}
