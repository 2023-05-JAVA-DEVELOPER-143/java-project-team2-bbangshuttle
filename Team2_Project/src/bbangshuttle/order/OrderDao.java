package bbangshuttle.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import bbangshuttle.common.DataSource;
/*	
 	Dao 클래스 기본 메소드 
 	insert
	selectByNo or selectById
	selectAll
	updateById or updateByNo
	deleteById or updateByNo
 
 */
public class OrderDao {
	
	private DataSource dataSource;
	// 카트, 상품도 추가
	
	public OrderDao() throws Exception {
		dataSource = new DataSource();
	}
	
	// 주문 전체 삭제
	public int deleteByMemberId(String member_Id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_MEMBER_ID);
			pstmt.setString(1, member_Id);
			rowCount = pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	
	
	// 주문 한건 삭제 
	public int daleteByOrderNo(int o_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_O_NO);
			pstmt.setInt(1, o_no);
			rowCount = pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	
	
	// 주문 생성
	public int insert(Order order) throws Exception {
		/*
		insert into orders(o_no,o_date,o_price,o_desc,member_id) 
		VALUES (orders_o_no_SEQ.nextval, sysdate-2, 5000, '따뜻한 아이스 아메리카노 외0종', 
				'parkshuttle33');
		
		insert into order_item(oi_no, oi_qty, o_no, p_no) 
		VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 9);

		 */
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_INSERT);
			pstmt1.setInt(1, order.getO_price());
			pstmt1.setString(2, order.getO_desc());
			pstmt1.setString(3, order.getMember_id());
			pstmt1.executeUpdate();
			
			pstmt2 = con.prepareStatement(OrderSQL.ORDERITEM_INSERT);
			for(OrderItem orderItem : order.getOrderItemList()) {
				pstmt2.setInt(1, orderItem.getOi_qty());
				pstmt2.setInt(2, orderItem.getProduct().getP_no());
				pstmt2.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		return 0;		
	}
	
	
	// 주문 전체(특정 사용자)
	public List<Order> findOrderByMemberId(String member_id)throws Exception {
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			/*
			 * select * from orders where member_id = 'kimshuttle11';
			 */
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_BY_MEMBER_ID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/*
				 	private int o_no; // 주문번호
					private Date o_date; // 주문날짜
					private int o_price; // 주문가격
					private String o_desc; // 주문설명

					private String member_id; // 멤버아이디
				 */
				orderList.add(new Order(rs.getInt("o_no"), rs.getDate("o_date"), 
								rs.getInt("o_price"), rs.getString("o_desc"), 
								rs.getString("member_id"), null));
			}
			
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}
	
	
	/*
	 * 주문 1개 보기 (주문 상세 리스트)
	 */
	public Order findByOrderNo(int o_no) throws Exception {
		Order order = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		return order;
	}
	
	
	
	
	
	
	
	
}
