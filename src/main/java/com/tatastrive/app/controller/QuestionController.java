package com.tatastrive.app.controller;

import com.tatastrive.app.dto.QuestionDTO;
import com.tatastrive.app.response.ApiResponse;
import com.tatastrive.app.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Add a question to a quiz
     */
    @PostMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<ApiResponse<QuestionDTO>> addQuestionToQuiz(
            @PathVariable Long quizId,
            @RequestBody QuestionDTO questionDTO) {

        QuestionDTO createdQuestion = questionService.addQuestionToQuiz(quizId, questionDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Question added successfully", createdQuestion));
    }

    /**
     * Get a question by ID
     */
    @GetMapping("/questions/{id}")
    public ResponseEntity<ApiResponse<QuestionDTO>> getQuestionById(@PathVariable Long id) {
        QuestionDTO question = questionService.getQuestionById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Question found", question));
    }

    /**
     * Get all questions for a specific quiz
     */
    @GetMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<ApiResponse<List<QuestionDTO>>> getQuestionsByQuizId(@PathVariable Long quizId) {
        List<QuestionDTO> questions = questionService.getQuestionsByQuizId(quizId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Questions retrieved successfully", questions));
    }

    /**
     * Delete a question by ID
     */
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Question deleted successfully", null));
    }
}

