package com.Jee.study_planner.model;

public class StudentRequest {
    private String examName;
    private String examDate;
    private String hoursPerDay;
    private String weakSubject;

    private String daysMissed;
    private String missedTopics;
    private boolean isRecovery;

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

    public String getWeakSubject() {
        return weakSubject;
    }

    public void setWeakSubject(String weakSubject) {
        this.weakSubject = weakSubject;
    }

    public String getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(String hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public String getDaysMissed() {
        return daysMissed;
    }

    public void setDaysMissed(String daysMissed) {
        this.daysMissed = daysMissed;
    }

    public String getMissedTopics() {
        return missedTopics;
    }

    public void setMissedTopics(String missedTopics) {
        this.missedTopics = missedTopics;
    }

    public boolean isRecovery() {
        return isRecovery;
    }

    public void setRecovery(boolean recovery) {
        isRecovery = recovery;
    }
}
