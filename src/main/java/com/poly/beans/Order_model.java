package com.poly.beans;


import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Order_model {
	
	private Integer id;
	
	private String customerName;
	
	private String address;
	
	private Integer userId;
	
	private Date createDate;
	
}
