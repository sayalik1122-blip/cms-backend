package com.auxirem.service;

import com.auxirem.model.Exam;
import com.auxirem.payload.response.ExamResponse;
import java.util.List;
import java.util.Map;

public interface ExamService {
    List<ExamResponse> getAllExams();
    ExamResponse getExamById(Long id);
    ExamResponse createExam(Exam exam);
    ExamResponse updateExam(Long id, Exam exam);
    ExamResponse patchExam(Long id, Map<String, Object> updates);
    void deleteExam(Long id);
}
