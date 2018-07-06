package ua.com.proteins.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private String productDescription;
	private BigDecimal weight;

	private BigDecimal price;

	@ManyToOne
	private ProductProducer productProducer;

	@ManyToOne
	private ProductTaste productTaste;

	@ManyToOne
	private ProductCategory productCategory;

	@OneToMany(mappedBy = "product")
	private List<Orders> orders;

	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Product() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductProducer getProductProducer() {
		return productProducer;
	}

	public void setProductProducer(ProductProducer productProducer) {
		this.productProducer = productProducer;
	}

	public ProductTaste getProductTaste() {
		return productTaste;
	}

	public void setProductTaste(ProductTaste productTaste) {
		this.productTaste = productTaste;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}


}
