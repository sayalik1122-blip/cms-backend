package com.auxirem.config;

import com.auxirem.model.*;
import com.auxirem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            seedAdmins();
            seedStudents();
            seedFacultyAndSubjects();
            seedExams();
        }
    }

    private void seedAdmins() {
        User admin = User.builder()
                .name("Admin User")
                .email("admin@edu.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .institution("National Institute of Technology")
                .build();
        userRepository.save(admin);
    }

    private void seedStudents() {
        Student s1 = Student.builder()
                .name("Diya Patel")
                .email("diya@example.com")
                .password(passwordEncoder.encode("student123"))
                .role(Role.STUDENT)
                .course("Mechanical Eng.")
                .year("3rd")
                .phone("8765432109")
                .status("Active")
                .build();

        Student s2 = Student.builder()
                .name("Ananya Singh")
                .email("ananya@example.com")
                .password(passwordEncoder.encode("student123"))
                .role(Role.STUDENT)
                .course("Computer Science")
                .year("4th")
                .phone("6543210987")
                .status("Active")
                .build();

        studentRepository.saveAll(Arrays.asList(s1, s2));
    }

    private void seedFacultyAndSubjects() {
        Faculty f1 = Faculty.builder()
                .name("Dr. Rajesh Kumar")
                .department("Computer Science")
                .experience("15 Years")
                .email("rajesh@example.com")
                .subjects(Arrays.asList("Algorithms", "AI"))
                .build();
        Faculty savedFaculty = facultyRepository.save(f1);

        Subject sub1 = Subject.builder()
                .name("Data Structures")
                .code("CS201")
                .faculty(savedFaculty)
                .semester("3rd")
                .credits(4)
                .build();
        subjectRepository.save(sub1);
    }

    private void seedExams() {
        Exam e1 = Exam.builder()
                .subject("Data Structures")
                .date("2026-05-11")
                .time("11:26")
                .hall("5")
                .duration("1 hour")
                .totalMarks("100")
                .status("Marked")
                .build();
        examRepository.save(e1);
    }
}
