package ua.com.proteins.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.proteins.dao.OrdersDao;
import ua.com.proteins.dao.UsersDao;
import ua.com.proteins.dto.filter.OrdersFilter;
import ua.com.proteins.dto.form.OrdersForm;
import ua.com.proteins.entity.Orders;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.OrdersService;
import ua.com.proteins.specification.OrdersSpecification;
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private UsersDao usersDao;
	

	public List<Orders> findAll() {
		
		return ordersDao.findAll();
	}

	public Orders findOne(int id) {
	
		return ordersDao.findOne(id);
	}

	public void delete(int id) {
	
		ordersDao.delete(id);
	}


	public Orders findUnique(String amount,Users users, Product product) {
	
		return ordersDao.findUnique(new Integer(amount),users.getId(),product.getId());
	}

	public void save(OrdersForm form) {
		Orders orders = new Orders();
		orders.setId(form.getId());
		orders.setAmount(new Integer(form.getAmount()));
		orders.setUsers(form.getUsers());
		orders.setProduct(form.getProduct());
		ordersDao.save(orders);
		
	}

	public OrdersForm findForm(int id) {
		Orders orders = findOne(id);
		OrdersForm form = new OrdersForm();
		form.setAmount(String.valueOf(orders.getAmount()));
		form.setId(orders.getId());
		form.setUsers(orders.getUsers());
		form.setProduct(orders.getProduct());
		return form;
	}

	@Override
	public Page<Orders> findAll(Pageable pageable, OrdersFilter filter) {
		return ordersDao.findAll(new OrdersSpecification(filter),pageable);
	}

	@Override
	public List<Orders> findByUserId(int usersId) {
		return ordersDao.findByUserId(usersId);
	}

	@Override
	public Orders findOne(int id, boolean flag) {
		return ordersDao.findOne(id, flag);
	}

	@Override
	public List<Orders> findFlag(int usersId, boolean flag) {
		return ordersDao.findFlag(usersId, flag);
	}

	@Override
	public void save(Orders orders) {
		ordersDao.save(orders);
	}

	@Override
	public BigDecimal sum(int usersId,boolean flag) {
		
		
		List<Orders> pr = ordersDao.findFlag(usersId, flag);
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < pr.size(); i++) {
			Orders order = pr.get(i);
			sum = sum.add(order.getProduct().getPrice().multiply(new BigDecimal(order.getAmount())));
		}
		
		
		return sum;
	}

}
