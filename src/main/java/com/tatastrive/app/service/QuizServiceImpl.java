package com.tatastrive.app.service;

import com.tatastrive.app.dto.QuizDTO;
import com.tatastrive.app.entity.Quiz;
import com.tatastrive.app.entity.User;
import com.tatastrive.app.exception.ResourceNotFoundException;
import com.tatastrive.app.repository.QuizRepository;
import com.tatastrive.app.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);

        if (quizDTO.getCreatorId() != null) {
            User creator = userRepository.findById(quizDTO.getCreatorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + quizDTO.getCreatorId()));
            quiz.setCreatedBy(creator);
        }

        Quiz savedQuiz = quizRepository.save(quiz);
        return modelMapper.map(savedQuiz, QuizDTO.class);
    }

    @Override
    public QuizDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + id));
        return modelMapper.map(quiz, QuizDTO.class);
    }

    @Override
    public List<QuizDTO> getQuizzesByCreator(Long creatorId) {
        User user = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + creatorId));

        List<Quiz> quizzes = quizRepository.findByCreatedBy(user);

        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizDTO updateQuiz(Long id, QuizDTO quizDTO) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + id));

        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());

        Quiz updatedQuiz = quizRepository.save(quiz);
        return modelMapper.map(updatedQuiz, QuizDTO.class);
    }

    @Override
    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found with ID: " + id);
        }
        quizRepository.deleteById(id);
    }
}
