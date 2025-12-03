package poly.edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.edu.backend.entity.User;
import poly.edu.backend.service.UserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("user")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("user/{id}")
    public User findById(@PathVariable("id") String id){
        return userService.findById(id);
    }

    @PostMapping("user")
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    @PutMapping("user/{id}")
    public User update(@PathVariable("id") String id, @RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("user/{id}")
    public void delete(@PathVariable("id") String id){
        userService.deleteById(id);
    }
}
