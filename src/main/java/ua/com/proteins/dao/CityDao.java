package ua.com.proteins.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.proteins.entity.City;

public interface CityDao extends JpaRepository<City, Integer>,JpaSpecificationExecutor<City>{

	City findByNameOfCity(String nameOfCity);

}
