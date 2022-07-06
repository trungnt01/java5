package com.poly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.beans.Cart_model;
import com.poly.entities.Cart;


@Service
public class CartMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Cart convertToEntity(Cart_model cart_model) {
		Cart cart = this.mapper.map(cart_model, Cart.class);
		return cart;
	}
	
	public Cart_model convertToDTO(Cart cart) {
		Cart_model cart_model = this.mapper.map(cart, Cart_model.class);
		return cart_model;
	}
}
