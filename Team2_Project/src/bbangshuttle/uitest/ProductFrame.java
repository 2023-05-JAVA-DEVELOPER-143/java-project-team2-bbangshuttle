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
import bbangshuttle.product.Product;
import bbangshuttle.product.ProductService;

public class ProductFrame extends JFrame {
    private JList<Product> productList;
    private JPanel homePanel;
    private JButton allMenuButton;
    private JButton bakeryButton;
    private JButton beverageButton;
    private JButton addToCartButton;
    private JComboBox<Integer> quantityComboBox;
    private JLabel totalLabel;
    private int totalPrice = 0;

    private ProductService productService;
    private CartService cartService;
    private Member currentUser;

    public ProductFrame(Member currentUser) throws Exception {
        this.currentUser = currentUser;
        productService = new ProductService();
        cartService = new CartService();

        setTitle("Product Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Product 목록을 보여줄 JList 생성
        productList = new JList<>();
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateTotalPrice();
            }
        });

        // 홈 패널에 카테고리 선택 버튼 추가
        allMenuButton = new JButton("전체메뉴");
        allMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					productService.ProductFindByAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        bakeryButton = new JButton("베이커리");
        bakeryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProductListByCategory(1);
            }
        });

        beverageButton = new JButton("음료");
        beverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProductListByCategory(2);
            }
        });

        homePanel = new JPanel();
        homePanel.add(allMenuButton);
        homePanel.add(bakeryButton);
        homePanel.add(beverageButton);

        // 장바구니 버튼
        addToCartButton = new JButton("장바구니에 담기");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    int quantity = Integer.parseInt(quantityComboBox.getSelectedItem().toString());
                    try {
                        Cart cart = new Cart(0, quantity, currentUser.getMemberId(), selectedProduct);
                        cartService.addCart(cart);
                        updateTotalPrice();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
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

        // 주문하기 버튼
        JButton orderButton = new JButton("주문하기");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new OrderFrame(currentUser).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 하단 영역에 버튼들 추가
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(addToCartButton);
        bottomPanel.add(new JLabel("수량:"));
        bottomPanel.add(quantityComboBox);
        bottomPanel.add(totalLabel);
        bottomPanel.add(orderButton);

        // 프레임에 추가
        add(homePanel, BorderLayout.NORTH);
        add(new JScrollPane(productList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Product 목록 초기화
        updateProductList();
    }

    private void updateTotalPrice() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            int quantity = Integer.parseInt(quantityComboBox.getSelectedItem().toString());
            totalPrice = selectedProduct.getPrice() * quantity;
            totalLabel.setText("총 가격: " + totalPrice + "원");
        }
    }

    private void updateProductList() {
        try {
            // 전체 상품 목록 가져오기
            List<Product> products = productService.ProductFindByAll();
            Product[] productArray = products.toArray(new Product[0]);
            productList.setListData(productArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 선택에 따른 상품 목록 업데이트
    private void updateProductListByCategory(int category) {
        try {
            List<Product> products;
            if ("전체메뉴".equals(category)) {
                products = productService.ProductFindByAll();
            } else {
                products = productService.productCategoryAll(category);
            }
            Product[] productArray = products.toArray(new Product[0]);
            productList.setListData(productArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProductFrame(new Member()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
