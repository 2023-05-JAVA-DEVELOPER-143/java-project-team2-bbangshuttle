package bbangshuttle.test;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberDao;

public class MemberDaoTestMain {

	public static void main(String[] args) throws Exception{
		
		MemberDao memberDao = new MemberDao();
		
		/*
		System.out.println("1. 회원가입 : " 
				+ memberDao.insert(new Member("Yoonshuttle166","6666", "윤셔틀", "Yoonshuttle166@gmail.com", "제주시", "1992/05/06", "010-6666-6666", null, 0)));
		*/
		
		/*
		System.out.println("2. 회원정보수정 : " 
				+ memberDao.update(new Member("Yoonshuttle166","xxxx", "윤셔틀", "xxxx@gmail.com", "여기가어디야", "1992/05/06", "010-xxxx-xxxx", null, 0)));
		*/
		
		/*
		System.out.println("3. 회원탈퇴 : " + memberDao.delete("Yoonshuttle166"));
		*/
		
		System.out.println("4. 아이디 찾기 : " + memberDao.findByEmail(""));
	}

}
