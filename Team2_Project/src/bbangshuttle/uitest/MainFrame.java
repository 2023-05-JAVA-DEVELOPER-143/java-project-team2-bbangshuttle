package bbangshuttle.uitest;


import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private Member loggedInMember; // 로그인한 회원 정보
    private ProductFrame productFrame;
    private CartFrame cartFrame;
    private OrderFrame orderFrame;

    private MemberService memberService;

    public MainFrame() throws Exception {
        setTitle("빵셔틀");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        memberService = new MemberService();

        // 로그인 프로세스 호출
        loginProcess();

        // ProductFrame
        productFrame = new ProductFrame(loggedInMember);

        // CartFrame
        cartFrame = new CartFrame(loggedInMember);

        // OrderFrame
        orderFrame = new OrderFrame(loggedInMember);

        setVisible(true);
    }

    // 로그인 프로세스 메서드
    private void loginProcess() {
        boolean loggedIn = false;
        while (!loggedIn) {
            try {
                LoginFrame loginFrame = new LoginFrame(memberService);
                loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                loginFrame.setVisible(true);

                // 로그인 창이 닫힐 때까지 대기
                while (loginFrame.isVisible()) {
                    Thread.sleep(100);
                }

                Member loginMember = loginFrame.getLoggedInMember();
                if (loginMember != null) {
                    loggedIn = true;
                    loggedInMember = loginMember;
                } else {
                    int option = JOptionPane.showConfirmDialog(this,
                            "로그인을 취소하시겠습니까?", "로그인 취소", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "로그인 중 오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
				new MainFrame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}
