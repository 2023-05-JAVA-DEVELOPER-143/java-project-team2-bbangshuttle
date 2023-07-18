package bbangshuttle.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bbangshuttle.common.DataSource;

public class ProductDao {
	private DataSource dataSource;
	public ProductDao() throws Exception{
		dataSource=new DataSource();
	}
	/*
	 * 상품번호로 검색.
	 */
	public Product findByProductNo(int p_no) throws Exception {
		Product product= null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
		pstmt.setInt(1, p_no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(
					rs.getInt("p_no"),
					rs.getString("p_name"), 
					rs.getString("p_desc"),
					rs.getString("p_image"), 
					rs.getInt("p_price"),
					rs.getInt("p_view_count"),
					rs.getInt("p_view_count")
					);
		}
		return product;
	}
	
	
}
