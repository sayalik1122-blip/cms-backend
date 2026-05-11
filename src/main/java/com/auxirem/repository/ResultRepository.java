package com.auxirem.repository;

import com.auxirem.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByExamId(Long examId);
    List<Result> findByStudentId(Long studentId);
}
