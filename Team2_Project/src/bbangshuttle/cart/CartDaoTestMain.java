package bbangshuttle.cart;

public class CartDaoTestMain  {
	
	public static void main(String[] args)throws Exception {
		CartDao cartDao = new CartDao();
		System.out.println("회원님 장바구니");
		System.out.println(cartDao.cartSelectNo(1, "kimshuttle11"));
	}
}
