package bbangshuttle.order;

public class OrderSQL {
	public final static String ORDER_DELETE_BY_MEMBER_ID = "";
	public final static String ORDER_DELETE_BY_O_NO = "";
	public final static String ORDER_INSERT = "";
	public final static String ORDERITEM_INSERT = "insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, ?, orders_o_no_SEQ.currval, ? )";
	public final static String ORDER_SELECT_BY_MEMBER_ID = "insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate, ?, ?, ?)"; 
	public final static String ORDER_SELECT_WITH_PRODUCT_BY_MEMBER_ID ="";
	
}
