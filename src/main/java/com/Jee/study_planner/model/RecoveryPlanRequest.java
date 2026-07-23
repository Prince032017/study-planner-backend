package com.Jee.study_planner.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class RecoveryPlanRequest extends BaseStudentRequest {

    @Min(value = 1, message = "Days missed must be greater than zero")
    private int daysMissed;

    @NotEmpty(message = "Please provide missed topics")
    private List<String> missedTopics;

    public int getDaysMissed() {
        return daysMissed;
    }

    public void setDaysMissed(int daysMissed) {
        this.daysMissed = daysMissed;
    }

    public List<String> getMissedTopics() {
        return missedTopics;
    }

    public void setMissedTopics(List<String> missedTopics) {
        this.missedTopics = missedTopics;
    }
}