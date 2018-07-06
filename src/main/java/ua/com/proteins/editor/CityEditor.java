package ua.com.proteins.editor;

import java.beans.PropertyEditorSupport;

import ua.com.proteins.entity.City;
import ua.com.proteins.service.CityService;

public class CityEditor extends PropertyEditorSupport{

	
	
	public CityEditor(CityService cityService) {
		this.cityService = cityService;
	}

	private final CityService cityService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		City city = cityService.findOne(Integer.valueOf(text));
		setValue(city);
	}
	
	
}
