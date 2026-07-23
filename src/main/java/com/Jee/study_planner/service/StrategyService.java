package com.Jee.study_planner.service;
import com.Jee.study_planner.model.StudyPlanRequest;
import com.Jee.study_planner.model.StudyStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {

    public StudyStrategy build(StudyPlanRequest request,
                               long daysRemaining,
                               long weeksRemaining,
                               long totalHours) {

        StudyStrategy strategy = new StudyStrategy();

        strategy.setFoundationWeeks(
                (long)Math.ceil(weeksRemaining * 0.5));

        strategy.setRevisionWeeks(
                (long)Math.ceil(weeksRemaining * 0.3));

        strategy.setMockWeeks(
                weeksRemaining
                        - strategy.getFoundationWeeks()
                        - strategy.getRevisionWeeks());

        strategy.setRevisionFrequency(3);

        strategy.setTotalAvailableHours(totalHours);

        strategy.setPrioritySubjects(request.getWeakSubjects());

        strategy.setFeasible(true);

        return strategy;
    }
}
