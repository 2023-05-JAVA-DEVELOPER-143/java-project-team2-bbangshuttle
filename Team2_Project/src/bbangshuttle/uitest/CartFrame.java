package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartDao;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.Product;
import bbangshuttle.product.ProductDao;
import bbangshuttle.product.ProductService;

public class CartFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private AllProductPanel allProductPanel;
    private CategoryProductPanel bakeryProductPanel;
    private CategoryProductPanel beverageProductPanel;

    private ProductService productService;
    private ProductDao productDao;
    private List<Product> allProducts;
    private Cart cart;
    private CartDao cartDao;
    private CartService cartService;
    private Member loggedInMember; // 현재 로그인한 회원 정보

    public CartFrame(Member loggedInMember) throws Exception {
        this.loggedInMember = loggedInMember;

        setTitle("장바구니");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productDao = new ProductDao();
        productService = new ProductService();
        allProducts = productDao.findAll();
        cart = new Cart();
        cartDao = new CartDao();
        cartService = new CartService();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        allProductPanel = new AllProductPanel(allProducts);
        bakeryProductPanel = new CategoryProductPanel("베이커리", productService.productCategoryAll(1));
        beverageProductPanel = new CategoryProductPanel("음료", productService.productCategoryAll(2));

        mainPanel.add(allProductPanel, "전체");
        mainPanel.add(bakeryProductPanel, "베이커리");
        mainPanel.add(beverageProductPanel, "음료");

        cardLayout.show(mainPanel, "전체");
        add(mainPanel);

        allProductPanel.setCategoryButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = allProductPanel.getSelectedCategory();
                if (selectedCategory.equals("전체")) {
                    cardLayout.show(mainPanel, "전체");
                } else if (selectedCategory.equals("베이커리")) {
                    cardLayout.show(mainPanel, "베이커리");
                } else if (selectedCategory.equals("음료")) {
                    cardLayout.show(mainPanel, "음료");
                }
            }
        });

        setVisible(true);
    }

    // 장바구니 목록을 업데이트하는 메서드
    private void updateCartList() {
        try {
            List<Cart> cartItems = cartService.getCartItemByUserId(loggedInMember.getMemberId());
            allProductPanel.updateCartItems(cartItems);
            bakeryProductPanel.updateCartItems(cartItems);
            beverageProductPanel.updateCartItems(cartItems);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(CartFrame.this, "장바구니 목록을 가져오는 중 오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class AllProductPanel extends JPanel {
        private JButton categoryButton;
        private JComboBox<String> categoryComboBox;
        private DefaultComboBoxModel<String> comboBoxModel;
        private JTable cartTable;
        private CartTableModel cartTableModel;

        public AllProductPanel(List<Product> products) {
            setLayout(new BorderLayout());

            categoryButton = new JButton("카테고리별 보기");
            comboBoxModel = new DefaultComboBoxModel<>();
            categoryComboBox = new JComboBox<>(comboBoxModel);

            comboBoxModel.addElement("전체");
            comboBoxModel.addElement("베이커리");
            comboBoxModel.addElement("음료");

            JPanel topPanel = new JPanel();
            topPanel.add(categoryButton);
            topPanel.add(categoryComboBox);

            add(topPanel, BorderLayout.NORTH);

            cartTableModel = new CartTableModel(new ArrayList<>());
            cartTable = new JTable(cartTableModel);
            cartTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

            add(new JScrollPane(cartTable), BorderLayout.CENTER);
        }

        public void setCategoryButtonActionListener(ActionListener actionListener) {
            categoryButton.addActionListener(actionListener);
        }

        public String getSelectedCategory() {
            return categoryComboBox.getSelectedItem().toString();
        }

        public void updateCartItems(List<Cart> cartItems) {
            cartTableModel.setCartItems(cartItems);
            cartTableModel.fireTableDataChanged();
        }
    }

    private class CategoryProductPanel extends JPanel {
        private JButton backButton;
        private JTable cartTable;
        private CartTableModel cartTableModel;

        public CategoryProductPanel(String category, List<Product> products) {
            setLayout(new BorderLayout());

            backButton = new JButton("뒤로 가기");

            cartTableModel = new CartTableModel(new ArrayList<>());
            cartTable = new JTable(cartTableModel);
            cartTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

            JPanel topPanel = new JPanel();
            topPanel.add(backButton);

            add(topPanel, BorderLayout.NORTH);
            add(new JScrollPane(cartTable), BorderLayout.CENTER);
        }

        public void setBackButtonActionListener(ActionListener actionListener) {
            backButton.addActionListener(actionListener);
        }

        public void updateCartItems(List<Cart> cartItems) {
            cartTableModel.setCartItems(cartItems);
            cartTableModel.fireTableDataChanged();
        }
    }

    private static class CartTableModel extends DefaultTableModel {
        private List<Cart> cartItems;

        public CartTableModel(List<Cart> cartItems) {
            this.cartItems = cartItems;
        }

        public void setCartItems(List<Cart> cartItems) {
            this.cartItems = cartItems;
        }

        @Override
        public int getRowCount() {
            return cartItems.size();
        }

        @Override
        public int getColumnCount() {
            return 4; // cart_no, cart_qty, product, 상품명, 가격, 수량
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "상품 이미지";
                case 1:
                    return "상품명";
                case 2:
                    return "가격";
                case 3:
                    return "수량";
                default:
                    return "";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Cart cartItem = cartItems.get(rowIndex);
            Product product = cartItem.getProduct();

            switch (columnIndex) {
                case 0:
                    return new ImageIcon(product.getP_image());
                case 1:
                    return product.getP_name();
                case 2:
                    return product.getPrice();
                case 3:
                    return cartItem.getCart_qty();
                default:
                    return "";
            }
        }
    }

    private static class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CartFrame(null); // 로그인 기능이 없으므로 loggedInMember는 null로 설정
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
