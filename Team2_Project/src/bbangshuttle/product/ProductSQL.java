package bbangshuttle.product;

public class ProductSQL {
	public final static String PRODUCT_SELECT_ALL="select * from product";
	public final static String PRODUCT_SELECT_BY_NO="select * from product where p_no=?";
	public final static String PRODUCT_SELECT_BY_KEYWORD="select * from product where p_name like '%'||?||'%' or p_desc like '%'||?||'%'";
	public final static String PRODUCT_UPDATE_COUNT="update product set p_view_count=p_view_count+1 where p_no=?";
	
}
