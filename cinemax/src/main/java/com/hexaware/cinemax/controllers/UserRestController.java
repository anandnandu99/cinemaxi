// UserRestController.java (Controller)
package com.hexaware.cinemax.controllers;

import com.hexaware.cinemax.dto.UserDTO;
import com.hexaware.cinemax.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/welcome")
    public String hello() {
    	return "welcome";
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/authenticate/{username}/{password}")
    public ResponseEntity<UserDTO> authenticateUser(
            @PathVariable String username,
            @PathVariable String password
    ) {
        UserDTO authenticatedUser = userService.authenticateUser(username, password);
        return ResponseEntity.ok(authenticatedUser);
    }
}
