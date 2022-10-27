package obrien.Project.three.service;

import obrien.Project.three.dto.AnswerDTO;
import obrien.Project.three.dto.QuestionDTO;
import obrien.Project.three.entity.Question;

import java.util.Collection;
import java.util.Optional;

public interface QuestionService {
    void createQuestion(QuestionDTO questionDTO);
    Collection<Question> findAllQuestion();

    Optional<Question> findById(AnswerDTO answerDTO);
}
