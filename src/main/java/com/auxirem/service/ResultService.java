package com.auxirem.service;

import com.auxirem.payload.response.ResultResponse;
import com.auxirem.model.Result;
import java.util.List;

public interface ResultService {
    List<ResultResponse> getAllResults();
    List<ResultResponse> getResultsByExam(Long examId);
    List<ResultResponse> getResultsByStudent(Long studentId);
    ResultResponse createResult(Result result);
    void deleteResult(Long id);
}
