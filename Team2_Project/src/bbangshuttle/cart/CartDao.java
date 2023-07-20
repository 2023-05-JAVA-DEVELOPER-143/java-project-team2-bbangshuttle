package bbangshuttle.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bbangshuttle.common.DataSource;

public class CartDao {

	private DataSource dataSource;
	
	public CartDao() throws Exception {
		
		dataSource = new DataSource();
	
	}
	// 회원 장바구니 한개 주문목록
	public Cart cartSelectNo(int p_no , String member_id)throws Exception {
		Cart cart=null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_MEMVERID_SELECT_BY_NO);
		pstmt.setString(p_no, member_id);
		pstmt.setInt(p_no, p_no);
		ResultSet rs = pstmt.executeQuery();
		
		
		return cart;
	}
	
}
