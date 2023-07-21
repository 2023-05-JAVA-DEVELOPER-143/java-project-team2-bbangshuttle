package com.itwill.uidesign.member1;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bbangshuttle.member.Member;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductPanel extends JPanel {
	/*************1. Service 객체필드 선언*******************/
	//private Procu
	
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ProductPanel() {
		setBackground(new Color(245, 254, 173));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 376, 184);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uC9D1", "\uAC00\uACE0", "\uC2F6\uB2E4"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("너무졸려");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/********************************
					제픔의 리스트 보여주기
				********************************/
				Member loginMember = new Member();
				if (loginMember != null) {
					System.out.println("제품의 리스트보여주기");
					
				} else {
					System.out.println("로그인해라");
				}
				
			}
			
		});
		btnNewButton.setBounds(12, 204, 97, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("자고싶다");
		btnNewButton_1.setBounds(150, 204, 97, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("집갈래요");
		btnNewButton_2.setBounds(291, 204, 97, 23);
		add(btnNewButton_2);
		
		/*************1. Service 객체생성 *******************/
		// productService = new ProductService();
		

	}// 생성자끝
}
