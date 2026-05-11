package com.auxirem.repository;

import com.auxirem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByFacultyId(Long facultyId);
}
