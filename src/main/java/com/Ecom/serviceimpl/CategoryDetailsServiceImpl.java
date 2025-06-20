//package com.Ecom.serviceimpl;
//
//import com.Ecom.models.Category;
//import com.Ecom.repos.CategoryRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Qualifier
//public class CategoryDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private CategoryRepo categoryRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String categoryName) throws UsernameNotFoundException {
//        Category category = categoryRepo.findByName(categoryName);
//        if (category == null) {
//            throw new UsernameNotFoundException("Category not found");
//        }
//
//        // If the category needs to be assigned roles, you can use it here.
//        // For example, assuming categories can have roles, you can return it like so:
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + category.getName());  // Assuming role is derived from category name
//
//        return new org.springframework.security.core.userdetails.User(
//                category.getName(),  // Category name as the username
//                category.getDescription(),  // You can decide what to pass here based on the Category entity
//                Collections.singletonList(authority)
//        );
//    }
//}
