package poly.edu.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.backend.entity.Category;
import poly.edu.backend.repository.CategoryRepository;
import poly.edu.backend.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    public Category create(Category student) {
        return categoryRepository.save(student);
    }
    @Override
    public Category update(Category student) {
        return categoryRepository.save(student);
    }
    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
