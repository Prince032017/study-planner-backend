package com.Jee.study_planner.model;

public class PlanResponse {
    private String plan;
    private String examName;
    private long daysRemaining;
    private long weeksRemaining;
    private boolean success;
    private String errorMessage;

    //Construtor for success
    public PlanResponse(String plan, String examName, long daysRemaining, long weeksRemaining){
        this.plan = plan;
        this.examName = examName;
        this.daysRemaining = daysRemaining;
        this.weeksRemaining = weeksRemaining;
        this.success = true;
    }

    // Constructor for error
    public PlanResponse(String errorMessage){
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
