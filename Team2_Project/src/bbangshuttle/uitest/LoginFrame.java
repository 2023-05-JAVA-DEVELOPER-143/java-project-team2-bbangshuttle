package bbangshuttle.uitest;


import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField loginIdField;
    private JPasswordField loginPasswordField;
    private JButton loginButton;
    private Member loggedInMember; // 로그인한 회원 정보를 담을 변수

    private MemberService memberService;

    public LoginFrame(MemberService memberService) {
        this.memberService = memberService;
        setTitle("로그인");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setVisible(true);
    }

    private void initComponents() {
        JLabel idLabel = new JLabel("아이디:");
        JLabel passwordLabel = new JLabel("비밀번호:");

        loginIdField = new JTextField(15);
        loginPasswordField = new JPasswordField(15);

        loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void addComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel("아이디:"));
        panel.add(loginIdField);
        panel.add(new JLabel("비밀번호:"));
        panel.add(loginPasswordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void login() throws Exception {
        String memberId = loginIdField.getText();
        String password = new String(loginPasswordField.getPassword());

        Member loginMember = memberService.loginMember(memberId, password);
        if (loginMember != null) {
            loggedInMember = loginMember; // 로그인 성공 시, loggedInMember에 회원 정보 저장
            new MemberFrame(loggedInMember);
            dispose(); // 현재의 로그인 창은 닫음
        } else {
            JOptionPane.showMessageDialog(this, "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
        }
    }

    // 로그인한 회원 정보를 반환하는 메소드
    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MemberService memberService = new MemberService(); // 적절히 초기화된 MemberService 객체 생성
                new LoginFrame(memberService);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
