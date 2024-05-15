package com.example.invoiceapp.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.invoiceapp.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private User user;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRegistrationDate(LocalDate.now());
    }

    @Test
    public void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetUserName() {
        assertEquals("testUser", user.getUserName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetRegistrationDate() {
        assertEquals(LocalDate.now(), user.getRegistrationDate());
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userRepository.save(user));
    }
   

}


    
    

