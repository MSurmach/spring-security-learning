package com.youtube.amigoscode.springsecuritylearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.youtube.amigoscode.springsecuritylearning.repository.ApplicationUserDetailsRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private ApplicationUserDetailsRepository userDetailsRepository;

	@Autowired
	public ApplicationUserDetailsService(ApplicationUserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetailsRepository.loadUserByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("User with name = %s is not found", username)));
	}
}
