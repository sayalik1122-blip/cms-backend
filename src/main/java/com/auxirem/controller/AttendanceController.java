package com.auxirem.controller;

import com.auxirem.model.Attendance;
import com.auxirem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAll() {
        return attendanceService.getAllAttendance();
    }

    @PostMapping
    public Attendance create(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @PutMapping("/{id}")
    public Attendance update(@PathVariable Long id, @RequestBody Attendance attendance) {
        return attendanceService.updateAttendance(id, attendance);
    }

    @PatchMapping("/{id}")
    public Attendance patch(@PathVariable Long id, @RequestBody Attendance attendance) {
        // Simplified patch for status toggle
        Attendance existing = attendanceService.getAllAttendance().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
        if (attendance.getStatus() != null) existing.setStatus(attendance.getStatus());
        return attendanceService.updateAttendance(id, existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
