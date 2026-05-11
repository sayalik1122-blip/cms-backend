package com.auxirem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemSetting {
    @Id
    private String id = "current_settings"; // Single record pattern

    private String institutionName;
    private String institutionEmail;
    private String address;
    private String language;
    private String timezone;
    private String theme;
    private Boolean notifications;
}
