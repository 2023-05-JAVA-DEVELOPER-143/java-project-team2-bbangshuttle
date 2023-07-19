package bbangshuttle.product;

import java.util.List;


public class ProductSevrice {
	private ProductDao productDao;
	
	public ProductSevrice() throws Exception {
		this.productDao = new ProductDao();
	}
	//상품 번호로 검색
	public Product ProductFindByNo(int p_no)throws Exception {
		
		return productDao.findByProductNo(p_no);
	}
	// 상품 전체 출력
	public List<Product> ProductFindByAll() throws Exception{
		
		return productDao.findAll();
	}
	//키워드로 검색
	public List<Product> ProductFindByKetword(String keyword)throws Exception{
			
		return productDao.findByKeyword(keyword);
	}
	//카운트증가
	public int productCountUpdate(int p_no) throws Exception{
			
<<<<<<< HEAD
		return productDao.updateViewCount(p_no);
	}
		
=======
			return productDao.updateViewCount(p_no);
		}
		//카테고리 전체 검색
		public List<Product> productCategoryAll (int p_category) throws Exception {
			
			return productDao.productCategoryList(p_category);
		}
>>>>>>> branch 'main' of https://github.com/2023-05-JAVA-DEVELOPER-143/java-project-team2-bbbb.git
}
