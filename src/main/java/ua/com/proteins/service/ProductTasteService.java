package ua.com.proteins.service;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductTaste;

public interface ProductTasteService {
	List<ProductTaste> findAll();

	void delete(int id);

	void save(String taste);
	
	void save(ProductTaste productTaste);
	
	ProductTaste findOne(int id);

	ProductTaste findByTaste(String taste);
	
	Page<ProductTaste> findAll(Pageable pageable, SimpleFilter filter);
}
