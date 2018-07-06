package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductProducer;

public interface ProductProducerService {
	List<ProductProducer> findAll();

	void delete(int id);

	void save(String name);
	void save(ProductProducer productProducer);
	
	ProductProducer findOne(int id);

	ProductProducer findByName(String name);
	
	Page<ProductProducer> findAll(Pageable pageable, SimpleFilter filter);
	
}
