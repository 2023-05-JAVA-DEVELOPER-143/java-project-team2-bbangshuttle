package bbangshuttle.order;

import bbangshuttle.common.DataSource;

public class OrderDao {
	
	private DataSource dataSource;
	
	public OrderDao() throws Exception {
		dataSource = new DataSource();
	}
	
	/*
	 주문 전체 삭제
	 주문 한건 삭제
	 
	 주문 생성
	 주문 전체 (특정사용자)
	 
	 주문 + 주문 아이템 전체 (특정사용자)
	 주문 한개 보기(주문상세리스트)
	 
	 */
	
	// 주문 전체 삭제
	public int deleteByMemberId(String memberId) throws Exception {
		
		return 0;
	}
	
	
	// 주문 한건 삭제 
	public int daleteByOrderNo(int o_no) throws Exception {
		
		return 0;
	}
	
	
	
}
