package com.auxirem.controller;

import com.auxirem.model.Faculty;
import com.auxirem.payload.response.FacultyResponse;
import com.auxirem.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyResponse>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacultyResponse> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, faculty));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
