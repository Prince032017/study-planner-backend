package com.Jee.study_planner.controller;

import com.Jee.study_planner.model.PlanResponse;
import com.Jee.study_planner.model.RecoveryPlanRequest;
import com.Jee.study_planner.model.StudyPlanRequest;
import com.Jee.study_planner.service.RecoveryPlanService;
import com.Jee.study_planner.service.StudyPlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudyPlanController {
    private final StudyPlanService studyPlanService;
    private final RecoveryPlanService recoveryPlanService;

    // Constructor injection — best practice over @Autowired
    public StudyPlanController(
            StudyPlanService studyPlanService,
            RecoveryPlanService recoveryPlanService
    ){

        this.studyPlanService = studyPlanService;
        this.recoveryPlanService = recoveryPlanService;
    }

    // Health check — test this first
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Study Planner API is running");
    }

    // Generate study plan
    @PostMapping("/study-plan")
    public ResponseEntity<PlanResponse> generateStudyPlan(@Valid @RequestBody StudyPlanRequest request){
        PlanResponse response = studyPlanService.generateStudyPlan(request);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().body(response);
    }

    @PostMapping("/recovery-plan")
    public ResponseEntity<PlanResponse> generateRecoveryPlan(@Valid @RequestBody RecoveryPlanRequest request){
        PlanResponse response = recoveryPlanService.generateRecoveryPlan(request);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().body(response);
    }
}
