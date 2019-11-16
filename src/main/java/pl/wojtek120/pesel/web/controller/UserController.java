package pl.wojtek120.pesel.web.controller;

import org.springframework.web.bind.annotation.*;
import pl.wojtek120.pesel.model.dto.UserDto;
import pl.wojtek120.pesel.model.services.UserService;

import javax.validation.Valid;
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

    /**
     * Add new user to database
     *
     * @param user - user dto
     */
    @PostMapping
    public void addNewUser(@Valid @RequestBody UserDto user) {
        userService.add(user);
    }
}
