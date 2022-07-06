package com.poly.beans;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
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
public class Product_model {

	private Integer id;
	
	@NotNull
	@NotBlank
	@Length(min = 1, max = 255)
	private String name;
	
	private String image;
	
	@NotNull
	@NotBlank
	@Min(0)
	private String price;
	
	private Date createdDate;
	
	@NotNull	
	@Min(0)
	@Max(1)
	private Integer available;
	
	@NotNull	
	private Integer categoryId;
}
