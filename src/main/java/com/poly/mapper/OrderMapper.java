package com.poly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.beans.Order_model;
import com.poly.entities.Order;


@Service
public class OrderMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Order convertToEntity(Order_model order_model) {
		Order order = this.mapper.map(order_model, Order.class);
		return order;
	}
	
	public Order_model convertToDTO(Order order) {
		Order_model order_model = this.mapper.map(order, Order_model.class);
		return order_model;
	}
	
}
