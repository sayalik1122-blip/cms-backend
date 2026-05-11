package com.auxirem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalyticsController {

    @GetMapping("/chartData")
    public Map<String, Object> getChartData() {
        Map<String, Object> data = new HashMap<>();

        // Enrollment trend (dummy data)
        List<Map<String, Object>> studentGrowth = Arrays.asList(
                createPoint("Jan", 120),
                createPoint("Feb", 150),
                createPoint("Mar", 180),
                createPoint("Apr", 210),
                createPoint("May", 250)
        );

        // Department distribution (dummy data)
        List<Map<String, Object>> departmentDistribution = Arrays.asList(
                createValPoint("Computer Science", 450),
                createValPoint("Mechanical", 300),
                createValPoint("Civil", 200),
                createValPoint("Electrical", 250)
        );

        data.put("studentGrowth", studentGrowth);
        data.put("departmentDistribution", departmentDistribution);

        return data;
    }

    private Map<String, Object> createPoint(String name, int count) {
        Map<String, Object> p = new HashMap<>();
        p.put("name", name);
        p.put("students", count);
        return p;
    }

    private Map<String, Object> createValPoint(String name, int value) {
        Map<String, Object> p = new HashMap<>();
        p.put("name", name);
        p.put("value", value);
        return p;
    }
}
