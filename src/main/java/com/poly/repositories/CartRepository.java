package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Query("SELECT o FROM Cart o WHERE o.account.id = :accountId AND o.product.id = :productId ")
	public Cart findByAccountIdAndProductId(@Param("accountId") int accountId , @Param("productId") int productId);
	
	@Query("SELECT o FROM Cart o WHERE o.account.id = :accountId  ")
	public List<Cart> findByAccountId(@Param("accountId") int accountId );
}
