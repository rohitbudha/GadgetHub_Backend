//package com.Ecom.controllers;
//import com.Ecom.models.Category;
//import com.Ecom.repos.CategoryRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/admin/")
//public class AdminController {
//    @Autowired
//    private CategoryRepo categoryRepo;
//
////    @Autowired
////    private CloudinaryService cloudinaryService;
////
////    @Autowired
////    private ProductRepo productRepo;
//
////    @GetMapping("/category")
////    public List<Category> getAllCategories() {
////        return categoryRepo.findAll();
////    }
//
//
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategory(@RequestBody Category category)
////            throws IOException {
////
////        System.out.println( category);
////
////    categoryRepo.save(category);
////
////        return ResponseEntity.status(HttpStatus.CREATED).body(category);
////    }
//
//
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategory(@RequestBody Category category) {
////        Category saved = categoryRepo.save(category);
////        return ResponseEntity.ok(saved);
////    }
//
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategory(
////            @RequestParam("name") String name,
////            @RequestParam("description") String description,
////            @RequestParam("image") MultipartFile imageFile
////    ) throws IOException {
////
////        // (Optional) Upload to Cloudinary
////        String imageUrl = cloudinaryService.uploadFile(imageFile); // you'll need to implement this
////
////        Category category = new Category();
////        category.setName(name);
////        category.setDescription(description);
////        category.setImage(imageUrl); // Save image URL, not raw file
////
////        categoryRepo.save(category);
////
////        return ResponseEntity.status(HttpStatus.CREATED).body(category);
////    }
//
//
//
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategory(@RequestParam("name") String name,
////                                         @RequestParam("description") String description,
////                                         @RequestParam("image") MultipartFile image)
////            throws IOException {
////
////        String uploadDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources",
////                "static", "images", "category_img").toString();
////
////        if (image.isEmpty()) {
////            return ResponseEntity.badRequest().body("No image uploaded!");
////        }
////
////        Files.createDirectories(Paths.get(uploadDirectory));
////        String filename = image.getOriginalFilename();
////        Path filePath = Paths.get(uploadDirectory, filename);
////        Files.write(filePath, image.getBytes());
////
////        // Create category and set fields
////        Category category = new Category();
////        category.setName(name);
////        category.setDescription(description);
////        category.setImg(filename); // Only store filename in DB
////
////        categoryRepo.save(category);
////
////        return ResponseEntity.status(HttpStatus.CREATED).body(category);
////    }
//
//
//
//
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategories(@RequestBody List<Category> categories,
////                                           @RequestParam("image") MultipartFile image)
////            throws IOException {
////
////        // Define upload directory
////        String uploadDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources",
////                "static", "images", "category_img").toString();
////
////        List<Category> savedCategories = new ArrayList<>();
////
////        // Process each category in the list
////        for (Category category : categories) {
////            if (!image.isEmpty()) {
////                // Ensure directory exists
////                Files.createDirectories(Paths.get(uploadDirectory));
////
////                // Generate filename (optional random naming for security reasons)
////                String filename = image.getOriginalFilename();
////
////                // Save the file to the directory
////                Path fileNameAndPath = Paths.get(uploadDirectory, filename);
////                Files.write(fileNameAndPath, image.getBytes());
////
////                // Set the filename in the category object
////                category.setImg(filename);
////            } else {
////                // If no image uploaded, return a warning message
////                return ResponseEntity.badRequest().body("No image uploaded for one or more categories!");
////            }
////
////            // Save category to the database
////            categoryRepo.save(category);
////            savedCategories.add(category);
////        }
////
////        // Return success response with the list of saved categories and 201 CREATED status
////        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategories);
////    }
//
//
////
////    @PostMapping("/category/add")
////    public ResponseEntity<?> addCategories(@RequestParam("categories") String categoriesJson,
////                                           @RequestParam("image") MultipartFile image) throws IOException {
////
////        // Parse JSON into List<Category>
////        ObjectMapper mapper = new ObjectMapper();
////        List<Category> categories = mapper.readValue(categoriesJson, new TypeReference<List<Category>>() {});
////
////        String uploadDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources",
////                "static", "images", "category_img").toString();
////
////        List<Category> savedCategories = new ArrayList<>();
////
////        for (Category category : categories) {
////            if (!image.isEmpty()) {
////                Files.createDirectories(Paths.get(uploadDirectory));
////
////                String filename = image.getOriginalFilename();
////                Path fileNameAndPath = Paths.get(uploadDirectory, filename);
////                Files.write(fileNameAndPath, image.getBytes());
////
////                category.setImg(filename);
////            } else {
////                return ResponseEntity.badRequest().body("No image uploaded for one or more categories!");
////            }
////
////            categoryRepo.save(category);
////            savedCategories.add(category);
////        }
////
////        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategories);
////    }
//
//
//    @GetMapping("/category/edit/{id}")
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
//
//    @PutMapping("/category/update")
//    public ResponseEntity<?> updateCategory(@RequestBody Category category,
//                                            @RequestParam("image") MultipartFile image)
//            throws IOException {
//        // Define upload directory
//        String uploadDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources",
//                "static", "images", "category_img").toString();
//
//        // Handle image upload
//        if (!image.isEmpty()) {
//            // Ensure the directory exists
//            Files.createDirectories(Paths.get(uploadDirectory));
//
//            // Generate a filename (keeping it simple here, but you could use UUID for uniqueness)
//            String filename = image.getOriginalFilename();
//
//            // Save the file to the directory
//            Path fileNameAndPath = Paths.get(uploadDirectory, filename);
//            Files.write(fileNameAndPath, image.getBytes());
//
//            // Set the filename in the category object
//            category.setImage(filename);
//        } else {
//            // If no image is uploaded, set a message
//            return ResponseEntity.badRequest().body("No image uploaded!");
//        }
//
//        // Save the updated category in the database
//        categoryRepo.save(category);
//
//        // Return the updated category as a JSON response with 200 OK
//        return ResponseEntity.ok(category);
//    }
//
//
//    @DeleteMapping("/category/delete/{id}")
//    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
//        // Check if category exists
//        if (categoryRepo.existsById(id)) {
//            // Delete the category by ID
//            categoryRepo.deleteById(id);
//
//            // Return a success message with a 204 No Content status
//            return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                    .body("Category deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Category not found");
//        }
//    }
//
////    @PostMapping("/addProduct")
////    public ResponseEntity<String> addProduct(@RequestBody Product product) {
////        try {
////            Category category = categoryRepo.findById(product.getId())
////                    .orElseThrow(() -> new RuntimeException("Category not found"));
////
////            Product p = new Product();
////            p.setName(product.getName());
////            p.setDescription(product.getDescription());
////            p.setPrice(product.getPrice());
////            p.setImgUrl(product.getImgUrl());
////            p.setCategory(category);
////
////            productRepo.save(product);
////
////            return ResponseEntity.ok("Product added successfully!");
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add product.");
////        }
////    }
//
//
//
//}
