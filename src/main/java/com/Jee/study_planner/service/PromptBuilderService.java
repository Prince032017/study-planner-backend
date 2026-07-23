package com.Jee.study_planner.service;

import com.Jee.study_planner.model.*;
import org.springframework.stereotype.Service;


@Service
public class PromptBuilderService {

    public String buildStudyPlanPrompt(StudyContext<StudyPlanRequest, StudyStrategy> context) {

        StudyPlanRequest request = context.getStudentRequest();
        StudyStrategy strategy = context.getStrategy();

        return String.format("""
        STUDENT PROFILE
        ----------------
        - Exam: %s
        - Exam Date: %s
        - Days Remaining: %d days (%d weeks)
        - Daily Study Hours: %s hours
        - Total Hours Available: %d hours
        - Weak Subjects: %s
        - Number of Weak Subjects: %d

        STUDY STRATEGY
        ----------------
        - Foundation Phase: %d weeks
        - Revision Phase: %d weeks
        - Mock Test Phase: %d weeks
        - Revision Frequency: Every %d days

        WEAK SUBJECT RULES (MANDATORY)
        ----------------
        1. Allocate 40%% MORE daily study time to weak subjects (%s).
        2. Start from HIGH-WEIGHTAGE chapters, never Chapter 1 by default.
        3. Mention the TOP 3 scoring chapters for every weak subject.
        4. Every %d day must include revision of weak subjects.
        5. During the foundation phase (%d weeks), weak subjects must always
           be scheduled before strong subjects.
        6. If available study hours are insufficient, prioritize only
           high ROI chapters.

        PLAN STRUCTURE
        ----------------
        Phase 1 (%d weeks)
        - Build strong fundamentals
        - Cover only high-weightage chapters first
        - No mock tests
        - Daily chapter practice

        Phase 2 (%d weeks)
        - Begin Previous Year Questions (PYQs)
        - Timed practice
        - Revision every %d days

        Phase 3 (%d weeks)
        - Daily Full Mock Tests
        - Analyze mistakes
        - Revise weak chapters every 2 days
        - No new topics

        FOR EVERY WEEK INCLUDE
        ----------------
        • Week Theme
        • Exact daily hour allocation for every subject
        • Exact chapter names
        • Weekly goal
        • Weekly revision target
        • Recovery strategy if one study day is missed

        FINAL SECTION
        ----------------
        WARNING SIGNS
        - Mention 3 indicators that the student is falling behind.
        - Give one corrective action for each indicator.
        - Mention which topics should be skipped first if time becomes insufficient.

        IMPORTANT
        ----------------
        - Use only official %s syllabus chapter names.
        - Never invent chapter names.
        - Never give generic advice.
        - Every available study hour must be allocated.
        - Prioritize marks over syllabus completion whenever necessary.
        """,

                request.getExamName(),
                request.getExamDate(),

                context.getDaysRemaining(),
                context.getWeeksRemaining(),

                request.getHoursPerDay(),

                context.getTotalHours(),

                String.join(", ", request.getWeakSubjects()),

                request.getWeakSubjects().size(),

                strategy.getFoundationWeeks(),
                strategy.getRevisionWeeks(),
                strategy.getMockWeeks(),
                strategy.getRevisionFrequency(),

                String.join(", ", request.getWeakSubjects()),

                strategy.getRevisionFrequency(),

                strategy.getFoundationWeeks(),

                strategy.getFoundationWeeks(),

                strategy.getRevisionWeeks(),

                strategy.getRevisionFrequency(),

                strategy.getMockWeeks(),

                request.getExamName()
        );
    }
    // ─── Build Recovery Prompt ─────────────────────────────────────────────────
    public String buildRecoveryPrompt(StudyContext<RecoveryPlanRequest, RecoveryStrategy> context) {

        RecoveryPlanRequest request = context.getStudentRequest();
        RecoveryStrategy strategy =  context.getStrategy();

        return String.format("""
RECOVERY PLAN REQUEST
=====================

STUDENT PROFILE
--------------------
- Exam: %s
- Exam Date: %s
- Days Remaining: %d days (%d weeks)
- Daily Study Hours: %d hours
- Total Available Study Hours: %d hours

RECOVERY DETAILS
--------------------
- Days Missed: %d
- Topics Not Completed: %s

RECOVERY OBJECTIVE
--------------------
Create the most realistic catch-up strategy while maximizing marks.

Recover the student's progress without causing burnout.

If completing the full syllabus is unrealistic, optimize for maximum score instead of maximum syllabus coverage.

RECOVERY RULES (MANDATORY)
--------------------
1. Begin with ONE encouraging sentence.
2. Do NOT make the student feel guilty.
3. Redistribute unfinished topics across the remaining %d days.
4. Prioritize the highest-weightage missed chapters from the official %s syllabus.
5. If any missed topic has prerequisite chapters, complete those prerequisites first before continuing.
6. If the syllabus cannot realistically be completed, explicitly identify:
   - Topics to Skip
   - Topics to Postpone
   - Topics that are Non-Negotiable
7. Allocate study hours realistically without overloading any day.
8. Schedule revision after every %d study days.
9. Reserve one buffer session every week for unexpected delays.

RECOVERY PLAN FORMAT
--------------------
For EVERY remaining week provide:

• Week Number
• Weekly Goal
• Chapters to Finish
• Daily Study Hour Split
• Revision Plan
• Previous Year Question (PYQ) Practice
• Buffer Session
• Expected Completion Percentage
• Catch-up Strategy if the week is not completed

START TODAY
--------------------
Before Week 1, provide a "Start Today" section explaining exactly what the student should study on Day 1 with hour-wise allocation.

WARNING SECTION
--------------------
Provide:

- Three warning signs that indicate the student is falling behind again.
- Immediate corrective action to take within the next 24 hours for each warning sign.

NON-NEGOTIABLE DAILY HABITS
--------------------
List the three daily habits the student must never skip.

IMPORTANT
--------------------
Optimize for marks rather than syllabus completion.
Be realistic.
Use official %s syllabus chapter names only.
Never invent chapter names.
Avoid generic motivational advice.
""",
                request.getExamName(),
                request.getExamDate(),
                context.getDaysRemaining(),
                context.getWeeksRemaining(),
                request.getHoursPerDay(),
                context.getTotalHours(),

                request.getDaysMissed(),
                String.join(", ", request.getMissedTopics()),

                context.getDaysRemaining(),
                request.getExamName(),
                strategy.getRevisionFrequency(),
                request.getExamName()

        );
    }
}
