package com.tatastrive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tatastrive.app.dto.PlayerDTO;
import com.tatastrive.app.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Join a game session
    @PostMapping("/join")
    public ResponseEntity<PlayerDTO> joinSession(
            @RequestParam String gameSessionCode,
            @RequestParam String nickname) {
        PlayerDTO playerDTO = playerService.joinSession(gameSessionCode, nickname);
        return ResponseEntity.ok(playerDTO);
    }

    // Get a player by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        PlayerDTO playerDTO = playerService.getPlayerById(id);
        return ResponseEntity.ok(playerDTO);
    }

    // Get all players in a session
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<PlayerDTO>> getPlayersBySession(@PathVariable Long sessionId) {
        List<PlayerDTO> players = playerService.getPlayersBySession(sessionId);
        return ResponseEntity.ok(players);
    }
}
