package ua.com.proteins.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.proteins.dto.filter.StreetFilter;
import ua.com.proteins.entity.Street;

public class StreetSpecification implements Specification<Street>{
	
	private final StreetFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private void filterByCity(Root<Street> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCityId().isEmpty()){
			predicates.add(root.get("city").in(filter.getCityId()));
		}
	}

	public StreetSpecification(StreetFilter filter) {
		this.filter = filter;
	}
	
	private void fetch(Root<Street> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("city");
		}
	}
	
	private void filterByStreetLike(Root<Street> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("nameOfStreet")), filter.getSearch().toLowerCase()+"%"));
			}
		}


	@Override
	public Predicate toPredicate(Root<Street> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByCity(root, query, cb);
		filterByStreetLike(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
