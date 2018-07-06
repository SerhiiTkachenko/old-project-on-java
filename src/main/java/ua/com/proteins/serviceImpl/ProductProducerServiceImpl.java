package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.ProductProducerDao;
import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.service.ProductProducerService;
@Service
public class ProductProducerServiceImpl  implements ProductProducerService{

	@Autowired
	private ProductProducerDao productProducerDao;

	
	public List<ProductProducer> findAll() {
		return productProducerDao.findAll();
	}

	
	public void delete(int id) {
		productProducerDao.delete(id);
	}

	
	public void save(String name) {
		ProductProducer productProducer = new ProductProducer();
		productProducer.setName(name);
		productProducerDao.save(productProducer);
	}


	public ProductProducer findOne(int id) {
		// TODO Auto-generated method stub
		return productProducerDao.findOne(id);
	}


	public void save(ProductProducer productProducer) {
		// TODO Auto-generated method stub
		productProducerDao.save(productProducer);
	}


	public ProductProducer findByName(String name) {
		// TODO Auto-generated method stub
		return productProducerDao.findByName(name);
	}


	@Override
	public Page<ProductProducer> findAll(Pageable pageable, SimpleFilter filter) {
		// TODO Auto-generated method stub
		return productProducerDao.findAll(findByNameLike(filter), pageable);
	}


	private Specification<ProductProducer> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
