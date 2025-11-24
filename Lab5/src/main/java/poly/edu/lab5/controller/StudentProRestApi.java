package poly.edu.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.edu.lab5.entity.Student;
import poly.edu.lab5.service.StudentService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class StudentProRestApi {
    @Autowired
    StudentService studentService;

    @GetMapping("students") // => {key1: student, key2: student, ... }
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("students/{id}") //=> student
    public Student findById(@PathVariable("id") String id){
        return studentService.findById(id);
    }

    @PostMapping("students") //=> student
    public Student create(@RequestBody Student student){
        return studentService.create(student);
    }
    @PutMapping("students/{id}") //=> student
    public Student update(@PathVariable("id") String id, @RequestBody
    Student student){

        return studentService.update(student);
    }
    @DeleteMapping("students/{id}") //=> nothing
    public void delete(@PathVariable("id") String id){
        studentService.deleteById(id);
    }
}
