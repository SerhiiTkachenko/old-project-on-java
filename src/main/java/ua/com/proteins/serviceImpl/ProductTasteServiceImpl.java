package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.ProductTasteDao;
import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.ProductTasteService;
@Service
public class ProductTasteServiceImpl implements ProductTasteService {

	@Autowired
	private ProductTasteDao productTasteDao;

	public List<ProductTaste> findAll() {
		
		return productTasteDao.findAll();
	}

	public void delete(int id) {
		productTasteDao.delete(id);
		
	}
	

	public void save(String taste) {
		ProductTaste productTaste = new ProductTaste();
		productTaste.setTaste(taste);
		productTasteDao.save(productTaste);
		
	}

	public ProductTaste findOne(int id) {
		return productTasteDao.findOne(id);
	}

	public void save(ProductTaste productTaste) {
		productTasteDao.save(productTaste);
	}

	public ProductTaste findByTaste(String taste) {
		return productTasteDao.findByTaste(taste);
	}

	@Override
	public Page<ProductTaste> findAll(Pageable pageable, SimpleFilter filter) {
		return productTasteDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<ProductTaste> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("taste")), filter.getSearch().toLowerCase()+"%");
		};
	}
	

}
