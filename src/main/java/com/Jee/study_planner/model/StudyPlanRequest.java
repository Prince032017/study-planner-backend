package com.Jee.study_planner.model;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class StudyPlanRequest extends BaseStudentRequest {

    @NotEmpty(message = "At least one weak subject is required")
    private List<String> weakSubjects;

    public List<String> getWeakSubjects() {
        return weakSubjects;
    }

    public void setWeakSubjects(List<String> weakSubjects) {
        this.weakSubjects = weakSubjects;
    }
}