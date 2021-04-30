package com.kitrum.task.manager.user;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.user.dto.FullUserDto;
import com.kitrum.task.manager.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullUserDto> getUser(@PathVariable String id)
            throws ResourceNotFoundException {

        FullUserDto user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }
}
