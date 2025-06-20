package com.Ecom.controllers;

//import com.Ecom.DTOs.JwtResponse;
//import com.Ecom.DTOs.LoginRequest;
//import com.Ecom.configs.JwtTokenProvider;
import com.Ecom.DTOs.UserDTO;
import com.Ecom.configs.EmailService;
import com.Ecom.configs.GoogleService;
import com.Ecom.models.Category;
import com.Ecom.models.Product;
import com.Ecom.repos.CategoryRepo;
import com.Ecom.repos.CustomerRepo;
import com.Ecom.repos.ProductRepo;
import com.Ecom.serviceimpl.CustomerDetailsServiceImpl;
import com.Ecom.services.ProductService;
import com.Ecom.utils.JwtUtil;
import jakarta.persistence.Id;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")

@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private CustomerRepo customerRepo;


    @Autowired
    private EmailService emailService;

    //    @PostMapping("/add")
    //    public ResponseEntity<Map<String, String>> addCustomer(@RequestBody List<Customer> customers) {
    //
    //        for (Customer customer : customers) {
    //            customer.setRole("USER");
    //            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
    //            customerService.addCustomer(customer);
    //        }
    //        return ResponseEntity.ok(Map.of("message", "Customer added successfully"));
    //    }
    //
//@PostMapping("/add")
//public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer) {
//    customer.setRole("USER");
//    customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
//
////    customerService.addCustomer(customer);
//    Customer user = customerService.findByEmail(email);
//    if (user == null) {
//        user = new Customer();
//        user.setEmail(email);
//        user.setFname(fname);
//        user.setLname(lname);
//        user.setRole("USER");
//        customerRepo.save(user);
//        emailService.sendWelcomeEmail(user.getEmail(), user.getFname(),user.getLname());
//    }
//    customerRepo.save(customer);
//
//
//    return ResponseEntity.ok(Map.of("message", "Customer added successfully"));
//}


@PostMapping("/add")
public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer) {
    customer.setRole("USER");
    customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

    // Check if a user with this email already exists
    Customer existingUser = customerService.findByEmail(customer.getEmail());

    if (existingUser == null) {
        // Save the new customer
        Customer savedCustomer = customerRepo.save(customer);

        // Send welcome email
        emailService.sendWelcomeEmail(savedCustomer.getEmail(), savedCustomer.getFname(), savedCustomer.getLname());

        return ResponseEntity.ok(Map.of("message", "Customer added and email sent successfully"));
    } else {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Customer with this email already exists"));
    }
}

  @Autowired
  private ProductService productService;

@Autowired
private ProductRepo productRepo;
        @GetMapping("/cget")
  public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product p = productRepo.save(product);
        return ResponseEntity.ok(p);
    }


 @GetMapping("/product/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // If not found
        }
    }



