package bbangshuttle.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bbangshuttle.common.DataSource;

public class memberDao {
	private DataSource dataSource;
	public memberDao() throws Exception {
		dataSource = new DataSource();
	}
	public int insert(Member user) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_INSERT);
		pstmt.setString(1, user.getMemberId());
		pstmt.setString(2, user.getMemberPassword());
		pstmt.setString(3, user.getMemberName());
		pstmt.setString(4, user.getMemberEmail());
		pstmt.setString(5, user.getMemberAddress());
		pstmt.setString(6, user.getMemberNumber());
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}	
	
	public int update(Member user) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_UPDATE);
		pstmt.setString(1, user.getMemberName());
		pstmt.setString(2, user.getMemberAddress());
		pstmt.setString(3, user.getMemberEmail());
		pstmt.setString(4, user.getMemberNumber());
		
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	
	public int delete(String m_Id) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_REMOVE);
		pstmt.setString(1, m_Id);
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	
	
	
}
