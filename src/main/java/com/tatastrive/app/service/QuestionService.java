package com.tatastrive.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.*;
@Service
public interface QuestionService {
    QuestionDTO addQuestionToQuiz(Long quizId, QuestionDTO questionDTO);
    QuestionDTO getQuestionById(Long id);
    List<QuestionDTO> getQuestionsByQuizId(Long quizId);
    void deleteQuestion(Long id);
}