package com.poly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.beans.Product_model;
import com.poly.entities.Product;


@Service
public class ProductMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Product convertToEntity(Product_model product_model) {
		Product product = this.mapper.map(product_model, Product.class);
		return product;
	}
	
	public Product_model convertToDTO(Product product) {
		Product_model product_model = this.mapper.map(product, Product_model.class);
		return product_model;
	}
}
