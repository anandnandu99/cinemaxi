package com.hexaware.cinemax.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cinemax.dto.AdminDTO;
import com.hexaware.cinemax.services.IAdminService;


@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody AdminDTO adminDTO) {
        try {
            adminService.addAdmin(adminDTO);
            return new ResponseEntity<>("Admin added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding admin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAdminCredentials(
            @RequestParam String username,
            @RequestParam String password) {
        boolean isValidCredentials = adminService.verifyAdminCredentials(username, password);
        if (isValidCredentials) {
            return new ResponseEntity<>("Credentials are valid", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}
