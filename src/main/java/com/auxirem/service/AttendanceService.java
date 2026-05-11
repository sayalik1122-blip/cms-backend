package com.auxirem.service;

import com.auxirem.model.Attendance;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendance();
    Attendance markAttendance(Attendance attendance);
    Attendance updateAttendance(Long id, Attendance attendance);
    void deleteAttendance(Long id);
}
