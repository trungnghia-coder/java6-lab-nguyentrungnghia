package poly.edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
