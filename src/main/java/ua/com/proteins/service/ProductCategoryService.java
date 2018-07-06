package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductCategory;

public interface ProductCategoryService {

	void save(ProductCategory productCategory);

	void save(String category);

	List<ProductCategory> findAll();

	ProductCategory findOne(int id);

	void delete(int id);

	void update(ProductCategory category);

	ProductCategory findByByCategory(String category);

	Page<ProductCategory> findAll(Pageable pageable, SimpleFilter filter);
}
