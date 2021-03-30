package es.ieslavereda.Chess.model.common;

/**
 * 
 * @author ppereaf
 *
 */

public enum Tipo {
	
	WHITE_KING(Color.WHITE,"b_rey.gif" ),
	WHITE_QUEEN(Color.WHITE,"b_reina.gif" ),
	WHITE_ROOK(Color.WHITE,"b_torre.gif" ),
	WHITE_KNIGHT(Color.WHITE,"b_caballo.gif" ),
	WHITE_BISHOP(Color.WHITE,"b_alfil.gif" ),
	WHITE_PAWN(Color.WHITE,"b_peon.gif" ),
	BLACK_KING(Color.BLACK,"n_rey.gif" ),
	BLACK_QUEEN(Color.BLACK,"n_reina.gif" ),
	BLACK_ROOK(Color.BLACK,"n_torre.gif" ),
	BLACK_KNIGHT(Color.BLACK,"n_caballo.gif" ),
	BLACK_BISHOP(Color.BLACK,"n_alfil.gif" ),
	BLACK_PAWN(Color.BLACK,"n_peon.gif" )
	;
	
	private Color color;
	private String filename;
	
	/**
	 * Define el tipo de pieza
	 * @param color Define el color de la pieza
	 * @param forma Indica la forma de la pieza
	 */
	
	Tipo(Color color, String forma){
		this.color = color;
		this.filename = forma;
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
	
	public String getFilename() {
		return filename;
	}
}
