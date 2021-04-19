package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class JPTurnos extends JPanel {
	
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JLabel lblMove;
	private JLabel lblTurn;
	private JLabel lblSelected;
	private JLabel lblSelectedPieza;

	public JPTurnos() {
		setBorder(new TitledBorder(null, "TURN", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(2, 0, 0, 0));
		
		panelSuperior = new JPanel();
		add(panelSuperior);
		panelSuperior.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		lblMove = new JLabel("Move");
		panelSuperior.add(lblMove, "cell 0 0,alignx center,aligny center");
		
		lblTurn = new JLabel("");
		panelSuperior.add(lblTurn, "cell 0 2,alignx center,aligny center");
		
		panelInferior = new JPanel();
		add(panelInferior);
		panelInferior.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		lblSelected = new JLabel("Selected");
		panelInferior.add(lblSelected, "cell 0 0,alignx center,aligny center");
		
		lblSelectedPieza = new JLabel("");
		panelInferior.add(lblSelectedPieza, "cell 0 2,alignx center,aligny center");

	}

	public JPanel getPanelSuperior() {
		return panelSuperior;
	}

	public JPanel getPanelInferior() {
		return panelInferior;
	}

	public JLabel getLblTurn() {
		return lblTurn;
	}

	public void setLblTurn(JLabel lblTurn) {
		this.lblTurn = lblTurn;
	}

	public JLabel getLblSelectedPieza() {
		return lblSelectedPieza;
	}

	public void setLblSelectedPieza(JLabel lblSelectedPieza) {
		this.lblSelectedPieza = lblSelectedPieza;
	}
	
	
	
	
}
