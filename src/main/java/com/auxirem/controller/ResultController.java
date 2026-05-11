package com.auxirem.controller;

import com.auxirem.model.Result;
import com.auxirem.payload.response.ResultResponse;
import com.auxirem.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping
    public ResponseEntity<List<ResultResponse>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ResultResponse>> getResultsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(resultService.getResultsByExam(examId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultResponse>> getResultsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResultResponse> createResult(@RequestBody Result result) {
        return ResponseEntity.ok(resultService.createResult(result));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
