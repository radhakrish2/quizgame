package com.tatastrive.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionCode;
    private LocalDateTime startedAt;
    private boolean isActive;

    @ManyToOne
    private User host;

    @ManyToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL)
    private List<GameQuestion> gameQuestions;
}