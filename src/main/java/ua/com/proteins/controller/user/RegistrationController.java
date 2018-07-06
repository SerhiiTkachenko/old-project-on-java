package ua.com.proteins.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.proteins.dto.form.UsersForm;
import ua.com.proteins.entity.City;
import ua.com.proteins.entity.Country;
import ua.com.proteins.entity.Role;
import ua.com.proteins.entity.Street;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.CityService;
import ua.com.proteins.service.CountryService;
import ua.com.proteins.service.StreetService;
import ua.com.proteins.service.UsersService;
import ua.com.proteins.validator.UsersFormValidator;





@Controller
public class RegistrationController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StreetService streetService;
	
	@Autowired
	private CityService cityService;
	
	@ModelAttribute("registration")
	public UsersForm getForm(){
		return new UsersForm();
	}
	
	
	
	@InitBinder("registration")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UsersFormValidator(usersService));
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("usersForm", new UsersForm());
		model.addAttribute("countrys", countryService.findall());
		return "user-registration";
	}
	
	@PostMapping("/registration")
    public String save(@ModelAttribute("registration") @Valid UsersForm usersForm, BindingResult bindingResult,Model model){
	if (bindingResult.hasErrors()) {
		
		return "user-registration";
		
	}
		City city = getUserCityForms(usersForm);
		cityService.save(city);
		
		Street street = getUserStreetForms(usersForm);
		street.setCity(city);
		streetService.save(street);
		
		Country country = getUserCountryForms(usersForm);
		countryService.save(country);
		
		
		Users users = getUsersForms(usersForm);
		users.setCountry(country);
		users.setStreet(street);
		usersService.save(users);
		return "redirect:/login";
	
	
    }
	
	
	
	
	
	
	
	
	
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	private Users getUsersForms(UsersForm usersForm){
		Users users = new Users();
		users.setName(usersForm.getName());
		users.setSurname(usersForm.getSurname());
		users.setMob(usersForm.getMob());
		users.setEmail(usersForm.getEmail());
		users.setPassword(usersForm.getPassword());
		users.setRole(Role.ROLE_USER);
		return users;
	}
	
	private Country getUserCountryForms(UsersForm usersForm){
		if (countryService.findByName(usersForm.getCountry())==null){ 
			Country country = new Country(); 
			country.setName(usersForm.getCountry()); 
			return country; 
			
		}else return countryService.findByName(usersForm.getCountry()); 
		
	}
	
	
	private City getUserCityForms(UsersForm usersForm){
		if (cityService.findByNameOfCity(usersForm.getNameOfCity())==null){
			City city = new City();
			city.setNameOfCity(usersForm.getNameOfCity());
			return city;
		}else return cityService.findByNameOfCity(usersForm.getNameOfCity());
	}
	
	private Street getUserStreetForms(UsersForm usersForm){
		if (streetService.findByNameOfStreet(usersForm.getNameOfStreet())==null){
			Street street = new Street();
			street.setNameOfStreet(usersForm.getNameOfStreet());
			return street;
		}else return streetService.findByNameOfStreet(usersForm.getNameOfStreet());
	}
		
		
		

}
