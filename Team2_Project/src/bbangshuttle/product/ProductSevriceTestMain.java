package bbangshuttle.product;

public class ProductSevriceTestMain {
	
	public static void main(String[] args)throws Exception{
		
		ProductSevrice productSevrice = new ProductSevrice();
		
		System.out.println(productSevrice.ProductFindByNo(10));
		System.out.println("------------------------------");
		
		System.out.println(productSevrice.ProductFindByAll());
		
		System.out.println("------------------------------");
		
		System.out.println(productSevrice.ProductFindByKetword("촉촉한쵹호칩"));
		
		System.out.println("------------------------------");
		System.out.println(productSevrice.productCountUpdate(10));

		System.out.println("------------------------------");

	
	}
}
