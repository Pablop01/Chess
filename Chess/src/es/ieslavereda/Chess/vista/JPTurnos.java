package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.border.TitledBorder;

import es.ieslavereda.Chess.controladores.ControladorPrincipal;
import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;

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
		lblTurn.setIcon(new ImageIcon(JPTurnos.class.getResource("/es/ieslavereda/Chess/recursos/b_peon.gif")));
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
	
	public void cambioTurno() {
		
		if(ControladorPrincipal.getTurn() == Color.WHITE) {
			lblTurn.setIcon(new ImageIcon(JPTurnos.class.getResource("/es/ieslavereda/Chess/recursos/b_peon.gif")));
		}else {
			lblTurn.setIcon(new ImageIcon(JPTurnos.class.getResource("/es/ieslavereda/Chess/recursos/n_peon.gif")));
		}
	}
	
    private Image getScaledImage(Image srcImg, int size){
    	
        int h = size, w = size;
        
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
	
	
}
