package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.ProductTasteService;

public class ProductTasteEditor  extends PropertyEditorSupport {

	private final ProductTasteService productTasteService;

	public ProductTasteEditor(ProductTasteService productTasteService) {
		this.productTasteService = productTasteService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ProductTaste productTaste = productTasteService.findOne(Integer.valueOf(text));
		setValue(productTaste);
	}
	
	
}
