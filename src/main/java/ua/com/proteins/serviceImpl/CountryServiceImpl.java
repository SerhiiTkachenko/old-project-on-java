package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.dao.CountryDao;
import ua.com.proteins.entity.Country;
import ua.com.proteins.service.CountryService;
@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryDao countryDao;
	


	@Override
	public void save(Country country) {
		countryDao.save(country);
		
	}

	@Override
	public Country findOne(int id) {
		// TODO Auto-generated method stub
		return countryDao.findOne(id);
	}

	@Override
	public void update(Country country) {
		countryDao.save(country);
	}
	
	public void delete(int id) {
		countryDao.delete(id);
	}

	@Override
	public Country findByName(String name) {
		
		return countryDao.findByName(name);
	}


	@Override
	public List<Country> findall() {
		// TODO Auto-generated method stub
		return countryDao.findAll();
	}

	@Override
	public void save(String name) {
		Country country = new Country();
		country.setName(name);
		countryDao.save(country);
		
	}
	
	@Override
	public Page<Country> findAll(Pageable pageable, SimpleFilter filter) {
		return countryDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<Country> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
