package com.auxirem.payload.request;

import lombok.Data;

@Data
public class StudentRequest {
    private String id;
    private String name;
    private String email;
    private String password;
    private String course;
    private String year;
    private String phone;
    private String status;
    private String address;
}
