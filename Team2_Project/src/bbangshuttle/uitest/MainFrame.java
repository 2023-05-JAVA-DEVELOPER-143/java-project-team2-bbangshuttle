package bbangshuttle.uitest;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private Member loggedInMember;

    public MainFrame(Member loggedInMember) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/o_user.png")));
        this.loggedInMember = loggedInMember;

        setTitle(loggedInMember.getMemberName()+"님 로그인중");
        setSize(640, 960);
        setSize(350, 600);		// 고정사이즈
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 각 프레임으로 이동하는 버튼 생성
        JButton productButton = new JButton("상품 목록");
        productButton.addActionListener(e -> showProductFrame());

        JButton cartButton = new JButton("장바구니");
        cartButton.addActionListener(e -> showCartFrame());

        JButton orderButton = new JButton("주문 목록");
        orderButton.addActionListener(e -> showOrderFrame());

        // 로그인한 회원이 없을 경우 로그인 프레임으로 이동하는 버튼 생성
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(e -> showLoginFrame());

        // 로그인한 회원이 있을 경우 로그인 버튼은 비활성화
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

        getContentPane().add(panel);
        
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(e -> logout());
        panel.add(logoutButton);
    }

    private void showProductFrame() {
        try {
            ProductFrame productFrame = new ProductFrame(loggedInMember);
            productFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCartFrame() {
        try {
            CartFrame cartFrame = new CartFrame(loggedInMember);
            cartFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showOrderFrame() {
        try {
            CartService cartService = new CartService();
            List<Cart> orderedItems = cartService.getCartItemByUserId(loggedInMember.getMemberId());
            OrderFrame orderFrame = new OrderFrame(loggedInMember, null, orderedItems);
            orderFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoginFrame() {
        try {
            LoginFrame loginFrame = new LoginFrame(new MemberService());
            loginFrame.setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그아웃 기능 구현
    private void logout() {
        loggedInMember = null;
        JOptionPane.showMessageDialog(this, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        // 로그아웃 후 다시 로그인 프레임을 띄움
        showLoginFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
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
