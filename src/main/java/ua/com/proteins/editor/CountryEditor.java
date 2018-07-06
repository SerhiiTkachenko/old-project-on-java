package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;


import ua.com.proteins.entity.Country;
import ua.com.proteins.service.CountryService;

public class CountryEditor extends PropertyEditorSupport{

	private final CountryService countryService;



	public CountryEditor(CountryService countryService) {
		this.countryService = countryService;
	}



	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country = countryService.findOne(Integer.valueOf(text));
		setValue(country);
	}
	
}

