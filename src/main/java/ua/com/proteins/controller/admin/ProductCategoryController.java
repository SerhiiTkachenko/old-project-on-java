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
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.service.ProductCategoryService;
import ua.com.proteins.validator.ProductCategoryValidator;
import static ua.com.proteins.util.ParamBuilder.*;
@Controller
@RequestMapping("/admin/productCategory")
@SessionAttributes("productCategory")
public class ProductCategoryController {

	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@ModelAttribute("filter")
	 	public SimpleFilter getFilter(){
	 		return new SimpleFilter();
	 	}
	
	@ModelAttribute("productCategory")
	public ProductCategory getForm(){
		return new ProductCategory();
	}
	
	@InitBinder("productCategory")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new ProductCategoryValidator(productCategoryService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", productCategoryService.findAll(pageable, filter));
		return "admin-productCategory";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("productCategory",productCategoryService.findOne(id));
		return show(model,pageable, filter);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		productCategoryService.delete(id);
		return "redirect:/admin/productCategory"+getParams(pageable, filter);
	}
	

	@PostMapping
	public String save(@ModelAttribute("productCategory")  @Valid ProductCategory productCategory, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		productCategoryService.save(productCategory);
		status.setComplete();
		return "redirect:/admin/productCategory"+getParams(pageable, filter);
	}
	
}

