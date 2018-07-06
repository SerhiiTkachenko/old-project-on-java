package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.dto.form.OrdersForm;
import ua.com.proteins.service.OrdersService;

public class OrdersValidator implements Validator{
	
	private static final Pattern REG = Pattern.compile("^([0-9]{1,17})$");
	private final OrdersService ordersService;
	
	

	public OrdersValidator(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public boolean supports(Class<?> clazz) {
		
		return OrdersForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		OrdersForm form = (OrdersForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "", "Can't be empty");
		if(!REG.matcher(form.getAmount()).matches()){
			errors.rejectValue("amount", "", "Can be separated , or . or write only numbers");
		}
		if(errors.getFieldError("amount") == null && errors.getFieldError("product") == null 
				&& errors.getFieldError("user") == null){
			if(ordersService.findUnique(form.getAmount(), 
					form.getUsers(),form.getProduct())!=null){
				errors.rejectValue("amount", "", "Already exist");
			}
			
		}
		
	}

}
