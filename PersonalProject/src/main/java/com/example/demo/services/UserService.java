package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.medol.User;
import com.example.demo.repository.UserRepository;



@Service
public class UserService{
	@Autowired 
    private UserRepository userRepository;
	
	public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		
		return userRepository.save(user);		
	}
}
