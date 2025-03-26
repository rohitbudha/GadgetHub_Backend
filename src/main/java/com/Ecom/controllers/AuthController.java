//package com.Ecom.controllers;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Ecom.configs.JwtUtil;
//import com.Ecom.models.Customer;
//import com.Ecom.repos.CustomerRepo;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//	
//	@Autowired
//    private  AuthenticationManager authenticationManager;
//	@Autowired
//    private  JwtUtil jwtUtil;
////	@Autowired
////    private  UserDetailsService userDetailsService;
//	
//	@Autowired
//  private CustomerRepo customerRepo;
//	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
//	    try {
//	        // Authenticate the user
//	    	
//	    
//	        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
//	                new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
//	        );
//
//	        // Get authenticated user details
//	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	        // Retrieve the customer from the database
//	        Customer customer = customerRepo.findByEmail(userDetails.getUsername())
//	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//	        // Generate JWT token for the authenticated customer
//	        String token = jwtUtil.generateToken(customer);
//
//	        // Return the token in response
//	        return ResponseEntity.ok(Map.of("token", token));
//
//	    } catch (BadCredentialsException e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
//	    }
//	}
//
//	
//}
//	
