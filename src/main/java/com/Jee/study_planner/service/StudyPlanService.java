package com.Jee.study_planner.service;

import com.Jee.study_planner.constants.PromptConstants;
import com.Jee.study_planner.model.*;
import org.springframework.stereotype.Service;

@Service
public class StudyPlanService {

    private final PromptBuilderService promptBuilder;
    private final GroqClient groqClient;
    private final StudyContextBuilder studyContextBuilder;

    public StudyPlanService(
            PromptBuilderService promptBuilder,
            GroqClient groqClient,
            StudyContextBuilder studyContextBuilder
    ) {
        this.promptBuilder = promptBuilder;
        this.groqClient = groqClient;
        this.studyContextBuilder = studyContextBuilder;
    }

    public PlanResponse generateStudyPlan(StudyPlanRequest request) {

        try {

            StudyContext<StudyPlanRequest, StudyStrategy>context = studyContextBuilder.build(request);

            String prompt = promptBuilder.buildStudyPlanPrompt(context);

            String plan = groqClient.generate(
                    PromptConstants.STUDY_PLAN_SYSTEM_PROMPT,
                    prompt
            );

            return new PlanResponse(
                    plan,
                    request.getExamName(),
                    context.getDaysRemaining(),
                    context.getWeeksRemaining()
            );

        } catch (Exception e) {

            return new PlanResponse(
                    "Failed to generate study plan: " + e.getMessage()
            );
        }
    }
}