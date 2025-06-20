package com.Ecom.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecom.models.Customer;
import com.Ecom.repos.CustomerRepo;
import com.Ecom.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{



	@Autowired
	private CustomerRepo  customerRepo;

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepo.findById(id);
		
	}

	@Override
	public Customer updateCustomer(int id, Customer updatedCustomer) {
		
		return customerRepo.findById(id).map(customer -> {
            customer.setFname(updatedCustomer.getFname());
            customer.setLname(updatedCustomer.getLname());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setGender(updatedCustomer.getGender());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setNumber(updatedCustomer.getNumber());
            return customerRepo.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	@Override
	public void deleteCustomer(int id) {

		customerRepo.deleteById(id);
	}

}
