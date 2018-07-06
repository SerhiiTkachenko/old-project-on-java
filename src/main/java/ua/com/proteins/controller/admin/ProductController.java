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
import org.springframework.data.domain.Sort;

import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.dto.form.ProductForm;
import ua.com.proteins.editor.ProductCategoryEditor;
import ua.com.proteins.editor.ProductProducerEditor;
import ua.com.proteins.editor.ProductTasteEditor;
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.ProductCategoryService;
import ua.com.proteins.service.ProductProducerService;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.service.ProductTasteService;
import ua.com.proteins.util.ParamBuilder;
import ua.com.proteins.validator.ProductValidator;


@Controller
@RequestMapping("/admin/product")
@SessionAttributes("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductProducerService productProducerService;
	
	@Autowired
	private ProductTasteService productTasteService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	@InitBinder("product")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(ProductProducer.class, new ProductProducerEditor(productProducerService));
		binder.registerCustomEditor(ProductTaste.class, new ProductTasteEditor(productTasteService));
		binder.registerCustomEditor(ProductCategory.class, new ProductCategoryEditor(productCategoryService));
		binder.setValidator(new ProductValidator(productService));
	}
	@ModelAttribute("filter")
	public ProductFilter getFilter(){
		return new ProductFilter();
	}
	
	@ModelAttribute("product")
	public ProductForm getForm(){
		return new ProductForm();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") ProductFilter filter){
		model.addAttribute("page", productService.findAll(pageable, filter));
		model.addAttribute("productProducers", productProducerService.findAll());
		model.addAttribute("productTastes", productTasteService.findAll());
		model.addAttribute("productCategorys", productCategoryService.findAll());
		return "admin-product";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") ProductFilter filter){
		productService.delete(id);
		return "redirect:/admin/product"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") ProductFilter filter){
		model.addAttribute("product", productService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("product") @Valid ProductForm product, BindingResult br, Model model, SessionStatus status,@PageableDefault(direction=Sort.Direction.DESC, sort="id") 
	Pageable pageable, @ModelAttribute("filter") ProductFilter filter){
		if(br.hasErrors())  return show(model, pageable, filter);
		productService.save(product);
		status.setComplete();
		return "redirect:/admin/product"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, ProductFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			builder.append("&max=");
			builder.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			builder.append("&min=");
			builder.append(filter.getMin());
		}
		if(!filter.getProductProducerId().isEmpty()){
			for (int id : filter.getProductProducerId()) {
				builder.append("&productProducerId=");
				builder.append(id);
			}
		}
		if(!filter.getProductTasteId().isEmpty()){
			for (int id : filter.getProductTasteId()) {
				builder.append("&productTasteId=");
				builder.append(id);
			}
		}
		if(!filter.getProductCategoryId().isEmpty()){
			for (int id : filter.getProductCategoryId()) {
				builder.append("&productCategoryId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
	
	
}
