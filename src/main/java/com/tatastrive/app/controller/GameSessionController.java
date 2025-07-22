package com.tatastrive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tatastrive.app.dto.GameSessionDTO;
import com.tatastrive.app.service.GameSessionService;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    // Create a new game session
    @PostMapping
    public ResponseEntity<GameSessionDTO> createGameSession(@RequestBody GameSessionDTO sessionDTO) {
        GameSessionDTO createdSession = gameSessionService.createGameSession(sessionDTO);
        return ResponseEntity.ok(createdSession);
    }

    // Start a game session by session code
    @PutMapping("/start/{sessionCode}")
    public ResponseEntity<GameSessionDTO> startSession(@PathVariable String sessionCode) {
        GameSessionDTO session = gameSessionService.startSession(sessionCode);
        return ResponseEntity.ok(session);
    }

    // Get session by session code
    @GetMapping("/code/{sessionCode}")
    public ResponseEntity<GameSessionDTO> getSessionByCode(@PathVariable String sessionCode) {
        GameSessionDTO session = gameSessionService.getSessionByCode(sessionCode);
        return ResponseEntity.ok(session);
    }

    // Get all sessions hosted by a user
    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<GameSessionDTO>> getSessionsByHost(@PathVariable Long hostId) {
        List<GameSessionDTO> sessions = gameSessionService.getSessionsByHost(hostId);
        return ResponseEntity.ok(sessions);
    }

    // End a game session by session ID
    @PutMapping("/end/{sessionId}")
    public ResponseEntity<Void> endSession(@PathVariable Long sessionId) {
        gameSessionService.endSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}
