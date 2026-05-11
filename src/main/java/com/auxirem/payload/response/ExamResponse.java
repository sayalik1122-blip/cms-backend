package com.auxirem.payload.response;

import lombok.Data;

@Data
public class ExamResponse {
    private Long id;
    private String subject;
    private String date;
    private String time;
    private String hall;
    private String duration;
    private String totalMarks;
    private String status;
}
