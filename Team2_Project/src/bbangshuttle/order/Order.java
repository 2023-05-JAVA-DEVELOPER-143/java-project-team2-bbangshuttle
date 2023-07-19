package bbangshuttle.order;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;



/**
 VO(Value Object),DTO(Data Transfer Object)
	- orders 테이블 1개 row의 데이타의 값을 가지는객체
	- orders 테이블 1개 row의 데이타값을 이동(파라메타,리턴데이타)시키기위한객체 
	- orders 테이블의 컬럼과 동일한수의 멤버변수를가지는객체

*/
public class Order {
	
	private int o_no;
	private Date o_date;
	private int o_price;
	private int o_totprice;
	private String o_desc;
	
	/*************FK****************/
	private String member_id;
	
	/***********List<OrderItem>****/
	private List<OrderItem> orderItemList;
	public Order() {
		orderItemList=new ArrayList<OrderItem>();
	}
	public Order(int o_no, Date o_date, int o_price, int o_totprice, String o_desc, String member_id,
			List<bbangshuttle.order.OrderItem> orderItemList) {
		super();
		this.o_no = o_no;
		this.o_date = o_date;
		this.o_price = o_price;
		this.o_totprice = o_totprice;
		this.o_desc = o_desc;
		this.member_id = member_id;
		this.orderItemList = orderItemList;
	}
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public int getO_price() {
		return o_price;
	}
	public void setO_price(int o_price) {
		this.o_price = o_price;
	}
	public int getO_totprice() {
		return o_totprice;
	}
	public void setO_totprice(int o_totprice) {
		this.o_totprice = o_totprice;
	}
	public String getO_desc() {
		return o_desc;
	}
	public void setO_desc(String o_desc) {
		this.o_desc = o_desc;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	@Override
	public String toString() {
		return "Order [o_no=" + o_no + ", o_date=" + o_date + ", o_price=" + o_price + ", o_totprice=" + o_totprice
				+ ", o_desc=" + o_desc + ", member_id=" + member_id + ", orderItemList=" + orderItemList + "]";
	}
	
	
	
	
}
