package com.Ecom.services;

import com.Ecom.models.Category;
import com.Ecom.models.Customer;
import com.Ecom.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public interface ProductService {
//    Product addProduct(Product product);
Product addProduct(Product product, MultipartFile image) throws IOException;
    List<Product> getAllProducts();
    Product getProductById(int id);
//    Customer updateProduct(int id, Product updatedProduct);
//Product updateProduct(Product product, MultipartFile image) throws IOException;
Product updateProduct(Integer id, Product updatedProduct);

    void deleteProduct(int id);

}
