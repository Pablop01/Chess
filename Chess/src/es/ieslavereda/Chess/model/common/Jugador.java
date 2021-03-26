package es.ieslavereda.Chess.model.common;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 
 * @author ppereaf
 *
 */

public class Jugador {

	private String name;
	private Color color;

	/**
	 * Constructor del Jugador donde indica su nombre y el color de sus piezas
	 * @param name Nombre que va a tener el jugador
	 * @param color Color de sus piezas
	 */
	
	public Jugador(String name, Color color) {
		this.color = color;
		this.name = name;
	}
	
	/**
	 * Constructor del Jugador donde indica el color de sus piezas
	 * @param color Color de sus piezas
	 */
	
	public Jugador(Color color) {
		this.color = color;
	}

	/**
	 * Obtener el nombre del jugador
	 * @return String con el nombre
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Obtener el color de las piezas del jugador
	 * @return Color
	 */
	
	public Color getColor() {
		return color;
	}

	/**
	 * Indicar el nombre del jugador
	 * @param name Nombre que le queremos poner
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Indicar el color de las piezas del jugador
	 * @param color Color que le queremos poner a sus piezas
	 */

	public void setColor(Color color) {
		this.color = color;
	}	
}