//    @PutMapping("/product/update/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id,
//                                            @RequestBody Product updatedProduct) {
//
//        Optional<Product> productOptional = productRepo.findById(id);
//        if (!productOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//
//        Product product = productOptional.get();
//        product.setName(updatedProduct.getName());
//        product.setDescription(updatedProduct.getDescription());
//        product.setCategory(updatedProduct.getCategory());
//        product.setPrice(updatedProduct.getPrice());
//        product.setImageUrl(updatedProduct.getImageUrl());
//
//
//
//
//
//        // Update image if provided
//        if (updatedProduct.getImageUrl() != null && !updatedProduct.getImageUrl().isEmpty()) {
//            product.setImageUrl(updatedProduct.getImageUrl());
//        }
//
//        productRepo.save(product);
//        return ResponseEntity.ok(product);
//    }



    @PutMapping("/product/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id,
                                           @RequestBody Product updatedProduct) {
        try {
            Product updated = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




    @DeleteMapping("/Product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        // Check if category exists
        if (productRepo.existsById(id)) {
            // Delete the category by ID
            productRepo.deleteById(id);

            // Return a success message with a 204 No Content status
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }
    }







//    public ResponseEntity<Product> addProduct(
//            @RequestParam("name") String name,
//            @RequestParam("category") String category,
//            @RequestParam("price") double price,
//            @RequestParam("description") String description,
//            @RequestParam("image") MultipartFile image) throws IOException {
//
//        // Create a new Product object
//        Product product = new Product(name, category, price, description, null);
//
//        // Call the addProduct method in ProductService to handle image upload and saving
//        Product savedProduct = productService.addProduct(product, image);
//
//        // Return the saved product
//        return ResponseEntity.ok(savedProduct);
//    }

//    @PutMapping("/product/update/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
//        try {
//            Product existingProduct = productService.getProductById(id);
//            if (existingProduct == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//            }
//
//            existingProduct.setName(updatedProduct.getName());
//            existingProduct.setCategory(updatedProduct.getCategory());
//            existingProduct.setPrice(updatedProduct.getPrice());
//            existingProduct.setDescription(updatedProduct.getDescription());
//            existingProduct.setImageUrl(updatedProduct.getImageUrl());
//
//            Product savedProduct = productService.addProduct(existingProduct);
//            return ResponseEntity.ok(savedProduct);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
//        }
//    }

@Autowired
private CategoryRepo categoryRepo;
    @PostMapping("/category/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category saved = categoryRepo.save(category);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }


//        @GetMapping("/category/edit/{id}")
//    public ResponseEntity<?> editCategory(@PathVariable("id") int id) {
//        Category category = categoryRepo.getCategoryById(id);
//
//        if (category == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Category with id " + id + " not found.");
//        }
//
//        return ResponseEntity.ok(category);
//    }
@PutMapping("/category/update/{id}")
public ResponseEntity<?> updateCategory(@PathVariable("id") Integer id,
                                        @RequestBody Category updatedCategory) {

    Optional<Category> categoryOptional = categoryRepo.findById(id);
    if (!categoryOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
    }

    Category category = categoryOptional.get();
    category.setName(updatedCategory.getName());
    category.setDescription(updatedCategory.getDescription());

    // Update image if provided
    if (updatedCategory.getImage() != null && !updatedCategory.getImage().isEmpty()) {
        category.setImage(updatedCategory.getImage());
    }

    categoryRepo.save(category);
    return ResponseEntity.ok(category);
}



    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        // Check if category exists
        if (categoryRepo.existsById(id)) {
            // Delete the category by ID
            categoryRepo.deleteById(id);

            // Return a success message with a 204 No Content status
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category not found");
        }
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
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);

            Customer customer = customerService.findByEmail(userDTO.getEmail());

            if (customer == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO("Customer not found", null, null, null,null));
            }

            String token = jwtUtil.generateToken(userDTO.getEmail());

            userDTO.setToken(token);
            userDTO.setRole(customer.getRole());
            userDTO.setFname(customer.getFname());

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO("Invalid credentials", null, null, null,null));  // Invalid credentials
        }
    }




    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
        try {
            String idToken = body.get("token");
            System.out.println("Id Token is : " +idToken);
            var payload = googleService.verifyToken(idToken);


            String email = payload.getEmail();
            String fname = payload.get("given_name") != null ? payload.get("given_name").toString() : "No";
            String lname = payload.get("family_name") != null ? payload.get("family_name").toString() : "Name";



            Customer user = customerService.findByEmail(email);
            if (user == null) {
                user = new Customer();
                user.setEmail(email);
                user.setFname(fname);
                user.setLname(lname);
                user.setRole("USER");
                customerRepo.save(user);
                emailService.sendWelcomeEmail(user.getEmail(), user.getFname(),user.getLname());
            }

            // Generate JWT token for the user
            String token = jwtUtil.generateToken(email);

            // Return the response with JWT token, role, and first name
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", user.getRole(),
                    "fname", user.getFname(),
                    "lname", user.getLname()
                    ));

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            // Return a response indicating an error, specifically unauthorized access
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or authentication failed");
        }
    }

}





