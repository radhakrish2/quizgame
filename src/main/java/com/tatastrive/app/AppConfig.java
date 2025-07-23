package com.tatastrive.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tatastrive.app.dto.QuestionDTO;
import com.tatastrive.app.dto.QuizDTO;
import com.tatastrive.app.entity.Option;
import com.tatastrive.app.entity.Question;
import com.tatastrive.app.entity.Quiz;

@Configuration
public class AppConfig {

	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ✅ Mapping from Quiz to QuizDTO
        modelMapper.typeMap(Quiz.class, QuizDTO.class).addMappings(mapper -> 
            mapper.map(src -> src.getCreatedBy().getId(), QuizDTO::setCreatorId)
        );

        // ✅ Mapping from QuestionDTO to Question with Option binding
        TypeMap<QuestionDTO, Question> questionTypeMap = modelMapper.createTypeMap(QuestionDTO.class, Question.class);

        questionTypeMap.addMappings(mapper -> {
            mapper.skip(Question::setOptions); // We'll set it manually
        }).setPostConverter(context -> {
            QuestionDTO source = context.getSource();
            Question destination = context.getDestination();

            if (source.getOptions() != null) {
                List<Option> options = source.getOptions().stream().map(optionDTO -> {
                    Option option = new Option();
                    option.setId(optionDTO.getId());
                    option.setText(optionDTO.getText());
                    option.setIndex(optionDTO.getIndex());
                    option.setQuestion(destination); // Important for bidirectional mapping
                    return option;
                }).toList();
                destination.setOptions(options);
            }

            return destination;
        });

        return modelMapper;
    }
	
	
}
