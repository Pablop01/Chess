package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JPFichas extends JPanel {

	public JPFichas(String titulo) {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), titulo, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	}

}
