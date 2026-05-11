package com.auxirem.repository;

import com.auxirem.model.TimetableSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimetableSlot, Long> {
}
