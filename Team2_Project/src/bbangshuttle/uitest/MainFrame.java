package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Main Frame");
        setSize(1024, 860); // 사이즈 1024x860으로 변경
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 메인 패널 생성
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // ProductFrame으로 이동할 수 있는 버튼
        JButton productFrameButton = new JButton("상품 프레임으로");
        productFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductFrame productFrame = new ProductFrame(new Member());
                    productFrame.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(productFrameButton);

        // CartFrame으로 이동할 수 있는 버튼
        JButton cartFrameButton = new JButton("장바구니 프레임으로");
        cartFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CartFrame cartFrame = new CartFrame(new Member());
                    cartFrame.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(cartFrameButton);

        // OrderFrame으로 이동할 수 있는 버튼
        JButton orderFrameButton = new JButton("주문 프레임으로");
        orderFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderFrame orderFrame = new OrderFrame(new Member());
                    orderFrame.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(orderFrameButton);

        // 로그인 프레임으로 이동할 수 있는 버튼
        JButton loginFrameButton = new JButton("로그인 프레임으로");
        loginFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginFrame loginFrame = new LoginFrame(new MemberService());
                    loginFrame.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(loginFrameButton);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
