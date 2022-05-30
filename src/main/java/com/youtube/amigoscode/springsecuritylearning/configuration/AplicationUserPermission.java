package com.youtube.amigoscode.springsecuritylearning.configuration;

public enum AplicationUserPermission {
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSES_READ("courses:read"),
	COURSES_WRITE("courses:write");

	private final String permission;

	private AplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
