package com.test.task01LoginAppBack.controller;

import com.test.task01LoginAppBack.model.User;
import com.test.task01LoginAppBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4201")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User loginData(@RequestParam String username,
                          @RequestParam String password) {
        //7061737323313233
//        String decryptedUser = Encryption.decrypt(username, "7061737323313233");
//        System.out.println(decryptedUser);
//        String decryptedPass = Encryption.decrypt(password, "7061737323313233");
//        System.out.println(decryptedPass);

        return this.userService.checkUser(username, password);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }
}
