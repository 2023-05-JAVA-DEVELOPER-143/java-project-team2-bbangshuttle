package com.itwill.uidesign.member;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberPanel extends JPanel {
	private JTextField IdField;
	private JTextField PasswordField;
	private JTextField NameField;
	private JTextField EmailField;
	private JTextField AddressField;
	private JTextField BirthField;
	private JTextField NumberField;
	private JTextField idListField;
	private JPasswordField passwordListField;
	private JTextField idUpdateField;
	private JTextField passwordUpdateField; 
	private JTextField nameUpdateField;
	private JTextField emailUpdateField;
	private JTextField addressUpdateField;
	private JTextField birthUpdateField;
	private JTextField numberUpdateField;

	/**
	 * Create the panel.
	 */
	public MemberPanel() {
		setBackground(new Color(252, 207, 252));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTabbedPane memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		memberTabbedPane.setPreferredSize(new Dimension(10, 10));
		add(memberTabbedPane);
		
		JPanel memberListPanel = new JPanel();
		memberTabbedPane.addTab("로그인", null, memberListPanel, null);
		memberListPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(15, 15));
		scrollPane.setBounds(0, 269, 353, -267);
		memberListPanel.add(scrollPane);
		
		JLabel loginLabel = new JLabel("로그인");
		loginLabel.setBounds(159, 32, 57, 15);
		memberListPanel.add(loginLabel);
		
		idListField = new JTextField();
		idListField.setBounds(133, 99, 116, 21);
		memberListPanel.add(idListField);
		idListField.setColumns(10);
		
		JButton loginListButton = new JButton("로그인");
		loginListButton.setBounds(43, 231, 97, 23);
		memberListPanel.add(loginListButton);
		
		JButton idSearchListButton = new JButton("아이디찾기");
		idSearchListButton.setBounds(159, 231, 97, 23);
		memberListPanel.add(idSearchListButton);
		
		JButton passwordSearchListButton = new JButton("비밀번호찾기");
		passwordSearchListButton.setBounds(268, 231, 116, 23);
		memberListPanel.add(passwordSearchListButton);
		
		JLabel idListLabel = new JLabel("아이디");
		idListLabel.setBounds(52, 102, 57, 15);
		memberListPanel.add(idListLabel);
		
		JLabel passwordListLabel = new JLabel("패스워드");
		passwordListLabel.setBounds(52, 161, 57, 15);
		memberListPanel.add(passwordListLabel);
		
		passwordListField = new JPasswordField();
		passwordListField.setBounds(133, 158, 116, 21);
		memberListPanel.add(passwordListField);
		
		JLabel idcorrectListLabel = new JLabel("");
		idcorrectListLabel.setForeground(new Color(255, 0, 0));
		idcorrectListLabel.setBounds(133, 130, 106, 15);
		memberListPanel.add(idcorrectListLabel);
		
		JPanel memberUpdatePanel = new JPanel();
		memberTabbedPane.addTab("수정", null, memberUpdatePanel, null);
		memberUpdatePanel.setLayout(null);
		
		idUpdateField = new JTextField();
		idUpdateField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*************아이디, 이름, 생년월일은 수정이 안된다***************/
				
			}
		});
		idUpdateField.setColumns(10);
		idUpdateField.setBounds(157, 83, 116, 21);
		memberUpdatePanel.add(idUpdateField);
		
		JLabel idLabel_1 = new JLabel("회원정보수정");
		idLabel_1.setBounds(157, 58, 97, 15);
		memberUpdatePanel.add(idLabel_1);
		
		passwordUpdateField = new JTextField();
		passwordUpdateField.setColumns(10);
		passwordUpdateField.setBounds(157, 114, 116, 21);
		memberUpdatePanel.add(passwordUpdateField);
		
		nameUpdateField = new JTextField();
		nameUpdateField.setColumns(10);
		nameUpdateField.setBounds(157, 145, 116, 21);
		memberUpdatePanel.add(nameUpdateField);
		
		emailUpdateField = new JTextField();
		emailUpdateField.setColumns(10);
		emailUpdateField.setBounds(157, 189, 116, 21);
		memberUpdatePanel.add(emailUpdateField);
		
		addressUpdateField = new JTextField();
		addressUpdateField.setColumns(10);
		addressUpdateField.setBounds(157, 227, 116, 21);
		memberUpdatePanel.add(addressUpdateField);
		
		JLabel IdUpdateLabel = new JLabel("아이디");
		IdUpdateLabel.setBounds(44, 86, 57, 15);
		memberUpdatePanel.add(IdUpdateLabel);
		
		JLabel PasswordUpdateLabel = new JLabel("패스워드");
		PasswordUpdateLabel.setBounds(44, 117, 57, 15);
		memberUpdatePanel.add(PasswordUpdateLabel);
		
		JLabel NameUpdateLabel = new JLabel("이름");
		NameUpdateLabel.setBounds(44, 148, 57, 15);
		memberUpdatePanel.add(NameUpdateLabel);
		
		JLabel EmailUpdateLabel = new JLabel("이메일");
		EmailUpdateLabel.setBounds(44, 192, 57, 15);
		memberUpdatePanel.add(EmailUpdateLabel);
		
		JLabel AddressUpdateLabel = new JLabel("주소");
		AddressUpdateLabel.setBounds(44, 230, 57, 15);
		memberUpdatePanel.add(AddressUpdateLabel);
		
		JLabel BirthUpdateLabel = new JLabel("생년월일");
		BirthUpdateLabel.setBounds(44, 276, 57, 15);
		memberUpdatePanel.add(BirthUpdateLabel);
		
		JLabel NumberUpdateLabel = new JLabel("연락처");
		NumberUpdateLabel.setBounds(44, 315, 57, 15);
		memberUpdatePanel.add(NumberUpdateLabel);
		
		birthUpdateField = new JTextField();
		birthUpdateField.setColumns(10);
		birthUpdateField.setBounds(157, 273, 116, 21);
		memberUpdatePanel.add(birthUpdateField);
		
		numberUpdateField = new JTextField();
		numberUpdateField.setColumns(10);
		numberUpdateField.setBounds(157, 312, 116, 21);
		memberUpdatePanel.add(numberUpdateField);
		
		JButton userInfoUpdateButton = new JButton("회원정보");
		userInfoUpdateButton.setBounds(54, 363, 97, 23);
		memberUpdatePanel.add(userInfoUpdateButton);
		
		JButton UpdateFormButton = new JButton("변경폼");
		UpdateFormButton.setBounds(157, 363, 97, 23);
		memberUpdatePanel.add(UpdateFormButton);
		
		JButton UpdateButton = new JButton("변경사항저장");
		UpdateButton.setBounds(266, 363, 116, 23);
		memberUpdatePanel.add(UpdateButton);
		
		JPanel memberJoinPanel = new JPanel();
		memberJoinPanel.setBackground(new Color(217, 252, 255));
		memberJoinPanel.setToolTipText("");
		memberTabbedPane.addTab("가입", null, memberJoinPanel, null);
		memberJoinPanel.setLayout(null);
		
		IdField = new JTextField();
		IdField.setBounds(181, 35, 116, 21);
		memberJoinPanel.add(IdField);
		IdField.setColumns(10);
		
		JLabel idJoinLabel = new JLabel("회원가입");
		idJoinLabel.setBounds(181, 10, 57, 15);
		memberJoinPanel.add(idJoinLabel);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(181, 66, 116, 21);
		memberJoinPanel.add(PasswordField);
		
		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(181, 97, 116, 21);
		memberJoinPanel.add(NameField);
		
		EmailField = new JTextField();
		EmailField.setColumns(10);
		EmailField.setBounds(181, 141, 116, 21);
		memberJoinPanel.add(EmailField);
		
		AddressField = new JTextField();
		AddressField.setColumns(10);
		AddressField.setBounds(181, 179, 116, 21);
		memberJoinPanel.add(AddressField);
		
		JLabel IdJoinLabel = new JLabel("아이디");
		IdJoinLabel.setBounds(68, 38, 57, 15);
		memberJoinPanel.add(IdJoinLabel);
		
		JLabel PasswordJoinLabel = new JLabel("패스워드");
		PasswordJoinLabel.setBounds(68, 69, 57, 15);
		memberJoinPanel.add(PasswordJoinLabel);
		
		JLabel NameJoinLabel = new JLabel("이름");
		NameJoinLabel.setBounds(68, 100, 57, 15);
		memberJoinPanel.add(NameJoinLabel);
		
		JLabel EmailJoinLabel = new JLabel("이메일");
		EmailJoinLabel.setBounds(68, 144, 57, 15);
		memberJoinPanel.add(EmailJoinLabel);
		
		JLabel AddressJoinLabel = new JLabel("주소");
		AddressJoinLabel.setBounds(68, 182, 57, 15);
		memberJoinPanel.add(AddressJoinLabel);
		
		JLabel BirthJoinLabel = new JLabel("생년월일");
		BirthJoinLabel.setBounds(68, 228, 57, 15);
		memberJoinPanel.add(BirthJoinLabel);
		
		JLabel NumberJoinLabel = new JLabel("연락처");
		NumberJoinLabel.setBounds(68, 267, 57, 15);
		memberJoinPanel.add(NumberJoinLabel);
		
		BirthField = new JTextField();
		BirthField.setColumns(10);
		BirthField.setBounds(181, 225, 116, 21);
		memberJoinPanel.add(BirthField);
		
		NumberField = new JTextField();
		NumberField.setColumns(10);
		NumberField.setBounds(181, 264, 116, 21);
		memberJoinPanel.add(NumberField);
		
		JButton checkButton = new JButton("중복확인");
		checkButton.setBounds(309, 34, 97, 23);
		memberJoinPanel.add(checkButton);
 
	}
}
