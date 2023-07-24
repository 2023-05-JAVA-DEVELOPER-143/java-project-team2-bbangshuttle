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
import bbangshuttle.member.MemberService;

public class LoginFrame extends JFrame {
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    private MemberService memberService;

    public LoginFrame(MemberService memberService) {
        this.memberService = memberService;

        setTitle("Login Frame");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 아이디 입력 필드
        JLabel userNameLabel = new JLabel("아이디:");
        userNameField = new JTextField(10);

        // 비밀번호 입력 필드
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField(10);

        // 로그인 버튼
        loginButton = new JButton("로그인");
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
                        JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패. 아이디와 비밀번호를 확인하세요.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginFrame.this, "로그인 과정에서 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // 비밀번호 필드는 사용 후에 지워줌.
                passwordField.setText("");
            }
        });

        // 회원가입 버튼
        signUpButton = new JButton("회원가입");
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

        // 레이아웃 설정
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.add(userNameLabel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        getContentPane().add(loginPanel, BorderLayout.CENTER);
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
