package com.itwill.uidesign.member2;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class MemberPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MemberPanel() {
		setBackground(new Color(252, 207, 252));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTabbedPane memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		memberTabbedPane.setPreferredSize(new Dimension(10, 10));
		add(memberTabbedPane);
		
		JPanel memberJoinPanel = new JPanel();
		memberJoinPanel.setBackground(new Color(217, 252, 255));
		memberJoinPanel.setToolTipText("");
		memberTabbedPane.addTab("가입", null, memberJoinPanel, null);
		memberJoinPanel.setLayout(null);
		
		JPanel memberListPanel = new JPanel();
		memberTabbedPane.addTab("로그인", null, memberListPanel, null);
		memberListPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(15, 15));
		scrollPane.setBounds(0, 269, 353, -267);
		memberListPanel.add(scrollPane);
		
		JPanel memberUpdatePanel = new JPanel();
		memberTabbedPane.addTab("수정", null, memberUpdatePanel, null);
		memberUpdatePanel.setLayout(null);

	}

}
