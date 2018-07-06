package ua.com.proteins.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Query;

import ua.com.proteins.entity.Users;

public interface UsersDao extends JpaRepository<Users,Integer>{
	
	@Query("SELECT a FROM Users a LEFT JOIN FETCH a.street")
	List<Users> findAll();
	
	Users findByName(String name);
	
	Users findBySurname(String surname);
	
	Users findByEmail(String email);
	
	//Servlet
}
