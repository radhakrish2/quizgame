package com.tatastrive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tatastrive.app.dto.PlayerAnswerDTO;
import com.tatastrive.app.service.PlayerAnswerService;

@RestController
@RequestMapping("/api/answers")
public class PlayerAnswerController {

    @Autowired
    private PlayerAnswerService playerAnswerService;

    // Submit an answer
    @PostMapping("/submit")
    public ResponseEntity<PlayerAnswerDTO> submitAnswer(
            @RequestParam Long playerId,
            @RequestParam Long gameQuestionId,
            @RequestParam int selectedOptionIndex) {
        PlayerAnswerDTO savedAnswer = playerAnswerService.submitAnswer(playerId, gameQuestionId, selectedOptionIndex);
        return ResponseEntity.ok(savedAnswer);
    }

    // Get answers by player
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerAnswerDTO>> getAnswersByPlayer(@PathVariable Long playerId) {
        List<PlayerAnswerDTO> answers = playerAnswerService.getAnswersByPlayer(playerId);
        return ResponseEntity.ok(answers);
    }

    // Get answers by game question
    @GetMapping("/question/{gameQuestionId}")
    public ResponseEntity<List<PlayerAnswerDTO>> getAnswersByGameQuestion(@PathVariable Long gameQuestionId) {
        List<PlayerAnswerDTO> answers = playerAnswerService.getAnswersByGameQuestion(gameQuestionId);
        return ResponseEntity.ok(answers);
    }
}
