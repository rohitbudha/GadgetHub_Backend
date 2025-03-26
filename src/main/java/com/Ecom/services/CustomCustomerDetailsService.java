//package com.Ecom.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.Ecom.models.Customer;
//import com.Ecom.repos.CustomerRepo;
//
//@Service
//public class CustomCustomerDetailsService implements UserDetailsService {
//
//    @Autowired
//    private CustomerRepo customerRepo;
//    
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Customer customer = customerRepo.findByCustomerEmail(email);
//        if (customer == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        // Spring Security expects roles to be prefixed with "ROLE_"
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole());
//        return new org.springframework.security.core.userdetails.User(
//            customer.getUsername(), 
//            customer.getPassword(), 
//            Collections.singletonList(authority)
//        );
//    }
//}