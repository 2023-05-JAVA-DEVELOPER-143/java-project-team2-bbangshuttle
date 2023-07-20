package bbangshuttle.test;

import bbangshuttle.order.OrderDao;

public class OrderDaoTestMain {

	public static void main(String[] args) throws Exception{
		
		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.findOrderByMemberId("leeshuttle22"));
		
		// System.out.println(orderDao.deleteByMemberId("kimshuttle11"));
		
		
		
		//System.out.println(orderDao.findOrderWithOrderItemMemberId("kimshuttle11"));
		
		
		
		/*
		 * 1.상품에서직접주문
		 */
		/*
		 * 2.cart에서 주문
		 */
	}

}
