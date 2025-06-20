//package com.Ecom.controllers;
//
//import com.Ecom.configs.GoogleService;
//import com.Ecom.models.Customer;
//import com.Ecom.repos.CustomerRepo;
//import com.Ecom.services.CustomerService;
//import com.Ecom.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private GoogleService googleService;
//
//    @Autowired
//    private CustomerRepo customerRepo;
//    @Autowired
//    private CustomerService customerService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    //
//    @PostMapping("/google-login")
//    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
//        try {
//            // Get the token from the request body
//            String idToken = body.get("token");
//            System.out.println("Id Token is : " +idToken);
//            // Verify the token with the Google service
//            var payload = googleService.verifyToken(idToken);
//
//            // Extract necessary details from the payload
//            String email = payload.getEmail();
//            String name = (String) payload.get("name"); // Could be null, handle gracefully
//
//            if (name == null) {
//                name = "Unknown"; // Set a default if name is not available
//            }
//
//            // Check if customer exists, otherwise register
//            Customer user = customerService.findByEmail(email);
//            if (user == null) {
//                // If user doesn't exist, create a new one
//                user = new Customer();
//                user.setEmail(email);
//                user.setFname(name);
//                user.setRole("USER"); // Set the default role as "USER"
//                customerRepo.save(user); // Save the user in the database
//            }
//
//            // Generate JWT token for the user
//            String token = jwtUtil.generateToken(email);
//
//            // Return the response with JWT token, role, and first name
//            return ResponseEntity.ok(Map.of(
//                    "token", token,
//                    "role", user.getRole(),
//                    "fname", user.getFname()
//            ));
//
//        } catch (Exception e) {
//            // Log the exception for debugging
//            e.printStackTrace();
//            // Return a response indicating an error, specifically unauthorized access
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or authentication failed");
//        }
//    }
//}