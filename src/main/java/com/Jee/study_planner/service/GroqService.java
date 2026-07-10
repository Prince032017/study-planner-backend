package com.Jee.study_planner.service;

import com.Jee.study_planner.model.PlanResponse;
import com.Jee.study_planner.model.StudentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public GroqService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    public PlanResponse generateStudyPlan(StudentRequest request){
        try{
            LocalDate today = LocalDate.now();
            LocalDate examDate = LocalDate.parse(request.getExamDate());
            long daysRemaining = ChronoUnit.DAYS.between(today,examDate);
            long weeksRemaining = (long) Math.ceil(daysRemaining/ 7.0);
            long totalHours = daysRemaining * Long.parseLong(request.getHoursPerDay());

            String prompt = buildStudyPlanPrompt(request, daysRemaining, weeksRemaining, totalHours);

            String plan = callGroqApi(
                    prompt,
                    "You are an expert JEE/NEET coach who has helped thousands " +
                    "of Indian students crack competitive exams. " +
                    "You give specific, actionable, realistic advice. " +
                    "You always mention actual chapter names from the syllabus. " +
                    "You understand that Indian students face real life disruptions " +
                    "and your plans account for that."
            );

            return new PlanResponse(
                    plan,
                    request.getExamName(),
                    daysRemaining,
                    weeksRemaining
            );
        }catch(Exception e){
            return new PlanResponse("Failed to generate study plan: " + e.getMessage());
        }
    }

    //Recovery Plan
    public PlanResponse generateRecoveryPlan(StudentRequest request){
        try{
            LocalDate today = LocalDate.now();
            LocalDate examDate = LocalDate.parse(request.getExamDate());
            long daysRemaining = ChronoUnit.DAYS.between(today, examDate);
            long weeksRemaining = (long) Math.ceil(daysRemaining/7.0);
            String prompt = buildRecoveryPrompt(request, daysRemaining);

            String plan = callGroqApi(
                    prompt,
                    "You are a no-nonsense JEE/NEET recovery coach. " +
                    "You are honest about what is achievable. " +
                    "You never give false hope but always give a realistic path forward. " +
                    "You understand Indian students and their exam pressures deeply."
            );
            return new PlanResponse(
                    plan,
                    request.getExamName(),
                    daysRemaining,
                    weeksRemaining
            );
        } catch (Exception e) {
            return new PlanResponse("Failed to generate recovery plan: "+ e.getMessage());
        }
    }

    private String callGroqApi(String userPrompt, String systemPrompt){
        Map<String,Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userPrompt)
                ),
                "max_tokens", 4096,
                "temperature", 0.7
        );

        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map> choices = (List<Map>) response.get("choices");
        Map message = (Map) choices.get(0).get("message");
        return (String) message.get("content");
    }

    private String buildStudyPlanPrompt(StudentRequest request,
                                        long daysRemaining,
                                        long weeksRemaining,
                                        long totalHours) {
        return String.format("""
            Student details:
            - Exam: %s
            - Exam date: %s
            - Days remaining until exam: %d days (%d weeks)
            - Study hours available per day: %s hours
            - Weak subjects: %s
            - Total study hours available: %d hours
            
            Create a %d-week study plan that:
            
            1. PRIORITISATION STRATEGY:
               - First %d weeks: Cover full syllabus,
                 spend 40%% more time on weak subjects (%s)
               - Next %d weeks: Revision cycles,
                 start previous year questions (PYQ)
               - Last %d weeks: Full mock tests daily,
                 only revision — no new topics
            
            2. For each week provide:
               - Week number and theme
               - Daily hour breakdown per subject
               - Specific topics to cover that week
               - Weekly target
               - One recovery tip if they miss a day
            
            3. Be specific to %s syllabus — mention actual chapter names
            
            4. End with "Warning Signs" section —
               3 signs the student is falling behind
               and exactly what to do when that happens
            
            Format with clear Week headings. Be specific and realistic.
            """,
                request.getExamName(),
                request.getExamDate(),
                daysRemaining, weeksRemaining,
                request.getHoursPerDay(),
                request.getWeakSubject(),
                totalHours,
                weeksRemaining,
                (long) Math.ceil(weeksRemaining * 0.5), request.getWeakSubject(),
                (long) Math.ceil(weeksRemaining * 0.3),
                (long) Math.ceil(weeksRemaining * 0.2),
                request.getExamName()
        );
    }

    // ─── Build Recovery Prompt ─────────────────────────────────────────────────
    private String buildRecoveryPrompt(StudentRequest request, long daysRemaining) {
        return String.format("""
            A student has fallen behind and needs a realistic catch-up plan.
            
            Situation:
            - Exam: %s
            - Exam date: %s
            - Days remaining until exam: %d days
            - Days they missed studying: %s days
            - Topics NOT completed: %s
            - Study hours available per day going forward: %s hours
            
            IMPORTANT RULES:
            1. Do NOT make the student feel guilty.
               Open with ONE encouraging sentence.
            2. Be brutally realistic about what is achievable.
            3. Redistribute missed topics into remaining %d days.
            4. Prioritise missed topics by %s exam weightage.
            5. If some topics cannot be covered, explicitly say which to SKIP.
            6. Include a Daily Schedule showing how to split %s hours per day.
            7. Include a Warning section — if they miss even ONE more day,
               what should they cut?
            8. End with The Non-Negotiables —
               3 things they must do every single day no matter what.
            
            Format with clear headings. Use tables for daily schedule.
            Be specific with chapter names.
            """,
                request.getExamName(),
                request.getExamDate(),
                daysRemaining,
                request.getDaysMissed(),
                request.getMissedTopics(),
                request.getHoursPerDay(),
                daysRemaining,
                request.getExamName(),
                request.getHoursPerDay()
        );
    }
}
