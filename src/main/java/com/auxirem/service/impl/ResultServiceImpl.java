package com.auxirem.service.impl;

import com.auxirem.model.Result;
import com.auxirem.payload.response.ResultResponse;
import com.auxirem.repository.ResultRepository;
import com.auxirem.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public List<ResultResponse> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultResponse> getResultsByExam(Long examId) {
        return resultRepository.findByExamId(examId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultResponse> getResultsByStudent(Long studentId) {
        return resultRepository.findByStudentId(studentId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResultResponse createResult(Result result) {
        result.setMarkedAt(LocalDateTime.now());
        return mapToResponse(resultRepository.save(result));
    }

    @Override
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    private ResultResponse mapToResponse(Result result) {
        ResultResponse response = new ResultResponse();
        response.setId(result.getId());
        response.setExamId(result.getExam() != null ? result.getExam().getId() : null);
        response.setStudentId(result.getStudent() != null ? String.format("S%03d", result.getStudent().getId()) : null);
        response.setStudentName(result.getStudentName());
        response.setSubject(result.getSubject());
        response.setMarks(result.getMarks());
        response.setTotalMarks(result.getTotalMarks());
        response.setGrade(result.getGrade());
        response.setPercentage(result.getPercentage());
        response.setMarkedAt(result.getMarkedAt());
        return response;
    }
}
