package com.tatastrive.app.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.*;
@Service
public interface QuizService {
    QuizDTO createQuiz(QuizDTO quizDTO);
    QuizDTO getQuizById(Long id);
    List<QuizDTO> getQuizzesByCreator(Long creatorId);
    List<QuizDTO> getAllQuizzes();
    QuizDTO updateQuiz(Long id, QuizDTO quizDTO);
    void deleteQuiz(Long id);
}
