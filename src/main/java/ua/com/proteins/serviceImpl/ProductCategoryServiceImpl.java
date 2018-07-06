package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.ProductCategoryDao;
import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	public void save(ProductCategory productCategory) {
		productCategoryDao.save(productCategory);
	}

	public List<ProductCategory> findAll() {
		return productCategoryDao.findAll();
	}

	public ProductCategory findOne(int id) {
		return productCategoryDao.findOne(id);
	}

	public void delete(int id) {
	productCategoryDao.delete(id);
	}

	public void update(ProductCategory productCategory) {
	productCategoryDao.save(productCategory);
	}


	public void save(String category) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategory(category);
		productCategoryDao.save(productCategory);
	}

	@Override
	public ProductCategory findByByCategory(String category) {
		return productCategoryDao.findByCategory(category);
	}
	
	@Override
	public Page<ProductCategory> findAll(Pageable pageable, SimpleFilter filter) {
		return productCategoryDao.findAll(findByNameLike(filter), pageable);
	}

	private Specification<ProductCategory> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("category")), filter.getSearch().toLowerCase()+"%");
		};
	}
	

}
