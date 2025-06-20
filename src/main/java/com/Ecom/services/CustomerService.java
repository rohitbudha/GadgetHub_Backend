package com.Ecom.services;


import java.util.List;
import java.util.Optional;

import com.Ecom.models.Customer;


public interface CustomerService {
    Customer findByEmail(String email);
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(int id);
    Customer updateCustomer(int id, Customer updatedCustomer);
    void deleteCustomer(int id);
}
