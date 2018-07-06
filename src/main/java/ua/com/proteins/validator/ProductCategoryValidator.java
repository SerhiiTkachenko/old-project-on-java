package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.service.ProductCategoryService;


public class ProductCategoryValidator implements Validator{
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");

	private final ProductCategoryService productCategoryService;

	public ProductCategoryValidator(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductCategory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductCategory productCategory = (ProductCategory) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can't be empty");
		if(productCategoryService.findByByCategory(productCategory.getCategory())!=null){
			errors.rejectValue("category", "", "Already exist");
		}
		
		if (!REGX.matcher(productCategory.getCategory()).matches()) { 
			errors.rejectValue("category", "", "Ведіть тільки букви"); 
			}
	}
	
}
