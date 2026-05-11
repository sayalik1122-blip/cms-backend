package com.auxirem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String studentName;
    private String feeType;
    private Double amount;
    private LocalDate dueDate;
    private String status; // Paid, Unpaid
    private String transactionId;
}
