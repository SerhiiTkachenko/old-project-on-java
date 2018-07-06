package ua.com.proteins.dao;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.proteins.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product> {
	
	
	@Query("SELECT pp FROM Product pp WHERE pp.productCategory.id = ?1")
	Page<Product> findByPidvudCategoryId(int id,Pageable pageable);
	
	@Query("SELECT a FROM Product a WHERE a.price = ?1 and a.weight = ?2 and a.productDescription = ?3 and a.productName = ?4 and a.productProducer.id = ?5 and a.productTaste.id = ?6 and a.productCategory.id = ?7")
	Product findUnique(BigDecimal price,BigDecimal weight,String productDescription,String productName,int productProducerId,int productTasteId,int productCategoryId);

	@Query("SELECT a FROM Product a LEFT JOIN FETCH a.productProducer LEFT JOIN FETCH a.productTaste LEFT JOIN FETCH a.productCategory")
	List<Product> findAll();
	
	@Query("SELECT a FROM Product a LEFT JOIN FETCH a.productProducer LEFT JOIN FETCH a.productTaste LEFT JOIN FETCH a.productCategory WHERE a.id=?1")
	Product findOne(int id);


	@Query(value="SELECT a FROM Product a LEFT JOIN FETCH a.productProducer LEFT JOIN FETCH a.productTaste LEFT JOIN FETCH a.productCategory",
			countQuery="SELECT count(a.id) FROM Product a")
	
	Page<Product> findAll(Pageable pageable);
	
//	@Query("SELECT i FROM Product i WHERE i.product_Producer.id = ?1")
//	List<Product> findByProductProducerId(int id);
//	
//	@Query("SELECT i FROM Product i WHERE i.product_Taste.id = ?1")
//	List<Product> findByProductTasteId(int id);
}
