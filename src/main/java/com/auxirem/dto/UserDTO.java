package com.auxirem.dto;

import com.auxirem.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String institution;
    private Role role;
}
