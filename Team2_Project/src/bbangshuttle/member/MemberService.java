package bbangshuttle.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;



public class MemberService {
	private MemberDao memberDao;
	public MemberService() throws Exception{
		memberDao=new MemberDao();
	}
	/*
	 * 회원가입
	 */
	/*public int addMember(Member member) throws Exception{
		//1.아이디중복체크
		int userCount = rs.getInt(1);
		return userCount;
	} */
	
	
	
	
	
	
	/*
	 * 회원로그인
	 */
	/*public int login(String memberID,String password)throws Exception{
		// 0:실패 1:성공
		int result=0;
		if(memberDao.)
	}*/
	

	
	
	/*
	 * 회원아이디중복체크
	 */
	/*public boolean isDuplicatedId(String memberId) throws Exception{
		if(memberDao.)
	}*/
	
	
	/*
	 * 회원상세보기
	 */
	public String memberDetail(String memberId) throws Exception{
		return memberDao.findByID(memberId);
	}
	/*
	 * 회원수정
	 */
	public int memberUpdate(Member member)throws Exception{
		return memberDao.update(member);
	}
	/*
	 * 회원탈퇴
	 */
	public int memberDelete(String memberId) throws Exception{
		return memberDao.delete(memberId);
	}
	/*
	 * 회원로그아웃
	 */
	public void logout() {}
	
}