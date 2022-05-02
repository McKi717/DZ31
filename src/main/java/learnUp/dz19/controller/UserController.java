package learnUp.dz19.controller;

import learnUp.dz19.entity.Role;
import learnUp.dz19.entity.User;
import learnUp.dz19.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Boolean createUser(User user) {
        User entity = new User();
        entity.setUsername("aid");
        entity.setPassword("12345");
        entity.setRoles(user.getRoles());
        userService.saveUser(entity);
        return true;
    }
}
