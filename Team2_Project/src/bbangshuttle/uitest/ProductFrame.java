package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.Product;
import bbangshuttle.product.ProductService;

public class ProductFrame extends JFrame {
    private JTable productTable;
    private ProductTableModel tableModel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private ProductService productService;
    private CartService cartService;
    private Member currentUser;

    public ProductFrame(Member currentUser) throws Exception {
        this.currentUser = currentUser;
        productService = new ProductService();
        cartService = new CartService();

        setTitle("Product Frame");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 카드 레이아웃을 사용하여 4개의 패널로 구성
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 상품 목록을 보여줄 테이블 생성
        tableModel = new ProductTableModel();
        productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectedProduct = tableModel.getProductAt(selectedRow);
                    showProductDetail(selectedProduct);
                }
            }
        });

        // 프레임에 추가
        add(createMenuPanel(), BorderLayout.NORTH);
        cardPanel.add(createProductPanel("전체메뉴", 0), "전체메뉴");
        cardPanel.add(createProductPanel("베이커리", 1), "베이커리");
        cardPanel.add(createProductPanel("음료", 2), "음료");
        add(cardPanel, BorderLayout.CENTER);

        // Product 목록 초기화
        updateProductList();
    }

    private JPanel createMenuPanel() {
        // 카테고리 선택 버튼 추가
        JPanel menuPanel = new JPanel();
        menuPanel.add(createCategoryButton("전체메뉴", 0));
        menuPanel.add(createCategoryButton("베이커리", 1));
        menuPanel.add(createCategoryButton("음료", 2));
        return menuPanel;
    }

    private JButton createCategoryButton(String categoryName, int categoryNumber) {
        JButton categoryButton = new JButton(categoryName);
        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, categoryName);
            }
        });
        return categoryButton;
    }

    private JPanel createProductPanel(String categoryName, int categoryNumber) {
        JPanel productPanel = new JPanel(new BorderLayout());

        // 카테고리 별로 상품 목록을 보여줄 테이블 생성
        JTable productListByCategory = new JTable();
        productListByCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productListByCategory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = productListByCategory.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectedProduct = tableModel.getProductAt(selectedRow);
                    showProductDetail(selectedProduct);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productListByCategory);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        // 카테고리 별로 상품 목록 초기화
        updateProductListByCategory(categoryNumber, productListByCategory);

        return productPanel;
    }

    private void updateProductListByCategory(int category, JTable table) {
        try {
            List<Product> products;
            if (category == 0) {
                products = productService.ProductFindByAll();
            } else {
                products = productService.productCategoryAll(category);
            }
            ProductTableModel model = new ProductTableModel(products);
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProductList() {
        try {
            // 전체 상품 목록 가져오기
            List<Product> products = productService.ProductFindByAll();
            tableModel.setData(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProductDetail(Product product) {
        // 카드 레이아웃에서 사용할 상세 정보 패널 생성
        JPanel detailPanel = createProductDetailPanel(product);

        // 카드 레이아웃에 상세 정보 패널 추가 및 전환
        cardPanel.add(detailPanel, "상세페이지");
        cardLayout.show(cardPanel, "상세페이지");
    }

    private JPanel createProductDetailPanel(Product product) {
        JPanel detailPanel = new JPanel(new BorderLayout());

        // 이미지
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(120, 80)); // 이미지 칸의 크기를 120x80 픽셀로 조정

        // 이미지 정보 설정
        String imagePath = product.getP_image(); // 상대주소에 있는 이미지 경로
        
        ImageIcon imageIcon = new ImageIcon(imagePath);
        imageLabel.setIcon(imageIcon);

        // 상품명
        JLabel nameLabel = new JLabel(product.getP_name());

        // 가격
        JLabel priceLabel = new JLabel(product.getPrice() + "원");

        // 상품 설명
        JLabel descriptionLabel = new JLabel(product.getP_desc());

        // 조회수
        JLabel viewCountLabel = new JLabel("조회수: " + product.getP_view_count());

        // 수량 선택 콤보박스
        Integer[] quantities = {1, 2, 3, 4, 5};
        JComboBox<Integer> quantityComboBox = new JComboBox<>(quantities);

        // 장바구니 담기 버튼
        JButton addToCartButton = new JButton("장바구니에 담기");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantityComboBox.getSelectedItem();
                try {
                    Cart cart = new Cart(0, quantity, currentUser.getMemberId(), product);
                    cartService.addCart(cart);
                    // 장바구니에 담은 후의 처리 (예: 장바구니 아이콘 업데이트 등)
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 패널에 컴포넌트 추가
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(imageLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(viewCountLabel);
        infoPanel.add(new JLabel("수량:"));
        infoPanel.add(quantityComboBox);
        infoPanel.add(addToCartButton);

        detailPanel.add(infoPanel, BorderLayout.CENTER);

        return detailPanel;
    }

    // 테이블 모델 클래스
    private class ProductTableModel extends AbstractTableModel {
        private List<Product> data;
        private String[] columnNames = {"이미지", "상품명", "가격", "상품설명"};

        public ProductTableModel() {
        }

        public ProductTableModel(List<Product> data) {
            this.data = data;
        }

        public void setData(List<Product> data) {
            this.data = data;
            fireTableDataChanged();
        }

        public Product getProductAt(int row) {
            return data.get(row);
        }

        @Override
        public int getRowCount() {
            if (data == null) {
                return 0;
            }
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Product product = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    // 이미지
                    return product.getP_image();
                case 1:
                    // 상품명
                    return product.getP_name();
                case 2:
                    // 가격
                    return product.getPrice() + "원";
                case 3:
                    // 상품설명
                    return product.getP_desc();
                default:
                    return null;
            }
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
