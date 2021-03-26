package es.ieslavereda.Chess;

import es.ieslavereda.Chess.model.common.*;
import es.ieslavereda.Chess.tools.Input;
import java.util.Scanner;

/**
 * 
 * @author ppereaf
 *
 */

public class Juego {

	private Jugador white;
	private Jugador black;
	private Tablero board;

	/**
	 * Constructor de Juego
	 */
	
	public Juego() {
		white = new Jugador(Color.WHITE);
		black = new Jugador(Color.BLACK);
		board = new Tablero();
	}

	/**
	 * Metodo para empezar la partida
	 */
	
	public void start() {

		Color turn = Color.WHITE;
		
		Scanner sc = new Scanner(System.in);
		
		white.setColor(Color.WHITE);
		black.setColor(Color.BLACK);
		
		System.out.println("Introduce el nombre del jugador de las piezas Blancas");
		String j1 = sc.nextLine();
		System.out.println("Introduce el nombre del jugador de las piezas Negras");
		String j2 = sc.nextLine();
		
		white.setName(j1);
		black.setName(j2);

		do {
			switch (turn) {
			case WHITE:
				borrarPantalla();
				System.out.println(board.print(Color.WHITE));
				System.out.println("Turno de Blancas");
				comprobarJaque(board,Color.BLACK);
				movePiece(white);
				break;
			case BLACK:
				borrarPantalla();
				System.out.println(board.print(Color.BLACK));
				System.out.println("Turno de Negras");
				comprobarJaque(board,Color.WHITE);
				movePiece(black);
				break;
			}

			turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];

		} while (board.blackKingIsAlive() && board.whiteKingIsAlive());
		
		if(!board.blackKingIsAlive()) {
			borrarPantalla();
			System.out.println(board.print(Color.WHITE));
			System.out.println("Ganan las blancas!");
		}else if(!board.whiteKingIsAlive()) {
			borrarPantalla();
			System.out.println(board.print(Color.BLACK));
			System.out.println("Ganan las Negras!");
		}

	}

	/**
	 * Mover la pieza de una celda a otra
	 * @param jugador Indica el jugador que va a mover la pieza
	 */
	
	private void movePiece(Jugador jugador) {

		Coordenada c;
		Coordenada c2;
		Pieza p;

		boolean moved = false;

		do {

			c = Input.getCoordenada("Selecciona la pieza que quieres mover");
			if (board.getCeldaAt(c).contienePieza()) {
				if (board.getCeldaAt(c).getPieza().getColor() == jugador.getColor()) {
					if (board.getCeldaAt(c).getPieza().getNextMovements().getSize() != 0) {
						do {
							c2 = Input.getCoordenada("Selecciona donde te quieres mover");
							if (board.getCeldaAt(c).getPieza().canMoveTo(c2)) {
								board.getCeldaAt(c).getPieza().moveTo(c2);
								moved = true;
							} else {
								System.out.println("No puedes moverte a esa celda");
							}

						} while (!moved);
					} else {
						System.out.println("Esta pieza no se puede mover");
					}
				} else {
					System.out.println("Esta pieza no es tuya");
				}
			} else {
				System.out.println("No hay piezas en esta celda");
			}

		} while (!moved);

	}
	
	/**
	 * Metodo para borrar la pantalla en el Terminal
	 */
	
	public static void borrarPantalla() {
        System.out.println("\u001b[H\u001b[2J");
        System.out.flush();
    }
	
	/**
	 * Comprueba si hay Jaque en la partida
	 * @param board Tablero de la partida
	 * @param color Color de las piezas de las que vamos a comprobar el Jaque
	 */
	
	public static void comprobarJaque(Tablero board,Color color) {
		
		Lista<Coordenada> lista = new Lista<Coordenada>();
		
		if(color == Color.WHITE) {
			
			Nodo<Pieza> aux = board.getBlancas().getNodoHead();
			
			while(aux != null) {
				lista.addAll(aux.getInfo().getNextMovements());
				aux = aux.getSiguiente();
			}
				
			if(lista.contains(board.blackKingCoordenada())) {
				System.out.println("Jaque de las Blancas");
			}
			
		}else if(color == Color.BLACK) {
			
			Nodo<Pieza> aux = board.getNegras().getNodoHead();
			
			while(aux != null) {
				lista.addAll(aux.getInfo().getNextMovements());
				aux = aux.getSiguiente();
			}
				
			if(lista.contains(board.whiteKingCoordenada())) {
				System.out.println("Jaque de las Negras");
			}
			
		}
		
		
		
	}
	
}
