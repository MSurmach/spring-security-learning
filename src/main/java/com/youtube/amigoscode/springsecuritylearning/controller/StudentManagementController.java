package com.youtube.amigoscode.springsecuritylearning.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.amigoscode.springsecuritylearning.model.Student.java.Student;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

	private final static List<Student> STUDENTS = new ArrayList<>(
			Arrays.asList(
					new Student(1, "James Bond"),
					new Student(2, "Maria Jones"),
					new Student(3, "Anna Smith")));

	@GetMapping
	@PreAuthorize("hasAnyRole ('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	public static List<Student> getStudents() {
		return STUDENTS;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerStudent(@RequestBody Student student) {
		System.out.println("register student: " + student);
		STUDENTS.add(student);
	}

	@DeleteMapping(path = "/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable Integer studentId) {
		System.out.println("delete student with id: " + studentId);
		Optional<Student> found = STUDENTS.stream()
				.filter(student -> student.getStudentId().equals(studentId))
				.findFirst();
		STUDENTS.remove(found.get());
	}

	@PutMapping(path = "/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
		System.out.println("update student: " + student + " with id: " + studentId);
		STUDENTS.stream()
				.filter(updatableStudent -> updatableStudent.getStudentId().equals(studentId))
				.forEach(updatableStudent -> updatableStudent.setStudentName(student.getStudentName()));
	}

}
