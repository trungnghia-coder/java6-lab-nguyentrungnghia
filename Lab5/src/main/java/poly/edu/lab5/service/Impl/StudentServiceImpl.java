package poly.edu.lab5.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.lab5.entity.Student;
import poly.edu.lab5.repository.StudentRepo;
import poly.edu.lab5.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
    @Override
    public Student findById(String id) {
        return studentRepo.findById(id).orElse(null);
    }
    @Override
    public Student create(Student student) {
        return studentRepo.save(student);
    }
    @Override
    public Student update(Student student) {
        return studentRepo.save(student);
    }
    @Override
    public void deleteById(String id) {
        studentRepo.deleteById(id);
    }
}
