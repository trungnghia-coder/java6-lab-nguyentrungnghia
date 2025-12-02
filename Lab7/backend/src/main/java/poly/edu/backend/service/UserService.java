package poly.edu.backend.service;

import poly.edu.backend.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User create(User student);
    User update(User student);
    void deleteById(Integer id);
}
