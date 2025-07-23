package com.tatastrive.app.service;

import com.tatastrive.app.dto.QuestionDTO;
import com.tatastrive.app.entity.Option;
import com.tatastrive.app.entity.Question;
import com.tatastrive.app.entity.Quiz;
import com.tatastrive.app.exception.ResourceNotFoundException;
import com.tatastrive.app.repository.QuestionRepository;
import com.tatastrive.app.repository.QuizRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionDTO addQuestionToQuiz(Long quizId, QuestionDTO questionDTO) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quizId));

        Question question = modelMapper.map(questionDTO, Question.class);
        question.setQuiz(quiz);

        // Important: set the `question` reference in each Option
        if (question.getOptions() != null) {
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }
        }

        Question savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }

    @Override
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId).stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with ID: " + id);
        }
        questionRepository.deleteById(id);
    }
}
