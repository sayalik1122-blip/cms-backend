package com.auxirem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String studentName; // Denormalized for convenience as per JSON
    private String subject;     // Denormalized as per JSON

    private int marks;
    private int totalMarks;
    private String grade;
    private double percentage;
    private LocalDateTime markedAt;
}
