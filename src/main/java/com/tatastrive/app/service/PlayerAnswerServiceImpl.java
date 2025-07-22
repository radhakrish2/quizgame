package com.tatastrive.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatastrive.app.dto.PlayerAnswerDTO;
import com.tatastrive.app.entity.GameQuestion;
import com.tatastrive.app.entity.Player;
import com.tatastrive.app.entity.PlayerAnswer;
import com.tatastrive.app.repository.GameQuestionRepository;
import com.tatastrive.app.repository.PlayerAnswerRepository;
import com.tatastrive.app.repository.PlayerRepository;

@Service
public class PlayerAnswerServiceImpl implements PlayerAnswerService {

    @Autowired
    private PlayerAnswerRepository playerAnswerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameQuestionRepository gameQuestionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerAnswerDTO submitAnswer(Long playerId, Long gameQuestionId, int selectedOptionIndex) {
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        Optional<GameQuestion> gameQuestionOpt = gameQuestionRepository.findById(gameQuestionId);

        if (!playerOpt.isPresent()) {
            throw new RuntimeException("Player not found with id: " + playerId);
        }

        if (!gameQuestionOpt.isPresent()) {
            throw new RuntimeException("Game question not found with id: " + gameQuestionId);
        }

        PlayerAnswer answer = new PlayerAnswer();
        answer.setPlayer(playerOpt.get());
        answer.setGameQuestion(gameQuestionOpt.get());
        answer.setSelectedOptionIndex(selectedOptionIndex);

        PlayerAnswer saved = playerAnswerRepository.save(answer);
        return modelMapper.map(saved, PlayerAnswerDTO.class);
    }

    @Override
    public List<PlayerAnswerDTO> getAnswersByPlayer(Long playerId) {
        List<PlayerAnswer> answers = playerAnswerRepository.findByPlayerId(playerId);
        return answers.stream()
                .map(answer -> modelMapper.map(answer, PlayerAnswerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerAnswerDTO> getAnswersByGameQuestion(Long gameQuestionId) {
        List<PlayerAnswer> answers = playerAnswerRepository.findByGameQuestionId(gameQuestionId);
        return answers.stream()
                .map(answer -> modelMapper.map(answer, PlayerAnswerDTO.class))
                .collect(Collectors.toList());
    }
}
