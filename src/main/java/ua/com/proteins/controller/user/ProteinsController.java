package ua.com.proteins.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.dto.form.ProductForm;
import ua.com.proteins.editor.ProductCategoryEditor;
import ua.com.proteins.editor.ProductProducerEditor;
import ua.com.proteins.editor.ProductTasteEditor;
import ua.com.proteins.entity.Orders;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.OrdersService;
import ua.com.proteins.service.ProductCategoryService;
import ua.com.proteins.service.ProductProducerService;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.service.ProductTasteService;
import ua.com.proteins.service.UsersService;
import ua.com.proteins.util.ParamBuilder;

@Controller
@RequestMapping("/proteins")
@SessionAttributes("proteins")
public class ProteinsController {
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private ProductProducerService productProducerService;
	
	@Autowired
	private ProductTasteService productTasteService;
	
	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OrdersService ordersService;
	
	@ModelAttribute("proteins")
	public ProductForm getForm() {
		return new ProductForm();
	}
	
	@ModelAttribute("filter")
	public ProductFilter getFilter(){
		return new ProductFilter();
	}
	
	@InitBinder("proteins")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(ProductCategory.class, new ProductCategoryEditor(productCategoryService));
		binder.registerCustomEditor(ProductTaste.class, new ProductTasteEditor(productTasteService));
		binder.registerCustomEditor(ProductProducer.class, new ProductProducerEditor(productProducerService));
		
	}

	@GetMapping
	public String show(
			Model model,
			@PageableDefault(direction = Sort.Direction.DESC, sort = "id", size = 12) Pageable pageable,
			@ModelAttribute("filter") ProductFilter filter) {

		model.addAttribute("page", productService.findAll(pageable, filter));
		model.addAttribute("productCategorys", productCategoryService.findAll());
		model.addAttribute("productProducers", productProducerService.findAll());
		model.addAttribute("productTastes", productTasteService.findAll());
		return "user-proteins";

	}
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, Model model ,@PageableDefault(direction=Sort.Direction.DESC, sort="id", size=12) Pageable pageable, @ModelAttribute("filter") ProductFilter filter, Principal principal){
		if(principal!=null){	
			Users users = usersService.findByEmail(principal.getName());
		Orders orders = new Orders();
		orders.setAmount(1);
		orders.setFlag(false);
		Product product = productService.findOne(id);
		orders.setProduct(product);
		orders.setUsers(users);
		ordersService.save(orders);
		return "redirect:/proteins"+getParams(pageable, filter);
		}
		return "redirect:/login";
	}
	
	
	@GetMapping("/adds/{id}")
	public String adds(@PathVariable int id, Model model ,@PageableDefault(direction=Sort.Direction.DESC, sort="id", size=12) Pageable pageable, @ModelAttribute("filter") ProductFilter filter, Principal principal){
		if(principal!=null){	
			Users users = usersService.findByEmail(principal.getName());
		Orders orders = new Orders();
		orders.setAmount(1);
		orders.setFlag(false);
		Product product = productService.findOne(id);
		orders.setProduct(product);
		orders.setUsers(users);
		ordersService.save(orders);
		return "redirect:/category/3"+getParams(pageable, filter);
		}
		return "redirect:/login";
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
