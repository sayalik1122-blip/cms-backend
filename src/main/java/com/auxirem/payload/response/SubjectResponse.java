package com.auxirem.payload.response;

import lombok.Data;

@Data
public class SubjectResponse {
    private Long id;
    private String displayId;
    private String name;
    private String code;
    private String semester;
    private Integer credits;
    private String faculty;
}
