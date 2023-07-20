package bbangshuttle.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bbangshuttle.common.DataSource;
import bbangshuttle.product.Product;

public class CartDao {

	private DataSource dataSource;

	public CartDao() throws Exception {

		dataSource = new DataSource();

	}

	// 회원 장바구니 한개 주문목록
	public Cart cartSelectNo(int p_no, String member_id) throws Exception {
		Cart cart = null;
		Product product = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMVERID_SELECT_BY_NO);
		pstmt.setString(1, member_id);
		pstmt.setInt(2, p_no);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			cart = new Cart(rs.getInt("cart_no"), rs.getInt("cart_qty"), rs.getString("member_id"),
					new Product(rs.getInt("p_no"), rs.getString("p_name"), rs.getString("p_desc"),
							rs.getString("p_image"), rs.getInt("p_price"), rs.getInt("p_view_count"),
							rs.getInt("p_category")));

		}
		return cart;
	}
	// " "회원 장바구니 상품 전체 읽기

	public List<Cart> cartSelectByMemeberNo(String member_id) throws Exception {
		List<Cart> cartList = new ArrayList<>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMBERID_SELECT_BY_ALL);
		pstmt.setString(1, member_id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			cartList.add(new Cart(rs.getInt("cart_no"), rs.getInt("cart_qty"), rs.getString("member_id"),
					new Product(rs.getInt("p_no"), rs.getString("p_name"), rs.getString("p_desc"),
							rs.getString("p_image"), rs.getInt("p_price"), rs.getInt("p_view_count"),
							rs.getInt("p_category"))));
		}

		return cartList;
	}

	// " "회원 장바구니 " "품목 하나 업데이트
	public int cartMemberUpdateNo(int cart_qty, String member_id, int p_no) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMBERID_UPDATE);
		pstmt.setInt(1, cart_qty);
		pstmt.setString(2, member_id);
		pstmt.setInt(3, p_no);

		int update= pstmt.executeUpdate();

		return update;


	}

	// " "회원 장바구니 품목 전체 변경

	public int cartMemberUpdateAll(int cart_qty, String member_id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMBERID_UPDATE_ALL);
		pstmt.setInt(1, cart_qty);
		pstmt.setString(2, member_id);
		int update = pstmt.executeUpdate();
		
		return update;
	}
	public int cartMemberDeleteNo(String member_id ,int p_no ) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMBERID_DELETE_BY_P_NO);
		pstmt.setString(1, member_id);
		pstmt.setInt(2, p_no);
		int delete = pstmt.executeUpdate();
		return delete;
	}
	public int cartMemberDeleteByAll(String member_id) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMBERID_SELECT_BY_ALL);
		pstmt.setString(1, member_id);
		int delete = pstmt.executeUpdate();
		return delete;
	}
}
