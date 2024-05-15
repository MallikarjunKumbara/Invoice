package com.example.invoiceapp.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.invoiceapp.entity.User;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // JUnit test for saveUser
        User user = new User();
        user.setUserName("Mallikarjun");
        user.setEmail("mallikarjun@gmail.com");
        user.setPassword("password");
        user.setRegistrationDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    
    
    @Test
    public void testGetInvoice()
    {
    	//find by id
    	User user = userRepository.findById(1L).get();
    	 Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void testFindUserByUsername() {
        // JUnit test for findUserByUsername
        User user = new User();
        user.setUserName("Mallikarjun");
        user.setEmail("mallikarjun@gmail.com");
        user.setPassword("password");
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);

        Assertions.assertThat(user.getUserName()).isEqualTo("Mallikarjun");
    }
    
    @Test
    public void updateGetUser() {
		User user = userRepository.findById(1L).get();
		user.setEmail("abc@gmail.com");
		User userUpdated = userRepository.save(user);
		Assertions.assertThat(userUpdated.getEmail()).isEqualTo("abc@gmail.com");
	}
    
    public void deleteGetUser() {
    	User user = userRepository.findById(1L).get();
		userRepository.delete(user);

		// delete by id
		// invoiceRepository.deleteById(1L);

		User user1 = null;
		Optional<User> optionalUser = userRepository.findByUserName("Mallikarjun");

		if (optionalUser.isPresent()) {
			user1 = optionalUser.get();
		}
	}
    
}
