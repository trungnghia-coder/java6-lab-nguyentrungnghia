package poly.edu.backend.service;

import poly.edu.backend.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    Product create(Product student);
    Product update(Product student);
    void deleteById(Integer id);
}
