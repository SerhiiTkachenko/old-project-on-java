package ua.com.proteins.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.proteins.entity.Street;

public interface StreetDao extends JpaRepository<Street, Integer>,
		JpaSpecificationExecutor<Street> {

	@Query("SELECT a FROM Street a LEFT JOIN FETCH a.city")
	List<Street> findAll();

	@Query("SELECT a FROM Street a LEFT JOIN FETCH a.city WHERE a.id=?1")
	Street findOne(int id);

	Street findByNameOfStreet(String nameOfStreet);

	@Query(value = "SELECT a FROM Street a LEFT JOIN FETCH a.city", countQuery = "SELECT count(a.id) FROM Street a")
	Page<Street> findAll(Pageable pageable);
}
