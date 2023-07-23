package bbangshuttle.uitest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MainFrame extends JFrame {
    private Member loggedInMember;

    public MainFrame(Member loggedInMember) {
        this.loggedInMember = loggedInMember;

        setTitle("Main Frame");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 각 프레임으로 이동하는 버튼 생성
        JButton productButton = new JButton("상품 목록");
        productButton.addActionListener(e -> {
			try {
				showProductFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        JButton cartButton = new JButton("장바구니");
        cartButton.addActionListener(e -> {
			try {
				showCartFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        JButton orderButton = new JButton("주문 목록");
        orderButton.addActionListener(e -> {
			try {
				showOrderFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        // 로그인한 회원이 없을 경우 로그인 프레임으로 이동하는 버튼 생성
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(e -> {
			try {
				showLoginFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        // 로그인한 회원이 있을 경우 로그인 프레임으로 가는 버튼은 비활성화
        if (loggedInMember != null) {
            loginButton.setEnabled(false);
        } else {
            // 로그인하지 않은 경우 나머지 버튼 비활성화
            productButton.setEnabled(false);
            cartButton.setEnabled(false);
            orderButton.setEnabled(false);
        }

        // 메인 프레임에 버튼 추가
        JPanel panel = new JPanel();
        panel.add(productButton);
        panel.add(cartButton);
        panel.add(orderButton);
        panel.add(loginButton);

        add(panel);
    }

    private void showProductFrame() throws Exception{
        ProductFrame productFrame = new ProductFrame(loggedInMember);
        productFrame.setVisible(true);
    }

    private void showCartFrame() throws Exception{
        CartFrame cartFrame = new CartFrame(loggedInMember);
        cartFrame.setVisible(true);
    }

    private void showOrderFrame() throws Exception{
        OrderFrame orderFrame = new OrderFrame(loggedInMember);
        orderFrame.setVisible(true);
    }

    private void showLoginFrame()throws Exception {
        LoginFrame loginFrame = new LoginFrame(new MemberService());
        loginFrame.setVisible(true);
        dispose();
    }

    // 로그아웃 기능 구현
    private void logout() throws Exception{
        loggedInMember = null;
        JOptionPane.showMessageDialog(this, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        // 로그아웃 후 다시 로그인 프레임을 띄움
        showLoginFrame();
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
