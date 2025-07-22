package com.tatastrive.app.service;

import java.util.List;

import com.tatastrive.app.dto.PlayerDTO;

public interface PlayerService {

    // Join a session with the given code and nickname
    PlayerDTO joinSession(String sessionCode, String nickname);

    // Get a player by their ID
    PlayerDTO getPlayerById(Long id);

    // Get all players who joined a specific session
    List<PlayerDTO> getPlayersBySession(Long sessionId);
}
