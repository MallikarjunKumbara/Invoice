package com.example.invoiceapp.repository;

import com.example.invoiceapp.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	boolean existsByEmail(String email);

	Optional<User> findByUserName(String username);


	User findByUserNameAndPassword(String userName, String password);
    
}
