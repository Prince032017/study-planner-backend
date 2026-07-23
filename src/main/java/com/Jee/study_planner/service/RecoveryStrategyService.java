package com.Jee.study_planner.service;

import com.Jee.study_planner.model.RecoveryPlanRequest;
import com.Jee.study_planner.model.RecoveryStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryStrategyService {

    public RecoveryStrategy build(
            RecoveryPlanRequest request,
            long daysRemaining,
            long weeksRemaining,
            long totalHours) {

        RecoveryStrategy strategy = new RecoveryStrategy();

        strategy.setRecoveryWeeks(weeksRemaining);

        strategy.setRevisionFrequency(2);

        strategy.setTotalAvailableHours(totalHours);

        strategy.setPriorityTopics(request.getMissedTopics());

        strategy.setFeasible(true);

        strategy.setRecoveryIntensity("MEDIUM");

        strategy.setTopicsToSkip(List.of());

        return strategy;
    }
}