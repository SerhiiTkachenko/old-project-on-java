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
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.service.ProductProducerService;
import ua.com.proteins.validator.ProductProducerValidator;
import static ua.com.proteins.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/productProducer")
@SessionAttributes("productProducer")
public class ProductProducerController {
	
	@Autowired
	private ProductProducerService product_ProducerService;
	
	@ModelAttribute("productProducer")
	public ProductProducer getForm(){
		return new ProductProducer();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@InitBinder("productProducer")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new ProductProducerValidator(product_ProducerService));
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", product_ProducerService.findAll(pageable,filter));
		return "admin-productProducer";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("productProducer", product_ProducerService.findOne(id));
		return show(model,pageable,filter);
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		product_ProducerService.delete(id);
		return "redirect:/admin/productProducer" + getParams(pageable,filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("productProducer") @Valid ProductProducer productProducer,BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model,pageable,filter);
		product_ProducerService.save(productProducer);
		status.setComplete();
		return "redirect:/admin/productProducer" + getParams(pageable,filter);
	}
	
}
