package com.Ecom.controllers;

//import com.Ecom.DTOs.JwtResponse;
//import com.Ecom.DTOs.LoginRequest;
//import com.Ecom.configs.JwtTokenProvider;
import com.Ecom.DTOs.UserDTO;
import com.Ecom.serviceimpl.CustomerDetailsServiceImpl;
import com.Ecom.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Ecom.models.Customer;
import com.Ecom.services.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private  JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addCustomer(@RequestBody List<Customer> customers) {

        for (Customer customer : customers) {
            customer.setRole("USER");
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customerService.addCustomer(customer);
        }
        return ResponseEntity.ok(Map.of("message", "Customer added successfully"));
    }


    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        try {
            // Authenticate the user with email and password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);

            // Fetch the Customer from the database using the email (you need a service method for this)
            Customer customer = customerService.findByEmail(userDTO.getEmail());

            if (customer == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO("Customer not found", null, null, null));
            }

            // Generate JWT token if authentication is successful
            String token = jwtUtil.generateToken(userDTO.getEmail());

            // Set the token and role in UserDTO and return it
            userDTO.setToken(token);
            userDTO.setRole(customer.getRole());

            return ResponseEntity.ok(userDTO);  // Return the UserDTO with the token and role
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO("Invalid credentials", null, null, null));  // Invalid credentials
        }
    }


}





