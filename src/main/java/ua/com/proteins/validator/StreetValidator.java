package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.entity.Street;
import ua.com.proteins.service.StreetService;

public class StreetValidator implements Validator{
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");

	private StreetService streetService;
	
	
	public StreetValidator(StreetService streetService) {
		this.streetService = streetService;
	}

	public boolean supports(Class<?> clazz) {
		
		return Street.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Street street = (Street)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfStreet", "", "Can't be empty");
		if(streetService.findByNameOfStreet(street.getNameOfStreet())!=null){
			errors.rejectValue("nameOfStreet","", "Already exist");
		}
		
		if (!REGX.matcher(street.getNameOfStreet()).matches()) { 
			errors.rejectValue("nameOfStreet", "", "Ведіть тільки букви"); 
			}
	}

}
