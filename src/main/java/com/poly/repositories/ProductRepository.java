package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT o FROM Product o WHERE o.name like :name%")
	public List<Product> findByName(@Param("name") String name);
	
	public List<Product > findByNameStartingWith(String name);
}
