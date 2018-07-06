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

import ua.com.proteins.dto.filter.OrdersFilter;
import ua.com.proteins.dto.form.OrdersForm;
import ua.com.proteins.editor.ProductEditor;
import ua.com.proteins.editor.UsersEditor;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.OrdersService;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.service.UsersService;
import ua.com.proteins.util.ParamBuilder;
import ua.com.proteins.validator.OrdersValidator;

@Controller
@RequestMapping("/admin/orders")
@SessionAttributes("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UsersService usersService;
	
	
	@InitBinder("orders")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Product.class, new ProductEditor(productService));
		binder.registerCustomEditor(Users.class, new UsersEditor(usersService));
		binder.setValidator(new OrdersValidator(ordersService));
	}
	
	@ModelAttribute("orders")
	public OrdersForm getForm(){
		return new OrdersForm();
	}
	
	@ModelAttribute("filter")
	public OrdersFilter getFilter(){
		return new OrdersFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") OrdersFilter filter){
		model.addAttribute("page", ordersService.findAll(pageable, filter));
		model.addAttribute("products", productService.findAll());
		model.addAttribute("userss", usersService.findAll());
		return "admin-orders";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") OrdersFilter filter){
		ordersService.delete(id);
		return "redirect:/admin/orders"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") OrdersFilter filter){
		model.addAttribute("orders", ordersService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@PostMapping 
	public String save(@ModelAttribute("orders") @Valid OrdersForm orders,BindingResult br, Model model,SessionStatus status,
			@PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") OrdersFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		ordersService.save(orders);
		status.setComplete();
		return "redirect:/admin/orders"+getParams(pageable, filter);
		
	}
	
	private String getParams(Pageable pageable, OrdersFilter filter){
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
		if(!filter.getProductId().isEmpty()){
			for (int id : filter.getProductId()) {
				builder.append("&productId=");
				builder.append(id);
			}
		}
		if(!filter.getUsersId().isEmpty()){
			for (int id : filter.getUsersId()) {
				builder.append("&usersId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
}
