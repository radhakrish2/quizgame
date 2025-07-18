package com.tatastrive.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatastrive.app.entity.PlayerAnswer;

import java.util.List;

@Repository
public interface PlayerAnswerRepository extends JpaRepository<PlayerAnswer, Long> {
    List<PlayerAnswer> findByPlayerId(Long playerId);
    List<PlayerAnswer> findByGameQuestionId(Long gameQuestionId);
}
