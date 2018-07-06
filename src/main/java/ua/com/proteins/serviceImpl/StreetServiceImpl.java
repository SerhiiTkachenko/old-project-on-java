package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.StreetDao;
import ua.com.proteins.dto.filter.StreetFilter;
import ua.com.proteins.entity.Street;
import ua.com.proteins.service.StreetService;
import ua.com.proteins.specification.StreetSpecification;
@Service
public class StreetServiceImpl implements StreetService {

	@Autowired
	private StreetDao streetDao;

	public void save(Street streets) {
		streetDao.save(streets);
		
	}

	public List<Street> findAll() {
		
		return streetDao.findAll();
	}

	public Street findOne(int id) {
		
		return streetDao.findOne(id);
	}

	public void delete(int id) {
		streetDao.delete(id);
		
	}

	public Street findByNameOfStreet(String nameOfStreet) {
		return streetDao.findByNameOfStreet(nameOfStreet);
	}

	@Override
	public Page<Street> findAll(Pageable pageable, StreetFilter filter) {
		return streetDao.findAll(new StreetSpecification(filter), pageable);
	}
	
	
	

}
