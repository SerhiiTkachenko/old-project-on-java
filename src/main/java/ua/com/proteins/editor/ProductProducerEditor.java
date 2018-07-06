package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.service.ProductProducerService;

public class ProductProducerEditor extends PropertyEditorSupport{

	
	public ProductProducerEditor(ProductProducerService productProducerService) {
		this.productProducerService = productProducerService;
	}

	private final ProductProducerService productProducerService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ProductProducer productProducer = productProducerService.findOne(Integer.valueOf(text));
		setValue(productProducer);
	}
	
	
	
	
	
	
	
	
	
	
	
//	private final IngredientService ingredientService;
//
//	public IngredientEditor(IngredientService ingredientService) {
//		this.ingredientService = ingredientService;
//	}
//
//	@Override
//	public void setAsText(String text) throws IllegalArgumentException {
//		Ingredient ingredient = ingredientService.findOne(Long.valueOf(text));
//		setValue(ingredient);
//	}
}
