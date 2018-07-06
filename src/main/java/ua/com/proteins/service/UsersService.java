package ua.com.proteins.service;

import java.util.List;

import ua.com.proteins.entity.Users;

public interface UsersService {
	
	void save (Users users);
	List<Users> findAll();
	Users findOne(int id);
	void delete (int id);
	void update (Users users);
	Users findUsersByName(String name);
	Users findByEmail(String email);
}
