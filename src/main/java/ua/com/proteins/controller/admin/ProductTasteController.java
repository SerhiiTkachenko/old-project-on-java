package ua.com.proteins.controller.admin;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import ua.com.proteins.dto.filter.SimpleFilter;
import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.ProductTasteService;
import ua.com.proteins.validator.ProductTasteValidator;
import static ua.com.proteins.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/productTaste")
@SessionAttributes("productTaste")
public class ProductTasteController {
	
	@Autowired
	private ProductTasteService product_TasteService;
	
	@ModelAttribute("productTaste")
	public ProductTaste getForm(){
		return new ProductTaste();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@InitBinder("productTaste")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new ProductTasteValidator(product_TasteService));
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", product_TasteService.findAll(pageable,filter));
		return "admin-productTaste";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		product_TasteService.delete(id);
		return "redirect:/admin/productTaste"+ getParams(pageable,filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("productTaste", product_TasteService.findOne(id));
		return show(model,pageable,filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("productTaste") @Valid ProductTaste productTaste,BindingResult br, Model model,SessionStatus status,
			 @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model,pageable,filter);
		product_TasteService.save(productTaste);
		status.setComplete();
		return "redirect:/admin/productTaste"+ getParams(pageable,filter);
	}
	
	
}
