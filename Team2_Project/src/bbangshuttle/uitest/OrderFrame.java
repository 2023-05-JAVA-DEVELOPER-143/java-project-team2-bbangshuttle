package bbangshuttle.uitest;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import bbangshuttle.member.Member;
import bbangshuttle.order.Order;
import bbangshuttle.order.OrderItem;
import bbangshuttle.order.OrderService;

public class OrderFrame extends JFrame {
    private JPanel mainPanel;
    private JComboBox<Order> orderComboBox;
    private JTable orderTable;
    private JButton deleteOrderButton;
    private JButton completeOrderButton;

    private OrderService orderService;
    private Member loggedInMember;

    public OrderFrame(Member loggedInMember) throws Exception {
        this.loggedInMember = loggedInMember;

        setTitle("주문 목록");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        orderService = new OrderService();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        orderComboBox = new JComboBox<>();
        orderTable = new JTable();
        deleteOrderButton = new JButton("주문 삭제");
        completeOrderButton = new JButton("주문 완료");

        mainPanel.add(orderComboBox, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        mainPanel.add(deleteOrderButton, BorderLayout.SOUTH);
        mainPanel.add(completeOrderButton, BorderLayout.SOUTH);

        updateOrderComboBox();
        showOrderDetail();

        orderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOrderDetail();
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedOrder();
            }
        });

        completeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeOrder();
            }
        });

        add(mainPanel);

        setVisible(true);
    }

    private void updateOrderComboBox() throws Exception {
        orderComboBox.removeAllItems();
        List<Order> orders = orderService.orderList(loggedInMember.getMemberId());
        for (Order order : orders) {
            orderComboBox.addItem(order);
        }
    }

    private void showOrderDetail() {
        Order selectedOrder = (Order) orderComboBox.getSelectedItem();
        if (selectedOrder != null) {
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"상품명", "수량", "가격"}, 0);
            for (OrderItem orderItem : selectedOrder.getOrderItemList()) {
                String productName = orderItem.getProduct().getP_name();
                int quantity = orderItem.getOi_qty();
                int price = orderItem.getProduct().getPrice();
                tableModel.addRow(new Object[]{productName, quantity, price});
            }
            orderTable.setModel(tableModel);
        } else {
            orderTable.setModel(new DefaultTableModel());
        }
    }

    private void deleteSelectedOrder() {
        Order selectedOrder = (Order) orderComboBox.getSelectedItem();
        if (selectedOrder != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "주문을 삭제하시겠습니까?",
                    "삭제 확인", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    orderService.deleteByOrderNo(selectedOrder.getO_no());
                    updateOrderComboBox();
                    showOrderDetail();
                    JOptionPane.showMessageDialog(this, "주문이 삭제되었습니다.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "주문 삭제 중 오류가 발생하였습니다.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "삭제할 주문을 선택하세요.");
        }
    }

    private void completeOrder() {
        Order selectedOrder = (Order) orderComboBox.getSelectedItem();
        if (selectedOrder != null) {
            double totalPrice = selectedOrder.getO_price();
            int pointToBeAdded = (int) (totalPrice * 0.01);

            // 멤버 포인트 업데이트
            loggedInMember.setMemberPoint(loggedInMember.getMemberPoint() + pointToBeAdded);

            // 업데이트 된 멤버 정보를 저장 (실제 응용 프로그램에서는 데이터베이스에 저장)
            // 여기서는 간단히 메시지만 표시
            JOptionPane.showMessageDialog(this, pointToBeAdded + "포인트가 회원님의 포인트로 추가되었습니다.", "주문 완료", JOptionPane.INFORMATION_MESSAGE);

            // 완료한 주문은 콤보 박스에서 삭제하고 테이블 갱신
            orderComboBox.removeItem(selectedOrder);
            showOrderDetail();
        } else {
            JOptionPane.showMessageDialog(this, "주문을 선택하세요.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new OrderFrame(null); // 로그인 기능이 없으므로 loggedInMember는 null로 설정
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
