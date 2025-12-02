package poly.edu.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.backend.entity.Product;
import poly.edu.backend.repository.ProductRepository;
import poly.edu.backend.service.ProductService;

import java.util.List;

@Service
public class ProdcutServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public Product create(Product student) {
        return productRepository.save(student);
    }
    @Override
    public Product update(Product student) {
        return productRepository.save(student);
    }
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
