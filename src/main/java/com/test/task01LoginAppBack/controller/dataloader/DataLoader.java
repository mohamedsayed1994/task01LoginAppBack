package com.test.task01LoginAppBack.controller.dataloader;

import com.test.task01LoginAppBack.model.User;
import com.test.task01LoginAppBack.sec.SecUser;
import com.test.task01LoginAppBack.sec.SecUserService;
import com.test.task01LoginAppBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final SecUserService secUserService;

    @Autowired
    public DataLoader(UserService userService, SecUserService secUserService) {
        this.userService = userService;
        this.secUserService = secUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("aly", "aly123");
        this.userService.create(user1);
        SecUser user2 = new SecUser("mohamed", "123456", true, "USER_ROLE");
        this.secUserService.addUser(user2);
        SecUser user3 = new SecUser("omar", "omar_123", true, "USER_ROLE");
        this.secUserService.addUser(user3);
        SecUser user = new SecUser("admin", "admin123", true, "ADMIN_ROLE , USER_ROLE");
        this.secUserService.addUser(user);
    }
}
