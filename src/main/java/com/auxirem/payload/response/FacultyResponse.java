package com.auxirem.payload.response;

import lombok.Data;
import java.util.List;

@Data
public class FacultyResponse {
    private Long id;
    private String displayId;
    private String name;
    private String department;
    private String experience;
    private String email;
    private List<String> subjects;
    private String phone;
}
