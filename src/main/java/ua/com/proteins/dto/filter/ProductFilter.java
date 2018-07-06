package ua.com.proteins.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductFilter {

	private String max = "";
	
	private String min = "";
	
	private BigDecimal maxValue;
	
	private BigDecimal minValue;
	
    private String maxWeight = "";
	
	private String minWeight = "";
	
	private BigDecimal maxWeightValue;
	
	public String getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(String maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(String minWeight) {
		this.minWeight = minWeight;
	}

	public BigDecimal getMaxWeightValue() {
		return maxWeightValue;
	}

	public void setMaxWeightValue(BigDecimal maxWeightValue) {
		this.maxWeightValue = maxWeightValue;
	}

	public BigDecimal getMinWeightValue() {
		return minWeightValue;
	}

	public void setMinWeightValue(BigDecimal minWeightValue) {
		this.minWeightValue = minWeightValue;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchDescription() {
		return searchDescription;
	}

	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}

	private BigDecimal minWeightValue;
	
	private String search = "";
	
	private String searchDescription = "";
	
	

	private List<Integer> productProducerId = new ArrayList<>();

	private List<Integer> productTasteId = new ArrayList<>();
	
	private List<Integer> productCategoryId = new ArrayList<>();

	public List<Integer> getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(List<Integer> productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

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

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public List<Integer> getProductProducerId() {
		return productProducerId;
	}

	public void setProductProducerId(List<Integer> productProducerId) {
		this.productProducerId = productProducerId;
	}

	public List<Integer> getProductTasteId() {
		return productTasteId;
	}

	public void setProductTasteId(List<Integer> productTasteId) {
		this.productTasteId = productTasteId;
	}
	
	
}
