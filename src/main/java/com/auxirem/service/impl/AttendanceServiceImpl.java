package com.auxirem.service.impl;

import com.auxirem.model.Attendance;
import com.auxirem.repository.AttendanceRepository;
import com.auxirem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance attendance) {
        attendance.setId(id);
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
