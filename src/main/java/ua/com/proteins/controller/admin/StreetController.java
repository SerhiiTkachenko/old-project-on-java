package ua.com.proteins.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.proteins.dto.filter.StreetFilter;
import ua.com.proteins.editor.CityEditor;
import ua.com.proteins.entity.City;
import ua.com.proteins.entity.Street;
import ua.com.proteins.service.CityService;
import ua.com.proteins.service.StreetService;
import ua.com.proteins.util.ParamBuilder;
import ua.com.proteins.validator.StreetValidator;

@Controller
@RequestMapping("/admin/street")
@SessionAttributes("street")
public class StreetController {

	@Autowired
	private StreetService streetService;

	@Autowired
	private CityService cityService;

	@InitBinder("street")
	protected void bind(WebDataBinder binder) {
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
		binder.setValidator(new StreetValidator(streetService));
	}

	@ModelAttribute("filter")
	public StreetFilter getFilter(){
		return new StreetFilter();
	}
	
	@ModelAttribute("street")
	public Street getForm() {
		return new Street();
	}

	@GetMapping
	public String show(Model model,@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") StreetFilter filter) {
		model.addAttribute("page", streetService.findAll(pageable, filter));
		model.addAttribute("citys", cityService.findAll());
		return "admin-street";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") StreetFilter filter) {
		streetService.delete(id);
		return "redirect:/admin/street"+getParams(pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") StreetFilter filter) {
		model.addAttribute("street", streetService.findOne(id));
		return show(model, pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute("street") @Valid Street street, BindingResult br, Model model,SessionStatus status,
			@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") StreetFilter filter) {
		if(br.hasErrors()) return show(model, pageable, filter);
		streetService.save(street);
		status.setComplete();
		return "redirect:/admin/street"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable,StreetFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		
		if(!filter.getCityId().isEmpty()){
			for (int id : filter.getCityId()) {
				builder.append("&city=");
				builder.append(id);
			}
		}
		return builder.toString();
	}

}
