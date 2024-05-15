package com.example.invoiceapp.service;

import static org.mockito.Mockito.*;

import com.example.invoiceapp.dto.UserDTO;
import com.example.invoiceapp.entity.User;
import com.example.invoiceapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("testuser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");

        // When
        userService.registerUser(userDTO);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
    }
}
