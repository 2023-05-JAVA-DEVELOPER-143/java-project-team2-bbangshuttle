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
	public int addMember(Member member) throws Exception{
		//1.아이디중복체크
		if(memberDao.CountUserId(member.getMemberId())>=1) {
			//중복
			return -1;
		}else {
			//가입
			int rowCount=memberDao.insert(member);
			return rowCount;
		}
	} 
	
	/*
	 * 회원로그인
	 */
	public int login(String memberId,String password)throws Exception{
		// 0:실패 1:성공
		int result=0;
		if(memberDao.CountUserId(memberId)==1) {
			//아이디존재하는경우
			Member loginMember = memberDao(memberId);
			if(loginMember.getMemberPassword().equals(password)) {
				//패스워드일치
				result=1;
			}else {
				//패스워드불일치
				result=0;
			}
		}else {
			//회원이 아닌경우
			result=0;
		}
		return result;
	}
	
	
	
	/*
	 * 회원아이디중복체크
	 */
	public boolean isDuplicatedId(String memberId) throws Exception{
		if(memberDao.CountUserId(memberId)>=1) {
			return true;
		}else {
			return false;
		}
			
	}
	
	/*
	 * 회원상세보기
	 */
	public String memberDetail(String memberId) throws Exception{
		return memberDao(memberId);
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