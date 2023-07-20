package bbangshuttle.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bbangshuttle.common.DataSource;

public class memberDao {
	private DataSource dataSource;
	public memberDao() throws Exception {
		dataSource = new DataSource();
	}
	
	public int insert() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(null);
		
		
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	
	public int delete() throws Exception {
		return 0;
	}
	
	public int update() throws Exception {
		return 0;
	}
	
	public int select() throws Exception {
		return 0;
	}
	
	public int findAll() throws Exception {
		return 0;
	}
	
}
