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
	// 회원추가
	public int insert(Member member) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_INSERT);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPassword());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getMemberEmail());
		pstmt.setString(5, member.getMemberAddress());
		pstmt.setString(6, member.getMemberNumber());
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}	
	// 회원정보갱신
	public int update(Member member) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_UPDATE);
		pstmt.setString(1, member.getMemberName());
		pstmt.setString(2, member.getMemberAddress());
		pstmt.setString(3, member.getMemberEmail());
		pstmt.setString(4, member.getMemberNumber());
		
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	// 회원삭제
	public int delete(String memberId) throws Exception {
	    Connection con = dataSource.getConnection();
	    PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_DELETE);
	    pstmt.setString(1, memberId);
	    int rowCount = pstmt.executeUpdate();
	    pstmt.close();
	    con.close();
	    return rowCount;
	}
	public Member findById(String memberId)throws Exception {
		Member member=null;
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_ID);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			member=new Member(
					rs.getString("memberId"),
					rs.getString("memberPw"), 
					rs.getString("memberName"), 
					rs.getString("memberAddress"),
					rs.getString("memberEmail"),
					rs.getString("memberNumber"));
		}
		return member;
	}
	
	
}
