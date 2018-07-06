package ua.com.proteins.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.proteins.entity.ProductTaste;

public interface ProductTasteDao extends JpaRepository<ProductTaste, Integer>, JpaSpecificationExecutor<ProductTaste> {

	ProductTaste findByTaste(String taste);
	
	
}
