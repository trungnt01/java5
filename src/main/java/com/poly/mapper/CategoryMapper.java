package com.poly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.beans.Category_model;
import com.poly.entities.Category;


@Service
public class CategoryMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Category convertToEntity(Category_model category_model) {
		Category category = this.mapper.map(category_model, Category.class);
		return category;
	}
	
	public Category_model convertToDTO(Category category) {
		Category_model category_model = this.mapper.map(category, Category_model.class);
		return category_model;
	}
	
}
