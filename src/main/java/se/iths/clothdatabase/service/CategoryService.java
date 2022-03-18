package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        categoryRepository.deleteById(foundCategory.getId());
    }

    public Optional<CategoryEntity> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Iterable<CategoryEntity> findAllCategory() {
        return categoryRepository.findAll();
    }

    public CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        foundCategory.setCategoryName(foundCategory.getCategoryName());
        foundCategory.setType(foundCategory.getType());

        return categoryRepository.save(categoryEntity);
    }

}
