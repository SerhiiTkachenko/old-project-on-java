package ua.com.proteins.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class StreetFilter {

	private String search = "";

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	private List<Integer> cityId = new ArrayList<>();

	public List<Integer> getCityId() {
		return cityId;
	}

	public void setCityId(List<Integer> cityId) {
		this.cityId = cityId;
	}

	

}