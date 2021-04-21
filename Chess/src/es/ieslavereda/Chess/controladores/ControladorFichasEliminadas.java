package es.ieslavereda.Chess.controladores;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.GestionFichasEliminadas;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.JPFichasEliminadas;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorFichasEliminadas implements GestionFichasEliminadas {

	private JPFichasEliminadas vista;
	private HashMap<Pieza, JLabel> fichasEliminadas;

	public ControladorFichasEliminadas(JPFichasEliminadas panel) {
		vista = panel;
		fichasEliminadas = new HashMap<Pieza, JLabel>();
	}

	@Override
	public void addPiece(Pieza pieza) {

		if (pieza.getColor() == Color.WHITE) {
			add(pieza, vista.getPanelBlancas());
		} else {
			add(pieza, vista.getPanelNegras());
		}

	}

	@Override
	public void removePiece(Pieza pieza) {

		JLabel label = fichasEliminadas.get(pieza);

		if (pieza.getColor() == Color.WHITE) {
			vista.getPanelBlancas().remove(label);
			vista.getPanelBlancas().repaint();
		} else {
			vista.getPanelNegras().remove(label);
			vista.getPanelNegras().repaint();
		}

		fichasEliminadas.remove(pieza);

	}

	public void add(Pieza pieza, JPanel panel) {

		JLabel label = new JLabel();
		label.setOpaque(true);

		Image image = (new ImageIcon(Celda.class.getResource("/es/ieslavereda/Chess/recursos/" + pieza.getFileName()))
				.getImage());
		ImageIcon imageIconResized = new ImageIcon(getScaledImage(image, 25));
		label.setIcon(imageIconResized);

		panel.add(label);

		fichasEliminadas.put(pieza, label);

	}

	private Image getScaledImage(Image srcImg, int size) {

		int h = size, w = size;

		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	@Override
	public void removeAll() {

		if (fichasEliminadas.size() != 0) {

			Pieza pieza;
			Iterator<Pieza> it = fichasEliminadas.keySet().iterator();

			while(it.hasNext()) {
				pieza = it.next();
				if (pieza.getColor() == Color.WHITE) {
					vista.getPanelBlancas().remove(fichasEliminadas.get(pieza));
					vista.getPanelBlancas().repaint();
				} else {
					vista.getPanelNegras().remove(fichasEliminadas.get(pieza));
					vista.getPanelNegras().repaint();
				}
				it.remove();
			}

			vista.getPanelBlancas().repaint();
			vista.getPanelNegras().repaint();
		}
	}

}
