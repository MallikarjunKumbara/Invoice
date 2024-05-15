package com.example.invoiceapp.service;

import com.example.invoiceapp.dto.UserDTO;
import com.example.invoiceapp.entity.User;
import com.example.invoiceapp.repository.UserRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    private  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        // Implement user registration logic
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }
}
