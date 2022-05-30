package com.youtube.amigoscode.springsecuritylearning.configuration;

import java.util.Set;

import com.google.common.collect.Sets;

import static com.youtube.amigoscode.springsecuritylearning.configuration.AplicationUserPermission.*;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSES_READ, COURSES_WRITE));

	private final Set<AplicationUserPermission> permissions;

	private ApplicationUserRole(Set<AplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AplicationUserPermission> getPermissions() {
		return permissions;
	}

}
