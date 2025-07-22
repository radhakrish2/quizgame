package com.tatastrive.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.*;
@Service
public interface GameSessionService {
    GameSessionDTO createGameSession(GameSessionDTO sessionDTO);
    GameSessionDTO startSession(String sessionCode);
    GameSessionDTO getSessionByCode(String sessionCode);
    List<GameSessionDTO> getSessionsByHost(Long hostId);
    void endSession(Long sessionId);
}