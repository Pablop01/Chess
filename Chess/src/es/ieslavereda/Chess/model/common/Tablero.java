package es.ieslavereda.Chess.model.common;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.GridLayout;

/**
 * 
 * @author ppereaf
 *
 */

public class Tablero extends JPanel{

	private HashMap<Coordenada,Celda> tablero;
	private ArrayList<Pieza> blancas;
	private ArrayList<Pieza> blancasEliminadas;
	private ArrayList<Pieza> negras;
	private ArrayList<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;

	/**
	 * Constructor del tablero
	 */
	
	public Tablero() {
		super();
		setBounds(new Rectangle(0, 0, 500, 500));
		setLayout(new GridLayout(10, 10, 0, 0));
		tablero = new HashMap<Coordenada, Celda>();
		blancas = new ArrayList<>();
		blancasEliminadas = new ArrayList<>();
		negras = new ArrayList<>();
		negrasEliminadas = new ArrayList<>();
		
		// Inicializamos el tablero
		for (int fila = 0; fila < 8; fila++) {
			for (int col = 0; col < 8; col++)
				tablero.put(new Coordenada((char)('A'+col),1+fila), new Celda(new Coordenada((char)('A'+col),1+fila)));
				
		}
		
		inicializar();
		addToPanel();
	}

	/**
	 * Inicializa el tablero con las piezas colocadas
	 */
	
	public void inicializar() {



		whiteKing = new King(Color.WHITE, new Coordenada('E', 1), this);
		blancas.add(whiteKing);

		blancas.add(new Rook(Color.WHITE, new Coordenada('A', 1), this));
		blancas.add(new Knight(Color.WHITE, new Coordenada('B', 1), this));
		blancas.add(new Bishop(Color.WHITE, new Coordenada('C', 1), this));
		blancas.add(new Queen(Color.WHITE, new Coordenada('D', 1), this));
		blancas.add(new Bishop(Color.WHITE, new Coordenada('F', 1), this));
		blancas.add(new Knight(Color.WHITE, new Coordenada('G', 1), this));
		blancas.add(new Rook(Color.WHITE, new Coordenada('H', 1), this));

		blackKing = new King(Color.BLACK, new Coordenada('E', 8), this);
		negras.add(blackKing);
		
		negras.add(new Rook(Color.BLACK, new Coordenada('A', 8), this));
		negras.add(new Knight(Color.BLACK, new Coordenada('B', 8), this));
		negras.add(new Bishop(Color.BLACK, new Coordenada('C', 8), this));
		negras.add(new Queen(Color.BLACK, new Coordenada('D', 8), this));
		negras.add(new Bishop(Color.BLACK, new Coordenada('F', 8), this));
		negras.add(new Knight(Color.BLACK, new Coordenada('G', 8), this));
		negras.add(new Rook(Color.BLACK, new Coordenada('H', 8), this));
		
		for (int i = 0; i < 8; i++) {
			blancas.add(new Pawn(Color.WHITE, new Coordenada((char) ('A' + i), 2), this));
			negras.add(new Pawn(Color.BLACK, new Coordenada((char) ('A' + i), 7), this));
		}

		
		
	}
	
	public void reiniciar() {
		
		
		blancas = new ArrayList<>();
		blancasEliminadas = new ArrayList<>();
		negras = new ArrayList<>();
		negrasEliminadas = new ArrayList<>();
		
		for(Component c : this.getComponents()) {
			if(c instanceof Celda) {
				((Celda) c).setPieza(null);
				c.setEnabled(true);
			}
		}
		
		inicializar();
		
	}

	private void addToPanel() {
		
		Color colorCelda;
		int numero;
		
		add(getNewLabel(""));
		for(int i = 0;i<8;i++)
		    add(getNewLabel(String.valueOf((char)('A'+i))));
		add(getNewLabel(""));
		
		for (int i = 8; i > 0; i--) {
			this.add(getNewLabel(String.valueOf(i)));
			for(int j = 0; j< 8; j++) {
				
				numero = i+j;
				
				if(esPar(numero)) {
					colorCelda = Color.WHITE;
				}else {
					colorCelda = Color.BLACK;
				}
				
				this.add((tablero.get(new Coordenada((char)('A'+j),i))));
				tablero.get(new Coordenada((char)('A'+j),i)).setCellBackground(colorCelda);
				
				
			}
			this.add(getNewLabel(String.valueOf(i)));
		}
		
		
		add(getNewLabel(""));
		for(int i = 0;i<8;i++)
		    add(getNewLabel(String.valueOf((char)('A'+i))));
		add(getNewLabel(""));
		
	}
	
	public void repaintBoard() {
		
		Color colorCelda;
		int numero;
		
		for (int i = 8; i > 0; i--) {
			for(int j = 0; j< 8; j++) {
				
				numero = i+j;
				
				if(esPar(numero)) {
					colorCelda = Color.WHITE;
				}else {
					colorCelda = Color.BLACK;
				}
				tablero.get(new Coordenada((char)('A'+j),i)).setCellBackground(colorCelda);	
			}

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
	
	public ArrayList<Pieza> getBlancas() {
		return blancas;
	}
	
	/**
	 * Para obtener el listado de piezas negraS que siguen vivas
	 * @return Lista de piezas
	 */
	
	public ArrayList<Pieza> getNegras() {
		return negras;
	}

	/**
	 * Elimina una pieza del tablero y la añade al listado de piezas muertas
	 * @param p Pieza que vamos a eliminar
	 */
	
	public void eliminarPieza(Pieza p) {

		if (p.getColor() == Color.WHITE) {
			blancasEliminadas.add(blancas.remove(blancas.indexOf(p)));
		} else
			negrasEliminadas.add(negras.remove(negras.indexOf(p)));

	}

	/**
	 * Devuelve la celda que hay en la coordenada que le pasamos por parametro
	 * @param c Coordenada que le pasamos
	 * @return Devuelve la celda
	 */
	
	public Celda getCeldaAt(Coordenada c) {

		return tablero.get(c);
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
	
	private JLabel getNewLabel(String text) {
	    JLabel label = new JLabel(text);
	    label.setOpaque(true);    
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    label.setBackground(java.awt.Color.DARK_GRAY);
	    label.setForeground(java.awt.Color.WHITE);
	    return label;
	}
	
	private boolean esPar(int n) {
		
		if(n%2==0) {
			return true;
		}
		
		return false;	
	}

	public HashMap<Coordenada, Celda> getTablero() {
		return tablero;
	}

}
