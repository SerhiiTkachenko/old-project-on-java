package ua.com.proteins.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.proteins.dto.filter.OrdersFilter;
import ua.com.proteins.entity.Orders;

public class OrdersSpecification implements Specification<Orders> {

	private final OrdersFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	public OrdersSpecification(OrdersFilter filter) {
		this.filter = filter;
		
		if(REG.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new Integer(filter.getMax().replace(',', '.')));
		}
		if(REG.matcher(filter.getMin()).matches()){
			filter.setMinValue(new Integer(filter.getMin().replace(',', '.')));
		}
	}
	
	private static final Pattern REG = Pattern.compile("^([0-9]{1,17})$");

	
	private void filterByProduct(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getProductId().isEmpty()){
			predicates.add(root.get("product").in(filter.getProductId()));
		}
	}
	
	private void filterByUser(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getUsersId().isEmpty()){
			predicates.add(root.get("users").in(filter.getUsersId()));
		}
	}
	
	
	
	private void filterByAmount(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("amount"), filter.getMinValue()));
		}
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("amount"), filter.getMaxValue()));
		}
		
	}
	
	private void fetch(Root<Orders> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("product");
			root.fetch("users");
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByAmount(root, query, cb);
		filterByProduct(root, query, cb);
		filterByUser(root, query, cb);
		if(predicates.isEmpty()) return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
