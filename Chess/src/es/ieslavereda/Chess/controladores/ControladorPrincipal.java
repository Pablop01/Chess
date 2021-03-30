package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.Pieza;
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
				if (turn != p.getColor()) {
					JOptionPane.showMessageDialog(null, "No es tu turno", "Error", JOptionPane.ERROR_MESSAGE);
					p = null;
				}
			}

		} else {
			
			Coordenada c = ((Celda) arg0.getSource()).getCoordenada();
			
			for(int i=0; i < p.getNextMovements().getSize(); i++) {
				
				
			}
			
			p.moveTo(c);
			p = null;
			turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
		
			
		}

	}

}
