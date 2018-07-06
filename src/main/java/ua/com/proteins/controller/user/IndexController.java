package ua.com.proteins.controller.user;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.service.OrdersService;
import ua.com.proteins.service.ProductCategoryService;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.service.UsersService;

@Controller
public class IndexController {
	
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	
		
	@RequestMapping("/")
	public String index(Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
		}
		return "user-index";
	}
	


	@GetMapping("/login")
	public String login(){
		return "user-login";
	}
	
	
	@RequestMapping("/cabinet")
	public String cabinet(){
		return "user-cabinet";
	}
	
	@RequestMapping("/category/{id}") 
	public String category(@PathVariable int id, Model model,@PageableDefault(direction=Sort.Direction.DESC, sort="id", size=12) Pageable pageable, @ModelAttribute("filter") ProductFilter filter){ 
	model.addAttribute("category", categoryService.findOne(id)); 
	model.addAttribute("page", productService.findByCategory(id,pageable)); 
	return "user-gainer"; 
	}
	
	
	
	
	
	
	
	
	

	
}
