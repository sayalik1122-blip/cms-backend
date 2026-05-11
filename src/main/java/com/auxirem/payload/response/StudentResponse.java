package com.auxirem.payload.response;

import com.auxirem.model.Role;
import lombok.Data;

@Data
public class StudentResponse {
    private Long id; // Numeric ID for API calls
    private String displayId; // Formatted ID (e.g., S001) for UI
    private String name;
    private String email;
    private Role role;
    private String course;
    private String year;
    private String phone;
    private String status;
    private String address;
}
