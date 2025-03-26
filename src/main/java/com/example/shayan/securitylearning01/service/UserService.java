package com.example.shayan.securitylearning01.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shayan.securitylearning01.repository.User;
import com.example.shayan.securitylearning01.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user:\n" + e.getMessage());
        }
    }

    @Transactional
    public void createUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user:\n" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Error getting all users:\n" + e.getMessage());
        }
    }

    public User getUserById(String id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error getting user by id:\n" + e.getMessage());
        }
    }

    @Transactional
    public void updateUserbyId(User user, String id){
        try {
            User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(existingUser);

        } catch (Exception e) {
            throw new RuntimeException("Error updating user by id:\n" + e.getMessage());
        }
    }

    @Transactional
    public void deleteUser(String id) {
        try {
            userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error getting user by id:\n" + e.getMessage());
        }
    }
}
