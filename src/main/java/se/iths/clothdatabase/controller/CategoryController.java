package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
        CategoryEntity createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<CategoryEntity>> findCategoryById(@PathVariable Long id) {
        Optional<CategoryEntity> foundCategory = categoryService.findCategoryById(id);
        return new ResponseEntity<>(foundCategory, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<CategoryEntity>> findAllCategory() {
        Iterable<CategoryEntity> allCategory = categoryService.findAllCategory();
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity categoryEntity) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryEntity), HttpStatus.OK);
    }

}
