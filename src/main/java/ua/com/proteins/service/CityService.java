package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.City;



public interface CityService {
	void save(String nameOfCity);
	
	void save(City city);

	List<City> findAll();

	City findOne(int id);

	void delete(int id);

	City findByNameOfCity(String nameOfCity);
	
	Page<City> findAll(Pageable pageable, SimpleFilter filter);
	
}
