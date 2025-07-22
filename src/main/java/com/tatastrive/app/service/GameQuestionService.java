package com.tatastrive.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.*;
@Service
public interface GameQuestionService {
    GameQuestionDTO launchQuestion(Long sessionId, Long questionId);
    List<GameQuestionDTO> getQuestionsBySession(Long sessionId);
}