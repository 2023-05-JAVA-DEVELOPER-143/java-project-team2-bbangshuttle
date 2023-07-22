package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ProductFrame extends JFrame {
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
    private Member loggedInMember; // 로그인한 회원 정보를 저장할 변수

    public ProductFrame(Member loggedInMember) throws Exception {
        this.loggedInMember = loggedInMember;

        setTitle("제품 관리");
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

    private class AllProductPanel extends JPanel {
        private JButton categoryButton;
        private JComboBox<String> categoryComboBox;
        private DefaultComboBoxModel<String> comboBoxModel;

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

            String[] columnNames = {"이미지", "상품명", "가격", "상품설명"};
            Object[][] rowData = new Object[products.size()][4];

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                rowData[i][0] = new ImageIcon(product.getP_image());
                rowData[i][1] = product.getP_name();
                rowData[i][2] = product.getPrice();
                rowData[i][3] = product.getP_desc();
            }

            DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
            JTable productTable = new JTable(tableModel);
            productTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

            add(new JScrollPane(productTable), BorderLayout.CENTER);
        }

        public void setCategoryButtonActionListener(ActionListener actionListener) {
            categoryButton.addActionListener(actionListener);
        }

        public String getSelectedCategory() {
            return categoryComboBox.getSelectedItem().toString();
        }
    }

    private class CategoryProductPanel extends JPanel {
        private JButton backButton;
        private JTable productList;

        public CategoryProductPanel(String category, List<Product> products) {
            setLayout(new BorderLayout());

            backButton = new JButton("뒤로 가기");

            String[] columnNames = {"이미지", "상품명", "가격", "상품설명"};
            Object[][] rowData = new Object[products.size()][4];

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                rowData[i][0] = new ImageIcon(product.getP_image());
                rowData[i][1] = product.getP_name();
                rowData[i][2] = product.getPrice();
                rowData[i][3] = product.getP_desc();
            }

            DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
            productList = new JTable(tableModel);
            productList.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

            add(backButton, BorderLayout.NORTH);
            add(new JScrollPane(productList), BorderLayout.CENTER);

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "전체");
                }
            });

            productList.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = productList.getSelectedRow();
                    if (row >= 0) {
                        Product selectedProduct = products.get(row);
                        int response = JOptionPane.showConfirmDialog(ProductFrame.this,
                                selectedProduct.getP_name() + "를 장바구니에 담으시겠습니까?", "장바구니에 담기", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            int quantity = Integer.parseInt(JOptionPane.showInputDialog(ProductFrame.this, "수량을 입력하세요:"));
                            if (quantity > 0) {
                                try {
									cartService.addCart(loggedInMember.getMemberId(), selectedProduct.getP_no(), quantity);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                                JOptionPane.showMessageDialog(ProductFrame.this, selectedProduct.getP_name() + "가 장바구니에 추가되었습니다.");
                            } else {
                                JOptionPane.showMessageDialog(ProductFrame.this, "잘못된 수량입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            });
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
                
                Member loggedInMember = new Member(); 
                
                new ProductFrame(loggedInMember);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
