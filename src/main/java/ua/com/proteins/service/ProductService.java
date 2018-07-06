package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.dto.form.ProductForm;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.entity.ProductTaste;

public interface ProductService {
	Product findUnique(String price,String weight,String productDescription,String productName,ProductProducer productProducer,ProductTaste productTaste,ProductCategory productCategory);

	void save(ProductForm form);
	
	void save(Product product);

	List<Product> findAll();

	Product findOne(int id);

	void delete(int id);
	
	ProductForm findForm(int id);
	
	Page<Product> findAll(Pageable pageable, ProductFilter filter);

	Page<Product> findByCategory(int id, Pageable pageable);


}
