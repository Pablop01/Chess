package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.ieslavereda.Chess.model.common.Movimiento;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;

public class JPMovements extends JPanel {
	private JButton btnPreview;
	private JButton btnNext;
	private JScrollPane scrollPane;
	private JList<Movimiento> list;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "MOVEMENTS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		setLayout(new MigLayout("", "[][114.00,grow][]", "[207.00,grow][64.00,grow]"));
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		list = new JList();
		scrollPane.setViewportView(list);
		
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

	public JList<Movimiento> getList() {
		return list;
	}
	
}
