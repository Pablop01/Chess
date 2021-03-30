package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.model.common.Tablero;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private Pieza p = null;
	private Color turn = Color.WHITE;

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

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("qwer")) {

		} else if (arg0.getSource() instanceof Celda) {

			mecanica(arg0);
		}

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

							ce.setBorder(new LineBorder(java.awt.Color.YELLOW));

							if (ce.contienePieza()) {
								if (ce.getPieza().getColor() != turn) {
									ce.setBorder(new LineBorder(java.awt.Color.RED));
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
