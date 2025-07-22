package com.tatastrive.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.GameQuestionDTO;
import com.tatastrive.app.entity.GameQuestion;
import com.tatastrive.app.entity.GameSession;
import com.tatastrive.app.entity.Question;
import com.tatastrive.app.exception.ResourceNotFoundException;
import com.tatastrive.app.repository.GameQuestionRepository;
import com.tatastrive.app.repository.GameSessionRepository;
import com.tatastrive.app.repository.QuestionRepository;

@Service
public class GameQuestionServiceImpl implements GameQuestionService {

    @Autowired
    private GameQuestionRepository gameQuestionRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GameQuestionDTO launchQuestion(Long sessionId, Long questionId) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with ID: " + sessionId));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        Long orderIndex = gameQuestionRepository.countByGameSessionId(sessionId) + 1;

        GameQuestion gameQuestion = new GameQuestion();
        gameQuestion.setGameSession(session);
        gameQuestion.setQuestion(question);
        gameQuestion.setOrderIndex(orderIndex);
        gameQuestion.setAskedAt(LocalDateTime.now());

        gameQuestion = gameQuestionRepository.save(gameQuestion);
        return modelMapper.map(gameQuestion, GameQuestionDTO.class);
    }
    @Override
    public List<GameQuestionDTO> getQuestionsBySession(Long sessionId) {
        List<GameQuestion> gameQuestions = gameQuestionRepository.findByGameSessionId(sessionId);
        return gameQuestions.stream()
                .map(gq -> modelMapper.map(gq, GameQuestionDTO.class))
                .collect(Collectors.toList());
    }
}
