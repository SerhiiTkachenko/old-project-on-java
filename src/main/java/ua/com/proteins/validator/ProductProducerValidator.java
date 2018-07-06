package ua.com.proteins.validator;


import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.service.ProductProducerService;

public class ProductProducerValidator implements Validator {
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");
	
	private final ProductProducerService product_ProducerService;


	public ProductProducerValidator(
			ProductProducerService product_ProducerService) {
		this.product_ProducerService = product_ProducerService;
	}

	public boolean supports(Class<?> clazz) {
		
		return ProductProducer.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ProductProducer productProducer = (ProductProducer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(product_ProducerService.findByName(productProducer.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
		
		if (!REGX.matcher(productProducer.getName()).matches()) { 
			errors.rejectValue("name", "", "Ведіть тільки букви"); 
			}
		
	}
	
	
}
