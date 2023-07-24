package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberDao;
import bbangshuttle.member.MemberService;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {
	private MemberDao memberDao;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signUpButton;

	private MemberService memberService;
	private JButton IdSearchButton;
	private JButton PwSearchButton;
	private String memberEmail;

	public LoginFrame(MemberService memberService) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/o_user.png")));
		this.memberService = memberService;

		setTitle("Login Frame");
		setSize(300, 270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// 아이디 입력 필드
		JLabel userNameLabel = new JLabel("아이디:");
		userNameLabel.setBounds(46, 18, 94, 45);
		userNameField = new JTextField(10);
		userNameField.setBounds(140, 18, 132, 45);

		// 레이아웃 설정
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameField);

		getContentPane().add(loginPanel);

		// 로그인 버튼
		loginButton = new JButton("로그인");
		loginButton.setBounds(139, 128, 132, 45);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 로그인 버튼을 클릭하면 입력된 아이디와 비밀번호를 가져옴.
				String username = userNameField.getText();
				char[] password = passwordField.getPassword();

				// MemberService를 통해 로그인 기능을 수행함.
				try {
					Member member = memberService.loginMember(username, new String(password));
					if (member != null) {
						// 로그인 성공시 회원 프레임을 띄우고 현재 프레임을 숨김.
						MemberFrame memberFrame = new MemberFrame(memberService, member);
						memberFrame.setVisible(true);
						setVisible(false);
					} else {
						// 로그인 실패 처리
						JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패. 아이디와 비밀번호를 확인하세요.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(LoginFrame.this, "로그인 과정에서 오류가 발생했습니다.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				// 비밀번호 필드는 사용 후에 지워줌.
				passwordField.setText("");
			}
		});
		passwordField = new JPasswordField(10);
		passwordField.setBounds(140, 73, 132, 45);
		loginPanel.add(passwordField);

		// 비밀번호 입력 필드
		JLabel passwordLabel = new JLabel("비밀번호:");
		passwordLabel.setBounds(46, 73, 94, 45);
		loginPanel.add(passwordLabel);
		loginPanel.add(loginButton);

		IdSearchButton = new JButton("아이디 찾기");
		IdSearchButton.setBounds(13, 169, 128, 45);
		IdSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When IdSearchButton is clicked, prompt user for input
				memberEmail = JOptionPane.showInputDialog(LoginFrame.this, "이메일을 입력하세요:");
				if (memberEmail != null && !memberEmail.isEmpty()) {
					try {
						// Use MemberService to search for the ID
						String email = memberDao.findByEmail(memberEmail);
						if (email != null) {
							// Display the ID using a dialog box
							JOptionPane.showMessageDialog(LoginFrame.this, "검색된 이메일: " + memberEmail, "이메일 찾기 결과",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(LoginFrame.this, "검색된 이메일이 없습니다.", "이메일 찾기 결과",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(LoginFrame.this, "이메일 검색 과정에서 오류가 발생했습니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, "이메일을 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		loginPanel.add(IdSearchButton);

		PwSearchButton = new JButton("비밀번호 찾기");
		PwSearchButton.setBounds(13, 128, 128, 45);
		PwSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When IdSearchButton is clicked, prompt user for input
				String memberId = JOptionPane.showInputDialog(LoginFrame.this, "아이디를 입력하세요:");
				if (memberId != null && !memberId.isEmpty()) {
					try {
						// Use MemberService to search for the ID
						String id = memberDao.findByEmail(memberId);
						if (id != null) {
							// Display the ID using a dialog box
							JOptionPane.showMessageDialog(LoginFrame.this, "검색된 아이디: " + memberId, "아이디 찾기 결과",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(LoginFrame.this, "검색된 아이디가 없습니다.", "아이디 찾기 결과",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(LoginFrame.this, "아이디 검색 과정에서 오류가 발생했습니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, "아이디를 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		loginPanel.add(PwSearchButton);

		// 회원가입 버튼
		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(139, 169, 132, 45);
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼을 클릭하면 회원가입 폼 프레임을 띄움
				SignupFormFrame signupFormFrame = null;
				try {
					signupFormFrame = new SignupFormFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signupFormFrame.setVisible(true);
			}
		});
		loginPanel.add(signUpButton);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MemberService memberService = new MemberService();
					LoginFrame loginFrame = new LoginFrame(memberService);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
