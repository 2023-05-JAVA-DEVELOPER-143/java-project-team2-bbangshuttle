package bbangshuttle.user;

import java.sql.Date;

public class User {
	
	private String memberId;		// 아이디	
	private String memberPassword;  // 비밀번호
	private String memberName;		// 이름
	private String memberEmail;		// 이메일
	private String memberAddress;	// 주소
	private int memberBirth;		// 생년월일
	private String memberNumber;	// 연락처
	private Date memberRegdate;		// 가입일
	private int memberPoint;		// 보유 포인트
	private String orderDetails;	// 주문내역
	private String customerService;	// 고객센터
	
	public User() {
		
	}

	public User(String memberId, String memberPassword, String memberName, String memberEmail, String memberAddress,
			int memberBirth, String memberNumber, Date memberRegdate, int memberPoint, String orderDetails,
			String customerService) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberBirth = memberBirth;
		this.memberNumber = memberNumber;
		this.memberRegdate = memberRegdate;
		this.memberPoint = memberPoint;
		this.orderDetails = orderDetails;
		this.customerService = customerService;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public int getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(int memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public Date getMemberRegdate() {
		return memberRegdate;
	}

	public void setMemberRegdate(Date memberRegdate) {
		this.memberRegdate = memberRegdate;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getCustomerService() {
		return customerService;
	}

	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

	@Override
	public String toString() {
		return "User [memberId=" + memberId + ", memberPassword=" + memberPassword + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberAddress=" + memberAddress + ", memberBirth=" + memberBirth
				+ ", memberNumber=" + memberNumber + ", memberRegdate=" + memberRegdate + ", memberPoint=" + memberPoint
				+ ", orderDetails=" + orderDetails + ", customerService=" + customerService + "]";
	}
	
	
	


	
}