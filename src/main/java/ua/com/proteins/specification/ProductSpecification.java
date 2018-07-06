package ua.com.proteins.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.entity.Product;

public class ProductSpecification implements Specification<Product>{
	
	private final ProductFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private final static Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	public ProductSpecification(ProductFilter filter) {
		this.filter = filter;
		if(REG.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
		}
		if(REG.matcher(filter.getMin()).matches()){
			filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
		}
		if(REG.matcher(filter.getMaxWeight()).matches()){
			filter.setMaxWeightValue(new BigDecimal(filter.getMaxWeight().replace(',', '.')));
		}
		if(REG.matcher(filter.getMinWeight()).matches()){
			filter.setMinWeightValue(new BigDecimal(filter.getMinWeight().replace(',', '.')));
		}
	}

	
	private void filterByProductProducer(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getProductProducerId().isEmpty()){
			predicates.add(root.get("productProducer").in(filter.getProductProducerId()));
		}
	}
	
	private void filterByProductCategory(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getProductCategoryId().isEmpty()){
			predicates.add(root.get("productCategory").in(filter.getProductCategoryId()));
		}
	}
	
	private void filterByProductTaste(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getProductTasteId().isEmpty()){
			predicates.add(root.get("productTaste").in(filter.getProductTasteId()));
		}
	}
	
	private void filterByProductName(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("productName")), filter.getSearch().toLowerCase()+"%"));
		}
	}
	
	private void filterByProductDescription(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearchDescription().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("productDescription")), filter.getSearchDescription().toLowerCase()+"%"));
		}
	}
	
	private void filterByProductWeightAndPrice(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
		if(filter.getMaxWeightValue()!=null){
			predicates.add(cb.le(root.get("weight"), filter.getMaxWeightValue()));
		}
		if(filter.getMinWeightValue()!=null){
			predicates.add(cb.ge(root.get("weight"), filter.getMinWeightValue()));
		}
		
	}
	
	private void fetch(Root<Product> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("productProducer");
			root.fetch("productTaste");
			root.fetch("productCategory");
		}
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByProductProducer(root, query, cb);
		filterByProductTaste(root, query, cb);
		filterByProductWeightAndPrice(root, query, cb);
		filterByProductName(root, query, cb);
		filterByProductDescription(root, query, cb);
		filterByProductCategory(root, query, cb);
		if(predicates.isEmpty()) return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
