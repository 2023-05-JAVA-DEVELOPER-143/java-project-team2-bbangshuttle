package com.itwill.uidesign.cart;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class CartPanel extends JPanel {
	private JPanel panel;
	public CartPanel() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 426, 530);
		add(scrollPane);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 900));
		scrollPane.setViewportView(panel);

		JPanel cartItemPanel = new JPanel();
		cartItemPanel.setLayout(null);
		cartItemPanel.setPreferredSize(new Dimension(400, 72));
		cartItemPanel.setBorder(null);
		cartItemPanel.setBackground(Color.WHITE);
		panel.add(cartItemPanel);

		JLabel cartItemImageLabel = new JLabel("비글");
		cartItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		cartItemImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		cartItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartItemImageLabel.setBounds(0, 0, 69, 72);
		cartItemPanel.add(cartItemImageLabel);

		JLabel cartItemDescLabel = new JLabel("300,000");
		cartItemDescLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cartItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartItemDescLabel.setBounds(61, 25, 74, 23);
		cartItemPanel.add(cartItemDescLabel);

		JButton cartItemDeleteButton = new JButton("");
		cartItemDeleteButton.setBorder(null);
		cartItemDeleteButton.setBackground(Color.WHITE);
		cartItemDeleteButton.setBounds(335, 27, 15, 17);
		cartItemPanel.add(cartItemDeleteButton);

		JLabel cartItemTotPrice = new JLabel("9,000,000");
		cartItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		cartItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartItemTotPrice.setBounds(220, 25, 69, 22);
		cartItemPanel.add(cartItemTotPrice);

		JComboBox cartItemQtyComboBox = new JComboBox();
		cartItemQtyComboBox.setBounds(157, 25, 39, 23);
		cartItemPanel.add(cartItemQtyComboBox);
	}

	public void displaycartlist() {
		panel.removeAll();
		for (int i = 0; i < 5; i++) {

			JPanel cartItemPanel = new JPanel();
			cartItemPanel.setLayout(null);
			cartItemPanel.setPreferredSize(new Dimension(400, 72));
			cartItemPanel.setBorder(null);
			cartItemPanel.setBackground(Color.WHITE);
			panel.add(cartItemPanel);

			JLabel cartItemImageLabel = new JLabel("비글");
			cartItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			cartItemImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			cartItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			cartItemImageLabel.setBounds(0, 0, 69, 72);
			cartItemPanel.add(cartItemImageLabel);

			JLabel cartItemDescLabel = new JLabel("300,000");
			cartItemDescLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemDescLabel.setBounds(61, 25, 74, 23);
			cartItemPanel.add(cartItemDescLabel);

			JButton cartItemDeleteButton = new JButton("");
			cartItemDeleteButton.setBorder(null);
			cartItemDeleteButton.setBackground(Color.WHITE);
			cartItemDeleteButton.setBounds(335, 27, 15, 17);
			cartItemPanel.add(cartItemDeleteButton);

			JLabel cartItemTotPrice = new JLabel("9,000,000");
			cartItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemTotPrice.setBounds(220, 25, 69, 22);
			cartItemPanel.add(cartItemTotPrice);

			JComboBox cartItemQtyComboBox = new JComboBox();
			cartItemQtyComboBox.setBounds(157, 25, 39, 23);
			cartItemPanel.add(cartItemQtyComboBox);
		}
	}
}
