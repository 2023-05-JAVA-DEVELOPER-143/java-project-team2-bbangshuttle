package bbangshuttle.cart;

import bbangshuttle.product.Product;

/*
VO(Value Object),DTO(Data Transfer Object)
	- cart 테이블 1개 row의 데이타의 값을 가지는객체
	- cart 테이블 1개 row의 데이타값을 이동(파라메타,리턴데이타)시키기위한객체 
	- cart 테이블의 컬럼과 동일한수의 멤버변수를가지는객체

	
*/
public class Cart {
	private int cart_no;
	private int cart_qty;
	private String member_id; //FK설정 
	private Product product;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Cart(int cart_no, int cart_qty, String member_id, Product product) {
		super();
		this.cart_no = cart_no;
		this.cart_qty = cart_qty;
		this.member_id = member_id;
		this.product = product;
	}


	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getCart_qty() {
		return cart_qty;
	}

	public void setCart_qty(int cart_qty) {
		this.cart_qty = cart_qty;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", cart_qty=" + cart_qty + ", member_id=" + member_id + ", product="
				+ product + "]";
	}
	
	
}
