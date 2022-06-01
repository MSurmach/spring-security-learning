package com.youtube.amigoscode.springsecuritylearning.configuration;

import static com.youtube.amigoscode.springsecuritylearning.configuration.AplicationUserPermission.STUDENT_WRITE;
import static com.youtube.amigoscode.springsecuritylearning.configuration.ApplicationUserRole.*;
import static org.springframework.http.HttpMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*")
				.permitAll()
				.antMatchers("/api/**")
				.hasRole(STUDENT.name())
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/courses", true)
				.and()
				.rememberMe();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmithUser = User.builder()
				.username("annasmith")
				.password(passwordEncoder.encode("password"))
				.authorities(STUDENT.getAuthorities())
				.build();
		UserDetails lindaUser = User.builder()
				.username("linda")
				.password(passwordEncoder.encode("password"))
				.authorities(ADMIN.getAuthorities())
				.build();
		UserDetails tomUser = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("password"))
				.authorities(ADMINTRAINEE.getAuthorities())
				.build();
		return new InMemoryUserDetailsManager(
				annaSmithUser,
				lindaUser,
				tomUser);
	}
}
