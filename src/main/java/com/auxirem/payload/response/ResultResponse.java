package com.auxirem.payload.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResultResponse {
    private Long id;
    private Long examId;
    private String studentId;
    private String studentName;
    private String subject;
    private int marks;
    private int totalMarks;
    private String grade;
    private double percentage;
    private LocalDateTime markedAt;
}
