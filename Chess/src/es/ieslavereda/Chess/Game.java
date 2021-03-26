package es.ieslavereda.Chess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.model.common.Player;
import es.ieslavereda.Chess.model.common.Tablero;
import es.ieslavereda.Chess.sockets.Message;

public class Game extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idGame;
	private Player white;
	private Player black;
	private Tablero board;
	private ChessServer chessServer;

	public Game(ChessServer server) {
		super("GameThread");
		this.chessServer = server;
		this.idGame = server.getNewIdGame();
		board = new Tablero();
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	public void setWhite(Player white) {
		this.white = white;
	}

	public void setBlack(Player black) {
		this.black = black;
	}

	private String updateScreen(Player player) {

		return "\033[H\033[2J" + board.print(player.getColor());

	}

	public int getIdGame() {
		return idGame;
	}

	public void start() {

		sendInformation("Let's go.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Color turn = Color.WHITE;

		sendInformation(white, updateScreen(white));
		sendInformation(black, updateScreen(black));

		do {
			switch (turn) {
			case WHITE:
				movePiece(white);
				break;
			case BLACK:
				movePiece(black);
				break;
			}
			sendInformation(black, updateScreen(black));
			sendInformation(white, updateScreen(white));

			turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];

		} while (board.blackKingIsAlive()  && board.whiteKingIsAlive()  && black.getSocket().isConnected()
				&& white.getSocket().isConnected());

		if (board.whiteKingIsAlive())
			sendInformation(Color.WHITE + " wins.");
		else
			sendInformation(Color.BLACK + " wins.");

		try {
			white.getSocket().close();
			black.getSocket().close();
			chessServer.gameFinish(idGame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void movePiece(Player player) {

		Coordenada c;
		Pieza p;

		sendInformation("Move -> " + player.getColor() + "  (" + player.getName() + ").");
		sendInformation((player.getColor() == Color.WHITE) ? black : white,"Wait for the other player to finish moving...");

		boolean moved = false;

		do {
			
			// To do 
			// Game mechanics

		} while (!moved);

	}

	public Coordenada requestCoordinate(Player player) {
		Coordenada c = null;

		try {
			
			// To do
			// Send to the player the request coordinate message and wait for the response.
			// Check if the answer is a correct message, and return the coordinate recived

			

		} catch (Exception e) {

			e.printStackTrace();
		}

		return c;
	}

	private void sendInformation(String information) {

		sendInformation(white, information);
		sendInformation(black, information);

	}

	public void sendInformation(Player player, String information) {
		// To do
		// Send to the player the information

	}
}
