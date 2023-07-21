package bbangshuttle.order;

import java.util.List;

import bbangshuttle.cart.CartService;
import bbangshuttle.product.ProductSevrice;

public class OrderServiceTestMain {

	public static void main(String[] args) throws Exception{
		
		OrderService orderService = new OrderService();
		CartService cartService = new CartService();
		ProductSevrice productSevrice = new ProductSevrice();
		
		
		System.out.println("-------- 주문전체삭제 --------");
		System.out.println(orderService.deleteAll("parkshuttle33"));
		
		
		System.out.println("--------주문 목록--------");
		System.out.println(orderService.orderList("choishuttle44"));
		
		
		System.out.println("--------주문 + 주문 아이템 목록-------");
		System.out.println(orderService.orderWithOrderItemList("leeshuttle22"));
		
		
		System.out.println("--------주문 + 주문 아이템 상세보기-------");
		System.out.println(orderService.orderWithOrderItem(3));
		
		
		
	}

}
