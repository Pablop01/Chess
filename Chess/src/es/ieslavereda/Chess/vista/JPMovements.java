package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JButton;

public class JPMovements extends JPanel {
	private JButton btnPreview;
	private JButton btnNext;
	private JList list;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(null, "Movements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][114.00,grow][]", "[207.00,grow][64.00,grow]"));
		
		list = new JList();
		add(list, "cell 0 0 3 1,grow");
		
		btnPreview = new JButton("<");
		add(btnPreview, "cell 0 1");
		
		btnNext = new JButton(">");
		add(btnNext, "cell 2 1");

	}

	public JButton getBtnPreview() {
		return btnPreview;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JList getList() {
		return list;
	}
	
}
