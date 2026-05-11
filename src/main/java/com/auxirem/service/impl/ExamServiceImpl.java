package com.auxirem.service.impl;

import com.auxirem.model.Exam;
import com.auxirem.payload.response.ExamResponse;
import com.auxirem.repository.ExamRepository;
import com.auxirem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public List<ExamResponse> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponse getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return mapToResponse(exam);
    }

    @Override
    public ExamResponse createExam(Exam exam) {
        return mapToResponse(examRepository.save(exam));
    }

    @Override
    public ExamResponse updateExam(Long id, Exam examDetails) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        exam.setSubject(examDetails.getSubject());
        exam.setDate(examDetails.getDate());
        exam.setTime(examDetails.getTime());
        exam.setHall(examDetails.getHall());
        exam.setDuration(examDetails.getDuration());
        exam.setTotalMarks(examDetails.getTotalMarks());
        exam.setStatus(examDetails.getStatus());
        return mapToResponse(examRepository.save(exam));
    }

    @Override
    public ExamResponse patchExam(Long id, Map<String, Object> updates) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        
        updates.forEach((key, value) -> {
            switch (key) {
                case "status":
                    exam.setStatus((String) value);
                    break;
                case "subject":
                    exam.setSubject((String) value);
                    break;
                case "date":
                    exam.setDate((String) value);
                    break;
                case "time":
                    exam.setTime((String) value);
                    break;
                case "hall":
                    exam.setHall((String) value);
                    break;
                case "duration":
                    exam.setDuration((String) value);
                    break;
                case "totalMarks":
                    exam.setTotalMarks((String) value);
                    break;
            }
        });
        
        return mapToResponse(examRepository.save(exam));
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    private ExamResponse mapToResponse(Exam exam) {
        ExamResponse response = new ExamResponse();
        response.setId(exam.getId());
        response.setSubject(exam.getSubject());
        response.setDate(exam.getDate());
        response.setTime(exam.getTime());
        response.setHall(exam.getHall());
        response.setDuration(exam.getDuration());
        response.setTotalMarks(exam.getTotalMarks());
        response.setStatus(exam.getStatus());
        return response;
    }
}
