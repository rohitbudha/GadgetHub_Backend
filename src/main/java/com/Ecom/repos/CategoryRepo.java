package com.Ecom.repos;


import com.Ecom.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category getCategoryById(int id);
    Category findByName(String name);
}
