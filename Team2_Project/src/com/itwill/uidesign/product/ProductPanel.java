package com.itwill.uidesign.product;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Color;

public class ProductPanel extends JPanel {
	private JTable table;
	private JTable table_1;
	private JTextField bakreySearchField;
	private JTextField drinkSearchField;
	private JTable table_2;
	private JTextField textField;
	public ProductPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("전체", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 85, 358, 264);
		panel.add(scrollPane);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uC870\uD68C\uC218", "\uC0C1\uD488\uBA85", "\uAC00\uACA9"
			}
		));
		scrollPane.setViewportView(table_2);
		
		textField = new JTextField();
		textField.setText("검색창입니다");
		textField.setColumns(10);
		textField.setBounds(124, 32, 160, 21);
		panel.add(textField);
		
		JButton bakerySearchButton_1 = new JButton("");
		bakerySearchButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bakerySearchButton_1.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/search_image20.png")));
		bakerySearchButton_1.setOpaque(false);
		bakerySearchButton_1.setBorder(null);
		bakerySearchButton_1.setBackground(Color.WHITE);
		bakerySearchButton_1.setBounds(296, 31, 53, 32);
		panel.add(bakerySearchButton_1);
		
		JPanel bakeryPanel = new JPanel();
		bakeryPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("베이커리", null, bakeryPanel, null);
		bakeryPanel.setLayout(null); 
		
		JScrollPane bakeryscrollPane = new JScrollPane();
		bakeryscrollPane.setBounds(12, 103, 394, 231);
		bakeryPanel.add(bakeryscrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uC870\uD68C\uC218", "\uC0C1\uD488\uBA85", "\uAC00\uACA9"
			}
		));
		bakeryscrollPane.setViewportView(table);
		
		bakreySearchField = new JTextField();
		bakreySearchField.setText("검색창입니다");
		bakreySearchField.setBounds(101, 28, 160, 21);
		bakeryPanel.add(bakreySearchField);
		bakreySearchField.setColumns(10);
		
		JButton bakerySearchButton = new JButton("");
		bakerySearchButton.setBorder(null);
		bakerySearchButton.setOpaque(false);
		bakerySearchButton.setBackground(new Color(255, 255, 255));
		bakerySearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bakerySearchButton.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/search_image20.png")));
		bakerySearchButton.setBounds(270, 23, 53, 32);
		bakeryPanel.add(bakerySearchButton);
		
		JPanel drinkPanel = new JPanel();
		drinkPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("음료", null, drinkPanel, null);
		drinkPanel.setLayout(null);
		
		JScrollPane drinkscrollPane_1 = new JScrollPane();
		drinkscrollPane_1.setBounds(29, 89, 352, 229);
		drinkPanel.add(drinkscrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uC870\uD68C\uC218", "\uC0C1\uD488\uBA85", "\uAC00\uACA9"
			}
		));
		drinkscrollPane_1.setViewportView(table_1);
		
		drinkSearchField = new JTextField();
		drinkSearchField.setColumns(10);
		drinkSearchField.setBounds(96, 10, 160, 31);
		drinkPanel.add(drinkSearchField);
		
		JButton drinkSearchButton = new JButton("");
		drinkSearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		drinkSearchButton.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/search_image20.png")));
		drinkSearchButton.setOpaque(false);
		drinkSearchButton.setBorder(null);
		drinkSearchButton.setBackground(Color.WHITE);
		drinkSearchButton.setBounds(270, 9, 53, 32);
		drinkPanel.add(drinkSearchButton);
	}
}
