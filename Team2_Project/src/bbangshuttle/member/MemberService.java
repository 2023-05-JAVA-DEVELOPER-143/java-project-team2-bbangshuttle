package bbangshuttle.member;

import java.util.List;

public class MemberService {
	private MemberDao memberDao;
	public MemberService() throws Exception{
		memberDao=new MemberDao();
	}
	/*
	 * 회원가입
	 */
	public boolean addMember(Member newMember) throws Exception{
		boolean isSuccess=false;
		/*
		 * 아이디존재여부
		 */
	    String findMember= memberDao.findByID(newMember.getMemberId());
	    if(findMember==null) {
	    	//아이디중복안됨
	    	int rowCount=memberDao.insert(newMember);
	    	isSuccess = true;
	    }else {
	    	//아이디중복
	    	isSuccess=false;
	    }
	    return isSuccess;
	}
	
	/*
	 * 회원로그인
	 */
	
	
	
	/*
	 * 회원아이디중복체크
	 */
	
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
	
	
	
}