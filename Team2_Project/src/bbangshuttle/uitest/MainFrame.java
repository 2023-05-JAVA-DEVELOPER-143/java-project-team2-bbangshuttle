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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
    private Member loggedInMember;

    public MainFrame(Member loggedInMember) {
       setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/o_user.png")));
        this.loggedInMember = loggedInMember;

        setTitle(loggedInMember.getMemberName()+"님 로그인중");
        setSize(350, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 각 프레임으로 이동하는 버튼 생성
        JButton productButton = new JButton("상품 목록");
        productButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        productButton.setBounds(57, 328, 105, 47);
        productButton.addActionListener(e -> showProductFrame());

        JButton cartButton = new JButton("장바구니");
        cartButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        cartButton.setBounds(174, 328, 105, 47);
        cartButton.addActionListener(e -> showCartFrame());

        JButton orderButton = new JButton("주문 목록");
        orderButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        orderButton.setBounds(174, 385, 105, 47);
        orderButton.addActionListener(e -> showOrderFrame());

        // 로그인한 회원이 없을 경우 로그인 프레임으로 이동하는 버튼 생성
        JButton loginButton = new JButton("로그인");
        loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        loginButton.setBounds(57, 385, 105, 47);
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
        panel.setLayout(null);
        panel.add(productButton);
        panel.add(cartButton);
        panel.add(orderButton);
        panel.add(loginButton);

        getContentPane().add(panel);
        
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        logoutButton.setBounds(57, 442, 105, 47);
        logoutButton.addActionListener(e -> logout());

        panel.add(logoutButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\pngwing.com (4).png"));
        lblNewLabel.setBounds(39, 58, 283, 248);
        panel.add(lblNewLabel);
        
        
        JButton memberFrameButton = new JButton("이전 페이지");
        memberFrameButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        memberFrameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        memberFrameButton.setBounds(174, 442, 105, 47);
        memberFrameButton.addActionListener(e -> showMemberFrame());

        panel.add(memberFrameButton);
    }

    // 이전으로 버튼을 눌렀을 때 MemberFrame으로 돌아가는 기능 구현
    private void showMemberFrame() {
        try {
            MemberFrame memberFrame = new MemberFrame(new MemberService(), loggedInMember);
            memberFrame.setVisible(true);
            dispose(); // 현재 프레임 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }
    
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
       System.exit(0);
        
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