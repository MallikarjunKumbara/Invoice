package com.example.invoiceapp.controller;

import com.example.invoiceapp.dto.UserDTO;
import com.example.invoiceapp.entity.Invoice;
import com.example.invoiceapp.entity.User;
import com.example.invoiceapp.exception.UserNotFoundException;
import com.example.invoiceapp.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
    private  UserService userService;

    

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    	// register the user
        userService.registerUser(userDTO);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        return new ResponseEntity<>(userService.getUser(userName, password), HttpStatus.OK);
    }

    @GetMapping("/all")public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    
}
