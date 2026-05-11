package com.auxirem.controller;

import com.auxirem.model.TimetableSlot;
import com.auxirem.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimetableController {
    private final TimetableRepository repository;

    @GetMapping
    public List<TimetableSlot> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TimetableSlot create(@RequestBody TimetableSlot slot) {
        return repository.save(slot);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TimetableSlot update(@PathVariable Long id, @RequestBody TimetableSlot slot) {
        slot.setId(id);
        return repository.save(slot);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
