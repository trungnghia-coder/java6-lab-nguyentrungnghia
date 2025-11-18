package poly.edu.lab2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.lab2.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {
}
