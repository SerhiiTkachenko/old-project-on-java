package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.service.ProductCategoryService;


public class ProductCategoryEditor extends PropertyEditorSupport {

	private final ProductCategoryService productCategoryService;

	public ProductCategoryEditor(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ProductCategory productCategory = productCategoryService.findOne(Integer.valueOf(text));
		setValue(productCategory);
	}
	
}
