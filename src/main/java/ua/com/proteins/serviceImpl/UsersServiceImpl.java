package ua.com.proteins.serviceImpl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.UsersDao;
import ua.com.proteins.entity.Role;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.UsersService;
@Service("userDetailsService")
public class UsersServiceImpl implements UsersService, UserDetailsService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void save(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		users.setRole(Role.ROLE_USER);
		usersDao.save(users);
		
	}

	public List<Users> findAll() {
		return usersDao.findAll();
	}

	public Users findOne(int id) {
		return usersDao.findOne(id);
	}

	public void delete(int id) {
		usersDao.delete(id);
	}

	public void update(Users users) {
		usersDao.save(users);
		
	}

	public Users findUsersByName(String name) {
		return  usersDao.findByName(name);
	}

	public Users findByEmail(String email) {
		return usersDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return usersDao.findByEmail(username);
	}
	
	@PostConstruct
	public void addAdmin(){
		Users users = usersDao.findByEmail("admin");
		if(users==null){
			users = new Users();
			users.setPassword(encoder.encode("admin"));
			users.setEmail("admin");
			users.setRole(Role.ROLE_ADMIN);
			usersDao.save(users);
		}
	}

	

}
