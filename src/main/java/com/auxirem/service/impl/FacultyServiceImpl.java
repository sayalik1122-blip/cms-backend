package com.auxirem.service.impl;

import com.auxirem.model.Faculty;
import com.auxirem.payload.response.FacultyResponse;
import com.auxirem.repository.FacultyRepository;
import com.auxirem.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public List<FacultyResponse> getAllFaculty() {
        return facultyRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResponse getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        return mapToResponse(faculty);
    }

    @Override
    public FacultyResponse createFaculty(Faculty faculty) {
        return mapToResponse(facultyRepository.save(faculty));
    }

    @Override
    public FacultyResponse updateFaculty(Long id, Faculty facultyDetails) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        faculty.setName(facultyDetails.getName());
        faculty.setDepartment(facultyDetails.getDepartment());
        faculty.setExperience(facultyDetails.getExperience());
        faculty.setEmail(facultyDetails.getEmail());
        faculty.setSubjects(facultyDetails.getSubjects());
        faculty.setPhone(facultyDetails.getPhone());
        return mapToResponse(facultyRepository.save(faculty));
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    private FacultyResponse mapToResponse(Faculty faculty) {
        FacultyResponse response = new FacultyResponse();
        response.setId(faculty.getId());
        response.setDisplayId(faculty.getId() != null ? String.format("F%03d", faculty.getId()) : null);
        response.setName(faculty.getName());
        response.setDepartment(faculty.getDepartment());
        response.setExperience(faculty.getExperience());
        response.setEmail(faculty.getEmail());
        response.setSubjects(faculty.getSubjects());
        response.setPhone(faculty.getPhone());
        return response;
    }
}
