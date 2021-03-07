package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserDAO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import static com.example.demo.security.ApplicationUserRole.CUSTOMER;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserService(UserRepository userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s nor found!", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                CUSTOMER.getGrantedAuthorities());
        /* in this example project, there are only customer role users in the real database,
         so every users are loaded with CUSTOMER authorities here for simplicity. */
    }


    public UserDAO save(UserDTO user) {
        UserDAO newUser = new UserDAO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setPhone(user.getPhone());
        return userDAO.save(newUser);
    }

}


