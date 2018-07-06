package ua.com.proteins.dto.form;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.entity.ProductTaste;

public class ProductForm {

	private int id;
	private String weight;
	private String price;
	private String productName;
	private String productDescription;


	private ProductProducer productProducer;
	private ProductTaste productTaste;
	private ProductCategory productCategory;
	
	
	@Transient
	private MultipartFile file;
	
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public ProductCategory getproductCategory() {
		return productCategory;
	}

	public void setproductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	

}
