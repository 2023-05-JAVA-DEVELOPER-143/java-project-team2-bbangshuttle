package bbangshuttle.cart;

public class CartServiceTest {

	public static void main(String[] args) throws Exception {

		CartService cs = new CartService();
		//cart add
		System.out.println("1. add cart");
		int rowCount = cs.addCart("leeshuttle22", 1, 2);
		System.out.println(">>>"+rowCount +" 개 추가 성공");
		//특정상품 특정회원의 수량을 몇개로 변경
		System.out.println("2. update");
		
		
	}

}
