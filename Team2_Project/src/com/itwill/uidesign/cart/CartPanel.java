package com.itwill.uidesign.cart;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class CartPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CartPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.EAST);

	}
}
