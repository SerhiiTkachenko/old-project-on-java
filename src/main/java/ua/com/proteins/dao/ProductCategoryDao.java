package ua.com.proteins.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.proteins.entity.ProductCategory;


public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer>,JpaSpecificationExecutor<ProductCategory> {

	ProductCategory findByCategory(String category);

}
