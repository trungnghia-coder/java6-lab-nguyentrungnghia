package poly.edu.backend.service;

import poly.edu.backend.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category create(Category student);
    Category update(Category student);
    void deleteById(Integer id);
}
