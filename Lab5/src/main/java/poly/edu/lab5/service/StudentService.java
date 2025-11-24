package poly.edu.lab5.service;

import poly.edu.lab5.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(String id);
    Student create(Student student);
    Student update(Student student);
    void deleteById(String id);
}
