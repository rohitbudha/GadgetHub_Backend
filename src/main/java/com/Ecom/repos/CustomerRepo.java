package com.Ecom.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecom.models.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
 Customer findByEmail(String email);
	

}
