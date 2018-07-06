package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.entity.City;
import ua.com.proteins.service.CityService;

public class CityValidator implements Validator {
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");
	
	private final CityService cityService;
	

	public CityValidator(CityService cityService) {
		this.cityService = cityService;
	}

	public boolean supports(Class<?> clazz) {
		
		return City.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		City city = (City)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfCity", "", "Can't be empty");
		if(cityService.findByNameOfCity(city.getNameOfCity())!=null){
			errors.rejectValue("nameOfCity","", "Already exist");
		}
		
		if (!REGX.matcher(city.getNameOfCity()).matches()) { 
			errors.rejectValue("nameOfCity", "", "Ведіть тільки букви"); 
			}
		
	}

}
