package com.Jee.study_planner.model;

import java.util.List;
import java.util.Map;

public class StudyStrategy  extends Strategy{

    private long foundationWeeks;
    private long revisionWeeks;
    private long mockWeeks;

    private Map<String, Integer> dailyHoursPerSubject;

    private List<String> prioritySubjects;

    private int revisionFrequency;


    public long getFoundationWeeks() {
        return foundationWeeks;
    }

    public void setFoundationWeeks(long foundationWeeks) {
        this.foundationWeeks = foundationWeeks;
    }

    public long getRevisionWeeks() {
        return revisionWeeks;
    }

    public void setRevisionWeeks(long revisionWeeks) {
        this.revisionWeeks = revisionWeeks;
    }

    public long getMockWeeks() {
        return mockWeeks;
    }

    public void setMockWeeks(long mockWeeks) {
        this.mockWeeks = mockWeeks;
    }

    public Map<String, Integer> getDailyHoursPerSubject() {
        return dailyHoursPerSubject;
    }

    public void setDailyHoursPerSubject(Map<String, Integer> dailyHoursPerSubject) {
        this.dailyHoursPerSubject = dailyHoursPerSubject;
    }

    public List<String> getPrioritySubjects() {
        return prioritySubjects;
    }

    public void setPrioritySubjects(List<String> prioritySubjects) {
        this.prioritySubjects = prioritySubjects;
    }

    public int getRevisionFrequency() {
        return revisionFrequency;
    }

    public void setRevisionFrequency(int revisionFrequency) {
        this.revisionFrequency = revisionFrequency;
    }

}