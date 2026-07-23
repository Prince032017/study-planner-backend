package com.Jee.study_planner.service;

import com.Jee.study_planner.constants.PromptConstants;
import com.Jee.study_planner.model.PlanResponse;
import com.Jee.study_planner.model.RecoveryPlanRequest;
import com.Jee.study_planner.model.RecoveryStrategy;
import com.Jee.study_planner.model.StudyContext;
import org.springframework.stereotype.Service;

@Service
public class RecoveryPlanService {
    private final PromptBuilderService promptBuilder;
    private final GroqClient groqClient;
    private final StudyContextBuilder studyContextBuilder;

    public RecoveryPlanService(
            PromptBuilderService promptBuilder,
            GroqClient groqClient,
            StudyContextBuilder studyContextBuilder
    ){
        this.promptBuilder = promptBuilder;
        this.groqClient = groqClient;
        this.studyContextBuilder = studyContextBuilder;
    }

    public PlanResponse generateRecoveryPlan(RecoveryPlanRequest request){
        try{
            StudyContext<RecoveryPlanRequest, RecoveryStrategy> context = studyContextBuilder.build(request);
            String prompt = promptBuilder.buildRecoveryPrompt(context);

            String plan = groqClient.generate(
                    PromptConstants.RECOVERY_SYSTEM_PROMPT,
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
                    "Unable to generate recovery plan. Please try again later."
            );
        }
    }
}
