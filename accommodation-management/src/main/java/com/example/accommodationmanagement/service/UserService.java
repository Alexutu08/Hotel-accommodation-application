package com.example.accommodationmanagement.service;

import com.example.accommodationmanagement.config.*;
import com.example.accommodationmanagement.model.User;
import com.example.accommodationmanagement.repository.UserRepository;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Adaugam un SuperAdmin daca nu exista deja
        if (userRepository.findByUsername("superadmin").isEmpty()){
            User superAdmin = new User();
            superAdmin.setName("SuperAdmin");
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("superadmin@email.com");
            superAdmin.setPassword(passwordEncoder.encode("superadminpass"));
            superAdmin.setRole("ROLE_SUPERADMIN");
            userRepository.save(superAdmin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
//                true, true, true, true,
                Collections.emptyList() // list of granted authorities
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setName(user.getName());
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}