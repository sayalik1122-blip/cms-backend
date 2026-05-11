package com.auxirem.controller;

import com.auxirem.model.Fee;
import com.auxirem.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {
    private final FeeRepository repository;

    @GetMapping
    public List<Fee> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Fee create(@RequestBody Fee fee) {
        return repository.save(fee);
    }

    @PutMapping("/{id}")
    public Fee update(@PathVariable Long id, @RequestBody Fee fee) {
        fee.setId(id);
        return repository.save(fee);
    }

    @PatchMapping("/{id}")
    public Fee patch(@PathVariable Long id, @RequestBody Fee fee) {
        Fee existing = repository.findById(id).orElseThrow();
        if (fee.getStatus() != null) existing.setStatus(fee.getStatus());
        if (fee.getTransactionId() != null) existing.setTransactionId(fee.getTransactionId());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
