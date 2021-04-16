package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class JPFichasEliminadas extends JPanel {
	
	private JPanel panelNegras;
	private JPanel panelBlancas;

	public JPFichasEliminadas() {
		setLayout(new GridLayout(2, 0, 0, 0));
		
		panelBlancas = new JPFichas("WHITE");
		add(panelBlancas);
		
		panelNegras = new JPFichas("BLACK");
		add(panelNegras);

	}

	public JPanel getPanelNegras() {
		return panelNegras;
	}

	public JPanel getPanelBlancas() {
		return panelBlancas;
	}

	
	
}
