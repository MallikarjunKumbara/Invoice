package com.example.invoiceapp.service;

import com.example.invoiceapp.dto.UserDTO;
import com.example.invoiceapp.entity.Invoice;
import com.example.invoiceapp.entity.User;
import com.example.invoiceapp.exception.ResourceAlreadyExistsException;
import com.example.invoiceapp.exception.UserNotFoundException;
import com.example.invoiceapp.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	 
    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        // Check if user with the same email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " + userDTO.getEmail() + " already exists.");
        }

        // Implement user registration logic
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRegistrationDate(LocalDate.now());

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle database-related errors (e.g., constraint violation)
            throw new IllegalArgumentException("Failed to create user: " + e.getMessage());
        }
    }
    
    public User getUser(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        if(userName.equals("") || password.equals("")) {
			throw new UserNotFoundException("Don't pass empty credentials");
		}
        String name = user.getUserName();
		String pwd = user.getPassword();
		if(userName.equals(name) && password.equals(pwd)) {
			return userRepository.findByUserNameAndPassword(userName, password);
		}else {
			throw new UserNotFoundException("User not matched with credentials");
		}
        
    }
    
    public List<User> getAllUsers() {
		return userRepository.findAll();

}
}
