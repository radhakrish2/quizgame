package com.tatastrive.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tatastrive.app.dto.PlayerDTO;
import com.tatastrive.app.entity.GameSession;
import com.tatastrive.app.entity.Player;

import com.tatastrive.app.repository.PlayerRepository;
import com.tatastrive.app.repository.GameSessionRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerDTO joinSession(String gameSessionCode, String nickname) {
        Optional<GameSession> GameSessionOpt = gameSessionRepository.findBySessionCode(gameSessionCode);
        if (!GameSessionOpt.isPresent()) {
            throw new RuntimeException("GameSession not found with code: " + gameSessionCode);
        }

        GameSession GameSession = GameSessionOpt.get();
        Player player = new Player();
        player.setNickname(nickname);
        player.setGameSession(GameSession);

        Player savedPlayer = playerRepository.save(player);

        return modelMapper.map(savedPlayer, PlayerDTO.class);
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        return modelMapper.map(player, PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getPlayersBySession(Long gameSessionId) {
        List<Player> players = playerRepository.findByGameSessionId(gameSessionId);
        return players.stream()
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .collect(Collectors.toList());
    }
}
