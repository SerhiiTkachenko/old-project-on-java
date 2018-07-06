package ua.com.proteins.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.StreetFilter;
import ua.com.proteins.entity.Street;

public interface StreetService {
	void save(Street streets);

	List<Street> findAll();

	Street findOne(int id);

	void delete(int id);

	Street findByNameOfStreet(String nameOfStreet);
	
	Page<Street> findAll(Pageable pageable, StreetFilter filter);
}
