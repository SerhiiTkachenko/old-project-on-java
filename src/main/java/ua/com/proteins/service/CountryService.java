package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.Country;


public interface CountryService {
	void save(Country country);
	void save(String name);
	List<Country> findall();
	Country findOne(int id);
	void delete(int id);
	void update(Country country);
	Country findByName(String name);
	
	Page<Country> findAll(Pageable pageable, SimpleFilter filter);
}
