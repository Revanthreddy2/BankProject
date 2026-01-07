package com.user.service;

import com.user.entity.User;

public interface UserService {
	
	User createUser(User user);

    User getUserByEmail(String email);

    User activateUser(String email);

    User getProfile(String email);

}
