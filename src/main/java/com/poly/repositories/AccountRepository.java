package com.poly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("SELECT o FROM Account o WHERE o.email = :email")
	public Account findByEmail(@Param("email") String email);
	
	@Query("SELECT o FROM Account o WHERE o.username = :username")
//	c2 @Query(name = "Account.findByUsername")
	public Account findByUsername(@Param("username") String username);
}
