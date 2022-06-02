package com.youtube.amigoscode.springsecuritylearning.repository;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDetailsRepository {

	Optional<UserDetails> loadUserByUserName(String username);
}
