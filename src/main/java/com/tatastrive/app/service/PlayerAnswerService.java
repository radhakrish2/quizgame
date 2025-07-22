package com.tatastrive.app.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.*;
@Service
public interface PlayerAnswerService {
    PlayerAnswerDTO submitAnswer(Long playerId, Long gameQuestionId, int selectedOptionIndex);
    List<PlayerAnswerDTO> getAnswersByPlayer(Long playerId);
    List<PlayerAnswerDTO> getAnswersByGameQuestion(Long gameQuestionId);
}