package bbangshuttle.cart;
/*
 * sql ex) public final static String SELECT_XX="";
 */
public class CartSQL {
	// 회원 장바구니 입력
	public static final String CART_INSERT = "insert into cart(cart_no, member_Id, p_no, cart_qty) values(cart_cart_no_seq.nextval, ?, ? , ?)";
	//" "회원 장바구니의 " "상품 읽기
	public static final String CART_MEMVERID_SELECT_BY_NO = "select * from cart c join product p on c.p_no=p.p_no where c.member_id =? and p.p_no = ?";
	//" "회원 장바구니 상품 전체 읽기
	public static final String CART_MEMBERID_SELECT_BY_ALL = "select * from cart c join product p on c.p_no=p.p_no where c.member_id =?";
	//" "회원 장바구니 " "품목 하나 업데이트
	public static final String CART_MEMBERID_UPDATE = "update cart set cart_qty = cart_qty+10 where member_id = ?  and p_no = ? ";
	//" "회원 장바구니 품목 전체 변경
	public static final String CART_MEMBERID_UPDATE_ALL = "update cart set cart_qty = cart_qty+? where member_id =?";
	//" "회원 장바구니 ""품목 선택 삭제
	public static final String CART_MEMBERID_DELETE_BY_P_NO="delete from cart where member_id=? and p_no=?";
	//" " 회원 장바구니 전체 삭제
	public static final String CART_MEMBERID_DELETE_ALL="delete from cart where member_id=?";
	
	
}
