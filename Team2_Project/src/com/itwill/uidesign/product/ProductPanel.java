package com.itwill.uidesign.product;


import java.awt.Color;
import java.awt.Cursor;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bbangshuttle.product.Product;
import bbangshuttle.product.ProductService;

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
		bakeryscrollPane.setBounds(12, 103, 394, 265);
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

    private JTable bakeryTable;
    private JTable drinkTable;
    private JTextField bakerySearchField;
    private JTextField drinkSearchField;
    private ProductService productService;

    public ProductPanel() throws Exception {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        productService = new ProductService();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane);

        JPanel bakeryPanel = new JPanel();
        bakeryPanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("베이커리", null, bakeryPanel, null);
        bakeryPanel.setLayout(null);

        JScrollPane bakeryScrollPane = new JScrollPane();
        bakeryScrollPane.setBounds(12, 103, 394, 231);
        bakeryPanel.add(bakeryScrollPane);

        bakeryTable = new JTable();
        bakeryScrollPane.setViewportView(bakeryTable);

        bakerySearchField = new JTextField();
        bakerySearchField.setText("검색창입니다");
        bakerySearchField.setBounds(101, 28, 160, 21);
        bakeryPanel.add(bakerySearchField);
        bakerySearchField.setColumns(10);

        JButton bakerySearchButton = new JButton("");
        bakerySearchButton.setBorder(null);
        bakerySearchButton.setOpaque(false);
        bakerySearchButton.setBackground(new Color(255, 255, 255));
        bakerySearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bakerySearchButton.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/search_image20.png")));
        bakerySearchButton.setBounds(270, 23, 53, 32);
        bakeryPanel.add(bakerySearchButton);

        // Load bakery product data into the table
        loadBakeryData();

        JPanel drinkPanel = new JPanel();
        drinkPanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("음료", null, drinkPanel, null);
        drinkPanel.setLayout(null);

        JScrollPane drinkScrollPane = new JScrollPane();
        drinkScrollPane.setBounds(29, 89, 352, 229);
        drinkPanel.add(drinkScrollPane);

        drinkTable = new JTable();
        drinkScrollPane.setViewportView(drinkTable);

        drinkSearchField = new JTextField();
        drinkSearchField.setColumns(10);
        drinkSearchField.setBounds(96, 10, 160, 31);
        drinkPanel.add(drinkSearchField);

        JButton drinkSearchButton = new JButton("");
        drinkSearchButton.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/search_image20.png")));
        drinkSearchButton.setOpaque(false);
        drinkSearchButton.setBorder(null);
        drinkSearchButton.setBackground(Color.WHITE);
        drinkSearchButton.setBounds(270, 9, 53, 32);
        drinkPanel.add(drinkSearchButton);

        // Load drink product data into the table
        loadDrinkData();
    }

    private void loadBakeryData() {
        try {
            DefaultTableModel bakeryTableModel = new DefaultTableModel();
            bakeryTableModel.addColumn("상품번호");
            bakeryTableModel.addColumn("상품이름");
            bakeryTableModel.addColumn("가격");

            // Get bakery product list from ProductService
            List<Product> bakeryProductList = productService.ProductFindByAll();

            // Add bakery product data to the table model
            for (Product product : bakeryProductList) {
                bakeryTableModel.addRow(new Object[] { product.getP_no(), product.getP_name(), product.getPrice() });
            }

            // Set the table model to the table
            bakeryTable.setModel(bakeryTableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDrinkData() {
        try {
            DefaultTableModel drinkTableModel = new DefaultTableModel();
            drinkTableModel.addColumn("상품번호");
            drinkTableModel.addColumn("상품이름");
            drinkTableModel.addColumn("가격");

            // Get drink product list from ProductService
            List<Product> drinkProductList = productService.productCategoryAll(2);

            // Add drink product data to the table model
            for (Product product : drinkProductList) {
                drinkTableModel.addRow(new Object[] { product.getP_no(), product.getP_name(), product.getPrice() });
            }

            // Set the table model to the table
            drinkTable.setModel(drinkTableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

