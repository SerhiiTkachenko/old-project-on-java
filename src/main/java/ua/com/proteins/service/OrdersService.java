package ua.com.proteins.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.proteins.dto.filter.OrdersFilter;
import ua.com.proteins.dto.form.OrdersForm;
import ua.com.proteins.entity.Orders;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.Users;

public interface OrdersService {
	void save(OrdersForm form);
	void save(Orders orders);
	
	
	OrdersForm findForm(int id);
	List<Orders> findAll();
	List<Orders> findByUserId(int usersId);
	
	Orders findOne(int id,boolean flag);
	Orders findOne(int id);
	
	void delete (int id);
	Orders findUnique(String amount,Users users, Product product);
	
	Page<Orders> findAll(Pageable pageable, OrdersFilter filter);
	
	List<Orders> findFlag(int usersId,boolean flag);
	
	BigDecimal sum(int usersId,boolean flag);
	
}
