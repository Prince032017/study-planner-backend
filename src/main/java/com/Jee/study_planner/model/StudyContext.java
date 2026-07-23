package com.Jee.study_planner.model;

public class StudyContext<
        R extends BaseStudentRequest,
        S extends Strategy> {

    private R studentRequest;

    private S strategy;

    private long daysRemaining;
    private long weeksRemaining;
    private long totalHours;

    public R getStudentRequest() {
        return studentRequest;
    }

    public void setStudentRequest(R studentRequest) {
        this.studentRequest = studentRequest;
    }

    public S getStrategy() {
        return strategy;
    }

    public void setStrategy(S strategy) {
        this.strategy = strategy;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(long daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public long getWeeksRemaining() {
        return weeksRemaining;
    }

    public void setWeeksRemaining(long weeksRemaining) {
        this.weeksRemaining = weeksRemaining;
    }

    public long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }
}