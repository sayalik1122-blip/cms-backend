package com.auxirem.payload.request;

import lombok.Data;

@Data
public class SubjectRequest {
    private String name;
    private String code;
    private String semester;
    private Integer credits;
    private Long facultyId;
}
