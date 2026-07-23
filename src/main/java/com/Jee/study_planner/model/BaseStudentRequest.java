package com.Jee.study_planner.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BaseStudentRequest {

    @NotBlank(message = "Exam name is required")
    private String examName;

    @NotBlank(message = "Exam date is required")
    private String examDate;

    @Min(value = 1, message = "Hours per day must be at least 1")
    private int hoursPerDay;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }
}