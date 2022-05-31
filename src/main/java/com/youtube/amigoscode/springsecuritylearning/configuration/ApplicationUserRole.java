package com.youtube.amigoscode.springsecuritylearning.configuration;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import static com.youtube.amigoscode.springsecuritylearning.configuration.AplicationUserPermission.*;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSES_READ, COURSES_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSES_READ));

	private final Set<AplicationUserPermission> permissions;

	private ApplicationUserRole(Set<AplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AplicationUserPermission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = permissions.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}

}
