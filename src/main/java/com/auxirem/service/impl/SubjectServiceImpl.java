package com.auxirem.service.impl;

import com.auxirem.model.Faculty;
import com.auxirem.model.Subject;
import com.auxirem.payload.request.SubjectRequest;
import com.auxirem.payload.response.SubjectResponse;
import com.auxirem.repository.FacultyRepository;
import com.auxirem.repository.SubjectRepository;
import com.auxirem.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        return mapToResponse(subject);
    }

    @Override
    public SubjectResponse createSubject(SubjectRequest request) {
        Subject subject = new Subject();
        updateSubjectFields(subject, request);
        return mapToResponse(subjectRepository.save(subject));
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectRequest request) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        updateSubjectFields(subject, request);
        return mapToResponse(subjectRepository.save(subject));
    }

    private void updateSubjectFields(Subject subject, SubjectRequest request) {
        subject.setName(request.getName());
        subject.setCode(request.getCode());
        subject.setSemester(request.getSemester());
        subject.setCredits(request.getCredits());
        if (request.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            subject.setFaculty(faculty);
        }
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectResponse> getSubjectsByFaculty(Long facultyId) {
        return subjectRepository.findByFacultyId(facultyId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SubjectResponse mapToResponse(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setDisplayId(subject.getId() != null ? String.format("SUB%03d", subject.getId()) : null);
        response.setName(subject.getName());
        response.setCode(subject.getCode());
        response.setSemester(subject.getSemester());
        response.setCredits(subject.getCredits());
        response.setFaculty(subject.getFaculty() != null ? subject.getFaculty().getName() : null);
        return response;
    }
}
