package bbangshuttle.cart;
/*
 * sql ex) public final static String SELECT_XX="";
 */
public class CartSQL {
	
	public static final String CART_INSERT="insert into cart(cart_no,cart_qty,p_no,userid) values(cart_cart_no_SEQ.nextval,?,?,?)";
	public static final String CART_COUNT_BY_USERID_PRODUCT_NO="select count(*) as p_count from cart c where userid=? and p_no=?";
	public static final String CART_SELECT_BY_USERID="select c.*,p.* from cart c join product p on c.p_no=p.p_no where userid=?";
	public static final String CART_DELETE_BY_USERID="delete from cart where userid=?";
	public static final String CART_UPDATE_BY_CART_NO="update cart set cart_qty=? where cart_no=?";
	public static final String CART_UPDATE_BY_PRODUCT_NO_USERID="update cart set cart_qty=cart_qty+? where userid=? and p_no=?";
	public static final String CART_SELECT_BY_CART_NO="select * from cart c join product p on c.p_no=p.p_no where cart_no=?";

}
