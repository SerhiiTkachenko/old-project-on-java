package ua.com.proteins.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ua.com.proteins.dto.form.UsersForm;
import ua.com.proteins.service.UsersService;

public class UsersFormValidator  implements Validator{

	private static final Pattern PHONE_REGEX =  Pattern.compile("^[0-9]{1,12}$");
	
	private static final Pattern REGX = Pattern.compile("^[\\p{L}]{3,20}$");
	
	private final UsersService usersService;
	
	

	public UsersFormValidator(UsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UsersForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsersForm form = (UsersForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfCity", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfStreet", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can't be empty");
		
		if (!PHONE_REGEX.matcher(form.getMob()).matches()) {
			errors.rejectValue("mob", "", "Can be only digits");
		}
		
		if (!REGX.matcher(form.getCountry()).matches()) { 
			errors.rejectValue("country", "", "Ведіть тільки букви"); 
			}
		if (!REGX.matcher(form.getCountry()).matches()) { 
			errors.rejectValue("name", "", "Ведіть тільки букви"); 
			}
		if (!REGX.matcher(form.getCountry()).matches()) { 
			errors.rejectValue("surname", "", "Ведіть тільки букви"); 
			}
		if (!REGX.matcher(form.getCountry()).matches()) { 
			errors.rejectValue("nameOfCity", "", "Ведіть тільки букви"); 
			}
		if (!REGX.matcher(form.getCountry()).matches()) { 
			errors.rejectValue("nameOfStreet", "", "Ведіть тільки букви"); 
			}
		
		if(usersService.findByEmail(form.getEmail())!=null){
			errors.rejectValue("email", "", "Already exist");
		}
		
	}

}
