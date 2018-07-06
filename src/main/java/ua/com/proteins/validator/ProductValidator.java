package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.dto.form.ProductForm;
import ua.com.proteins.service.ProductService;

public class ProductValidator implements Validator{
	
	private static final  Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");
	
	private final ProductService productService;
	

	public ProductValidator(ProductService productService) {
		this.productService = productService;
	}

	public boolean supports(Class<?> clazz) {
		
		return ProductForm.class.equals(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		ProductForm form = (ProductForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productDescription", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "", "Can't be empty");
		if(!REG.matcher(form.getPrice()).matches()){
			errors.rejectValue("price", "", "Can be separated , or . or write only numbers");
		}
		
		
		if(!REG.matcher(form.getWeight()).matches()){
			errors.rejectValue("weight", "", "Can be separated , or . or write only numbers");
		}
		
		if (!REGX.matcher(form.getProductDescription()).matches()) { 
			errors.rejectValue("productDescription", "", "Ведіть тільки букви"); 
			}
		if (!REGX.matcher(form.getProductName()).matches()) { 
			errors.rejectValue("productName", "", "Ведіть тільки букви"); 
			}
		
		if(errors.getFieldError("price")==null && errors.getFieldError("weight")==null && errors.getFieldError("productDescription")==null && 
				errors.getFieldError("productName")==null){
			if(productService.findUnique(form.getPrice(),form.getWeight(),form.getProductDescription(),form.getProductName(), 
					form.getProductProducer(), form.getProductTaste(),form.getproductCategory())!=null){
				errors.rejectValue("productDescription", "", "Already exist");
				errors.rejectValue("productName", "", "Already exist");
			}
		}
		
	}

}
