package com.tatastrive.app.entity;

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
public class Question {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private int timeLimitSeconds;

    @ManyToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "question" , cascade = CascadeType.ALL)
    private List<Option> options;

    private int correctOptionIndex;
}