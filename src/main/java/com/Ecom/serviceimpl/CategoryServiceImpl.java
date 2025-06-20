package com.Ecom.serviceimpl;

import com.Ecom.models.Category;
import com.Ecom.repos.CategoryRepo;
import com.Ecom.services.CategoryService;
//import com.Ecom.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Category addCategory(Category category, MultipartFile image) throws IOException {

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void deletecategory(int id) {
        categoryRepo.deleteById(id);

    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.getCategoryById(id);
    }

    @Override
    public Category updateCategory(Category category, MultipartFile image) throws IOException {
        return categoryRepo.save(category);
    }
}
