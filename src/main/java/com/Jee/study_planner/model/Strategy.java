package com.Jee.study_planner.model;

public abstract class Strategy {

    private boolean feasible;

    private long totalAvailableHours;

    public boolean isFeasible() {
        return feasible;
    }

    public void setFeasible(boolean feasible) {
        this.feasible = feasible;
    }

    public long getTotalAvailableHours() {
        return totalAvailableHours;
    }

    public void setTotalAvailableHours(long totalAvailableHours) {
        this.totalAvailableHours = totalAvailableHours;
    }
}