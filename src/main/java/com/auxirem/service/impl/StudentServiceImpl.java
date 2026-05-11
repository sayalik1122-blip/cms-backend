package com.auxirem.service.impl;

import com.auxirem.model.Role;
import com.auxirem.model.Student;
import com.auxirem.payload.response.StudentResponse;
import com.auxirem.repository.StudentRepository;
import com.auxirem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    @Override
    public StudentResponse createStudent(Student student) {
        if (student.getPassword() != null) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        } else {
            student.setPassword(passwordEncoder.encode("student123"));
        }
        student.setRole(Role.STUDENT);
        return mapToResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        student.setYear(studentDetails.getYear());
        student.setPhone(studentDetails.getPhone());
        student.setStatus(studentDetails.getStatus());
        student.setAddress(studentDetails.getAddress());
        if (studentDetails.getPassword() != null && !studentDetails.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(studentDetails.getPassword()));
        }
        return mapToResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> findByCourse(String course) {
        return studentRepository.findByCourse(course).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setDisplayId(student.getId() != null ? String.format("S%03d", student.getId()) : null);
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setRole(student.getRole());
        response.setCourse(student.getCourse());
        response.setYear(student.getYear());
        response.setPhone(student.getPhone());
        response.setStatus(student.getStatus());
        response.setAddress(student.getAddress());
        return response;
    }
}
