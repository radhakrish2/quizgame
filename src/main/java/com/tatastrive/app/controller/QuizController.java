package com.tatastrive.app.controller;

import com.tatastrive.app.dto.QuizDTO;
import com.tatastrive.app.response.ApiResponse;
import com.tatastrive.app.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * Create a new quiz
     */
    @PostMapping
    public ResponseEntity<ApiResponse<QuizDTO>> createQuiz(@RequestBody QuizDTO quizDTO) {
        QuizDTO createdQuiz = quizService.createQuiz(quizDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz created successfully", createdQuiz));
    }

    /**
     * Get quiz by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuizDTO>> getQuizById(@PathVariable Long id) {
        QuizDTO quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz found", quiz));
    }

    /**
     * Get all quizzes
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<QuizDTO>>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(new ApiResponse<>(true, "All quizzes retrieved", quizzes));
    }

    /**
     * Get quizzes by creator (user ID)
     */
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<ApiResponse<List<QuizDTO>>> getQuizzesByCreator(@PathVariable Long creatorId) {
        List<QuizDTO> quizzes = quizService.getQuizzesByCreator(creatorId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quizzes by creator retrieved", quizzes));
    }

    /**
     * Update a quiz by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QuizDTO>> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO quizDTO) {
        QuizDTO updatedQuiz = quizService.updateQuiz(id, quizDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz updated successfully", updatedQuiz));
    }

    /**
     * Delete a quiz by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz deleted successfully", null));
    }
}
