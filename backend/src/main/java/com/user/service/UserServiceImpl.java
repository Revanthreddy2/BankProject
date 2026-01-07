package com.user.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.exception.ResourceNotFoundException;
import com.enums.UserStatus;
import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
		@Autowired
	 	private UserRepository userRepository;
		
		@Autowired
	    private PasswordEncoder passwordEncoder;

	    @Override
	    public User createUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setStatus(UserStatus.ACTIVE);
	        user.setVerified(false);
	        user.setCreatedAt(LocalDateTime.now());
	        return userRepository.save(user);
	    }

	    @Override
	    public User getUserByEmail(String email) {
	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    }

	    @Override
	    public User activateUser(String email) {
	        User user = getUserByEmail(email);
	        user.setStatus(UserStatus.ACTIVE);
	        user.setVerified(true);
	        return userRepository.save(user);
	    }

		@Override
		public User getProfile(String email) {
			return getUserByEmail(email);
		}

}
