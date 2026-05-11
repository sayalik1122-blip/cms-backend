package com.auxirem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timetable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimetableSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
}
