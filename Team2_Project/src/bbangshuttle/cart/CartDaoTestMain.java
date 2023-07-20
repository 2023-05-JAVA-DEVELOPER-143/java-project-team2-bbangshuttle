package bbangshuttle.cart;

public class CartDaoTestMain  {
	
	public static void main(String[] args)throws Exception {
		CartDao cartDao = new CartDao();
		System.out.println("회원님 장바구니");
		System.out.println(cartDao.cartSelectNo(1, "leeshuttle22"));
		System.out.println("회원님 장바구니 품목");
		System.out.println(cartDao.cartSelectByMemeberNo("leeshuttle22"));
		System.out.println("회원님 장바구니 선택 업데이트");
		System.out.println(cartDao.cartMemberUpdateNo(11, "parkshuttle33", 3));
		System.out.println("회원님 장바구니 품목 전체 업데이트");
		
	}
}
