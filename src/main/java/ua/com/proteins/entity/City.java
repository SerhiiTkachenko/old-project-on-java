package ua.com.proteins.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nameOfCity;

	
	@OneToMany(mappedBy = "city")
	private List<Street> street;

	public City() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameOfCity() {
		return nameOfCity;
	}

	public void setNameOfCity(String nameOfCity) {
		this.nameOfCity = nameOfCity;
	}

	public List<Street> getStreet() {
		return street;
	}

	public void setStreet(List<Street> street) {
		this.street = street;
	}

	

	
}
