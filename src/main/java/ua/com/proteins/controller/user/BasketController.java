package ua.com.proteins.controller.user;


import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.proteins.dto.form.OrdersForm;
import ua.com.proteins.entity.Orders;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.Users;
import ua.com.proteins.service.OrdersService;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.service.UsersService;

@Controller
@RequestMapping("/basket")
@SessionAttributes("basket")
public class BasketController {

	@ModelAttribute("basket")
	public OrdersForm  getForm(){
		return new OrdersForm();
	}
	
	@Autowired
	private UsersService usersService;
	
	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping
	public String basket(Model model, Principal principal){ 
	if(principal!=null){ 
	Users users = usersService.findByEmail(principal.getName()); 
	int userId =users.getId(); 
	
	model.addAttribute("orderss", ordersService.findFlag(userId, false)); 
	model.addAttribute("sum", ordersService.sum(userId, false));
	} 
	return "user-basket"; 
	}

	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		ordersService.delete(id);
		return "redirect:/basket";
	}
	
	@GetMapping("/plus/{id}")
	public String plus(@PathVariable int id){
		Orders orders = ordersService.findOne(id);
		int amount=orders.getAmount();
		amount++;
		orders.setAmount(amount);
		ordersService.save(orders);
		return "redirect:/basket";
	}
	@GetMapping("/minus/{id}")
	public String minus(@PathVariable int id){
		Orders orders = ordersService.findOne(id, false);
		int amount=orders.getAmount();
		amount--;
		orders.setAmount(amount);
		ordersService.save(orders);
		return "redirect:/basket";
	}
	
	@GetMapping("/price/{id}") 
	public String price(@PathVariable int id){ 
		
		
	return "redirect:/basket"; 
	}
	
	@RequestMapping("/accept")
	public String accept(Principal principal){
		Users users = usersService.findByEmail(principal.getName());
		int userId = users.getId();
		List<Orders> ord = ordersService.findFlag(userId, false);
		for (int i = 0; i < ord.size(); i++) {
			Orders orders = ord.get(i);
			orders.setFlag(true);
			ordersService.save(orders);
		}
		return "redirect:/basket";
	}
	//-----------------------------------------------
}
