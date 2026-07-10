package com.Jee.study_planner.controller;

import com.Jee.study_planner.model.PlanResponse;
import com.Jee.study_planner.model.StudentRequest;
import com.Jee.study_planner.service.GroqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudyPlanController {
    private final GroqService groqService;

    // Constructor injection — best practice over @Autowired
    public StudyPlanController( GroqService groqService){
        this.groqService = groqService;
    }

    // Health check — test this first
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Study Planner API is running");
    }

    // Generate study plan
    @PostMapping("/study-plan")
    public ResponseEntity<PlanResponse> generateStudyPlan(@RequestBody StudentRequest request){
        PlanResponse response = groqService.generateStudyPlan(request);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().body(response);
    }

    @PostMapping("/recovery-plan")
    public ResponseEntity<PlanResponse> generateRecoveryPlan(@RequestBody StudentRequest request){
        PlanResponse response = groqService.generateRecoveryPlan(request);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().body(response);
    }
}
