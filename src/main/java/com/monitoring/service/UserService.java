package com.monitoring.service;

import java.util.Optional;

import com.monitoring.dto.User;

public interface UserService {

	void save(User user);

	Optional<User> findByUsername(String username);

}
