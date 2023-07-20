package bbangshuttle.member;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import bbangshuttle.common.DataSource;

public class MemberDao {
	private DataSource dataSource;

	public MemberDao() throws Exception {
		dataSource = new DataSource();
	}

	// 회원추가
	public int insert(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_INSERT);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPassword());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getMemberEmail());
		pstmt.setString(5, member.getMemberAddress());
		pstmt.setString(6, member.getMemberNumber());
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}

	// 회원정보갱신
	public int update(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_UPDATE);
		pstmt.setString(1, member.getMemberName());
		pstmt.setString(2, member.getMemberAddress());
		pstmt.setString(3, member.getMemberEmail());
		pstmt.setString(4, member.getMemberNumber());

		int rowCount = pstmt.executeUpdate();
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
	
	 // 아이디찾기
	public String findByID(String memberName) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_ID);
		pstmt.setString(1, memberName);
		ResultSet rs = pstmt.executeQuery();
		String id = null;
		if (rs.next()) {
				id = rs.getString("member_id");
			
		}
		return id;
	}
	public int insert() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(null);
		
		
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}

	
}

