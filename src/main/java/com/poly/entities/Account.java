package com.poly.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Data

@NamedQueries(
		@NamedQuery(
		name="Account.findByUsername",
		query="SELECT o FROM Account o WHERE o.username = :username"
		)
)

public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String fullname;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String photo;
	
	private Integer admin;
	
	private Integer activated;
	
	@OneToMany(mappedBy = "account")
	private List<Cart> lstCart;
	
	@OneToMany(mappedBy = "account")
	private List<Order> lstOrder;
	
}
