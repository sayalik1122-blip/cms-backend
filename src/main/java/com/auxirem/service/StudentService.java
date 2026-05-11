package com.auxirem.service;

import com.auxirem.model.Student;
import com.auxirem.payload.response.StudentResponse;
import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    StudentResponse createStudent(Student student);
    StudentResponse updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<StudentResponse> findByCourse(String course);
}
