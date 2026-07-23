package com.Jee.study_planner.service;

import com.Jee.study_planner.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class StudyContextBuilder {

    private final StrategyService strategyService;
    private final RecoveryStrategyService recoveryStrategyService;

    public StudyContextBuilder(
            StrategyService strategyService,
             RecoveryStrategyService recoveryStrategyService
    ) {

        this.strategyService = strategyService;
        this.recoveryStrategyService = recoveryStrategyService;

    }

    public StudyContext<StudyPlanRequest, StudyStrategy> build(StudyPlanRequest request) {

        LocalDate today = LocalDate.now();
        LocalDate examDate = LocalDate.parse(request.getExamDate());

        long daysRemaining = ChronoUnit.DAYS.between(today, examDate);
        long weeksRemaining = (long) Math.ceil(daysRemaining / 7.0);
        long totalHours = daysRemaining * request.getHoursPerDay();

        StudyStrategy strategy = strategyService.build(
                request,
                daysRemaining,
                weeksRemaining,
                totalHours
        );

        StudyContext<StudyPlanRequest, StudyStrategy>  context = new StudyContext<>();

        context.setStudentRequest(request);
        context.setStrategy(strategy);
        context.setDaysRemaining(daysRemaining);
        context.setWeeksRemaining(weeksRemaining);
        context.setTotalHours(totalHours);

        return context;
    }


    public StudyContext<RecoveryPlanRequest, RecoveryStrategy> build(RecoveryPlanRequest request) {

        LocalDate today = LocalDate.now();
        LocalDate examDate = LocalDate.parse(request.getExamDate());

        long daysRemaining = ChronoUnit.DAYS.between(today, examDate);
        long weeksRemaining = (long) Math.ceil(daysRemaining / 7.0);
        long totalHours = daysRemaining * request.getHoursPerDay();

        RecoveryStrategy  strategy = recoveryStrategyService.build(
                request,
                daysRemaining,
                weeksRemaining,
                totalHours
        );

        StudyContext<RecoveryPlanRequest, RecoveryStrategy> context = new StudyContext<>();

        context.setStudentRequest(request);
        context.setStrategy(strategy);
        context.setDaysRemaining(daysRemaining);
        context.setWeeksRemaining(weeksRemaining);
        context.setTotalHours(totalHours);

        return context;
    }
}