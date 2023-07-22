package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.ProductService;

public class CartFrame extends JFrame {
    private JList<Cart> cartList;
    private JComboBox<Integer> quantityComboBox;
    private JLabel totalLabel;
    private int totalPrice = 0;

    private ProductService productService;
    private CartService cartService;
    private Member currentUser;

    public CartFrame(Member currentUser) throws Exception {
        this.currentUser = currentUser;
        productService = new ProductService();
        cartService = new CartService();

        setTitle("Cart Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 장바구니 목록을 보여줄 JList 생성
        cartList = new JList<>();
        cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cartList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateTotalPrice();
            }
        });

        // 수량 조절 콤보박스
        Integer[] quantities = {1, 2, 3, 4, 5};
        quantityComboBox = new JComboBox<>(quantities);
        quantityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTotalPrice();
            }
        });

        // 총 가격 표시 라벨
        totalLabel = new JLabel("총 가격: 0원");

        // 상품 수량 변경 버튼
        JButton updateQuantityButton = new JButton("수량 변경");
        updateQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cart selectedCart = cartList.getSelectedValue();
                if (selectedCart != null) {
                    int quantity = Integer.parseInt(quantityComboBox.getSelectedItem().toString());
                    try {
                        selectedCart.setCart_qty(quantity);
                        cartService.updateCart(selectedCart);
                        updateTotalPrice();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // 상품 삭제 버튼
        JButton removeButton = new JButton("삭제");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cart selectedCart = cartList.getSelectedValue();
                if (selectedCart != null) {
                    try {
                        cartService.deleteCartItemByCartNo(selectedCart.getCart_no());
                        updateCartList();
                        updateTotalPrice();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // 주문하기 버튼
        JButton orderButton = new JButton("주문하기");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 주문 프레임과 연동
                try {
                    new OrderFrame(currentUser).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 하단 영역에 버튼들 추가
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(updateQuantityButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(new JLabel("수량:"));
        bottomPanel.add(quantityComboBox);
        bottomPanel.add(totalLabel);
        bottomPanel.add(orderButton);

        // 프레임에 추가
        add(new JScrollPane(cartList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 장바구니 목록 초기화
        updateCartList();
    }

    private void updateTotalPrice() {
        Cart selectedCart = cartList.getSelectedValue();
        if (selectedCart != null) {
            int quantity = Integer.parseInt(quantityComboBox.getSelectedItem().toString());
            totalPrice = selectedCart.getProduct().getPrice() * quantity;
            totalLabel.setText("총 가격: " + totalPrice + "원");
        }
    }

    private void updateCartList() {
        try {
            // 장바구니 목록 가져오기
            List<Cart> carts = cartService.getCartItemByUserId(currentUser.getMemberId());
            Cart[] cartArray = carts.toArray(new Cart[0]);
            cartList.setListData(cartArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CartFrame(new Member()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
