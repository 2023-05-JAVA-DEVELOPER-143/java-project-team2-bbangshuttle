package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class OrderFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel orderContentPanel;
    private JLabel totalPriceLabel;
    private int totalPrice = 0;

    private CartService cartService;
    private Member currentUser;
    private CartFrame cartFrame;
    private List<Cart> cartItems; // 카트에서 가져온 주문한 상품 목록
    private List<Cart> orderedItems; // 주문 완료 후 주문한 상품 목록

    public OrderFrame(Member currentUser, CartFrame cartFrame, List<Cart> cartItems) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(OrderFrame.class.getResource("/bbangshuttle/images/6071826_delivery_food_meal_order_food delivery_icon.png")));
        this.currentUser = currentUser;
        this.cartFrame = cartFrame;
        this.cartItems = cartItems;
        orderedItems = new ArrayList<>(); // 초기화

        cartService = new CartService();

        setTitle("Order Frame");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout 사용
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 주문 전 페이지
        JPanel orderPagePanel = createOrderPage();
        cardPanel.add(orderPagePanel, "ORDER_PAGE");

        // 주문 완료 페이지
        JPanel completeOrderPage = createCompleteOrderPage();
        cardPanel.add(completeOrderPage, "COMPLETE_ORDER_PAGE");

        // 기본적으로 주문 전 페이지 보여줌
        cardLayout.show(cardPanel, "ORDER_PAGE");

        getContentPane().add(cardPanel, BorderLayout.CENTER);

        // 하단 버튼들 생성
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton completeOrderButton = new JButton("주문 완료");
        completeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeOrder();
            }
        });

        JButton clearAllButton = new JButton("전체 삭제");
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllOrders();
            }
        });

        JButton returnButton = new JButton("상품 페이지로 돌아가기");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProductFrame();
            }
        });

        bottomPanel.add(clearAllButton);
        bottomPanel.add(completeOrderButton);
        bottomPanel.add(returnButton);
        getContentPane().add(bottomPanel, BorderLayout.NORTH);
    }

    private JPanel createOrderPage() {
        JPanel orderPagePanel = new JPanel(new BorderLayout());

        // 주문한 상품 내용을 보여줄 패널 생성
        orderContentPanel = new JPanel();
        orderContentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        orderPagePanel.add(new JScrollPane(orderContentPanel), BorderLayout.CENTER);

        // 총 가격 표시 라벨
        totalPriceLabel = new JLabel("총 가격: 0원");
        updateTotalPrice();
        orderPagePanel.add(totalPriceLabel, BorderLayout.SOUTH);

        // 주문한 상품 목록 초기화
        updateOrderList();

        return orderPagePanel;
    }

    private JPanel createCompleteOrderPage() {
        JPanel completeOrderPage = new JPanel();
        completeOrderPage.setLayout(new BorderLayout());

        JLabel completeOrderLabel = new JLabel("주문이 완료되었습니다.");
        completeOrderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        completeOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        completeOrderPage.add(completeOrderLabel, BorderLayout.CENTER);

        return completeOrderPage;
    }

    private void updateTotalPrice() {
        totalPrice = 0;
        for (Cart cart : cartItems) {
            totalPrice += cart.getProduct().getPrice() * cart.getCart_qty();
        }
        totalPriceLabel.setText("총 가격: " + new DecimalFormat("#,###").format(totalPrice) + "원");
    }

    private void updateOrderList() {
        orderContentPanel.removeAll();
        for (Cart cart : cartItems) {
            JPanel productPanel = createProductPanel(cart);
            orderContentPanel.add(productPanel);
        }
        orderContentPanel.revalidate();
        orderContentPanel.repaint();
        updateTotalPrice(); // 주문 목록이 업데이트될 때 총 가격도 함께 업데이트
    }

    private JPanel createProductPanel(Cart cart) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel productInfoLabel = new JLabel("<html><font size='3'>" + ": " + cart.getProduct().getP_name() + "<br>"
                + "가격: " + new DecimalFormat("#,###").format(cart.getProduct().getPrice()) + "원<br>"
                + "설명: " + cart.getProduct().getP_desc() + "</font></html>");
        productPanel.add(productInfoLabel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel quantityLabel = new JLabel("수량: " + cart.getCart_qty());
        controlPanel.add(quantityLabel);

        // 상품 삭제 버튼 추가
        JButton removeButton = new JButton("삭제");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCartItem(cart);
            }
        });
        controlPanel.add(removeButton);

        productPanel.add(controlPanel, BorderLayout.SOUTH);

        // 카트 정보를 컴포넌트에 저장하여 사용
        productPanel.putClientProperty("cart", cart);

        return productPanel;
    }

    private void completeOrder() {
        // 주문 완료 메시지 표시
        JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");

        // 주문한 상품 목록을 orderedItems에 저장
        orderedItems.addAll(cartItems);

        // 주문 목록 업데이트
        if (cartFrame != null) {
            cartFrame.setOrderedItems(orderedItems);
            cartFrame.updateOrderList();
        }

        // 주문 완료 페이지로 전환
        cardLayout.show(cardPanel, "COMPLETE_ORDER_PAGE");

        // 주문 완료 후 카트 비우기
        clearAllOrders();
        // 맴버 포인트 적립
        addMemberPoint((int) (totalPrice * 0.01));
    }

    private void clearAllOrders() {
        try {
            cartService.deleteCartItemByUserId(currentUser.getMemberId());
            cartItems.clear(); // 주문한 상품 목록도 비워줘야함.
            updateOrderList();
            updateTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeCartItem(Cart cart) {
        try {
            cartService.deleteCartItemByCartNo(cart.getCart_no());
            cartItems.remove(cart);
            updateOrderList();
            updateTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMemberPoint(int point) {
        try {
            // 현재 맴버 포인트에 추가 포인트를 더함
            currentUser.setMemberPoint(currentUser.getMemberPoint() + point);

            // 맴버 포인트를 업데이트
            MemberService memberService = new MemberService();
            memberService.updateMemberPoint(currentUser, point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProductFrame() {
        try {
            new ProductFrame(currentUser).setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCartFrame() {
        try {
            new CartFrame(currentUser).setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new OrderFrame(new Member(), null, null).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
