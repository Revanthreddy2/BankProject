package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.enums.UserRole;
import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.entity.User;
import com.user.service.UserService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

    // Register user
//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//    }
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> createUser(
	        @Valid @RequestBody UserRequestDto request) {

	    User user = new User();
	    user.setFullName(request.getFullName());
	    user.setEmail(request.getEmail());
	    user.setMobile(request.getMobile());
	    user.setPassword(request.getPassword());
	    user.setRole(request.getRole());

	    User savedUser = userService.createUser(user);

	    UserResponseDto response = new UserResponseDto(
	            savedUser.getId(),
	            savedUser.getFullName(),
	            savedUser.getEmail(),
	            savedUser.getMobile(),
	            savedUser.getRole(),
	            savedUser.getStatus(),
	            savedUser.isVerified(),
	            savedUser.getCreatedAt()
	    );

	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


    // Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // Activate user
    @PutMapping("/activate/{email}")
    public ResponseEntity<UserResponseDto> activateUser(@PathVariable String email) {

        User user = userService.activateUser(email);

        UserResponseDto response = new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobile(),
                user.getRole(),
                user.getStatus(),
                user.isVerified(),
                user.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    // Get profile (used after login)
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile(@RequestParam String email) {
        User user = userService.getProfile(email);

        // Map User entity to DTO
        UserResponseDto response = new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobile(),
                user.getRole(),
                user.getStatus(),
                user.isVerified(),
                user.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

}
