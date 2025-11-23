package poly.edu.lab5.controller;

import org.springframework.web.bind.annotation.*;
import poly.edu.lab5.database.Database;
import poly.edu.lab5.entity.Student;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class StudentRestApi {
    @GetMapping("students.json") // => {key1: student, key2: student, ... }
    public Map<String, Student> findAll(){
        return Database.map;
    }

    @GetMapping("students/{key}.json") // => student
    public Student findByKey(@PathVariable("key") String key){
        return Database.map.get(key);
    }

    @PostMapping("students.json") // => {name: key}
    public Map<String, String> create(@RequestBody Student student){
        var key = Database.getKey();
        Database.map.put(key, student);
        return Map.of("name", key);
    }

    @PutMapping("students/{key}.json") // => student
    public Student update(@PathVariable("key") String key, @RequestBody Student student){
        Database.map.put(key, student);
        return Database.map.get(key);
    }

    @DeleteMapping("students/{key}.json") // nothing
    public void delete(@PathVariable("key") String key){
        Database.map.remove(key);
    }
}
