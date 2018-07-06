package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.proteins.entity.Country;
import ua.com.proteins.service.CountryService;

public class CountryValidator implements Validator{
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");
	
	private final CountryService countryService;

	

	public CountryValidator(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.equals(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		Country country = (Country) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(countryService.findByName(country.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
		
		if (!REGX.matcher(country.getName()).matches()) { 
			errors.rejectValue("name", "", "Ведіть тільки букви"); 
			}
	}
	
}
