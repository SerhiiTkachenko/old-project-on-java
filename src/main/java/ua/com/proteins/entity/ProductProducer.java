package ua.com.proteins.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class ProductProducer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@OneToMany(mappedBy="productProducer")
	private List<Product> product;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	

	
}