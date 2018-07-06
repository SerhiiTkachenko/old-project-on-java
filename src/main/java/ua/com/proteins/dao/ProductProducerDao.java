package ua.com.proteins.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.proteins.entity.ProductProducer;

public interface ProductProducerDao extends JpaRepository<ProductProducer, Integer>, JpaSpecificationExecutor<ProductProducer> {

	ProductProducer findByName(String name);

	
}
