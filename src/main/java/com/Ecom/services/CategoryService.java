package com.Ecom.services;
import java.io.IOException;
import java.util.List;

import com.Ecom.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CategoryService {
    Category addCategory(Category category, MultipartFile image) throws IOException;
    List<Category> listAllCategory();
    public void deletecategory(int id);
    Category getCategoryById(int id);
    Category updateCategory(Category category, MultipartFile image)throws IOException;

}
