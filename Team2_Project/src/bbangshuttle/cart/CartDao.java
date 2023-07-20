package bbangshuttle.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bbangshuttle.common.DataSource;
import bbangshuttle.product.Product;

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
		if(rs.next()) {
			cart=new Cart(rs.getInt("c_no"),rs.getInt("p_qty"),rs.getString("member_id"),
					new Product(rs.getInt("p_no"),rs.getString("p_name"),rs.getString("p_desc"),
							rs.getString("p_image"),rs.getInt("price"),rs.getInt("p_view_count"),
							rs.getInt("p_category")));
		}
		
		return cart;
	}
	
}
