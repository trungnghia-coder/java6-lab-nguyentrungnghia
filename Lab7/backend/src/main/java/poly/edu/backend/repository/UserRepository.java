package poly.edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.backend.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
