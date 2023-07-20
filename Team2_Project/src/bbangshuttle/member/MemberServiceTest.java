package bbangshuttle.member;

import java.sql.Date;

public class MemberServiceTest {
	
	public static void main(String[] args) throws Exception {
		MemberService memberService = new MemberService();
		//1.회원가입
		System.out.println("1.회원가입");
		//String memberId, String memberPassword, String memberName, String memberEmail, String memberAddress, String memberBirth, String memberNumber, Date memberRegdate, int memberPoint
		int rowCount = memberService.addMember(new Member("sdfsadf", "asdfasdf", "ahffk", "adsfsf@nasdfas", "adsfasf", "1999/09/09", "010-0101-0101", null, 0));
		System.out.println(">>>>"+rowCount);
		//2.회원로그인
		rowCount=0;
		rowCount = memberService.login("sdfsadf", "asdfasdf");
		System.out.println(">>>>"+rowCount);
		System.out.println();
		//3.회원아이디중복체크
		System.out.println("3.회원아이디중복체크");
        String testId = "sdfsadf";
        boolean isDuplicated = memberService.isDuplicatedId(testId);
        System.out.println(">>>> ID '" + testId + "' is " + (isDuplicated ? "duplicated" : "unique"));
		//4.회원상세보기
		
		//5.회원수정
		//6.회원탈퇴
	}
	
	
}
