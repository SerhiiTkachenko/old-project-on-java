package ua.com.proteins.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.CityDao;
import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.City;
import ua.com.proteins.service.CityService;
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	public void save(String nameOfCity) {
		City city = new City();
		city.setNameOfCity(nameOfCity);
		cityDao.save(city);
	}

	public List<City> findAll() {
		
		return cityDao.findAll();
	}

	public City findOne(int id) {
		
		return cityDao.findOne(id);
	}

	public void delete(int id) {
		
		cityDao.delete(id);
	}

	public void save(City city) {
		
		cityDao.save(city);
	}

	public City findByNameOfCity(String nameOfCity) {
		return cityDao.findByNameOfCity(nameOfCity);
	}

	@Override
	public Page<City> findAll(Pageable pageable, SimpleFilter filter) {
		
		return cityDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<City> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("nameOfCity")), filter.getSearch().toLowerCase()+"%");
		};
	}

	
}
