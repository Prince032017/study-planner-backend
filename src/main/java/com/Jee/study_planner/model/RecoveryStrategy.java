package com.Jee.study_planner.model;

import java.util.List;

public class RecoveryStrategy extends Strategy{

    private long recoveryWeeks;

    private int revisionFrequency;

    private List<String> priorityTopics;

    private String recoveryIntensity;

    private List<String> topicsToSkip;

    public long getRecoveryWeeks() {
        return recoveryWeeks;
    }

    public void setRecoveryWeeks(long recoveryWeeks) {
        this.recoveryWeeks = recoveryWeeks;
    }

    public int getRevisionFrequency() {
        return revisionFrequency;
    }

    public void setRevisionFrequency(int revisionFrequency) {
        this.revisionFrequency = revisionFrequency;
    }


    public List<String> getPriorityTopics() {
        return priorityTopics;
    }

    public void setPriorityTopics(List<String> priorityTopics) {
        this.priorityTopics = priorityTopics;
    }


    public String getRecoveryIntensity() {
        return recoveryIntensity;
    }

    public void setRecoveryIntensity(String recoveryIntensity) {
        this.recoveryIntensity = recoveryIntensity;
    }

    public List<String> getTopicsToSkip() {
        return topicsToSkip;
    }

    public void setTopicsToSkip(List<String> topicsToSkip) {
        this.topicsToSkip = topicsToSkip;
    }
}