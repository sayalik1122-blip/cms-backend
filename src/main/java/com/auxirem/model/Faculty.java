package com.auxirem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "faculty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private String experience;
    private String email;

    private String phone;

    @ElementCollection
    @CollectionTable(name = "faculty_subjects", joinColumns = @JoinColumn(name = "faculty_id"))
    @Column(name = "subject_name")
    private List<String> subjects;
}
