package com.poly.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="carts")
public class Cart implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Account account;
	
	@ManyToOne()
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	private Integer quantity;
	
}
