package bbangshuttle.member;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import bbangshuttle.common.DataSource;

public class MemberDao {
	private DataSource dataSource;

	public MemberDao() throws Exception {
		dataSource = new DataSource();
	}

	// 회원가입
	public int insert(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_INSERT);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPassword());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getMemberEmail());
		pstmt.setString(5, member.getMemberAddress());
		pstmt.setString(6, member.getMemberBirth());
		pstmt.setString(7, member.getMemberNumber());
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}

	// 회원정보수정
	public int update(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_UPDATE);
		pstmt.setString(1, member.getMemberPassword());
		pstmt.setString(2, member.getMemberEmail());
		pstmt.setString(3, member.getMemberAddress());
		pstmt.setString(4, member.getMemberNumber());
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}

	// 회원탈퇴
	public int delete(String memberId) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_DELETE);
		pstmt.setString(1, memberId);
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	
	 // 이메일 입력 후, 아이디 찾기
	public String findByEmail(String memberEmail) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_ID);
		pstmt.setString(1, memberEmail);
		ResultSet rs = pstmt.executeQuery();
		String memberId = null;
		if(rs.next()) {
			memberId = rs.getString("member_id");
		}
		return memberId;
	}
	
	// 아이디 입력 후, 비밀번호 찾기
	public String findById(String memberId) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_PW);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		String memberPassword = null;
		if(rs.next()) {
			memberPassword = rs.getString("member_password");
		}
		return memberPassword;
	}
	
	// 내 정보 출력 (비밀번호 입력 후 출력)
	public Member showMyInfo(String memberPassword) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_MYINFO);
		pstmt.setString(1, memberPassword);
		ResultSet rs = pstmt.executeQuery();
		Member member = null;
		if(rs.next()) {
			member = new Member(
					rs.getString("member_id"),
					rs.getString("member_password"),
					rs.getString("member_name"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_birth"),
					rs.getString("member_number"),
					rs.getDate("member_regdate"),
					rs.getInt("memner_point"));
		}
		return member;
	}
	
	
	// 회원 목록 전체 출력
	public ArrayList<Member> findAll() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_ALL);
		ResultSet rs=pstmt.executeQuery();
		ArrayList<Member> memberList=new ArrayList<Member>();
		while(rs.next()) {
			memberList.add(new Member(
					rs.getString("member_id"),
					rs.getString("member_password"),
					rs.getString("member_name"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_birth"),
					rs.getString("member_number"),
					rs.getDate("member_regdate"),
					rs.getInt("memner_point"))
					);
		}
		return memberList;
	}
	
	
	// 회원가입 시, 아이디 중복 체크
	public int CountUserId(String memberId) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELETE_BY_ID_COUNT);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int userCount = rs.getInt(1);
		return userCount;
	}
	
}
	
	
	

	

