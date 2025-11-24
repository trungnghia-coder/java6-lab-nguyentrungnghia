package poly.edu.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.lab5.entity.Student;

public interface StudentRepo extends JpaRepository<Student, String> {
}
