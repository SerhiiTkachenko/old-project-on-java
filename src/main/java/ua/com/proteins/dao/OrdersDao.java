package ua.com.proteins.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.proteins.entity.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>,JpaSpecificationExecutor<Orders> {
	
	@Query("SELECT a FROM Orders a WHERE a.amount = ?1 and a.product.id = ?2 and a.users.id = ?3")
	Orders findUnique(int amount,int productId, int usersId);

	@Query("SELECT a FROM Orders a LEFT JOIN FETCH a.users LEFT JOIN FETCH a.product")
	List<Orders> findAll();

	@Query("SELECT a FROM Orders a LEFT JOIN FETCH a.users LEFT JOIN FETCH a.product WHERE a.id=?1")
	Orders findOne(int id);
	
	@Query("SELECT a FROM Orders a LEFT JOIN FETCH a.users LEFT JOIN FETCH a.product WHERE a.id=?1 and a.flag=?2")
	Orders findOne(int id,boolean flag);

	@Query(value = "SELECT a FROM Orders a LEFT JOIN FETCH a.product LEFT JOIN FETCH a.users", 
			countQuery = "SELECT count(a.id) FROM Orders a")
	Page<Orders> findAll(Pageable pageable);

	@Query("select g from Orders g WHERE g.users.id=?1")
	List<Orders> findByUserId(int usersId);
	
	@Query("select z from Orders z where z.users.id=?1 and z.flag=?2")
	List<Orders> findFlag(int userId, boolean flag);
}
