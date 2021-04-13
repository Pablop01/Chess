package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.model.common.Tablero;
import es.ieslavereda.Chess.vista.Preferencias;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private Pieza p = null;
	private Color turn = Color.WHITE;
	private Preferencias jfPreferencias;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;

		inicializar();
	}

	private void inicializar() {

		Component[] components = vista.getPanelTablero().getComponents();

		for (Component c : components) {
			if (c instanceof Celda) {
				((Celda) c).addActionListener(this);
			}
		}

		// Añadir ActionListener
		vista.getMntmPreferences().addActionListener(this);

		// Añadir ActionCommand
		vista.getMntmPreferences().setActionCommand("Abrir preferencias");

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
		}

	}

	private void cambiarColorCeldaNegra() {
		
		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorCeldaNegra(),
				"Selecciona color de las celdas negras", jfPreferencias.getBtnColorCeldaNegra().getBackground());

		if(color!=null) {
			jfPreferencias.getBtnColorCeldaNegra().setBackground(color);
			MyConfig.getInstance().setBlackCellColor(color);
		}
		
	}

	private void cambiarColorCeldaBlanca() {

		java.awt.Color color = JColorChooser.showDialog(jfPreferencias.getBtnColorCeldaBlanca(),
				"Selecciona color de las celdas blancas", jfPreferencias.getBtnColorCeldaBlanca().getBackground());

		if(color!=null) {
			jfPreferencias.getBtnColorCeldaBlanca().setBackground(color);
			MyConfig.getInstance().setWhiteCellColor(color);
		}
		
	}

	private void abrirPreferencias() {

		jfPreferencias = new Preferencias();

		jfPreferencias.setVisible(true);

		// Añadir ActionListener
		jfPreferencias.getBtnColorCeldaBlanca().addActionListener(this);
		jfPreferencias.getBtnColorCeldaNegra().addActionListener(this);
		jfPreferencias.getBtnColorBordeCelda().addActionListener(this);
		jfPreferencias.getBtnColorBordeCeldaComer().addActionListener(this);

		// Añadir ActionCommand
		jfPreferencias.getBtnColorCeldaBlanca().setActionCommand("Cambiar Color Celda Blanca");
		jfPreferencias.getBtnColorCeldaNegra().setActionCommand("Cambiar Color Celda Negra");
		jfPreferencias.getBtnColorBordeCelda().setActionCommand("Cambiar Color Borde Celda");
		jfPreferencias.getBtnColorBordeCeldaComer().setActionCommand("Cambiar Color Borde Celda Comer");

	}

	private void mecanica(ActionEvent arg0) {

		if (p == null) {

			if (((Celda) arg0.getSource()).getPieza() == null) {
				JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esta Celda", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {

				p = ((Celda) arg0.getSource()).getPieza();

				if (p.getNextMovements().size() == 0) {
					JOptionPane.showMessageDialog(null, "Esta pieza no se puede mover", "Error",
							JOptionPane.ERROR_MESSAGE);
					p = null;
				}

				try {

					if (turn != p.getColor()) {
						JOptionPane.showMessageDialog(null, "No es tu turno", "Error", JOptionPane.ERROR_MESSAGE);
						p = null;
					} else {

						for (Coordenada c2 : p.getNextMovements()) {

							Celda ce = vista.getTablero().get(c2);

							ce.setBorder(
									new LineBorder(new java.awt.Color(MyConfig.getInstance().getYellowBorderColor())));

							if (ce.contienePieza()) {
								if (ce.getPieza().getColor() != turn) {
									ce.setBorder(new LineBorder(
											new java.awt.Color(MyConfig.getInstance().getRedBorderColor())));
								}
							}

						}

					}
				} catch (Exception e) {
				}
			}

		} else {

			Coordenada c = ((Celda) arg0.getSource()).getCoordenada();

			if (p.canMoveTo(c)) {

				for (Coordenada c2 : p.getNextMovements()) {

					vista.getTablero().get(c2).setBorder(null);

				}

				p.moveTo(c);
				p = null;
				turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
			} else {
				JOptionPane.showMessageDialog(null, "No puedes moverte a esta celda", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
