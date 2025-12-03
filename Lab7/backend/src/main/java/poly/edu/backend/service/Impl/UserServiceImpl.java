package poly.edu.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.backend.entity.User;
import poly.edu.backend.repository.UserRepository;
import poly.edu.backend.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User create(User student) {
        return userRepository.save(student);
    }
    @Override
    public User update(User student) {
        return userRepository.save(student);
    }
    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
