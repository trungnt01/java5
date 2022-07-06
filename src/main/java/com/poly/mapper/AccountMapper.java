package com.poly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.beans.Account_model;
import com.poly.entities.Account;


@Service
public class AccountMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Account convertToEntity(Account_model user) {
		Account account = this.mapper.map(user, Account.class);
		return account;
	}
	
	public Account_model convertToDTO(Account account) {
		Account_model user = this.mapper.map(account, Account_model.class);
		return user;
	}
	
}
