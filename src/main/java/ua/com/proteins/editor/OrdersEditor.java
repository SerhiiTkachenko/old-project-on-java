package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.Orders;
import ua.com.proteins.service.OrdersService;

public class OrdersEditor extends PropertyEditorSupport {

	public OrdersEditor(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	private final OrdersService ordersService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Orders orders = ordersService.findOne(Integer.valueOf(text));
		setValue(orders);
	}

}
