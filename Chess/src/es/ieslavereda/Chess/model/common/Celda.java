package es.ieslavereda.Chess.model.common;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * 
 * @author ppereaf
 *
 */

public class Celda extends JButton implements Serializable{
	
	private Pieza pieza;
	private Dimension dimension = new Dimension(50,50);
	private Coordenada c;
	private java.awt.Color colorCeldaNegra = new java.awt.Color(210,129,64);
	private java.awt.Color colorCeldaBlanca = new java.awt.Color(230,205,174);

	/**
	 * Constructor de celda
	 */
	
	public Celda(Coordenada c) {
		super();
		pieza = null;
		setPreferredSize(dimension);
		this.c = c;
	}

	/**
	 * Obtener pieza que hay en una celda
	 * @return Devuelve la pieza
	 */
	
	public Pieza getPieza() {
		return pieza;
	}

	/**
	 * Pone una pieza en la celda
	 * @param pieza Pieza que queremos poner
	 */
	
    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
            
        if (pieza != null) {
            Image image = (new ImageIcon(Celda.class.getResource("/es/ieslavereda/Chess/recursos/" + pieza.getFileName())).getImage());
            ImageIcon imageIconResized = new ImageIcon(getScaledImage(image,25));
            setIcon(imageIconResized);
        } else {
            setIcon(null);
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

	
	/**
	 * Indica si la celda contiene pieza
	 * @return Devuelve true en caso de que haya pieza
	 */
	
	public boolean contienePieza() {
		return pieza!=null;
	}
	
	public void setCellBackground(Color color) {
	    
		if(color == Color.WHITE) {
			setBackground(colorCeldaBlanca);
		} else {
			setBackground(colorCeldaNegra);
		}	
	}
	
	public void resaltar(java.awt.Color color, int size) { 
		
		setBorder(new LineBorder(color,size));
		
	}

	
	@Override
	public String toString() {
		if(pieza==null)
			return " ";
		else
			return pieza.toString();
	}
	
	public Coordenada getCoordenada() {
		return c;
	}
	
}
