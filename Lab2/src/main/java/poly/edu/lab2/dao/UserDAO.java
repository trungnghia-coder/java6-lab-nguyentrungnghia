package poly.edu.lab2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.lab2.entity.User;

public interface UserDAO extends JpaRepository<User, String> {
}
