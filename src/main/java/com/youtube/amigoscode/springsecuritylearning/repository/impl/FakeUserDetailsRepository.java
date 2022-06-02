package com.youtube.amigoscode.springsecuritylearning.repository.impl;

import static com.youtube.amigoscode.springsecuritylearning.configuration.ApplicationUserRole.ADMIN;
import static com.youtube.amigoscode.springsecuritylearning.configuration.ApplicationUserRole.ADMINTRAINEE;
import static com.youtube.amigoscode.springsecuritylearning.configuration.ApplicationUserRole.STUDENT;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.youtube.amigoscode.springsecuritylearning.repository.ApplicationUserDetailsRepository;

@Repository
public class FakeUserDetailsRepository implements ApplicationUserDetailsRepository {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public FakeUserDetailsRepository(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<UserDetails> loadUserByUserName(String username) {
		return usersInMemory.stream().filter(userDetails -> userDetails.getUsername().equals(username)).findFirst();
	}

	private final Set<UserDetails> usersInMemory = Stream.of(
			User.builder()
					.username("annasmith")
					.password(passwordEncoder.encode("password"))
					.authorities(STUDENT.getAuthorities())
					.build(),
			User.builder()
					.username("linda")
					.password(passwordEncoder.encode("password"))
					.authorities(ADMIN.getAuthorities())
					.build(),
			User.builder()
					.username("tom")
					.password(passwordEncoder.encode("password"))
					.authorities(ADMINTRAINEE.getAuthorities())
					.build())
			.collect(Collectors.toSet());
}
