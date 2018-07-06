package ua.com.proteins.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class OrdersFilter {

	private String max = "";

	private String min = "";

	private Integer maxValue;

	private Integer minValue;

	private String search = "";

	private List<Integer> productId = new ArrayList<>();

	private List<Integer> usersId = new ArrayList<>();

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Integer> getProductId() {
		return productId;
	}

	public void setProductId(List<Integer> productId) {
		this.productId = productId;
	}

	public List<Integer> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<Integer> usersId) {
		this.usersId = usersId;
	}

	

	

}
