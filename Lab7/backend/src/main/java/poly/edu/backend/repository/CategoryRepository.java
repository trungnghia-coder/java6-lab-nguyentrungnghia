package poly.edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
