package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class LoginDialog extends JDialog {
	/**********1.UserService객체멤버필드로선언*******/
	private MemberService userService;
	/************************************************/
	
	/*****LoginDailog객체는 ShopMainFrame객체의참조변수를 멤버변수로가져야한다.*******/
	private ShopMainFrame shopMainFrame;
	public void setShopMainFrame(ShopMainFrame shopMainFrame) {
		this.shopMainFrame = shopMainFrame;
	}
	/***********************************************************************************/
	private final JPanel contentPanel = new JPanel();
	private JTextField userIdTextField;
	private JPasswordField userPasswordField;
	private JLabel loginMessageLabel;
	/**
	 * Create the dialog.
	 */
	public LoginDialog() throws Exception{
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginDialog.class.getResource("/images/user.png")));
		setTitle("로그인");
		setBounds(100, 100, 350, 193);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			userIdTextField = new JTextField();
			userIdTextField.setText("guard2");
			userIdTextField.setToolTipText("아이디");
			userIdTextField.setBounds(69, 12, 240, 30);
			contentPanel.add(userIdTextField);
			userIdTextField.setColumns(10);
		}
		
		userPasswordField = new JPasswordField();
		userPasswordField.setText("2222");
		userPasswordField.setToolTipText("패쓰워드");
		userPasswordField.setBounds(69, 52, 240, 30);
		contentPanel.add(userPasswordField);
		
		JLabel idLabel = new JLabel("");
		idLabel.setIcon(new ImageIcon(LoginDialog.class.getResource("/images/user_login_id.png")));
		idLabel.setBounds(22, 10, 35, 36);
		contentPanel.add(idLabel);
		
		JLabel passwordLabel = new JLabel("");
		passwordLabel.setIcon(new ImageIcon(LoginDialog.class.getResource("/images/user_padlock.png")));
		passwordLabel.setBounds(22, 52, 35, 30);
		contentPanel.add(passwordLabel);
		{
			loginMessageLabel = new JLabel("");
			loginMessageLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			loginMessageLabel.setForeground(Color.RED);
			loginMessageLabel.setBounds(69, 92, 240, 28);
			contentPanel.add(loginMessageLabel);
		}
		
	
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("로그인");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/********로그인버튼클릭시*********/
						//1.입력유효성체크
						String userid = userIdTextField.getText();
						String password=userPasswordField.getText();
						if(userid.equals("")) {
							loginMessageLabel.setText("아이디를 입력하세요.");
							userIdTextField.requestFocus();
							return;
						}
						if(password.equals("")) {
							loginMessageLabel.setText("패쓰워드를 입력하세요.");
							userPasswordField.requestFocus();
							return;
						}
						//2.UserService.login 메쏘드호출
						/***********2.UserService.login 메쏘드호출**************/
						try {
							
							int result = userService.login(userid, password);
							if(result==1) {
								/*
								 * 로그인성공
								 *  - 성공한아이디로 User정보얻기
								 *  - ShopMainFrame에 User객체 넘겨주기(ShopMainFrame객체의 메소드호출시 인자로 넘겨주기)
								 *  - 로그인창닫기
								 */
								Member loginUser = userService.memberDetail(password);
								shopMainFrame.loginProcess(loginUser);
								dispose();
							}else if(result==0) {
								//로그인실패
								loginMessageLabel.setText("아이디또는비밀번호가 일치하지않습니다.");
								userIdTextField.requestFocus();
								userIdTextField.setSelectionStart(0);
								userIdTextField.setSelectionEnd(userid.length());
							}
						}catch (Exception ex) {
							ex.printStackTrace();
						}
						/******************************************************/
					}
				});
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton loginCancelButton = new JButton("취소");
				loginCancelButton.setActionCommand("Cancel");
				buttonPane.add(loginCancelButton);
			}
			{
				JButton userJoinButton = new JButton("회원가입");
				buttonPane.add(userJoinButton);
			}
		}
		/**********2.UserService객체생성초기화*******/
		userService=new MemberService();
		/********************************************/
	}//end constructor
}//end class
