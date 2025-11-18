package poly.edu.lab2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.lab2.entity.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
}
