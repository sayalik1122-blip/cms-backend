package com.auxirem.service;

import com.auxirem.payload.request.SubjectRequest;
import com.auxirem.payload.response.SubjectResponse;
import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getAllSubjects();
    SubjectResponse getSubjectById(Long id);
    SubjectResponse createSubject(SubjectRequest request);
    SubjectResponse updateSubject(Long id, SubjectRequest request);
    void deleteSubject(Long id);
    List<SubjectResponse> getSubjectsByFaculty(Long facultyId);
}
