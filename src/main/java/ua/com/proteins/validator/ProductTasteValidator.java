package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.ProductTasteService;

public class ProductTasteValidator implements Validator {
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");

	private final ProductTasteService product_TasteService;

	public ProductTasteValidator(ProductTasteService product_TasteService) {
		this.product_TasteService = product_TasteService;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProductTaste.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ProductTaste productTaste = (ProductTaste) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taste", "",
				"Can't be empty");
		if (product_TasteService.findByTaste(productTaste.getTaste())!= null) {
			errors.rejectValue("taste", "", "Already exist");

		}
		
		if (!REGX.matcher(productTaste.getTaste()).matches()) { 
			errors.rejectValue("taste", "", "Ведіть тільки букви"); 
			}

	}
}
