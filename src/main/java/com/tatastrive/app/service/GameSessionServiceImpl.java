package com.tatastrive.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.GameSessionDTO;
import com.tatastrive.app.entity.GameSession;
import com.tatastrive.app.entity.Quiz;
import com.tatastrive.app.entity.User;
import com.tatastrive.app.exception.ResourceNotFoundException;
import com.tatastrive.app.repository.GameSessionRepository;
import com.tatastrive.app.repository.QuizRepository;
import com.tatastrive.app.repository.UserRepository;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GameSessionDTO createGameSession(GameSessionDTO sessionDTO) {
        GameSession gameSession = new GameSession();
        gameSession.setSessionCode(sessionDTO.getSessionCode());
        gameSession.setStartedAt(LocalDateTime.now());
        gameSession.setActive(true);

        User host = userRepository.findById(sessionDTO.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with ID: " + sessionDTO.getHostId()));
        gameSession.setHost(host);

        Quiz quiz = quizRepository.findById(sessionDTO.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + sessionDTO.getQuizId()));
        gameSession.setQuiz(quiz);

        gameSession = gameSessionRepository.save(gameSession);
        return modelMapper.map(gameSession, GameSessionDTO.class);
    }

    @Override
    public GameSessionDTO startSession(String sessionCode) {
        GameSession session = gameSessionRepository.findBySessionCode(sessionCode)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with code: " + sessionCode));

        session.setActive(true);
        session.setStartedAt(LocalDateTime.now());
        session = gameSessionRepository.save(session);

        return modelMapper.map(session, GameSessionDTO.class);
    }

    @Override
    public GameSessionDTO getSessionByCode(String sessionCode) {
        GameSession session = gameSessionRepository.findBySessionCode(sessionCode)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with code: " + sessionCode));

        return modelMapper.map(session, GameSessionDTO.class);
    }

    @Override
    public List<GameSessionDTO> getSessionsByHost(Long hostId) {
        User host = userRepository.findById(hostId)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with ID: " + hostId));

        List<GameSession> sessions = gameSessionRepository.findByHost(host);
        return sessions.stream()
                .map(session -> modelMapper.map(session, GameSessionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void endSession(Long sessionId) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with ID: " + sessionId));

        session.setActive(false);
        gameSessionRepository.save(session);
    }
    
    
    
    
}
