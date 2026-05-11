package com.auxirem.controller;

import com.auxirem.model.Student;
import com.auxirem.payload.request.StudentRequest;
import com.auxirem.payload.response.StudentResponse;
import com.auxirem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .course(request.getCourse())
                .year(request.getYear())
                .phone(request.getPhone())
                .status(request.getStatus())
                .address(request.getAddress())
                .build();
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .course(request.getCourse())
                .year(request.getYear())
                .phone(request.getPhone())
                .status(request.getStatus())
                .address(request.getAddress())
                .build();
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> findByCourse(@RequestParam String course) {
        return ResponseEntity.ok(studentService.findByCourse(course));
    }
}
