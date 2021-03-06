package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.GestionFichasEliminadas;
import es.ieslavereda.Chess.model.common.Movimiento;
import es.ieslavereda.Chess.model.common.Pawn;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.model.common.Tablero;
import es.ieslavereda.Chess.vista.JPFichasEliminadas;
import es.ieslavereda.Chess.vista.JPTurnos;
import es.ieslavereda.Chess.vista.Preferencias;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal implements ActionListener, MouseListener {

	private VistaPrincipal vista;
	private Pieza p = null;
	private static Color turn = Color.WHITE;
	private Preferencias jfPreferencias;
	private GestionFichasEliminadas gestionFichasEliminadas;
	private DefaultListModel<Movimiento> dlm;
	private Deque<Movimiento> stack;
	private ArrayList<Movimiento> movimientos;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;
		stack = new ArrayDeque<Movimiento>();
		movimientos = new ArrayList<Movimiento>();
		inicializar();
	}

	private void inicializar() {

		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminados());

		dlm = new DefaultListModel<Movimiento>();

		vista.getPanelMovimientos().getList().setModel(dlm);

		Component[] components = vista.getPanelTablero().getComponents();

		for (Component c : components) {
			if (c instanceof Celda) {
				((Celda) c).addActionListener(this);
			}
		}

		// A??adir MouseListener
		vista.getPanelMovimientos().getList().addMouseListener(this);

		// A??adir ActionListener
		vista.getMntmPreferences().addActionListener(this);
		vista.getMntmNewGame().addActionListener(this);
		vista.getPanelMovimientos().getBtnNext().addActionListener(this);
		vista.getPanelMovimientos().getBtnPreview().addActionListener(this);
		vista.getMntmSave().addActionListener(this);
		vista.getMntmOpen().addActionListener(this);

		// A??adir ActionCommand
		vista.getMntmPreferences().setActionCommand("Abrir preferencias");
		vista.getMntmNewGame().setActionCommand("New Game");
		vista.getMntmSave().setActionCommand("Save");
		vista.getPanelMovimientos().getBtnNext().setActionCommand("Next");
		vista.getPanelMovimientos().getBtnPreview().setActionCommand("Previous");
		vista.getMntmOpen().setActionCommand("Open");

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("Abrir preferencias")) {
			abrirPreferencias();
		} else if (arg0.getSource() instanceof Celda) {
			mecanica(arg0);
		} else if (comando.equals("Cambiar Color Celda Blanca")) {
			cambiarColorCeldaBlanca();
		} else if (comando.equals("Cambiar Color Celda Negra")) {
			cambiarColorCeldaNegra();
		} else if (comando.equals("Cambiar Color Borde Celda")) {
			cambiarColorBordeCelda();
		} else if (comando.equals("Cambiar Color Borde Celda Comer")) {
			cambiarColorBordeCeldaComer();
		} else if (comando.equals("New Game")) {
			newGame();
		} else if (comando.equals("Next")) {
			nextMovement();
		} else if (comando.equals("Previous")) {
			previousMovement();
		} else if (comando.equals("Save")) {
			saveGame();
		} else if (comando.equals("Open")) {
			try {
				openGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void openGame() throws Exception {

		newGame();
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new FileNameExtensionFilter("Ajedrez data file", "data"));

		int opcion = jfc.showOpenDialog(vista);
		if (opcion == JFileChooser.APPROVE_OPTION) {

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()))) {

				movimientos = (ArrayList<Movimiento>) ois.readObject();
				colorcarPiezas();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void colorcarPiezas() throws Exception {

		for (Movimiento m : movimientos) {

			nextMovement(m);

		}

	}

	private void saveGame() {

		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new FileNameExtensionFilter("Ajedrez data file", "data"));

		Movimiento m;
		
		for (int i = 0; i < dlm.getSize(); i++) {

			m = dlm.get(i);
			movimientos.add(m);

			
		}
		
		int opcion = jfc.showSaveDialog(vista);
		if (opcion == JFileChooser.APPROVE_OPTION) {
		
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jfc.getSelectedFile()))) {

				oos.writeObject(movimientos);
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void nextMovement() {

		try {

			Movimiento m = stack.pop();
			nextMovement(m);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "No hay movimientos para rehacer", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void nextMovement(Movimiento m) throws Exception {
		Coordenada origen, destino;
		dlm.addElement(m);

		destino = m.getDestino();
		origen = m.getOrigen();

		switch (m.getTipoAccion()) {
		case Movimiento.NOT_KILL:

			((Tablero) vista.getPanelTablero()).getPiezaAt(origen).colocate(destino);
			Movimiento.increaseNumberOfMovements();

			break;
		case Movimiento.KILL:

			((Tablero) vista.getPanelTablero()).getPiezaAt(origen).colocate(destino);
			gestionFichasEliminadas.addPiece(m.getPieza());
			Movimiento.increaseNumberOfMovements();

			break;

		case Movimiento.RISE:

			m.getPiezaGenerada().colocate(destino);
			((Tablero) vista.getPanelTablero()).getCeldaAt(origen).setPieza(null);
			if (m.getPiezaPeon().getColor() == Color.WHITE) {
				((Tablero) vista.getPanelTablero()).getBlancas().add(m.getPiezaGenerada());
				((Tablero) vista.getPanelTablero()).getBlancas().remove(m.getPiezaPeon());
			} else {
				((Tablero) vista.getPanelTablero()).getNegras().add(m.getPiezaGenerada());
				((Tablero) vista.getPanelTablero()).getNegras().remove(m.getPiezaPeon());
			}
			Movimiento.increaseNumberOfMovements();

			break;
		case Movimiento.RISE_KILLING:

			m.getPiezaGenerada().colocate(destino);
			((Tablero) vista.getPanelTablero()).getCeldaAt(origen).setPieza(null);

			if (m.getPiezaPeon().getColor() == Color.WHITE) {
				((Tablero) vista.getPanelTablero()).getBlancas().add(m.getPiezaGenerada());
				((Tablero) vista.getPanelTablero()).getBlancas().remove(m.getPiezaPeon());
				((Tablero) vista.getPanelTablero()).getNegras().add(m.getPieza());
			} else {
				((Tablero) vista.getPanelTablero()).getNegras().add(m.getPiezaGenerada());
				((Tablero) vista.getPanelTablero()).getNegras().remove(m.getPiezaPeon());
				((Tablero) vista.getPanelTablero()).getBlancas().add(m.getPieza());
			}

			gestionFichasEliminadas.addPiece(m.getPieza());
			Movimiento.increaseNumberOfMovements();

			break;

		default:
			throw new Exception("Tipo no conocido");
		}
		turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
		vista.getPanelTurnos().cambioTurno();
	}

	private void previousMovement() {

		try {

			Movimiento m = dlm.remove(dlm.getSize() - 1);
			stack.push(m);
			Coordenada origen, destino;
			destino = m.getDestino();
			origen = m.getOrigen();
			Pieza p = m.getPieza();

			switch (m.getTipoAccion()) {
			case Movimiento.NOT_KILL:
				
				((Tablero) vista.getPanelTablero()).getPiezaAt(destino).setPosicion(origen);
				((Tablero) vista.getPanelTablero()).getCeldaAt(origen).setPieza(((Tablero) vista.getPanelTablero()).getPiezaAt(destino));
				((Tablero) vista.getPanelTablero()).getCeldaAt(destino).setPieza(null);

				break;
			case Movimiento.KILL:

				((Tablero) vista.getPanelTablero()).getPiezaAt(destino).colocate(origen);
				((Tablero) vista.getPanelTablero()).getCeldaAt(destino).setPieza(p);
				gestionFichasEliminadas.removePiece(m.getPieza());
				if (p.getColor() == Color.WHITE) {
					vista.getPanelTablero().getBlancas().add(p);
				} else {
					vista.getPanelTablero().getNegras().add(p);
				}

				break;
			case Movimiento.RISE:

				m.getPiezaPeon().colocate(origen);
				((Tablero) vista.getPanelTablero()).getCeldaAt(destino).setPieza(null);
				if (m.getPiezaPeon().getColor() == Color.WHITE) {
					((Tablero) vista.getPanelTablero()).getBlancas().remove(m.getPiezaGenerada());
					((Tablero) vista.getPanelTablero()).getBlancas().add(m.getPiezaPeon());
				} else {
					((Tablero) vista.getPanelTablero()).getNegras().remove(m.getPiezaGenerada());
					((Tablero) vista.getPanelTablero()).getNegras().add(m.getPiezaPeon());
				}

				break;

			case Movimiento.RISE_KILLING:

				m.getPiezaPeon().colocate(origen);
				((Tablero) vista.getPanelTablero()).getCeldaAt(destino).setPieza(m.getPieza());

				if (m.getPiezaPeon().getColor() == Color.WHITE) {
					((Tablero) vista.getPanelTablero()).getBlancas().remove(m.getPiezaGenerada());
					((Tablero) vista.getPanelTablero()).getBlancas().add(m.getPiezaPeon());
					((Tablero) vista.getPanelTablero()).getNegras().remove(m.getPieza());
				} else {
					((Tablero) vista.getPanelTablero()).getNegras().remove(m.getPiezaGenerada());
					((Tablero) vista.getPanelTablero()).getNegras().add(m.getPiezaPeon());
					((Tablero) vista.getPanelTablero()).getBlancas().remove(m.getPieza());
				}

				gestionFichasEliminadas.removePiece(m.getPieza());

				break;

			default:
				throw new Exception("Tipo no conocido");
			}
			turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
			vista.getPanelTurnos().cambioTurno();

			Movimiento.decreaseNumberOfMovements();

		} catch (ArrayIndexOutOfBoundsException ae) {

			JOptionPane.showMessageDialog(null, "No hay movimientos para deshacer", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void newGame() {

		dlm.removeAllElements();
		gestionFichasEliminadas.removeAll();
		Movimiento.setNumero(1);
		turn = Color.WHITE;
		despintarBordes();
		vista.getPanelTurnos().selectedPieceNull();
		vista.getPanelTurnos().cambioTurno();
		p = null;
		((Tablero) vista.getPanelTablero()).reiniciar();

	}

	private void despintarBordes() {
				
		if (p != null) {

			for (Coordenada c2 : p.getNextMovements()) {

				Celda ce = vista.getTablero().get(c2);

				ce.resaltar(java.awt.Color.GRAY, 1);

				if (ce.contienePieza()) {
					if (ce.getPieza().getColor() != turn) {
						ce.resaltar(java.awt.Color.GRAY, 1);
					}
				}

			}
		}
	}

	private void cambiarColorBordeCeldaComer() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorBordeCeldaComer(),
				"Selecciona color de las celdas negras", jfPreferencias.getBtnColorBordeCeldaComer().getBackground());

		if (color != null) {
			jfPreferencias.getBtnColorBordeCeldaComer().setBackground(color);
			MyConfig.getInstance().setBorderColorComer(color);
		}

	}

	private void cambiarColorBordeCelda() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorBordeCelda(),
				"Selecciona color de las celdas negras", jfPreferencias.getBtnColorBordeCelda().getBackground());

		if (color != null) {
			jfPreferencias.getBtnColorBordeCelda().setBackground(color);
			MyConfig.getInstance().setBorderColor(color);
		}

	}

	private void cambiarColorCeldaNegra() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorCeldaNegra(),
				"Selecciona color de las celdas negras", jfPreferencias.getBtnColorCeldaNegra().getBackground());

		if (color != null) {
			jfPreferencias.getBtnColorCeldaNegra().setBackground(color);
			MyConfig.getInstance().setBlackCellColor(color);
			Celda.colorCeldaNegra = color;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}

	}

	private void cambiarColorCeldaBlanca() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorCeldaBlanca(),
				"Selecciona color de las celdas blancas", jfPreferencias.getBtnColorCeldaBlanca().getBackground());

		if (color != null) {
			jfPreferencias.getBtnColorCeldaBlanca().setBackground(color);
			MyConfig.getInstance().setWhiteCellColor(color);
			Celda.colorCeldaBlanca = color;
			((Tablero) vista.getPanelTablero()).repaintBoard();

		}

	}

	private void abrirPreferencias() {

		jfPreferencias = new Preferencias();

		jfPreferencias.setVisible(true);

		// A??adir ActionListener
		jfPreferencias.getBtnColorCeldaBlanca().addActionListener(this);
		jfPreferencias.getBtnColorCeldaNegra().addActionListener(this);
		jfPreferencias.getBtnColorBordeCelda().addActionListener(this);
		jfPreferencias.getBtnColorBordeCeldaComer().addActionListener(this);

		// A??adir ActionCommand
		jfPreferencias.getBtnColorCeldaBlanca().setActionCommand("Cambiar Color Celda Blanca");
		jfPreferencias.getBtnColorCeldaNegra().setActionCommand("Cambiar Color Celda Negra");
		jfPreferencias.getBtnColorBordeCelda().setActionCommand("Cambiar Color Borde Celda");
		jfPreferencias.getBtnColorBordeCeldaComer().setActionCommand("Cambiar Color Borde Celda Comer");

	}

	private void mecanica(ActionEvent arg0) {

		if (p == null) {

			comprobarSeleccion(arg0);

		} else {

			moverPieza(arg0);

		}

	}

	private void comprobarSeleccion(ActionEvent arg0) {

		if (((Celda) arg0.getSource()).getPieza() == null) {
			JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esta Celda", "Error",
					JOptionPane.ERROR_MESSAGE);

		} else {

			p = ((Celda) arg0.getSource()).getPieza();

			if (p.getNextMovements().size() == 0) {
				JOptionPane.showMessageDialog(null, "Esta pieza no se puede mover", "Error", JOptionPane.ERROR_MESSAGE);
				p = null;
			}

			try {

				if (turn != p.getColor()) {
					JOptionPane.showMessageDialog(null, "No es tu turno", "Error", JOptionPane.ERROR_MESSAGE);
					p = null;
				} else {

					vista.getPanelTurnos().selectedPiece(p);
					pintarBordes();

				}
			} catch (Exception e) {
			}
		}
	}

	private void pintarBordes() {

		for (Coordenada c2 : p.getNextMovements()) {

			Celda ce = vista.getTablero().get(c2);

			ce.setBorder(new LineBorder(new java.awt.Color(MyConfig.getInstance().getYellowBorderColor()), 3));

			if (ce.contienePieza()) {
				if (ce.getPieza().getColor() != turn) {
					ce.setBorder(new LineBorder(new java.awt.Color(MyConfig.getInstance().getRedBorderColor()), 3));
				}
			}

		}
	}

	private void moverPieza(ActionEvent arg0) {

		Coordenada c = ((Celda) arg0.getSource()).getCoordenada();
		Tablero tablero = ((Tablero) vista.getPanelTablero());
		Celda ce = tablero.getCeldaAt(c);
		Coordenada origen = p.getPosicion();
		Coordenada destino = c;

		if (p.canMoveTo(c)) {

			for (Coordenada c2 : p.getNextMovements()) {

				vista.getTablero().get(c2).setBorder(null);

			}

			Movimiento m = null;

			if (ce.contienePieza()) {

				if ((c.getRow() == 1 || c.getRow() == 8) && p instanceof Pawn) {
					m = new Movimiento(origen, destino, Movimiento.RISE_KILLING, ce.getPieza(), null, p);
				} else {
					m = new Movimiento(origen, destino, Movimiento.KILL, ce.getPieza(), null, null);
				}

				gestionFichasEliminadas.addPiece(ce.getPieza());
			}

			if (m == null && (c.getRow() == 1 || c.getRow() == 8) && p instanceof Pawn) {
				m = new Movimiento(origen, destino, Movimiento.RISE, null, null, p);
			} else if (m == null) {
				m = new Movimiento(origen, destino, Movimiento.NOT_KILL, null, null, null);
			}

			dlm.addElement(m);

			p.moveTo(c);

			if (m.getTipoAccion() == Movimiento.RISE || m.getTipoAccion() == Movimiento.RISE_KILLING) {
				m.setPiezaGenerada(ce.getPieza());
			}

			p = null;
			vista.getPanelTurnos().selectedPieceNull();

			if (!tablero.blackKingIsAlive()) {
				JOptionPane.showMessageDialog(null, "Ganan las blancas!", "Ganador", JOptionPane.INFORMATION_MESSAGE);
				desactivarCeldas();
			}

			if (!tablero.whiteKingIsAlive()) {
				JOptionPane.showMessageDialog(null, "Ganan las negras!", "Ganador", JOptionPane.INFORMATION_MESSAGE);
				desactivarCeldas();
			}

			comprobarJaque(tablero, turn);
			turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
			vista.getPanelTurnos().cambioTurno();

		} else {
			JOptionPane.showMessageDialog(null, "No puedes moverte a esta celda", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void desactivarCeldas() {

		Tablero tablero = ((Tablero) vista.getPanelTablero());

		turn = Color.BLACK;

		for (Component c : tablero.getComponents()) {
			if (c instanceof Celda) {
				c.setEnabled(false);
			}
		}

	}

	public void comprobarJaque(Tablero board, Color color) {

		ArrayList<Coordenada> lista = new ArrayList<Coordenada>();

		if (color == Color.WHITE) {

			for (int i = 0; i < board.getBlancas().size(); i++) {

				Pieza p = board.getBlancas().get(i);
				lista.addAll(p.getNextMovements());

			}

			if (lista.contains(board.blackKingCoordenada())) {
				JOptionPane.showMessageDialog(null, "Jaque de las Blancas! Protege tu rey", "Jaque",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (color == Color.BLACK) {

			for (int i = 0; i < board.getNegras().size(); i++) {

				Pieza p = board.getNegras().get(i);
				lista.addAll(p.getNextMovements());

			}

			if (lista.contains(board.whiteKingCoordenada())) {
				JOptionPane.showMessageDialog(null, "Jaque de  Negras! Protege tu rey", "Jaque",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	public static Color getTurn() {
		return turn;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		Component c = arg0.getComponent();
		if (c == vista.getPanelMovimientos().getList()) {
			int index = vista.getPanelMovimientos().getList().getSelectedIndex();
			while (dlm.getSize() > index) {
				previousMovement();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
