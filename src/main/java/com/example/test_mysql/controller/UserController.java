package com.example.test_mysql.controller;

import com.example.test_mysql.controller.dto.UserDto;
import com.example.test_mysql.entity.User;
import com.example.test_mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private void create(@RequestBody UserDto requestCreated) {
        userService.create(requestCreated);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto requestUpdated) {
        userService.update(id, requestUpdated);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @DeleteMapping
    public void delete(){
        userService.delete();
    }

    @GetMapping("{id}")
    private User readOne(@PathVariable Long id) {
        return userService.readOne(id).orElse(null);
    }

    @GetMapping
    public List<User> readAll() {
        return userService.readAll();
    }
}
