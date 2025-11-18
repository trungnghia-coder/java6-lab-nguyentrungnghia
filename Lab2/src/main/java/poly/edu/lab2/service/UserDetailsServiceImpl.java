package poly.edu.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poly.edu.lab2.dao.UserDAO;
import poly.edu.lab2.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findById(username).get();
        String password = user.getPassword();
        String[] roles = user.getUserRoles().stream()
                .map(ur-> ur.getRole().getId().substring(5))
                .toList().toArray(String[] :: new);
        return org.springframework.security.core.userdetails.User.withUsername(username).password(password).roles(roles).build();
    }
}
