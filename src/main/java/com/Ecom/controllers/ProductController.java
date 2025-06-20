//package com.Ecom.controllers;
//
//import com.Ecom.models.Product;
//import com.Ecom.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/product")
//@CrossOrigin(origins = "http://localhost:3000")
//
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/get")
//  public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//    }
//
//    @PostMapping("add")
//    public ResponseEntity<?> addProduct(@RequestBody Product product) {
//        Product saved = productService.addProduct(product);
//        return ResponseEntity.ok(saved);
//    }
//}
//
