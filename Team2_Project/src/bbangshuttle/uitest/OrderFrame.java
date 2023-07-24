package bbangshuttle.uitest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.Product;

public class OrderFrame extends JFrame {
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

        // 주문한 상품 내용을 보여줄 패널 생성
        orderContentPanel = new JPanel();
        orderContentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        getContentPane().add(new JScrollPane(orderContentPanel), BorderLayout.CENTER);

        // 총 가격 표시 라벨
        totalPriceLabel = new JLabel("총 가격: 0원");
        updateTotalPrice();
        getContentPane().add(totalPriceLabel, BorderLayout.SOUTH);

        // 주문한 상품 목록 초기화
        updateOrderList();

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

        // 주문 완료 후 카트 비우고 CartFrame으로 돌아가기
        clearAllOrders();
        showCartFrame();
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
