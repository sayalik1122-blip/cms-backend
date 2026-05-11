package com.auxirem.dto;

import com.auxirem.model.Role;
import lombok.Data;

@Data
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private Role role;
    private String course;
    private String year;
    private String phone;
    private String status;
    private String address;
}
