package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.Product;
import ua.com.proteins.service.ProductService;

public class ProductEditor extends PropertyEditorSupport {

	
	
	public ProductEditor(ProductService productService) {
		this.productService = productService;
	}

	private final ProductService productService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		Product product = productService.findOne(Integer.valueOf(text));
		setValue(product);
	}
	
	
}
