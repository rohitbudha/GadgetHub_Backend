package com.Ecom.serviceimpl;

import com.Ecom.models.Category;
import com.Ecom.models.Customer;
import com.Ecom.models.Product;
import com.Ecom.repos.CategoryRepo;
import com.Ecom.repos.ProductRepo;
import com.Ecom.services.ProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepo productRepo;

//    @Autowired
//    private CategoryRepo categoryRepo;



//    @Override
//    public Product addProduct(Product product) {
//                return productRepo.save(product);
//
//    }


//    @Override
//    public Product updateProduct(Product product, MultipartFile image) throws IOException {
//        Optional<Product> productOptional = productRepo.findById(id);
//        if (!productOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//        Product product = productOptional.get();
//        product.setName(updatedProduct.getName());
//        product.setDescription(updatedProduct.getDescription());
//        product.setCategory(updatedProduct.getCategory());
//        product.setPrice(updatedProduct.getPrice());
//        product.setImageUrl(updatedProduct.getImageUrl());
//        return productRepo.save(product);
//    }

    @Override
    public Product addProduct(Product product, MultipartFile image) throws IOException {

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product updateProduct(Integer id, Product updatedProduct) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());



        // Update image URL if provided
        if (updatedProduct.getImageUrl() != null && !updatedProduct.getImageUrl().isEmpty()) {
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
        }

//        // Set category if valid
//        if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getId() != null) {
//            Category category = categoryRepo.findById(updatedProduct.getCategory().getId())
//                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
//            existingProduct.setCategory(category);
//        }

        return productRepo.save(existingProduct);
    }



    @Override
    public void deleteProduct(int id) {
     productRepo.deleteById(id);
    }
}
