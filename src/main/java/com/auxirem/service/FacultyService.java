package com.auxirem.service;

import com.auxirem.model.Faculty;
import com.auxirem.payload.response.FacultyResponse;
import java.util.List;

public interface FacultyService {
    List<FacultyResponse> getAllFaculty();
    FacultyResponse getFacultyById(Long id);
    FacultyResponse createFaculty(Faculty faculty);
    FacultyResponse updateFaculty(Long id, Faculty faculty);
    void deleteFaculty(Long id);
}
