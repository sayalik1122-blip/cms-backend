package com.auxirem.payload.response;

import com.auxirem.model.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String institution;
    private Role role;
}
