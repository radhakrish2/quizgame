package com.tatastrive.app.controller;

import com.tatastrive.app.dto.GameQuestionDTO;
import com.tatastrive.app.dto.PlayerAnswerDTO;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class GameWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public GameWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Admin starts quiz
    @MessageMapping("/start/{sessionCode}")
    public void startGame(@DestinationVariable String sessionCode) {
        messagingTemplate.convertAndSend("/topic/session/" + sessionCode + "/start", "Game started");
    }

    // Send question to all players
    @MessageMapping("/question/{sessionCode}")
    public void sendQuestion(@DestinationVariable String sessionCode, GameQuestionDTO questionDTO) {
        messagingTemplate.convertAndSend("/topic/session/" + sessionCode + "/question", questionDTO);
    }

    // Player submits answer
    @MessageMapping("/answer/{sessionCode}")
    public void receiveAnswer(@DestinationVariable String sessionCode, PlayerAnswerDTO answerDTO) {
        // You can store answerDTO using PlayerAnswerService here
        messagingTemplate.convertAndSend("/topic/session/" + sessionCode + "/answers", answerDTO);
    }

    // Notify session ended
    @MessageMapping("/end/{sessionCode}")
    public void endSession(@DestinationVariable String sessionCode) {
        messagingTemplate.convertAndSend("/topic/session/" + sessionCode + "/end", "Session ended");
    }
}
