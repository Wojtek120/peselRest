package pl.wojtek120.pesel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojtek120.pesel.model.dto.UserDto;
import pl.wojtek120.pesel.model.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     *
     * @return all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }
}
