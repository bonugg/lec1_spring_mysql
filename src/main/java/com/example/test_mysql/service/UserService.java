package com.example.test_mysql.service;

import com.example.test_mysql.controller.UserController;
import com.example.test_mysql.controller.dto.UserDto;
import com.example.test_mysql.entity.User;
import com.example.test_mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(UserDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("email already exists");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            throw new IllegalArgumentException("password not same");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
    }

    public void update(Long id, UserDto user) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updateUser = foundUser.get();
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            userRepository.save(updateUser);
        } else {
            throw new NoSuchElementException("User not found");
        }
    }

    public void delete(Long id) {
        Optional<User> aUser = readOne(id);
        if (aUser.isPresent()) {
            userRepository.delete(aUser.get());
        } else {
            throw new NoSuchElementException("1");
        }
    }

    public void delete(){
        userRepository.deleteAll();
    }

    public Optional<User> readOne(Long id) {
        return userRepository.findById(id);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }
}
