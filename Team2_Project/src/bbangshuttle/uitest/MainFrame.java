package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MainFrame extends JFrame {
    private MemberService memberService;
    private Member loggedInMember;

    public MainFrame(Member loggedInMember) throws Exception {
        this.loggedInMember = loggedInMember;
        this.memberService = new MemberService();

        setTitle("Main Frame");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 회원 정보 표시 페이지
        MemberFrame memberFrame = new MemberFrame(memberService, loggedInMember);
        add(memberFrame, BorderLayout.CENTER);

        // 로그아웃 버튼
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 프레임으로 돌아가기
                LoginFrame loginFrame = new LoginFrame(memberService);
                loginFrame.setVisible(true);
                dispose(); // 메인 프레임 닫기
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MemberService memberService = new MemberService();
                    // 여기서 로그인 프레임을 띄움.
                    LoginFrame loginFrame = new LoginFrame(memberService);
                    loginFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
